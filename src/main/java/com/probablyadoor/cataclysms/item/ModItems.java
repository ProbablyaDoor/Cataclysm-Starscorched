package com.probablyadoor.cataclysms.item;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import com.probablyadoor.cataclysms.item.custom.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems {
    public static final Item STARMETAL_SCRAP = registerItem("starmetal_scrap", new Item(new Item.Settings()));
    public static final Item STARMETAL_INGOT = registerItem("starmetal_ingot", new Item(new Item.Settings()));

    public static final Item MANGOSTEEN = registerItem("mangosteen", new Item(new Item.Settings().food(ModFoodComponents.MANGOSTEEN)){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.cataclysms.mangosteen.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });


    public static final Item CATACLYSM_ITEMS = registerItem("cataclysm_items", new Item(new Item.Settings()));
    public static final Item CATACLYSM_WEAPONS = registerItem("cataclysm_weapons", new Item(new Item.Settings()));
    public static final Item CATACLYSM_BLOCKS = registerItem("cataclysm_blocks", new Item(new Item.Settings()));
    public static final Item CATACLYSM_DEBUG = registerItem("cataclysm_debug", new Item(new Item.Settings()));

    public static final Item CHAOS_TOOL = registerItem("chaos_tool", new ChaosToolItem(new Item.Settings().maxCount(1)));

    public static final Item DAYBREAKER = registerItem("daybreaker", new DaybreakerItem(ModToolMaterials.DAYBREAKER, new Item.Settings()
            .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.DAYBREAKER, 5, -2.4f))));
    public static final Item FROSTFALL = registerItem("frostfall", new HammerItem(ModToolMaterials.FROSTFALL, new Item.Settings()
            .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.FROSTFALL, 4, -2.4f))));
    public static final Item MAGICBANE = registerItem("magicbane", new MagicbaneItem(ModToolMaterials.MAGICBANE, new Item.Settings()
            .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.MAGICBANE, 2, -2.4f))));

    public static final Item NIGHTVEIL = registerItem("nightveil",
    new NightveilItem(new Item.Settings().maxDamage(9999)));
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
