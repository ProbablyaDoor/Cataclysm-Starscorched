package com.probablyadoor.cataclysms.block.custom;

import com.probablyadoor.cataclysms.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class StarSlateBlock extends Block {
    public StarSlateBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity) {
            entity.damage(world.getDamageSources().magic(), 2.0F);
        }

        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);

        if (!world.isClient()) {
            if (!player.getMainHandStack().isIn(ModTags.Items.VALID_PICKAXES))
                player.damage(world.getDamageSources().magic(), 5.0F);
        }
        return state;


    }
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("tooltip.cataclysms.starscorched_slate.tooltip"));
        super.appendTooltip(stack, context, tooltip, options);
    }
}

