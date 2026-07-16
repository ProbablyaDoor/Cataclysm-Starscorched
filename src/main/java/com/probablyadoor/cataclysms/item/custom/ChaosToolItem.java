package com.probablyadoor.cataclysms.item.custom;

import com.probablyadoor.cataclysms.block.ModBlocks;
import com.probablyadoor.cataclysms.component.ModDataComponentTypes;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;


import java.util.List;
import java.util.function.Predicate;

public class ChaosToolItem extends Item {

    public ChaosToolItem(Settings settings) {super(settings);}

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_SHULKER_BULLET_HIT,
                SoundCategory.NEUTRAL,
                0.5F,
                0.8F / (world.getRandom().nextFloat() * 0.8F + 1.6F)

        );
        if (!world.isClient) {
            FireballEntity fireballEntity = new FireballEntity(world, user, user.getPos(), 5);
            fireballEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            fireballEntity.setPos(user.getX(), user.getY()+1, user.getZ());
            fireballEntity.setItem(new ItemStack(Items.ENDER_PEARL));
            world.spawnEntity(fireballEntity);
            int initialUses = itemStack.getOrDefault(ModDataComponentTypes.TOTAL_USES, 0);
            int currentUses = initialUses + 1;
            itemStack.set(ModDataComponentTypes.TOTAL_USES, currentUses);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.cataclysms.chaos_tool.shift_down"));
            tooltip.add(Text.translatable("tooltip.cataclysms.chaos_tool.shift_down2"));
            tooltip.add(Text.translatable("tooltip.cataclysms.chaos_tool.shift_down3"));
        } else {
            tooltip.add(Text.translatable("tooltip.cataclysms.chaos_tool"));
        }

        int uses = stack.getOrDefault(ModDataComponentTypes.TOTAL_USES, 0);
            tooltip.add(Text.literal("Projectiles Fired: " + uses));

        super.appendTooltip(stack, context, tooltip, options);
    }

}
