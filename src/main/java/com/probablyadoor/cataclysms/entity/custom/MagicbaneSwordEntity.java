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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MagicbaneSwordEntity extends PersistentProjectileEntity {
    private float rotation;
    public Vector2f groundedOffset = new Vector2f(0.0f, 0.0f);
    public int lifeTime = 250;

    public MagicbaneSwordEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.pickupType = PickupPermission.DISALLOWED;
    }

    public MagicbaneSwordEntity(World world, PlayerEntity player) {
        super(ModEntities.MAGICBANE_SWORD, player, world, new ItemStack(ModItems.MAGICBANE), null);
        this.pickupType = PickupPermission.DISALLOWED;
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
        World world = this.getWorld();
        Entity owner = getOwner();
        lifeTime--;
        if(lifeTime == 0){
                this.discard();
            }

        super.tick();

        if (this.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.ENCHANT,
                    this.getX(), this.getY() + 1, this.getZ(), 1,
                    0, 0, 0, 0);
        }

        if (owner == null) {
            this.discard();
            return;
        }
        final Vec3d center = owner.getPos().add(0.0, owner.getHeight() * 0.33, 0.0);

        final float radius = 1f;
        final float velocity = 25f;
        final double angle = this.age * velocity;

        final double spinx = center.x + (Math.cos(angle) * radius);
        final double spiny = center.y + 1;
        final double spinz = center.z + (Math.sin(angle) * radius);
        this.setPosition(spinx, spiny, spinz);
        this.setVelocity(Vec3d.ZERO);
        this.velocityDirty = true;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 10);
        if (entity instanceof LivingEntity livingEntity) {
            World world = livingEntity.getWorld();
            world.playSound(
                    null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    SoundRegistry.ITEM_MAGICGBANE_ATTACK,
                    SoundCategory.NEUTRAL,
                    1.5F,
                    0.8F / (world.getRandom().nextFloat() * 0.8F + 1.6F)
            );
            if (!world.isClient) {
                this.discard();
            }
        }
        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte)3);
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        float currentyaw = this.getYaw();
        float currentpitch = this.getPitch();

        this.setYaw(currentyaw);
        this.setPitch(currentpitch);
        this.prevYaw = currentyaw;
        this.prevPitch = currentpitch;
    }

    @Override
    public boolean isAttackable() {
        return false;
    }


    @Override
    public boolean isCollidable() {
        return false;
    }


    @Override
    public boolean canHit() {
        return false;
    }

    @Override
    public boolean canBeHitByProjectile() {
        return false;
    }

    @Override
    public void onPlayerCollision(net.minecraft.entity.player.PlayerEntity player) {
    }

    @Override
    public boolean isPushable() {
        return false;
    }

}
