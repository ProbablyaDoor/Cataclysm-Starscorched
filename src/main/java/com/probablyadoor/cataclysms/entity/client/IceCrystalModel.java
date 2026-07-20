package com.probablyadoor.cataclysms.entity.client;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import com.probablyadoor.cataclysms.entity.custom.FrostfallProjectileEntity;
import com.probablyadoor.cataclysms.entity.custom.IceCrystalEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class IceCrystalModel<T extends IceCrystalEntity> extends SinglePartEntityModel<T> {
	public static final EntityModelLayer ICE_CRYSTAL = new EntityModelLayer(Identifier.of(CataclysmStarscorched.MOD_ID, "ice_crystal"), "main");
	private final ModelPart ice_crystal;

	public IceCrystalModel(ModelPart root) {
		this.ice_crystal = root.getChild("ice_crystal");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData ice_crystal = modelPartData.addChild("ice_crystal", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -20.0F, -6.0F, 11.0F, 20.0F, 11.0F, new Dilation(0.0F))
		.uv(5, 2).cuboid(-4.0F, -30.0F, -5.0F, 8.0F, 10.0F, 9.0F, new Dilation(0.0F))
		.uv(12, 6).cuboid(-3.0F, -36.0F, -3.0F, 5.0F, 6.0F, 5.0F, new Dilation(0.0F))
		.uv(13, 4).cuboid(-8.0F, -15.0F, -3.0F, 2.0F, 15.0F, 7.0F, new Dilation(0.0F))
		.uv(13, 4).cuboid(5.0F, -14.0F, -6.0F, 2.0F, 14.0F, 7.0F, new Dilation(0.0F))
		.uv(12, 9).cuboid(-5.0F, -11.0F, -8.0F, 8.0F, 11.0F, 2.0F, new Dilation(0.0F))
		.uv(12, 9).cuboid(-4.0F, -17.0F, 5.0F, 8.0F, 17.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r1 = ice_crystal.addChild("cube_r1", ModelPartBuilder.create().uv(73, 7).cuboid(6.0F, -12.0F, -1.0F, 5.0F, 12.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 9.0F, 2.0F, -0.0699F, 0.9318F, 0.031F));

		ModelPartData cube_r2 = ice_crystal.addChild("cube_r2", ModelPartBuilder.create().uv(73, 7).cuboid(6.0F, -12.0F, -1.0F, 5.0F, 12.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 9.0F, 2.0F, 0.2356F, 0.9318F, 0.031F));

		ModelPartData cube_r3 = ice_crystal.addChild("cube_r3", ModelPartBuilder.create().uv(73, 7).cuboid(6.0F, -12.0F, -1.0F, 5.0F, 12.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 10.0F, 12.0F, -0.3915F, 1.1796F, -0.6323F));

		ModelPartData cube_r4 = ice_crystal.addChild("cube_r4", ModelPartBuilder.create().uv(68, 5).cuboid(3.0F, -16.0F, -1.0F, 8.0F, 16.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 9.0F, 8.0F, 0.118F, 0.9842F, 0.2931F));

		ModelPartData cube_r5 = ice_crystal.addChild("cube_r5", ModelPartBuilder.create().uv(68, 5).cuboid(3.0F, -16.0F, -1.0F, 8.0F, 16.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 15.0F, 2.0F, -0.0682F, 0.9508F, -0.2467F));

		ModelPartData cube_r6 = ice_crystal.addChild("cube_r6", ModelPartBuilder.create().uv(68, 5).cuboid(3.0F, -16.0F, -1.0F, 8.0F, 16.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 3.0F, 2.0F, -0.8845F, 0.4157F, -1.4142F));

		ModelPartData cube_r7 = ice_crystal.addChild("cube_r7", ModelPartBuilder.create().uv(45, 19).cuboid(0.0F, -13.0F, -1.0F, 11.0F, 13.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -14.0F, 5.0F, -2.7623F, -0.0402F, 2.1617F));

		ModelPartData cube_r8 = ice_crystal.addChild("cube_r8", ModelPartBuilder.create().uv(45, 19).cuboid(0.0F, -13.0F, -1.0F, 11.0F, 13.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -7.0F, 2.0F, 2.9227F, -0.3148F, -2.0281F));

		ModelPartData cube_r9 = ice_crystal.addChild("cube_r9", ModelPartBuilder.create().uv(68, 5).cuboid(3.0F, -16.0F, -1.0F, 8.0F, 16.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, 2.0F, -2.0F, 2.05F, -0.3148F, -2.0281F));

		ModelPartData cube_r10 = ice_crystal.addChild("cube_r10", ModelPartBuilder.create().uv(68, 5).cuboid(3.0F, -16.0F, -1.0F, 8.0F, 16.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 2.0F, 2.0F, -2.3383F, 0.0368F, -1.8506F));

		ModelPartData cube_r11 = ice_crystal.addChild("cube_r11", ModelPartBuilder.create().uv(6, 3).cuboid(3.0F, -16.0F, -1.0F, 8.0F, 16.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 1.0F, -7.0F, 0.3604F, -0.2457F, -0.7023F));
		return TexturedModelData.of(modelData, 100, 100);
	}
	@Override
	public void setAngles(IceCrystalEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		ice_crystal.render(matrices, vertexConsumer, light, overlay, color);
	}

	@Override
	public ModelPart getPart() {
		return ice_crystal;
	}
}