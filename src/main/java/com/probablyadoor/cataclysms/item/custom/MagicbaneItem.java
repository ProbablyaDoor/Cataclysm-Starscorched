package com.probablyadoor.cataclysms.item.custom;

import com.probablyadoor.cataclysms.component.ModDataComponentTypes;
import com.probablyadoor.cataclysms.sound.SoundRegistry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;

import java.util.List;

public class MagicbaneItem extends SwordItem {
    public MagicbaneItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!world.isClient) {
            if (entity instanceof LivingEntity user) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 220, 1, false, false));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 220, 0, false, false));


            }
        }
    }

    @Override
    public boolean postHit(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        World world = target.getWorld();

        if (!world.isClient) {
            if (world instanceof ServerWorld serverWorld) {
                world.playSound(
                        null,
                        attacker.getX(),
                        attacker.getY(),
                        attacker.getZ(),
                        SoundRegistry.ITEM_MAGICGBANE_ATTACK,
                        SoundCategory.NEUTRAL,
                        0.5F,
                        1.0F / (world.getRandom().nextFloat() * 0.8F + 1.6F));
            }

            if (target.getHealth() <= 0.0F) {
                int soulCount = itemStack.getOrDefault(ModDataComponentTypes.SOUL_COUNT, 0);
                itemStack.set(ModDataComponentTypes.SOUL_COUNT, soulCount + 1);
            }
        }
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        int soulCount = itemStack.getOrDefault(ModDataComponentTypes.SOUL_COUNT, 0);
        if (soulCount <= 0) {
            return TypedActionResult.fail(itemStack);
        }
        if (!world.isClient && world instanceof ServerWorld serverWorld && soulCount > 0) {
            user.getItemCooldownManager().set(this, 25);
            itemStack.set(ModDataComponentTypes.SOUL_COUNT, soulCount - 1);
            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    SoundEvents.ENTITY_WITHER_SHOOT,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    1.0F / (world.getRandom().nextFloat() * 0.8F + 1.6F));
            WitherSkullEntity witherSkullEntity = new WitherSkullEntity(world, user, user.getPos());
            witherSkullEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            witherSkullEntity.setPos(user.getX(), user.getY()+1, user.getZ());
            world.spawnEntity(witherSkullEntity);
        }
        return TypedActionResult.success(itemStack, world.isClient());

    }

    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        if (damageSource.getAttacker() instanceof LivingEntity attacker) {
            ItemStack itemStack = attacker.getMainHandStack();
            var itemEnchants = itemStack.get(DataComponentTypes.ENCHANTMENTS);
            if (itemEnchants != null) {
                return itemEnchants.getSize() * 1.0F;
            }
        }
        return 0.0F;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.cataclysms.magicbane.shift_down"));
        } else {
            tooltip.add(Text.translatable("tooltip.cataclysms.magicbane"));
        }

        var itemEnchants = itemStack.get(DataComponentTypes.ENCHANTMENTS);
        int enchantCount = (itemEnchants != null) ? itemEnchants.getSize() : 0;

        if (enchantCount > 0) {
            float bonusDamage = enchantCount * 1.0F;

            Text damageLine = Text.literal("+" + bonusDamage + " Enchantment Damage")
                    .formatted(Formatting.DARK_PURPLE);
            tooltip.add(damageLine);
        }

        int souls = itemStack.getOrDefault(ModDataComponentTypes.SOUL_COUNT, 0);
        if (souls > 0) {
            Text soulCounter = Text.literal("SOULS COLLECTED: " + souls)
                    .formatted(Formatting.DARK_RED);
            tooltip.add(soulCounter);
        }

        super.appendTooltip(itemStack, context, tooltip, options);
    }
}
