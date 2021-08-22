package net.lucraft.geodeoptimizer.fabric.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;

/**
 *
 */
public class GeneratorUtil {

    /**
     *
     * @param blocks
     * @param pos1
     * @param pos2
     * @return
     */
    public static boolean isCuboidEmpty(HashMap<BlockPos, BlockState> blocks, BlockPos pos1, BlockPos pos2) {
        int fromX = Math.min(pos1.getX(), pos2.getX());
        int fromY = Math.min(pos1.getY(), pos2.getY());
        int fromZ = Math.min(pos1.getZ(), pos2.getZ());
        int toX = Math.max(pos1.getX(), pos2.getX());
        int toY = Math.max(pos1.getY(), pos2.getY());
        int toZ = Math.max(pos1.getZ(), pos2.getZ());

        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (int x = fromX; x < toX; x++) {
            for (int y = fromY; y < toY; y++) {
                for (int z = fromZ; z < toZ; z++) {
                    pos.set(x, y, z);
                    if (blocks.get(pos).getBlock() != Blocks.AIR) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     *
     * @param blocks
     * @return
     */
    public static BlockPos findLowestBlock(HashMap<BlockPos, BlockState> blocks) {
        if (blocks.isEmpty()) {
            return null;
        }
        BlockPos lowest = blocks.keySet().stream().toList().get(0);
        for (BlockPos pos : blocks.keySet()) {
            if (pos.getY() < lowest.getY()) {
                lowest = pos;
            }
        }
        return lowest;
    }
}
