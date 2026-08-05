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
import net.minecraft.util.UseAction;
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
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        int soulCount = itemStack.getOrDefault(ModDataComponentTypes.SOUL_COUNT, 0);
        if (soulCount <= 0) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(user.getStackInHand(hand));
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int ticksUsed) {
        if (user instanceof PlayerEntity playerEntity) {
            int chargeTicks = this.getMaxUseTime(stack, user) - ticksUsed;
            int soulCount = stack.getOrDefault(ModDataComponentTypes.SOUL_COUNT, 0);

            if (chargeTicks >= 70 && soulCount >= 10) {
                if (!world.isClient && world instanceof ServerWorld serverWorld) {
                    int i;
                    world.playSound(
                            null,
                            user.getX(),
                            user.getY(),
                            user.getZ(),
                            SoundEvents.ENTITY_WITHER_SPAWN,
                            SoundCategory.NEUTRAL,
                            1F,
                            1.0F / (world.getRandom().nextFloat() * 0.8F + 1.6F));
                }

            } else if (chargeTicks >= 30 && soulCount >= 5) {
                if (!world.isClient && world instanceof ServerWorld serverWorld) {
                    int i;
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
                        witherSkullEntity.setOwner(user);
                        witherSkullEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 9F, 1.0F);
                        witherSkullEntity.setPos(user.getX(), user.getY() + 1, user.getZ());
                        world.spawnEntity(witherSkullEntity);
                        witherSkullEntity.setCharged(true);
                    for (i = 0; i < user.getRandom().nextInt(3) + 3; i++) {
                        WitherSkullEntity witherSkullEntity2 = new WitherSkullEntity(world, user, user.getPos());
                        witherSkullEntity2.setOwner(user);
                        witherSkullEntity2.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 6F, 2.0F);
                        witherSkullEntity2.setPos(user.getX(), user.getY() + 1, user.getZ());
                        world.spawnEntity(witherSkullEntity2);
                    }
                        playerEntity.getItemCooldownManager().set(this, 100);
                        playerEntity.getMainHandStack().set(ModDataComponentTypes.SOUL_COUNT, soulCount - 2);
                }
            } else if (chargeTicks >= 10 && soulCount >= 1) {
                if (!world.isClient && world instanceof ServerWorld serverWorld) {
                    world.playSound(
                            null,
                            user.getX(),
                            user.getY(),
                            user.getZ(),
                            SoundEvents.ENTITY_WITHER_SHOOT,
                            SoundCategory.NEUTRAL,
                            0.5F,
                            1.5F / (world.getRandom().nextFloat() * 0.8F + 1.6F));
                    WitherSkullEntity witherSkullEntity = new WitherSkullEntity(world, user, user.getPos());
                    witherSkullEntity.setOwner(user);
                    witherSkullEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 3F, 1.0F);
                    witherSkullEntity.setPos(user.getX(), user.getY() + 1, user.getZ());
                    world.spawnEntity(witherSkullEntity);
                    playerEntity.getItemCooldownManager().set(this, 50);
                    playerEntity.getMainHandStack().set(ModDataComponentTypes.SOUL_COUNT, soulCount - 1);

                }
            }
        }
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
