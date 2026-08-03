package com.probablyadoor.cataclysms.entity.custom;

import com.probablyadoor.cataclysms.effect.ModEffects;
import com.probablyadoor.cataclysms.entity.ModEntities;
import com.probablyadoor.cataclysms.item.ModItems;
import com.probablyadoor.cataclysms.particle.ModParticles;
import com.probablyadoor.cataclysms.sound.SoundRegistry;
import com.probablyadoor.cataclysms.util.WeaponHelpers;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.client.util.math.Vector2f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class FrostfallProjectileEntity extends PersistentProjectileEntity {
    private float rotation;
    private static final ParticleEmitterInfo ICEORB = new ParticleEmitterInfo(Identifier.of("cataclysms", "frostorb")).scale(0.45F, 0.45F, 0.45F);
    public Vector2f groundedOffset = new Vector2f(0.0f, 0.0f);
    public PlayerEntity owner;
    public int lifeTime = 40;
    public int radius = 5;
    public int kbstrength = 5;


    public FrostfallProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.pickupType = PickupPermission.DISALLOWED;
    }

    public FrostfallProjectileEntity(World world, PlayerEntity player) {
        super(ModEntities.FROSTFALL, player, world, new ItemStack(ModItems.FROSTFALL), null);
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
        if (this.isGrounded()) {
            if (owner != null && owner.distanceTo(this) > 1 && owner.distanceTo(this) < 500
                    && getWorld() instanceof ServerWorld serverWorld && age < 100) {
                owner.setVelocity((this.getX() - owner.getX()) / 8, (this.getY() - owner.getY()) / 8, (this.getZ() - owner.getZ()) / 8);
                owner.velocityModified = true;
                WeaponHelpers.tetherParticles(serverWorld, ParticleTypes.SOUL_FIRE_FLAME, this, owner, (int) this.distanceTo(owner));
            }
            if (owner != null && owner.distanceTo(this) > 1 && owner.distanceTo(this) < 3
                    && getWorld() instanceof ServerWorld serverWorld && age < 100) {
                this.discard();
                if (!world.isClient) {
                    AAALevel.addParticle(serverWorld, false, ICEORB.clone().position(this.getX(), this.getY()+0.1, this.getZ()));
                    world.playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.ITEM_TRIDENT_THUNDER,
                            SoundCategory.NEUTRAL,
                            1.5F,
                            1.5F / (world.getRandom().nextFloat() * 0.8F + 1.6F)
                    );

                    Box box = new Box(this.getX() + radius, this.getY() + radius, this.getZ() + radius,
                            this.getX() - radius, this.getY() - radius, this.getZ() - radius);
                    for (Entity entities : world.getOtherEntities(this, box, EntityPredicates.VALID_LIVING_ENTITY)) {
                        if ((entities instanceof LivingEntity livingEntity) && livingEntity != owner) {
                            if (livingEntity.distanceTo(this) > radius-2) {
                                    livingEntity.setVelocity((this.getX() - livingEntity.getX()) / 4, (this.getY() - livingEntity.getY()) / 4, (this.getZ() - livingEntity.getZ()) / 4);
                                livingEntity.setFrozenTicks(3000);
                            }
                        }
                    }
                }
            }
            lifeTime--;
            if(lifeTime == 0){
                this.discard();
            }

        }

        super.tick();

        if (this.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.CLOUD,
                    this.getX(), this.getY() + 0.5, this.getZ(), 1,
                    0, 0, 0, 0);
        }
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
                    SoundRegistry.ITEM_FROSTFALL_HIT,
                    SoundCategory.NEUTRAL,
                    1.5F,
                    0.8F / (world.getRandom().nextFloat() * 0.8F + 1.6F)
            );
            if (!world.isClient) {
                livingEntity.setFrozenTicks(3000);
            }
        }
        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte)3);
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult result) {
        super.onBlockHit(result);

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
