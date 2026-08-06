package com.probablyadoor.cataclysms.mixin;

import com.probablyadoor.cataclysms.component.ModDataComponentTypes;
import com.probablyadoor.cataclysms.effect.ModEffects;
import com.probablyadoor.cataclysms.entity.custom.MagicbaneSwordEntity;
import com.probablyadoor.cataclysms.item.ModItems;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class AttackMixin {
    @Inject(method = "attack(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"))
    private void onEntityAttack(Entity target, CallbackInfo callbackInfo) {
        PlayerEntity user = (PlayerEntity) (Object) this;
        this.customSlashParticle(user);
        if (target instanceof LivingEntity livingEntity) {
            frostfallCrit(livingEntity);
        }
    }

    @Unique
    private void customSlashParticle(PlayerEntity user) {
        final ParticleEmitterInfo MAGICSLASH = new ParticleEmitterInfo(Identifier.of("cataclysms", "magicslash")).scale(0.25F, 0.25F, 0.25F);
        final ParticleEmitterInfo FROSTRING = new ParticleEmitterInfo(Identifier.of("cataclysms", "iceslam")).scale(0.25F, 0.25F, 0.25F);
        final ParticleEmitterInfo SUNSLASH = new ParticleEmitterInfo(Identifier.of("cataclysms", "sunslash")).scale(0.25F, 0.25F, 0.25F);

        World world = user.getWorld();
        float yawRadians = (float) Math.toRadians(user.getYaw());
        float attackCooldown = user.getAttackCooldownProgress(0.5f);

        if (attackCooldown >= 1.0f && world instanceof ServerWorld serverWorld) {
            if (user.isHolding(ModItems.FROSTFALL)) {
                AAALevel.addParticle(serverWorld, false, FROSTRING.clone().position(user.getX(), user.getY()+0.5, user.getZ()).rotation(0.0F, yawRadians*-1, 0.0F));
            }
            if (user.isHolding(ModItems.MAGICBANE)) {
                AAALevel.addParticle(serverWorld, false, MAGICSLASH.clone().position(user.getX(), user.getY()+0.65, user.getZ()).rotation(0.0F, yawRadians*-1, 0.0F));
            }
            if (user.isHolding(ModItems.DAYBREAKER)) {
                AAALevel.addParticle(serverWorld, false, SUNSLASH.clone().position(user.getX(), user.getY()+1, user.getZ()).rotation(0.0F, yawRadians*-1, 0.0F));
            }

        }
    }

    @Unique
    private void frostfallCrit(LivingEntity target) {
        final ParticleEmitterInfo FROSTRING = new ParticleEmitterInfo(Identifier.of("cataclysms", "frostcube")).scale(target.getWidth()*0.55F, target.getHeight()*0.3F, target.getWidth()*0.55F);
        PlayerEntity player = (PlayerEntity) (Object) this;
        World world = target.getWorld();
        float cooldown = player.getAttackCooldownProgress(0.5F);
        boolean charged = cooldown > 0.9F;
        int proc_chance = 10;
        if (isCritical(player, charged)) {
            if (player.isHolding(ModItems.FROSTFALL)) {
                if (player.getRandom().nextInt(100) <= proc_chance) {
                    target.addStatusEffect(new StatusEffectInstance(ModEffects.ICED, 240, 0, false, false));
                    if (!world.isClient) {
                        AAALevel.addParticle(world, false, FROSTRING.clone().position(target.getX(), target.getY(), target.getZ()));
                    }
                }
            }
            if (player.isHolding(ModItems.MAGICBANE)) {
                if (player.getRandom().nextInt(30) <= proc_chance) {
                    if (!world.isClient) {
                        MagicbaneSwordEntity magicbaneSwordEntity = new MagicbaneSwordEntity(world, player);
                        magicbaneSwordEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0f, 1.5f, 0f);
                        world.spawnEntity(magicbaneSwordEntity);
                        magicbaneSwordEntity.setOwner(player);
                    }
                }
            }
            if (player.isHolding(ModItems.DAYBREAKER)) {
                int daybreaker_charge = player.getMainHandStack().getOrDefault(ModDataComponentTypes.DAYBREAKER_CHARGE, 0);
                if (daybreaker_charge <= 10) {
                    player.getMainHandStack().set(ModDataComponentTypes.DAYBREAKER_CHARGE, daybreaker_charge + 1);
                    if (!world.isClient) {
                        world.playSound(
                                null,
                                player.getX(),
                                player.getY(),
                                player.getZ(),
                                SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,
                                SoundCategory.NEUTRAL,
                                1F,
                                1.5F / (world.getRandom().nextFloat() * 0.8F + 1.6F));
                    }
                }
                if (daybreaker_charge == 10) {
                    if (!world.isClient) {
                        world.playSound(
                                null,
                                player.getX(),
                                player.getY(),
                                player.getZ(),
                                SoundEvents.ENTITY_WITHER_HURT,
                                SoundCategory.NEUTRAL,
                                5F,
                                2F / (world.getRandom().nextFloat() * 0.8F + 1.6F));
                    }
                }
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
