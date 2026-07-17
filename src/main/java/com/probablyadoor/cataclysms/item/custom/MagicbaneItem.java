package com.probablyadoor.cataclysms.item.custom;

import com.probablyadoor.cataclysms.component.ModDataComponentTypes;
import com.probablyadoor.cataclysms.item.ModItems;
import com.probablyadoor.cataclysms.sound.SoundRegistry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

import java.util.List;

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
                    SoundRegistry.ITEM_MAGICGBANE_ATTACK,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    1.0F / (world.getRandom().nextFloat() * 0.8F + 1.6F));
        }
        return true;
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
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.cataclysms.magicbane.shift_down"));
        } else {
            tooltip.add(Text.translatable("tooltip.cataclysms.magicbane"));
        }

        // 1. Check for the live enchantments component directly on the item stack
        var itemEnchants = stack.get(DataComponentTypes.ENCHANTMENTS);
        int enchantCount = (itemEnchants != null) ? itemEnchants.getSize() : 0;

        // 2. Display the bonus calculation directly on the tooltip if it has enchantments
        if (enchantCount > 0) {
            float bonusDamage = enchantCount * 1.0F;

            // Format it to match Minecraft's clean item tooltip style (e.g. Blue text)
            Text damageLine = Text.literal("+" + bonusDamage + " Enchantment Bonus Damage")
                    .formatted(Formatting.BLUE);

            tooltip.add(damageLine);
        }

        super.appendTooltip(stack, context, tooltip, options);
    }
}
