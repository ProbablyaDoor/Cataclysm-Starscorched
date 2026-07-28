package com.probablyadoor.cataclysms.block.custom;

import com.mojang.serialization.MapCodec;
import com.probablyadoor.cataclysms.block.entity.custom.PedestalBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PedestalBlock extends BlockWithEntity implements BlockEntityProvider {
    // Defines the physical shape of the block
    private static final VoxelShape SHAPE
            = PedestalBlock.createCuboidShape(2, 0, 2, 14, 13, 14);

    public static final MapCodec<PedestalBlock> CODEC = PedestalBlock.createCodec(PedestalBlock::new);

    public PedestalBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PedestalBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    // Runs when the pedestal is replaced by another block, or when it's broken
    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        // Checks if the pedestal block actually changed/broke, or just changed state
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            // Safety check if block entity matches with pedestal entity
            if (blockEntity instanceof PedestalBlockEntity) {
                // Spawns item drops into the world
                ItemScatterer.spawn(world, pos, ((PedestalBlockEntity) blockEntity));
                world.updateComparators(pos, this); // Updates redstone comparators nearby
            }

        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    // Runs when a player right-clicks an item on the pedestal
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        // Safety check if block entity matches with pedestal entity
        if (blockEntity instanceof PedestalBlockEntity pedestalBlockEntity) {
            boolean hasItemInHand = !stack.isEmpty();
            boolean pedestalIsEmpty = pedestalBlockEntity.isEmpty();

            // Places item on pedestal
            if (pedestalIsEmpty && hasItemInHand) {
                pedestalBlockEntity.setStack(0, stack.copyWithCount(1));
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 2f);
                stack.decrement(1);

                // Save changes, update clients
                pedestalBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);

            // Take back item on pedestal
            } else if (!hasItemInHand && !player.isSneaking()) {
                ItemStack stackOnPedestal = pedestalBlockEntity.getStack(0);
                player.setStackInHand(Hand.MAIN_HAND, stackOnPedestal);
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 2f);
                pedestalBlockEntity.clear(); // Removes ALL items stored in the pedestal

                // Save changes, update clients
                pedestalBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);
            }
        }

        return ItemActionResult.SUCCESS;
    }
}
