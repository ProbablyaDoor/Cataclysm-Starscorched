package com.probablyadoor.cataclysms.entity.custom;

import com.probablyadoor.cataclysms.effect.ModEffects;
import com.probablyadoor.cataclysms.entity.ModEntities;
import com.probablyadoor.cataclysms.particle.ModParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class IceCrystalEntity extends PathAwareEntity {

    private int lifeTime = 100;

    public IceCrystalEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
    World world = this.getWorld();
    @Override
    public void tick() {
        super.tick();

        //Lifetime
        lifeTime--;
        if(lifeTime == 0){
            world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 2, World.ExplosionSourceType.MOB);
            this.discard();

        }

        //Particles
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ModParticles.ICED_SNOWFLAKE_PARTICLE,
                    this.getX(), this.getY() + 0.5, this.getZ(), 1,
                    1.52, 1.52, 1.52, 0);
            serverWorld.spawnParticles(ParticleTypes.SNOWFLAKE,
                    this.getX(), this.getY(), this.getZ(), 45,
                    3, 0, 3, 0);
        }

        //AOE
        int radius = 6;

        if (!this.getWorld().isClient()) {
            if (this.age % 10 == 0) {
                Box box = new Box(this.getX() + radius, this.getY() + (float) radius / 3, this.getZ() + radius,
                        this.getX() - radius, this.getY() - (float) radius / 3, this.getZ() - radius);
                for (Entity entities : this.getWorld().getOtherEntities(this, box, EntityPredicates.VALID_LIVING_ENTITY)) {
                    if ((entities instanceof LivingEntity livingEntity) && livingEntity != this && !(livingEntity instanceof IceCrystalEntity)) {
                                livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.ICED, 20, 0), this);
                             }
                        }
                    }
                }
        }


    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.0f)
                .add(EntityAttributes.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, 1.0f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0f)
                .add(EntityAttributes.GENERIC_GRAVITY, 0.0f);
    }
    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return true;
    }
    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean isPushable() { return false; }

    @Override
    protected void pushAway(Entity entity) {
    }
}
