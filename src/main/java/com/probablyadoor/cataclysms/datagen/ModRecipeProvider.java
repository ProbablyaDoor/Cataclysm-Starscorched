package com.probablyadoor.cataclysms.datagen;

import com.probablyadoor.cataclysms.block.ModBlocks;
import com.probablyadoor.cataclysms.item.ModItems;
import com.probablyadoor.cataclysms.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> STARSCORCHED_SMELTABLES = List.of(ModItems.STARMETAL_SCRAP, ModBlocks.STARSCORCHED_SLATE);

        offerSmelting(exporter, STARSCORCHED_SMELTABLES, RecipeCategory.MISC, ModItems.STARMETAL_INGOT, 10.5f, 1000, "starmetal_ingot");
        offerBlasting(exporter, STARSCORCHED_SMELTABLES, RecipeCategory.MISC, ModItems.STARMETAL_INGOT, 10.5f, 500, "starmetal_ingot");

        offer2x2CompactingRecipe(exporter, RecipeCategory.MISC, ModBlocks.TESTING_BLOCK, ModBlocks.TESTING_BLOCK_REINFORCED);
        offer2x2CompactingRecipe(exporter, RecipeCategory.MISC, ModBlocks.TESTING_BLOCK_REINFORCED, ModBlocks.TESTING_BLOCK_UNBREAKABLE);


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CHAOS_TOOL)
                .pattern("TRO")
                .pattern("TRR")
                .pattern("RTT")
                .input('R', ModBlocks.TESTING_BLOCK_REINFORCED)
                .input('O', Blocks.REDSTONE_BLOCK)
                .input('T', Blocks.AIR);
    }
}
