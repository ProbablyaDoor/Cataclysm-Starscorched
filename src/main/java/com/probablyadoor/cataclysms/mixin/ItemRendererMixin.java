package com.probablyadoor.cataclysms.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.probablyadoor.cataclysms.CataclysmStarscorched;
import com.probablyadoor.cataclysms.item.ModItems;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow
    @Final
    private ItemModels models;

    @Shadow
    public abstract ItemModels getModels();

    @ModifyVariable(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(value = "HEAD"),
            argsOnly = true
    )
    public BakedModel renderItem(BakedModel bakedModel, @Local(argsOnly = true) ItemStack stack, @Local(argsOnly = true) ModelTransformationMode renderMode) {
        if (stack.getItem() == ModItems.MAGICBANE && (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED)) {
            return getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(CataclysmStarscorched.MOD_ID, "magicbane")));
        }
        if (stack.getItem() == ModItems.DAYBREAKER && (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED)) {
            return getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(CataclysmStarscorched.MOD_ID, "daybreaker")));

        }
        if (stack.getItem() == ModItems.FROSTFALL && (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED)) {
            return getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(CataclysmStarscorched.MOD_ID, "frostfall")));
        }

        return bakedModel;
    }

    @ModifyVariable(
            method = "getModel",
            at = @At(value = "STORE"),
            ordinal = 1
    )
    public BakedModel getHeldItemModelMixin(BakedModel bakedModel, @Local(argsOnly = true) ItemStack stack) {
        if (stack.getItem() == ModItems.MAGICBANE) {
            return this.models.getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(CataclysmStarscorched.MOD_ID, "magicbane_3d")));
        }
        if (stack.getItem() == ModItems.DAYBREAKER) {
            return this.models.getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(CataclysmStarscorched.MOD_ID, "daybreaker_3d")));
        }
        if (stack.getItem() == ModItems.FROSTFALL) {
            return this.models.getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(CataclysmStarscorched.MOD_ID, "frostfall_3d")));
        }

        return bakedModel;
    }
}