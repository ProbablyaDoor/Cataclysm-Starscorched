package com.probablyadoor.cataclysms.item.custom;

import com.probablyadoor.cataclysms.entity.custom.IceCrystalEntity;
import com.probablyadoor.cataclysms.sound.SoundRegistry;
import com.probablyadoor.cataclysms.util.FriendlyFireCheck;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DaybreakerItem extends SwordItem {
    public DaybreakerItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }
    private static final ParticleEmitterInfo SUNSLASH = new ParticleEmitterInfo(Identifier.of("cataclysms", "sunslash")).scale(0.35F, 0.35F, 0.35F);
    private static final ParticleEmitterInfo SUNLEAPSTART = new ParticleEmitterInfo(Identifier.of("cataclysms", "sunleapstart")).scale(0.45F, 0.45F, 0.45F);

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
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        int radius = 5;
        int kbstrength = 5;
        Vec3d rotation = user.getRotationVector().multiply(1f);
        user.addVelocity(rotation.getX(), 1.5, rotation.getZ());
        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            AAALevel.addParticle(serverWorld, false, SUNLEAPSTART.clone().position(user.getX(), user.getY()+0.1, user.getZ()));
            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    SoundEvents.ENTITY_BLAZE_SHOOT,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    1.0F / (world.getRandom().nextFloat() * 0.8F + 1.6F));
            Box box = new Box(user.getX() + radius, user.getY() + radius, user.getZ() + radius,
                    user.getX() - radius, user.getY() - radius, user.getZ() - radius);
            for (Entity entities : world.getOtherEntities(user, box, EntityPredicates.VALID_LIVING_ENTITY)) {
                if ((entities instanceof LivingEntity livingEntity) && livingEntity != user) {
                    if (livingEntity.getPos().add(0.0, livingEntity.getHeight() * 0.5, 0.0).squaredDistanceTo(user.getPos()) < radius * radius) {
                        livingEntity.setVelocity((livingEntity.getX()-user.getX())/kbstrength, 0.5, (livingEntity.getZ()-user.getZ())/kbstrength);
                        livingEntity.setFireTicks(100);
                    }
                }
            }
            user.getItemCooldownManager().set(this, 75);
        }
        return TypedActionResult.success(itemStack, world.isClient());

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

