package net.ddns.lucraft.geodeoptimizer.fabric.renderer;

import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;

public abstract class BlockRenderer {

    public abstract void renderBlock(BlockPos pos, BlockState state);

    public static void renderBlock(MatrixStack matrixStack, BlockPos pos, BlockState state) {
        matrixStack.push();

        

        matrixStack.pop();
    }
}
