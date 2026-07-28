package com.probablyadoor.cataclysms.block.entity.custom;

import com.probablyadoor.cataclysms.block.entity.ImplementedInventory;
import com.probablyadoor.cataclysms.block.entity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class PedestalBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PEDESTAL_BE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return null;
    }
}
