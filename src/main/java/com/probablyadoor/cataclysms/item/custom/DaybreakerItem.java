package com.probablyadoor.cataclysms.item.custom;

import com.probablyadoor.cataclysms.component.ModDataComponentTypes;
import com.probablyadoor.cataclysms.effect.ModEffects;
import com.probablyadoor.cataclysms.item.ModItems;
import com.probablyadoor.cataclysms.sound.SoundRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LodestoneTrackerComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class DaybreakerItem extends SwordItem {
    public DaybreakerItem(ToolMaterial material, Settings settings) {
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
                    SoundRegistry.ITEM_DAYBREAKER_ATTACK,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    1.0F / (world.getRandom().nextFloat() * 0.8F + 1.6F));
            world.createExplosion(attacker, target.getX(), target.getY(), target.getZ(), 2, World.ExplosionSourceType.MOB);


        }
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!world.isClient) {
            if (entity instanceof LivingEntity user) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 220, 0, false, false));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 220, 0, false, false));
            }
        }
    }
}

