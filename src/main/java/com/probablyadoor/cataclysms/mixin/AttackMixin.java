package com.probablyadoor.cataclysms.mixin;

import com.probablyadoor.cataclysms.effect.ModEffects;
import com.probablyadoor.cataclysms.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class AttackMixin {
    @Inject(method = "attack(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"))
    private void onEntityAttack(Entity target, CallbackInfo callbackInfo) {
        if (target instanceof LivingEntity livingEntity) {
            frostfallCrit(livingEntity);
        }
    }

    @Unique
    private void frostfallCrit(LivingEntity target) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        float cooldown = player.getAttackCooldownProgress(0.5F);
        boolean charged = cooldown > 0.9F;
        if (isCritical(player, charged)) {
            if (player.isHolding(ModItems.FROSTFALL)) {
                target.addStatusEffect(new StatusEffectInstance(ModEffects.ICED, 150, 0, false, false));
            }

        }
    }

    @Unique
    private boolean isCritical(PlayerEntity player, boolean charged) {
        if (!charged) return false;

        double velocityY = player.getVelocity().y;
        boolean isFalling = velocityY < -0.1;

        if (!isFalling) return false;
        if (player.isOnGround()) return false;
        if (player.isClimbing()) return false;
        if (player.isTouchingWater()) return false;
        if (player.hasStatusEffect(StatusEffects.BLINDNESS)) return false;
        if (player.hasStatusEffect(StatusEffects.SLOW_FALLING)) return false;
        if (player.hasVehicle()) return false;
        if (player.isSprinting()) return false;

        return true;
    }

}
