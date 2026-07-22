package com.probablyadoor.cataclysms.component;

import com.mojang.serialization.Codec;
import com.probablyadoor.cataclysms.CataclysmStarscorched;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<Integer> TOTAL_USES =
            register("total_uses", builder -> builder.codec(Codec.INT).cache());
    public static final ComponentType<Integer> CURRENT_ENCHANTMENTS =
            register("current_enchantments", builder -> builder.codec(Codec.INT).cache());

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(CataclysmStarscorched.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        CataclysmStarscorched.LOGGER.info("Registering Data Component Types for " + CataclysmStarscorched.MOD_ID);
    }
}

