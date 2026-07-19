package com.probablyadoor.cataclysms.effect;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> ICED = registerStatusEffect("iced",
    new IcedEffect(StatusEffectCategory.NEUTRAL, 0)
    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
            Identifier.of(CataclysmStarscorched.MOD_ID, "iced"), -1.0f,
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(EntityAttributes.GENERIC_JUMP_STRENGTH,
                    Identifier.of(CataclysmStarscorched.MOD_ID, "iced"), -1.0f,
                    EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE,
                    Identifier.of(CataclysmStarscorched.MOD_ID, "iced"), -1.0f,
                    EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                    Identifier.of(CataclysmStarscorched.MOD_ID, "iced"), -1.0f,
                    EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(CataclysmStarscorched.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        CataclysmStarscorched.LOGGER.info("Registering Mod Effects for " + CataclysmStarscorched.MOD_ID);
    }
}
