package com.probablyadoor.cataclysms.entity.custom;

import com.probablyadoor.cataclysms.entity.ModEntities;
import com.probablyadoor.cataclysms.item.ModItems;
import com.probablyadoor.cataclysms.sound.SoundRegistry;
import net.minecraft.client.util.math.Vector2f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class FrostfallProjectileEntity extends PersistentProjectileEntity {
    private float rotation;
    public Vector2f groundedOffset;
    public PlayerEntity owner;

    public FrostfallProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public FrostfallProjectileEntity(World world, PlayerEntity player) {
        super(ModEntities.FROSTFALL, player, world, new ItemStack(ModItems.FROSTFALL), null);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(Items.AIR);
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if (rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    public boolean isGrounded() {return inGround;}

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.CLOUD,
                    this.getX(), this.getY() + 0.5, this.getZ(), 1,
                    0, 0, 0, 0);
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 10);
        if (entity instanceof LivingEntity livingEntity) {
            World world = livingEntity.getWorld();
            if (!world.isClient) {
                IceCrystalEntity iceCrystalEntity = new IceCrystalEntity(ModEntities.ICE_CRYSTAL, world);
                iceCrystalEntity.setPosition(this.getPos());
                world.spawnEntity(iceCrystalEntity);
                iceCrystalEntity.owner = this.owner;

            }
            world.playSound(
                    null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    SoundRegistry.ITEM_FROSTFALL_HIT,
                    SoundCategory.NEUTRAL,
                    1.5F,
                    0.8F / (world.getRandom().nextFloat() * 0.8F + 1.6F)
            );
        }
        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult result) {
        super.onBlockHit(result);
        this.discard();
        World world = this.getWorld();
        world.playSound(
                null,
                this.getX(),
                this.getY(),
                this.getZ(),
                SoundRegistry.ITEM_FROSTFALL_HIT,
                SoundCategory.NEUTRAL,
                1.5F,
                0.8F / (world.getRandom().nextFloat() * 0.8F + 1.6F)
        );
        if (!world.isClient) {
            IceCrystalEntity iceCrystalEntity = new IceCrystalEntity(ModEntities.ICE_CRYSTAL, world);
            iceCrystalEntity.setPosition(this.getPos());
            world.spawnEntity(iceCrystalEntity);
            iceCrystalEntity.owner = this.owner;

        }

            if(result.getSide() == Direction.SOUTH) {
            groundedOffset = new Vector2f(215f, 180f);
        }
        if(result.getSide() == Direction.NORTH) {
            groundedOffset = new Vector2f(215f, 0f);

        }
        if(result.getSide() == Direction.EAST) {
            groundedOffset = new Vector2f(215f, -90f);
        }
        if(result.getSide() == Direction.WEST) {
            groundedOffset = new Vector2f(215f, 90f);
        }
        if(result.getSide() == Direction.DOWN) {
            groundedOffset = new Vector2f(115, 180f);
        }
        if(result.getSide() == Direction.UP) {
            groundedOffset = new Vector2f(285, 180f);
        }
    }
}
