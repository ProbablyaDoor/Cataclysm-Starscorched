package com.probablyadoor.cataclysms.item;

import com.google.common.base.Suppliers;
import com.probablyadoor.cataclysms.block.ModBlocks;
import com.probablyadoor.cataclysms.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.Objects;
import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {

    DAYBREAKER(ModTags.Blocks.INCORRECT_FOR_DAYBREAKER_TOOL, 999999, 9.0F, 4.0F, 15, () -> Ingredient.ofItems(new ItemConvertible[]{ModItems.STARMETAL_INGOT})),
    FROSTFALL(ModTags.Blocks.INCORRECT_FOR_FROSTFALL_TOOL, 999999, 9.0F, 4.0F, 15, () -> Ingredient.ofItems(new ItemConvertible[]{ModItems.STARMETAL_INGOT})),
    MAGICBANE(ModTags.Blocks.INCORRECT_FOR_MAGICBANE_TOOL, 999999, 9.0F, 4.0F, 30, () -> Ingredient.ofItems(new ItemConvertible[]{ModItems.STARMETAL_INGOT})),
    AQUAFLORA(ModTags.Blocks.INCORRECT_FOR_AQUAFLORA_TOOL, 999999, 9.0F, 4.0F, 15, () -> Ingredient.ofItems(new ItemConvertible[]{ModItems.STARMETAL_INGOT})),
    SUPERNOVA(ModTags.Blocks.INCORRECT_FOR_SUPERNOVA_TOOL, 999999, 9.0F, 4.0F, 15, () -> Ingredient.ofItems(new ItemConvertible[]{ModItems.STARMETAL_INGOT})),
    ADMIN(ModTags.Blocks.INCORRECT_FOR_ADMIN_TOOL, 999999, 999999F, 999999F, 15, () -> Ingredient.ofItems(new ItemConvertible[]{ModBlocks.TESTING_BLOCK_UNBREAKABLE}));


    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    private ModToolMaterials(final TagKey<Block> inverseTag, final int itemDurability, final float miningSpeed, final float attackDamage, final int enchantability, final Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        Objects.requireNonNull(repairIngredient);
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getDurability() {
        return 0;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return 0;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return null;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
