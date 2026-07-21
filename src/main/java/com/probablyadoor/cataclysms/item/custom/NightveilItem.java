package com.probablyadoor.cataclysms.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class NightveilItem extends CrossbowItem {
    public NightveilItem(Settings settings) {
        super(settings);
    }
    public float getDrawSpeed() {
        return 20.0F;
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!world.isClient) {
            if (entity instanceof LivingEntity user) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 220, 0, false, false));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 220, 0, false, false));

            }
        }
    }
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }


    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }
    public static boolean hasProjectile(ItemStack itemStack, Item fireworkRocket) {
        ChargedProjectilesComponent projectiles = itemStack.get(DataComponentTypes.CHARGED_PROJECTILES);
        return projectiles != null && projectiles.contains(Items.FIREWORK_ROCKET);
    }

}
