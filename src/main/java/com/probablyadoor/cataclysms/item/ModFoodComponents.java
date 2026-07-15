package com.probablyadoor.cataclysms.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent MANGOSTEEN = new FoodComponent.Builder().nutrition(5).saturationModifier(0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 100, 1), 0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, 0), 0.3f).build();

}
