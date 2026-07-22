package com.probablyadoor.cataclysms.entity.client;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import com.probablyadoor.cataclysms.entity.custom.FrostfallProjectileEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class FrostfallProjectileModel extends EntityModel<FrostfallProjectileEntity> {
    public static final EntityModelLayer FROSTFALL = new EntityModelLayer(Identifier.of(CataclysmStarscorched.MOD_ID, "frostfall"), "main");
    private final ModelPart frostfall;

    public FrostfallProjectileModel(ModelPart root) {
        this.frostfall = root.getChild("frostfall");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData frostfall = modelPartData.addChild("frostfall", ModelPartBuilder.create().uv(100, 17).cuboid(-4.0F, -15.0F, -5.0F, 8.0F, 11.0F, 10.0F, new Dilation(0.0F))
                .uv(176, 53).cuboid(0.5F, -17.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(176, 53).mirrored().cuboid(-1.5F, -17.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(128, 82).cuboid(-2.5F, -4.6F, -4.5F, 5.0F, 4.0F, 9.0F, new Dilation(0.0F))
                .uv(160, 53).mirrored().cuboid(-20.0F, -12.8F, 5.5F, 3.0F, 7.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(168, 53).mirrored().cuboid(-20.0F, -12.8F, -6.5F, 3.0F, 7.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(168, 53).cuboid(17.0F, -12.8F, -6.5F, 3.0F, 7.0F, 1.0F, new Dilation(0.0F))
                .uv(160, 53).cuboid(17.0F, -12.8F, 5.5F, 3.0F, 7.0F, 1.0F, new Dilation(0.0F))
                .uv(132, 43).mirrored().cuboid(-20.0F, -14.8F, -5.5F, 3.0F, 11.0F, 11.0F, new Dilation(0.0F)).mirrored(false)
                .uv(132, 43).cuboid(17.0F, -14.8F, -5.5F, 3.0F, 11.0F, 11.0F, new Dilation(0.0F))
                .uv(101, 0).mirrored().cuboid(-17.0F, -14.0F, -4.0F, 11.0F, 9.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
                .uv(159, 73).mirrored().cuboid(-6.0F, -14.0F, -4.0F, 3.0F, 7.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
                .uv(159, 73).cuboid(3.0F, -14.0F, -4.0F, 3.0F, 7.0F, 8.0F, new Dilation(0.0F))
                .uv(140, 0).mirrored().cuboid(-17.0F, -5.0F, -4.0F, 10.0F, 2.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
                .uv(101, 0).cuboid(6.0F, -14.0F, -4.0F, 11.0F, 9.0F, 8.0F, new Dilation(0.0F))
                .uv(140, 0).cuboid(7.0F, -5.0F, -4.0F, 10.0F, 2.0F, 8.0F, new Dilation(0.0F))
                .uv(90, 26).cuboid(-6.5F, 9.4F, 0.0F, 5.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(92, 22).cuboid(-4.5F, 11.4F, -0.5F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 20).cuboid(-3.5F, 10.4F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(96, 14).cuboid(-2.5F, 14.4F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(96, 12).cuboid(-1.5F, 15.4F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(96, 18).cuboid(-3.5F, 9.4F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(96, 16).cuboid(-4.5F, 10.4F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(92, 24).cuboid(-4.5F, 14.4F, 0.0F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(92, 24).cuboid(-4.5F, 5.4F, 0.0F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(96, 14).cuboid(-2.5F, 5.4F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(46, 73).cuboid(-2.5F, 7.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(46, 73).cuboid(-2.5F, 3.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(37, 77).cuboid(-0.5F, 5.4F, -1.5F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(38, 72).cuboid(-1.5F, 6.4F, -1.5F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(38, 72).cuboid(-1.5F, 3.4F, -1.5F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(37, 77).cuboid(-0.5F, 2.4F, -1.5F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(88, 0).cuboid(-1.5F, -0.5F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.1F))
                .uv(76, 0).cuboid(-1.5F, 2.7F, -1.5F, 3.0F, 4.0F, 3.0F, new Dilation(0.1F))
                .uv(38, 72).cuboid(-1.5F, 9.4F, -1.5F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(38, 72).cuboid(-0.5F, 15.4F, -1.5F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(39, 69).cuboid(-1.5F, 8.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(39, 69).cuboid(-1.5F, 5.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(39, 69).cuboid(-1.5F, 2.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(45, 69).cuboid(-0.5F, 7.4F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(45, 69).cuboid(-0.5F, 4.4F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(39, 69).cuboid(-0.5F, 14.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(45, 69).cuboid(0.5F, 13.4F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(45, 69).cuboid(-0.5F, 10.4F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(39, 69).cuboid(-1.5F, 11.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(39, 69).cuboid(0.5F, 17.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(39, 69).cuboid(2.5F, 19.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(45, 69).cuboid(1.5F, 16.4F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(45, 69).cuboid(0.5F, 20.4F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(45, 69).cuboid(0.5F, 23.4F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(45, 69).cuboid(0.5F, 26.4F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(45, 69).cuboid(-0.5F, 30.4F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(74, 47).cuboid(-0.5F, 29.4F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(71, 43).cuboid(-1.5F, 28.4F, -1.5F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(75, 50).cuboid(2.5F, 28.4F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(69, 50).cuboid(3.5F, 29.4F, -1.0F, 1.0F, 7.0F, 2.0F, new Dilation(0.0F))
                .uv(56, 50).cuboid(4.5F, 32.4F, -1.0F, 1.0F, 4.0F, 2.0F, new Dilation(0.0F))
                .uv(70, 27).cuboid(1.5F, 27.4F, 0.0F, 6.0F, 16.0F, 0.0F, new Dilation(0.0F))
                .uv(70, 27).cuboid(1.5F, 27.4F, 0.0F, 6.0F, 16.0F, 0.0F, new Dilation(0.0F))
                .uv(59, 59).cuboid(3.5F, 36.4F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(62, 46).cuboid(2.5F, 38.6F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(39, 69).cuboid(2.5F, 22.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(38, 72).cuboid(-1.5F, 12.4F, -1.5F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(38, 72).cuboid(0.5F, 16.4F, -1.5F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(38, 72).cuboid(-0.5F, 13.4F, -1.5F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(38, 72).cuboid(-0.5F, 13.4F, -1.5F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(37, 77).cuboid(-0.5F, 11.4F, -1.5F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(37, 77).cuboid(-0.5F, 8.4F, -1.5F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(37, 77).cuboid(0.5F, 14.4F, -1.5F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(37, 77).cuboid(1.5F, 17.4F, -1.5F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(37, 77).cuboid(0.5F, 21.4F, -1.5F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(38, 72).cuboid(1.5F, 19.4F, -1.5F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(38, 72).cuboid(0.5F, 18.4F, -1.5F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(38, 72).cuboid(2.5F, 20.4F, -1.5F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(37, 77).cuboid(0.5F, 24.4F, -1.5F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(38, 78).cuboid(-0.5F, 27.4F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(47, 78).cuboid(-1.0F, 32.4F, -1.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(56, 78).cuboid(-1.5F, 31.4F, -1.5F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(57, 83).cuboid(-4.0F, 30.5F, 0.0F, 9.0F, 5.0F, 0.0F, new Dilation(0.0F))
                .uv(38, 72).cuboid(2.5F, 23.4F, -1.5F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(46, 73).cuboid(-2.5F, 12.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(46, 73).cuboid(-2.5F, 9.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(46, 73).cuboid(-1.5F, 14.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(46, 73).cuboid(-1.5F, 16.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(46, 73).cuboid(-0.5F, 18.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(46, 73).cuboid(-0.5F, 20.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(46, 73).cuboid(-0.5F, 24.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(46, 73).cuboid(-0.5F, 26.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(46, 73).cuboid(-0.5F, 22.4F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(88, 64).cuboid(-1.0F, 34.7F, -1.5F, 3.0F, 4.0F, 3.0F, new Dilation(-0.1F)), ModelTransform.of(0.0F, -8.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r1 = frostfall.addChild("cube_r1", ModelPartBuilder.create().uv(90, 35).cuboid(0.0F, -4.0F, 0.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.2F)), ModelTransform.of(-2.7F, 36.6F, -1.6F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r2 = frostfall.addChild("cube_r2", ModelPartBuilder.create().uv(90, 35).mirrored().cuboid(-2.0F, -4.0F, 0.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.2F)).mirrored(false), ModelTransform.of(3.7F, 36.6F, -1.6F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r3 = frostfall.addChild("cube_r3", ModelPartBuilder.create().uv(90, 59).cuboid(-1.99F, -1.99F, -0.01F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 40.0F, -1.5F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r4 = frostfall.addChild("cube_r4", ModelPartBuilder.create().uv(90, 54).cuboid(-1.99F, -1.99F, -0.01F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 36.0F, -1.5F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r5 = frostfall.addChild("cube_r5", ModelPartBuilder.create().uv(71, 78).mirrored().cuboid(0.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-1.5F, 31.4F, -0.5F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r6 = frostfall.addChild("cube_r6", ModelPartBuilder.create().uv(71, 78).cuboid(-1.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, 31.4F, -0.5F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r7 = frostfall.addChild("cube_r7", ModelPartBuilder.create().uv(58, 46).cuboid(-0.99F, 0.01F, -0.01F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.5F, 39.4F, -0.5F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r8 = frostfall.addChild("cube_r8", ModelPartBuilder.create().uv(76, 66).cuboid(-0.98F, -0.98F, -0.02F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.4F, 42.1F, -0.5F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r9 = frostfall.addChild("cube_r9", ModelPartBuilder.create().uv(55, 59).cuboid(0.01F, -2.99F, 0.01F, 0.98F, 2.98F, 0.98F, new Dilation(0.0F)), ModelTransform.of(2.5F, 38.6F, -0.5F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r10 = frostfall.addChild("cube_r10", ModelPartBuilder.create().uv(63, 50).cuboid(-1.01F, -3.01F, -0.99F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(5.5F, 32.4F, 0.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r11 = frostfall.addChild("cube_r11", ModelPartBuilder.create().uv(70, 64).cuboid(-1.01F, -0.01F, 0.01F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(5.5F, 36.4F, -0.5F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r12 = frostfall.addChild("cube_r12", ModelPartBuilder.create().uv(75, 57).cuboid(-0.99F, -1.99F, 0.01F, 0.98F, 1.98F, 1.98F, new Dilation(0.0F)), ModelTransform.of(4.5F, 29.4F, -1.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r13 = frostfall.addChild("cube_r13", ModelPartBuilder.create().uv(63, 55).cuboid(0.01F, -1.99F, 0.01F, 0.98F, 1.98F, 1.98F, new Dilation(0.0F)), ModelTransform.of(3.5F, 33.2F, -1.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r14 = frostfall.addChild("cube_r14", ModelPartBuilder.create().uv(97, 10).cuboid(-1.39F, -0.99F, 0.01F, 1.38F, 0.98F, -0.02F, new Dilation(0.0F)), ModelTransform.of(-2.5F, 7.4F, 0.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r15 = frostfall.addChild("cube_r15", ModelPartBuilder.create().uv(96, 11).cuboid(-0.715F, 0.285F, 0.01F, 0.705F, 0.705F, -0.02F, new Dilation(0.0F)), ModelTransform.of(-4.7F, 5.2F, 0.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r16 = frostfall.addChild("cube_r16", ModelPartBuilder.create().uv(97, 10).cuboid(-1.39F, -0.99F, 0.01F, 1.38F, 0.98F, -0.02F, new Dilation(0.0F)), ModelTransform.of(-2.5F, 16.4F, 0.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r17 = frostfall.addChild("cube_r17", ModelPartBuilder.create().uv(96, 10).cuboid(-1.99F, -0.99F, 0.01F, 1.98F, 0.98F, -0.02F, new Dilation(0.0F)), ModelTransform.of(-4.5F, 12.4F, 0.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r18 = frostfall.addChild("cube_r18", ModelPartBuilder.create().uv(96, 11).cuboid(-0.715F, 0.285F, 0.01F, 0.705F, 0.705F, -0.02F, new Dilation(0.0F)), ModelTransform.of(-4.7F, 14.2F, 0.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r19 = frostfall.addChild("cube_r19", ModelPartBuilder.create().uv(96, 11).cuboid(-0.715F, 0.285F, 0.01F, 0.705F, 0.705F, -0.02F, new Dilation(0.0F)), ModelTransform.of(3.8F, 41.7F, 0.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r20 = frostfall.addChild("cube_r20", ModelPartBuilder.create().uv(96, 11).cuboid(-0.715F, 0.285F, 0.01F, 0.705F, 0.705F, -0.02F, new Dilation(0.0F)), ModelTransform.of(2.8F, 42.7F, 0.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r21 = frostfall.addChild("cube_r21", ModelPartBuilder.create().uv(96, 11).cuboid(-0.715F, 0.285F, 0.01F, 0.705F, 0.705F, -0.02F, new Dilation(0.0F)), ModelTransform.of(-6.7F, 10.2F, 0.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r22 = frostfall.addChild("cube_r22", ModelPartBuilder.create().uv(96, 11).cuboid(-1.39F, 0.01F, 0.01F, 1.38F, 0.98F, -0.02F, new Dilation(0.0F)), ModelTransform.of(-5.5F, 9.4F, 0.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r23 = frostfall.addChild("cube_r23", ModelPartBuilder.create().uv(190, 21).mirrored().cuboid(-5.0F, 0.0F, 0.0F, 5.0F, 6.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
                .uv(190, 21).mirrored().cuboid(-5.0F, 0.0F, -4.0F, 5.0F, 6.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.0F, -20.0F, 2.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r24 = frostfall.addChild("cube_r24", ModelPartBuilder.create().uv(190, 21).cuboid(0.0F, 0.0F, 0.0F, 5.0F, 6.0F, 0.0F, new Dilation(0.0F))
                .uv(190, 21).cuboid(0.0F, 0.0F, -4.0F, 5.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -20.0F, 2.0F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r25 = frostfall.addChild("cube_r25", ModelPartBuilder.create().uv(194, 42).cuboid(-11.0F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(194, 42).cuboid(-11.0F, -3.0F, 4.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(20.3F, -9.8F, -2.0F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r26 = frostfall.addChild("cube_r26", ModelPartBuilder.create().uv(194, 42).mirrored().cuboid(8.0F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
                .uv(194, 42).mirrored().cuboid(8.0F, -3.0F, -4.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-20.3F, -9.8F, 2.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r27 = frostfall.addChild("cube_r27", ModelPartBuilder.create().uv(155, 36).mirrored().cuboid(0.0F, 5.0F, 0.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
                .uv(139, 36).mirrored().cuboid(0.0F, 3.0F, 0.0F, 3.0F, 2.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
                .uv(138, 26).mirrored().cuboid(0.0F, 0.0F, -1.0F, 7.0F, 3.0F, 7.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-18.0F, -5.0F, -2.5F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r28 = frostfall.addChild("cube_r28", ModelPartBuilder.create().uv(139, 36).cuboid(-3.0F, 3.0F, 0.0F, 3.0F, 2.0F, 5.0F, new Dilation(0.0F))
                .uv(138, 26).cuboid(-7.0F, 0.0F, -1.0F, 7.0F, 3.0F, 7.0F, new Dilation(0.0F))
                .uv(155, 36).cuboid(-1.0F, 5.0F, 0.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(18.0F, -5.0F, -2.5F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r29 = frostfall.addChild("cube_r29", ModelPartBuilder.create().uv(100, 46).cuboid(-2.0F, -4.0F, -1.0F, 5.0F, 4.0F, 7.0F, new Dilation(0.0F))
                .uv(100, 57).cuboid(0.0F, -7.0F, 0.0F, 3.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(18.2F, -12.5F, -2.5F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r30 = frostfall.addChild("cube_r30", ModelPartBuilder.create().uv(100, 57).mirrored().cuboid(-3.0F, -7.0F, -1.0F, 3.0F, 3.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
                .uv(100, 46).mirrored().cuboid(-3.0F, -4.0F, -2.0F, 5.0F, 4.0F, 7.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-18.2F, -12.5F, -1.5F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r31 = frostfall.addChild("cube_r31", ModelPartBuilder.create().uv(100, 39).cuboid(-0.99F, -0.99F, -0.01F, 1.0F, 1.0F, 1.0F, new Dilation(-0.1F)), ModelTransform.of(0.0F, -7.5F, 4.1F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r32 = frostfall.addChild("cube_r32", ModelPartBuilder.create().uv(100, 39).cuboid(-0.99F, -0.99F, -0.01F, 1.0F, 1.0F, 1.0F, new Dilation(-0.1F)), ModelTransform.of(0.0F, -10.5F, 4.1F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r33 = frostfall.addChild("cube_r33", ModelPartBuilder.create().uv(100, 82).mirrored().cuboid(0.01F, 0.01F, 0.01F, 4.98F, 3.98F, 8.98F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.0F, -4.3F, -4.5F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r34 = frostfall.addChild("cube_r34", ModelPartBuilder.create().uv(100, 82).cuboid(-4.99F, 0.01F, 0.01F, 4.98F, 3.98F, 8.98F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -4.3F, -4.5F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r35 = frostfall.addChild("cube_r35", ModelPartBuilder.create().uv(154, 89).mirrored().cuboid(-5.98F, -0.98F, -0.02F, 6.0F, 1.0F, 10.0F, new Dilation(0.1F)).mirrored(false), ModelTransform.of(2.6F, -0.9F, -5.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r36 = frostfall.addChild("cube_r36", ModelPartBuilder.create().uv(154, 89).cuboid(0.01F, -0.99F, -0.01F, 6.0F, 1.0F, 10.0F, new Dilation(0.1F)), ModelTransform.of(-2.6F, -0.9F, -5.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData cube_r37 = frostfall.addChild("cube_r37", ModelPartBuilder.create().uv(192, 76).cuboid(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -18.0F, -1.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r38 = frostfall.addChild("cube_r38", ModelPartBuilder.create().uv(192, 95).cuboid(-4.0F, -5.0F, 0.0F, 4.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, -15.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r39 = frostfall.addChild("cube_r39", ModelPartBuilder.create().uv(192, 95).mirrored().cuboid(0.0F, -5.0F, 0.0F, 4.0F, 5.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.5F, -15.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r40 = frostfall.addChild("cube_r40", ModelPartBuilder.create().uv(180, 53).mirrored().cuboid(-0.99F, -1.99F, 0.01F, 0.98F, 1.98F, 0.98F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.5F, -17.0F, -0.5F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r41 = frostfall.addChild("cube_r41", ModelPartBuilder.create().uv(176, 57).mirrored().cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.0F, -18.5F, -0.5F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r42 = frostfall.addChild("cube_r42", ModelPartBuilder.create().uv(180, 56).mirrored().cuboid(0.01F, -0.99F, 0.01F, 0.98F, 0.98F, 0.98F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-1.0F, -20.0F, -0.5F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r43 = frostfall.addChild("cube_r43", ModelPartBuilder.create().uv(180, 53).cuboid(0.01F, -1.99F, 0.01F, 0.98F, 1.98F, 0.98F, new Dilation(0.0F)), ModelTransform.of(0.5F, -17.0F, -0.5F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r44 = frostfall.addChild("cube_r44", ModelPartBuilder.create().uv(180, 56).cuboid(-0.99F, -0.99F, 0.01F, 0.98F, 0.98F, 0.98F, new Dilation(0.0F)), ModelTransform.of(1.0F, -20.0F, -0.5F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r45 = frostfall.addChild("cube_r45", ModelPartBuilder.create().uv(176, 57).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -18.5F, -0.5F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r46 = frostfall.addChild("cube_r46", ModelPartBuilder.create().uv(100, 65).mirrored().cuboid(-0.3F, -9.3F, -0.3F, 3.6F, 9.6F, 8.6F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-3.4F, -5.7F, -4.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r47 = frostfall.addChild("cube_r47", ModelPartBuilder.create().uv(100, 65).cuboid(-3.3F, -9.3F, -0.3F, 3.6F, 9.6F, 8.6F, new Dilation(0.0F)), ModelTransform.of(3.4F, -5.7F, -4.0F, 0.0F, 0.0F, 0.3927F));
        return TexturedModelData.of(modelData, 200, 200);
    }
    @Override
    public void setAngles(FrostfallProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        frostfall.render(matrices, vertexConsumer, light, overlay, color);
    }
}
