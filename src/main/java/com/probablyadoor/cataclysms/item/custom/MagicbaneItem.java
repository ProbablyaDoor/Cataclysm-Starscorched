package com.probablyadoor.cataclysms.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class MagicbaneItem extends SwordItem {
    public MagicbaneItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = target.getWorld();
        if (!world.isClient) {
            world.playSound(
                    null,
                    attacker.getX(),
                    attacker.getY(),
                    attacker.getZ(),
                    SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    1.2F / (world.getRandom().nextFloat() * 0.8F + 1.6F));
        }
        return true;
    }
}
