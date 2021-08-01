package net.ddns.lucraft.geodeoptimizer.fabric.renderer;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class BlockPreviewRenderer extends BlockRenderer {

    private static BlockPreviewRenderer instance = new BlockPreviewRenderer();

    public static BlockPreviewRenderer getInstance() {
        return instance;
    }

    public BlockPreviewRenderer() {}

    public void enable() { }
    public void disable() { }

    @Override
    public void renderBlock(BlockPos pos, BlockState state) {
        // TODO: apply transparency
        BlockRenderer.renderBlock(null, pos, state);
    }
}
