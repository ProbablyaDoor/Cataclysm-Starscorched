package com.probablyadoor.cataclysms;

import com.probablyadoor.cataclysms.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class CataclysmStarscorchedDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

				pack.addProvider(ModBlockTagProvider::new);
				pack.addProvider(ModItemTagProvider::new);
				pack.addProvider(ModModelProvider::new);
				pack.addProvider(ModLootTableProvider::new);
				pack.addProvider(ModRecipeProvider::new);
	}
}
