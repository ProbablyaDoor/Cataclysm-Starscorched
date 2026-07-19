package com.probablyadoor.cataclysms.particle.custom;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class IcedSnowflakeParticle extends SpriteBillboardParticle {
    public IcedSnowflakeParticle(ClientWorld clientWorld, double x, double y, double z,
                                 SpriteProvider spriteProvider, double xSpeed, double ySpeed, double zSpeed) {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed);


        this.velocityMultiplier = 0.8f;
        this.scale *= 0.75F;
        this.maxAge = (int)(Math.random() * 2.0) + 60;
        this.setSpriteForAge(spriteProvider);

    }
    @Override
    public float getSize(float tickDelta) {
        float f = 1.0F - (this.age + tickDelta) / (this.maxAge * 1.5F);
        return this.scale * f;
    }
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new IcedSnowflakeParticle(world, x, y, z, this.spriteProvider, velocityX, velocityY, velocityZ);
        }
    }

}
