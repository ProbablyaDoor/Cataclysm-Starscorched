package com.probablyadoor.cataclysms.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.UseAction;

public class NightveilItem extends CrossbowItem {
    public NightveilItem(Settings settings) {
        super(settings);
    }
    public float getDrawSpeed() {
        return 20.0F;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }
    public static boolean hasProjectile(ItemStack itemStack, Item fireworkRocket) {
        ChargedProjectilesComponent projectiles = itemStack.get(DataComponentTypes.CHARGED_PROJECTILES);
        if (projectiles != null && projectiles.contains(Items.FIREWORK_ROCKET)) {
            return true;
        }
        return false;
    }
}
