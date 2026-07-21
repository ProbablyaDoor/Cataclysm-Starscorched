package com.probablyadoor.cataclysms.effect;

import com.probablyadoor.cataclysms.particle.ModParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class IcedEffect extends StatusEffect {
    protected IcedEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity.isAlive()) {
            entity.setFrozenTicks(160);
            double x = entity.getX();
            double y = entity.getY();
            double z = entity.getZ();
            entity.teleport(x, y, z, false);
            entity.setVelocity(0, 0, 0);
            World world = entity.getWorld();
            if (entity.getWorld() instanceof ServerWorld serverWorld) {
                float entityHeight = entity.getHeight();
                float entityWidth = entity.getWidth();

                serverWorld.spawnParticles(ModParticles.ICED_SNOWFLAKE_PARTICLE,
                        entity.getX(), entity.getY(), entity.getZ(), 2,
                        entityWidth*0.45, entityHeight*0.45, entityWidth*0.45, 0);
                serverWorld.spawnParticles(ParticleTypes.SNOWFLAKE,
                        entity.getX(), entity.getY(), entity.getZ(), 10,
                        entityWidth*0.25, 0, entityWidth*0.25, 0);

            }
            return true;
        }

        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
