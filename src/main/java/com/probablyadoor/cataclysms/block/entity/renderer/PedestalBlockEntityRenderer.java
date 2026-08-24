package com.probablyadoor.cataclysms.block.entity.renderer;

import com.probablyadoor.cataclysms.block.entity.custom.PedestalBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {
    public PedestalBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    // Runs every frame the block entity is being rendered
    @Override
    public void render(PedestalBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        // Use default Minecraft item renderer
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = entity.getStack(0); // Get pedestal's single inventory slot

        // Prepare matrices for coordinate system transformations
        matrices.push();

        // Rotation Transformations
        matrices.translate(0.5f, 1.15f, 0.5f);
        matrices.scale(1f, 1f, 1f);
        matrices.multiply(
                // Rotates around the Y axis
                RotationAxis.POSITIVE_Y.rotationDegrees(
                        entity.getRenderingRotation()
                )
        );

        itemRenderer.renderItem(
                stack,
                ModelTransformationMode.GROUND,
                getLightLevel(entity.getWorld(), entity.getPos()),
                OverlayTexture.DEFAULT_UV, // No special overlay (enchant glint, etc.)
                matrices,
                vertexConsumers,
                entity.getWorld(),
                1
        );

        // Restore original coordinate system
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
