package com.probablyadoor.cataclysms.datagen;

import com.probablyadoor.cataclysms.block.ModBlocks;
import com.probablyadoor.cataclysms.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TESTING_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TESTING_BLOCK_REINFORCED);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TESTING_BLOCK_UNBREAKABLE);

        blockStateModelGenerator.registerSingleton(ModBlocks.STARSCORCHED_SLATE, TexturedModel.CUBE_COLUMN);


    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CATACLYSM_ITEMS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CATACLYSM_BLOCKS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CATACLYSM_WEAPONS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CATACLYSM_DEBUG, Models.GENERATED);

        itemModelGenerator.register(ModItems.CHAOS_TOOL, Models.HANDHELD);

        itemModelGenerator.register(ModItems.MANGOSTEEN, Models.GENERATED);

        itemModelGenerator.register(ModItems.STARMETAL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.STARMETAL_SCRAP, Models.GENERATED);

    }
}
