package com.probablyadoor.cataclysms.block;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block TESTING_BLOCK = registerBlock("testing_block",
        new Block(AbstractBlock.Settings.create().strength(0.1f)
                .requiresTool().sounds(BlockSoundGroup.GLASS)));

    public static final Block TESTING_BLOCK_REINFORCED = registerBlock("testing_block_v2",
            new Block(AbstractBlock.Settings.create().strength(10f)
                    .requiresTool().sounds(BlockSoundGroup.COPPER)));

    public static final Block TESTING_BLOCK_UNBREAKABLE = registerBlock("testing_block_v3",
            new Block(AbstractBlock.Settings.create().strength(10000f)
                    .requiresTool().sounds(BlockSoundGroup.NETHERITE)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(CataclysmStarscorched.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(CataclysmStarscorched.MOD_ID, name),
        new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        CataclysmStarscorched.LOGGER.info("Registering Mod Blocks for " + CataclysmStarscorched.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
                fabricItemGroupEntries.add(ModBlocks.TESTING_BLOCK);
        });
    }
}