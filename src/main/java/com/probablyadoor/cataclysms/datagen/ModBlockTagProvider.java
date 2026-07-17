package com.probablyadoor.cataclysms.datagen;

import com.probablyadoor.cataclysms.block.ModBlocks;
import com.probablyadoor.cataclysms.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.TESTING_BLOCK)
                .add(ModBlocks.TESTING_BLOCK_REINFORCED)
                .add(ModBlocks.STARSCORCHED_SLATE);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.TESTING_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.TESTING_BLOCK_REINFORCED)
                .add(ModBlocks.STARSCORCHED_SLATE);

        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_FROSTFALL_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

    }
}
