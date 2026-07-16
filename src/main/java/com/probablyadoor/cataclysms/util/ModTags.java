package com.probablyadoor.cataclysms.util;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_DAYBREAKER_TOOL = createTag("needs_daybreaker_tool");
        public static final TagKey<Block> INCORRECT_FOR_DAYBREAKER_TOOL = createTag("incorrect_for_daybreaker_tool");

        public static final TagKey<Block> NEEDS_FROSTFALL_TOOL = createTag("needs_frostfall_tool");
        public static final TagKey<Block> INCORRECT_FOR_FROSTFALL_TOOL = createTag("incorrect_for_frostfall_tool");

        public static final TagKey<Block> NEEDS_MAGICBANE_TOOL = createTag("needs_magicbane_tool");
        public static final TagKey<Block> INCORRECT_FOR_MAGICBANE_TOOL = createTag("incorrect_for_magicbane_tool");

        public static final TagKey<Block> NEEDS_AQUAFLORA_TOOL = createTag("needs_aquaflora_tool");
        public static final TagKey<Block> INCORRECT_FOR_AQUAFLORA_TOOL = createTag("incorrect_for_aquaflora_tool");

        public static final TagKey<Block> NEEDS_SUPERNOVA_TOOL = createTag("needs_supernova_tool");
        public static final TagKey<Block> INCORRECT_FOR_SUPERNOVA_TOOL = createTag("incorrect_for_supernova_tool");

        public static final TagKey<Block> NEEDS_ADMIN_TOOL = createTag("needs_admin_tool");
        public static final TagKey<Block> INCORRECT_FOR_ADMIN_TOOL = createTag("incorrect_for_admin_tool");



        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(CataclysmStarscorched.MOD_ID, name));
        }
    }
    public static class Items{
        public static final TagKey<Item> STARSCORCHED_ITEMS = createTag("starscorched_items");
        public static final TagKey<Item> VALID_PICKAXES = createTag("valid_pickaxes");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(CataclysmStarscorched.MOD_ID, name));
        }
    }
}
