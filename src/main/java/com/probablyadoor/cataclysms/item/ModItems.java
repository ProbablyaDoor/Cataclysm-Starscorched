package com.probablyadoor.cataclysms.item;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import com.probablyadoor.cataclysms.item.custom.ChaosToolItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item STARMETAL_SCRAP = registerItem("starmetal_scrap", new Item(new Item.Settings()));
    public static final Item STARMETAL_INGOT = registerItem("starmetal_ingot", new Item(new Item.Settings()));

    public static final Item MANGOSTEEN = registerItem("mangosteen", new Item(new Item.Settings().food(ModFoodComponents.MANGOSTEEN)));


    public static final Item CATACLYSM_ITEMS = registerItem("cataclysm_items", new Item(new Item.Settings()));
    public static final Item CATACLYSM_WEAPONS = registerItem("cataclysm_weapons", new Item(new Item.Settings()));
    public static final Item CATACLYSM_BLOCKS = registerItem("cataclysm_blocks", new Item(new Item.Settings()));
    public static final Item CATACLYSM_DEBUG = registerItem("cataclysm_debug", new Item(new Item.Settings()));

    public static final Item CHAOS_TOOL = registerItem("chaos_tool", new ChaosToolItem(new Item.Settings().maxCount(1)));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CataclysmStarscorched.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CataclysmStarscorched.LOGGER.info("Registering Mod Items for " + CataclysmStarscorched.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(STARMETAL_SCRAP);
            fabricItemGroupEntries.add(STARMETAL_INGOT);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(MANGOSTEEN);
        });

    }

}
