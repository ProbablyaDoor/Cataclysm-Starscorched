package com.probablyadoor.cataclysms.item;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import com.probablyadoor.cataclysms.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup CATACLYSM_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CataclysmStarscorched.MOD_ID, "cataclysm_items"),
            FabricItemGroup.builder().icon(()-> new ItemStack(ModItems.CATACLYSM_ITEMS))
                    .displayName(Text.translatable("itemgroup.cataclysms.cataclysm_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.STARMETAL_SCRAP);
                        entries.add(ModItems.STARMETAL_INGOT);
                        entries.add(ModItems.MANGOSTEEN);
                    }).build());

    public static final ItemGroup CATACLYSM_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CataclysmStarscorched.MOD_ID, "cataclysm_blocks"),
            FabricItemGroup.builder().icon(()-> new ItemStack(ModItems.CATACLYSM_BLOCKS))
                    .displayName(Text.translatable("itemgroup.cataclysms.cataclysm_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.STARSCORCHED_SLATE);
                    }).build());

    public static final ItemGroup CATACLYSM_WEAPONS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CataclysmStarscorched.MOD_ID, "cataclysm_weapons"),
            FabricItemGroup.builder().icon(()-> new ItemStack(ModItems.CATACLYSM_WEAPONS))
                    .displayName(Text.translatable("itemgroup.cataclysms.cataclysm_weapons"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.DAYBREAKER);
                        entries.add(ModItems.FROSTFALL);
                        entries.add(ModItems.MAGICBANE);
                    }).build());

    public static final ItemGroup CATACLYSM_ADMIN = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CataclysmStarscorched.MOD_ID, "cataclysm_admin"),
            FabricItemGroup.builder().icon(()-> new ItemStack(ModItems.CATACLYSM_DEBUG))
                    .displayName(Text.translatable("itemgroup.cataclysms.cataclysm_admin"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.TESTING_BLOCK);
                        entries.add(ModBlocks.TESTING_BLOCK_REINFORCED);
                        entries.add(ModBlocks.TESTING_BLOCK_UNBREAKABLE);
                        entries.add(ModItems.CHAOS_TOOL);
                    }).build());





    public static void registerItemGroups() {
        CataclysmStarscorched.LOGGER.info("Registering Item Groups for " + CataclysmStarscorched.MOD_ID);
    }
}
