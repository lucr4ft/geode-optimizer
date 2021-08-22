package net.lucraft.geodeoptimizer.fabric.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashMap;

public class GenerationContext {

    private ArrayList<BlockPos> positions;
    private HashMap<BlockPos, BlockState> blocks;

    /**
     *
     * @param positions
     * @param blocks
     */
    public GenerationContext(ArrayList<BlockPos> positions, HashMap<BlockPos, BlockState> blocks) {
        this.positions = positions;
        this.blocks = blocks;
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
}
