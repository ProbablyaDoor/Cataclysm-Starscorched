package com.probablyadoor.cataclysms.item;

import com.google.common.base.Suppliers;
import com.probablyadoor.cataclysms.block.ModBlocks;
import com.probablyadoor.cataclysms.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;
import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {

    DAYBREAKER(ModTags.Blocks.INCORRECT_FOR_DAYBREAKER_TOOL, 9999, 9.0F, 4.0F, 15, () -> Ingredient.ofItems(new ItemConvertible[]{ModItems.STARMETAL_INGOT})),
    FROSTFALL(ModTags.Blocks.INCORRECT_FOR_FROSTFALL_TOOL, 9999, 9.0F, 4.0F, 15, () -> Ingredient.ofItems(new ItemConvertible[]{ModItems.STARMETAL_INGOT})),
    MAGICBANE(ModTags.Blocks.INCORRECT_FOR_MAGICBANE_TOOL, 9999, 9.0F, 4.0F, 30, () -> Ingredient.ofItems(new ItemConvertible[]{ModItems.STARMETAL_INGOT})),
    AQUAFLORA(ModTags.Blocks.INCORRECT_FOR_AQUAFLORA_TOOL, 9999, 9.0F, 4.0F, 15, () -> Ingredient.ofItems(new ItemConvertible[]{ModItems.STARMETAL_INGOT})),
    SUPERNOVA(ModTags.Blocks.INCORRECT_FOR_SUPERNOVA_TOOL, 9999, 9.0F, 4.0F, 15, () -> Ingredient.ofItems(new ItemConvertible[]{ModItems.STARMETAL_INGOT})),
    ADMIN(ModTags.Blocks.INCORRECT_FOR_ADMIN_TOOL, 9999, 9999, 9999, 15, () -> Ingredient.ofItems(new ItemConvertible[]{ModBlocks.TESTING_BLOCK_UNBREAKABLE}));


    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(final TagKey<Block> inverseTag, final int itemDurability,final float miningSpeed,
                     final float attackDamage, final int enchantability,final Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}