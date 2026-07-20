package com.probablyadoor.cataclysms.util;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.random.Random;
import org.joml.Matrix4f;

public class EntityLightRenderHandler {

    private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0) / 2.0);

    public static void renderEntityLight(LivingEntity entity, float entityYaw, float partialTicks, MatrixStack stack, double[] translation,
                                        VertexConsumerProvider bufferIn, int packedLightIn, int lifeTime, int[] rgbColorOne, int[] rgbColorTwo, int[] rgbColorThree, int[] rgbColorFour) {
        if (lifeTime > 0) {
            float l = ((float)lifeTime + partialTicks) / 200.0f;
            float m = Math.min(l > 0.8f ? (l - 0.8f) / 0.2f : 0.0f, 1.0f);
            Random random = Random.create(432L);
            VertexConsumer vertexConsumer4 = bufferIn.getBuffer(RenderLayer.getLightning());
            stack.push();
            stack.translate(translation[0], translation[1], translation[2]);
            for(int n = 0; (float)n < (l + l * l) / 2.0F * 60.0F; ++n) {
                stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(random.nextFloat() * 360.0f));
                stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(random.nextFloat() * 360.0f));
                stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(random.nextFloat() * 360.0f));
                stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(random.nextFloat() * 360.0f));
                stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(random.nextFloat() * 360.0f));
                stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(random.nextFloat() * 360.0f + l * 90.0f));
                float o = random.nextFloat() * 20.0f + 5.0f + m * 10.0f;
                float p = random.nextFloat() * 2.0f + 1.0f + m * 2.0f;
                Matrix4f matrix4f = stack.peek().getPositionMatrix();
                int q = (int)(255.0f * (1.0f - m));
                EntityLightRenderHandler.renderLight_1(vertexConsumer4, matrix4f, q, rgbColorOne);
                EntityLightRenderHandler.renderLight_2(vertexConsumer4, matrix4f, o, p, rgbColorTwo);
                EntityLightRenderHandler.renderLight_3(vertexConsumer4, matrix4f, o, p, rgbColorThree);
                EntityLightRenderHandler.renderLight_1(vertexConsumer4, matrix4f, q, rgbColorOne);
                EntityLightRenderHandler.renderLight_3(vertexConsumer4, matrix4f, o, p, rgbColorThree);
                EntityLightRenderHandler.renderLight_4(vertexConsumer4, matrix4f, o, p, rgbColorFour);
                EntityLightRenderHandler.renderLight_1(vertexConsumer4, matrix4f, q, rgbColorOne);
                EntityLightRenderHandler.renderLight_4(vertexConsumer4, matrix4f, o, p, rgbColorFour);
                EntityLightRenderHandler.renderLight_2(vertexConsumer4, matrix4f, o, p, rgbColorTwo);
            }
            stack.pop();
        }
    }

    private static void renderLight_1(VertexConsumer vertices, Matrix4f matrix, int alpha, int[] rgbColors) {
        vertices.vertex(matrix, 0.0f, 0.0f, 0.0f).color(rgbColors[0], rgbColors[1], rgbColors[2], alpha);
    }

    private static void renderLight_2(VertexConsumer vertices, Matrix4f matrix, float y, float x, int[] rgbColors) {
        vertices.vertex(matrix, -HALF_SQRT_3 * x, y, -0.5f * x).color(rgbColors[0], rgbColors[1], rgbColors[2], 0);
    }

    private static void renderLight_3(VertexConsumer vertices, Matrix4f matrix, float y, float x, int[] rgbColors) {
        vertices.vertex(matrix, HALF_SQRT_3 * x, y, -0.5f * x).color(rgbColors[0], rgbColors[1], rgbColors[2], 0);
    }

    private static void renderLight_4(VertexConsumer vertices, Matrix4f matrix, float y, float z, int[] rgbColors) {
        vertices.vertex(matrix, 0.0f, y, z).color(rgbColors[0], rgbColors[1], rgbColors[2], 0);
    }
}
