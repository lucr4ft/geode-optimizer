package net.lucraft.geodeoptimizer.fabric.generation.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashMap;

public class GenerationContext {

    private final ArrayList<BlockPos> positions;
    private final HashMap<BlockPos, BlockState> blocks;
    private final GenerationOptions options;

    /**
     *
     * @param positions
     * @param blocks
     */
    public GenerationContext(ArrayList<BlockPos> positions, HashMap<BlockPos, BlockState> blocks) {
        this.positions = positions;
        this.blocks = blocks;
        this.options = GenerationOptions.DEFAULT;
    }

    public GenerationContext(ArrayList<BlockPos> positions, HashMap<BlockPos, BlockState> blocks, GenerationOptions options) {
        this.positions = positions;
        this.blocks = blocks;
        this.options = options;
    }

    /**
     *
     * @return
     */
    public ArrayList<BlockPos> getPositions() {
        return positions;
    }

    /**
     *
     * @return
     */
    public HashMap<BlockPos, BlockState> getBlocks() {
        return blocks;
    }

    public GenerationOptions getOptions() {
        return options;
    }
}
