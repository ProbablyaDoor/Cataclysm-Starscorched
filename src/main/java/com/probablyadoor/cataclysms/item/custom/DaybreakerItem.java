package com.probablyadoor.cataclysms.item.custom;

import com.probablyadoor.cataclysms.sound.SoundRegistry;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class DaybreakerItem extends SwordItem {
    public DaybreakerItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }
    private static final ParticleEmitterInfo SUNSLASH = new ParticleEmitterInfo(Identifier.of("cataclysms", "sunslash")).scale(0.35F, 0.35F, 0.35F);

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = target.getWorld();

        // The particle effect maker can only detect yaw and pitch in radians for some reason
        float yawRadians = (float) Math.toRadians(attacker.getYaw());
        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            AAALevel.addParticle(serverWorld, false, SUNSLASH.clone().position(target.getX(), target.getY(), target.getZ()).rotation(0.0F, yawRadians*-1, 0.0F));
            world.playSound(
                    null,
                    attacker.getX(),
                    attacker.getY(),
                    attacker.getZ(),
                    SoundRegistry.ITEM_DAYBREAKER_ATTACK,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    1.0F / (world.getRandom().nextFloat() * 0.8F + 1.6F));
            target.setFireTicks(500);


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

