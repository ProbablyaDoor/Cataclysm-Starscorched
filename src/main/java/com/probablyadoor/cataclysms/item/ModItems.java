package com.probablyadoor.cataclysms.item;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item STARMETAL_SCRAP = registerItem("starmetal_scrap", new Item(new Item.Settings()));
    public static final Item STARMETAL_INGOT = registerItem("starmetal_ingot", new Item(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CataclysmStarscorched.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CataclysmStarscorched.LOGGER.info("Registering Mod Items for " + CataclysmStarscorched.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(STARMETAL_SCRAP);
            fabricItemGroupEntries.add(STARMETAL_INGOT);
        });
    }
}
