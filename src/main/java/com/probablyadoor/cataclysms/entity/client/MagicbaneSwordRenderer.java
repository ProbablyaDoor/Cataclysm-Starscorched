package com.probablyadoor.cataclysms.entity.client;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import com.probablyadoor.cataclysms.entity.custom.MagicbaneSwordEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class MagicbaneSwordRenderer extends EntityRenderer<MagicbaneSwordEntity> {
    protected MagicbaneSwordModel model;

    public MagicbaneSwordRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new MagicbaneSwordModel(ctx.getPart(MagicbaneSwordModel.MAGICBANE_SWORD));
    }

    @Override
    public void render(MagicbaneSwordEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw())));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getRenderingRotation() * 5f));
        matrices.translate(0, 0f, 0);
        matrices.scale(1, 1, 1);

        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers,
                this.model.getLayer(Identifier.of(CataclysmStarscorched.MOD_ID, "textures/entity/magicbane_sword.png")), false, false);
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(MagicbaneSwordEntity entity) {
        return Identifier.of(CataclysmStarscorched.MOD_ID, "textures/entity/magicbane_sword.png");
    }
}