package com.probablyadoor.cataclysms.entity.client;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import com.probablyadoor.cataclysms.entity.custom.MagicbaneSwordEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class MagicbaneSwordModel extends EntityModel<MagicbaneSwordEntity> {
	public static final EntityModelLayer MAGICBANE_SWORD = new EntityModelLayer(Identifier.of(CataclysmStarscorched.MOD_ID, "magicbane_sword"), "main");
	private final ModelPart magicbane_sword;

	public MagicbaneSwordModel(ModelPart root) {
		this.magicbane_sword = root.getChild("magicbane_sword");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("magicbane_sword", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -3.9F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(16, 0).cuboid(-3.0F, -5.9F, -1.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(8, 0).cuboid(-4.0F, -6.9F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(8, 0).cuboid(3.0F, -6.9F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 6).cuboid(-2.0F, -16.9F, -1.0F, 4.0F, 11.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 23.9F, 0.0F));

		ModelPartData cube_r1 = root.addChild("cube_r1", ModelPartBuilder.create().uv(0, 18).cuboid(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.01F)), ModelTransform.of(-0.7F, -15.5F, 0.0F, 0.0F, 0.0F, 0.7854F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(MagicbaneSwordEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		magicbane_sword.render(matrices, vertexConsumer, light, overlay, color);
	}
}