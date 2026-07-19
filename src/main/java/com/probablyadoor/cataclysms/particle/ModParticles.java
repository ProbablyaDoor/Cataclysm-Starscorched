package com.probablyadoor.cataclysms.particle;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import com.probablyadoor.cataclysms.particle.custom.IcedSnowflakeParticle;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.SonicBoomParticle;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final SimpleParticleType MAGIC_SWEEP_PARTICLE = registerParticle("magic_sweep_particle", FabricParticleTypes.simple());
    public static final SimpleParticleType ICED_SNOWFLAKE_PARTICLE = registerParticle("iced_snowflake_particle", FabricParticleTypes.simple());


    private static SimpleParticleType registerParticle(String name, SimpleParticleType particle) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CataclysmStarscorched.MOD_ID, name), particle);
    }

    public static void registerModParticles() {
        CataclysmStarscorched.LOGGER.info("Registering Mod Particles for " + CataclysmStarscorched.MOD_ID);
    }

    public static void registerParticleFactories() {
        ParticleFactoryRegistry registry = ParticleFactoryRegistry.getInstance();

        registry.register(MAGIC_SWEEP_PARTICLE, SonicBoomParticle.Factory::new);
        registry.register(ICED_SNOWFLAKE_PARTICLE, IcedSnowflakeParticle.Factory::new);
    }
}