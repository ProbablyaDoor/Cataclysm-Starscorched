package com.probablyadoor.cataclysms;

import com.probablyadoor.cataclysms.block.ModBlocks;
import com.probablyadoor.cataclysms.component.ModDataComponentTypes;
import com.probablyadoor.cataclysms.effect.ModEffects;
import com.probablyadoor.cataclysms.entity.ModEntities;
import com.probablyadoor.cataclysms.item.ModItemGroups;
import com.probablyadoor.cataclysms.item.ModItems;
import com.probablyadoor.cataclysms.particle.ModParticles;
import com.probablyadoor.cataclysms.sound.SoundRegistry;
import com.probablyadoor.cataclysms.util.HammerUsageEvent;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CataclysmStarscorched implements ModInitializer {
	public static final String MOD_ID = "cataclysms";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEffects.registerEffects();

		ModParticles.registerModParticles();
		ModDataComponentTypes.registerDataComponentTypes();
		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());
		SoundRegistry.initialize();

		ModEntities.registerModEntities();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
