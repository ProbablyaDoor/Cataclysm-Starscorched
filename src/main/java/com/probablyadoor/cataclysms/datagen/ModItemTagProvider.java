package com.probablyadoor.cataclysms.datagen;

import com.probablyadoor.cataclysms.item.ModItems;
import com.probablyadoor.cataclysms.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(ModTags.Items.VALID_PICKAXES)
                .add(Items.DIAMOND_PICKAXE)
                .add(Items.IRON_PICKAXE)
                .add(Items.NETHERITE_PICKAXE);

        getOrCreateTagBuilder(ModTags.Items.STARSCORCHED_ITEMS)
                .add(ModItems.STARMETAL_INGOT)
                .add(ModItems.STARMETAL_SCRAP);
    }

}
