package com.probablyadoor.cataclysms.entity.client;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import com.probablyadoor.cataclysms.entity.custom.IceCrystalEntity;
import com.probablyadoor.cataclysms.util.EntityLightRenderHandler;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class IceCrystalRenderer extends MobEntityRenderer<IceCrystalEntity, IceCrystalModel<IceCrystalEntity>> {
    int[] rgbColorOne = {10, 15, 94};
    int[] rgbColorTwo = {25, 121, 238};
    int[] rgbColorThree = {113, 235, 255};
    int[] rgbColorFour = {13, 3, 128};
    double[] translation = {0, 1, 0};


    public IceCrystalRenderer(EntityRendererFactory.Context context) {
        super(context, new IceCrystalModel<>(context.getPart(IceCrystalModel.ICE_CRYSTAL)), 0.75f);
    }

    @Override
    public Identifier getTexture(IceCrystalEntity entity) {
        return Identifier.of(CataclysmStarscorched.MOD_ID, "textures/entity/ice_crystal.png");
    }

    @Override
    public void render(IceCrystalEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1.5f, 1.5f, 1.5f);

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
        EntityLightRenderHandler.renderEntityLight(livingEntity, f, g, matrixStack, this.translation, vertexConsumerProvider, i,
                livingEntity.lifeTime, this.rgbColorOne, this.rgbColorTwo, this.rgbColorThree, this.rgbColorFour);
    }

}