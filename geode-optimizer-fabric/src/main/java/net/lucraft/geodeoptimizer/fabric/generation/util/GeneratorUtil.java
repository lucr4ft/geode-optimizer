package net.lucraft.geodeoptimizer.fabric.generation.util;

import net.lucraft.geodeoptimizer.fabric.util.tuples.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;

/**
 * @author Luca Lewin
 *
 * class which contains utility methods for calculation (and generation)
 */
public class GeneratorUtil {

    /**
     *
     * @param pos1 first position
     * @param pos2 second position
     * @return a BlockPos with the lowest x, y and z values from both
     * initial positions and a BlockPos with the highest x, y and z
     * values from both initial positions as a {@link Pair}
     */
    public static Pair<BlockPos, BlockPos> toMinMaxPositions(BlockPos pos1, BlockPos pos2) {
        int fromX = Math.min(pos1.getX(), pos2.getX());
        int fromY = Math.min(pos1.getY(), pos2.getY());
        int fromZ = Math.min(pos1.getZ(), pos2.getZ());
        int toX = Math.max(pos1.getX(), pos2.getX());
        int toY = Math.max(pos1.getY(), pos2.getY());
        int toZ = Math.max(pos1.getZ(), pos2.getZ());

        return Pair.of(new BlockPos(fromX, fromY, fromZ), new BlockPos(toX, toY, toZ));
    }

    /**
     *
     * @param blocks map of blocks
     * @param pos1 first position
     * @param pos2 second position
     * @return true, if only {@link Blocks#AIR} is inside the cuboid between pos1 and pos2, else false
     */
    public static boolean isCuboidEmpty(HashMap<BlockPos, BlockState> blocks, BlockPos pos1, BlockPos pos2) {
        BlockPos.Mutable pos = new BlockPos.Mutable();
        Pair<BlockPos, BlockPos> minMax = toMinMaxPositions(pos1, pos2);

        for (int x = minMax.left().getX(); x < minMax.right().getX(); x++) {
            for (int y = minMax.left().getY(); y < minMax.right().getY(); y++) {
                for (int z = minMax.left().getZ(); z < minMax.right().getZ(); z++) {
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
     * @param pos1 from position
     * @param pos2 to position
     * @return the horizontal distance between the two points
     */
    public static double calculate2DDistance(BlockPos pos1, BlockPos pos2) {
        double dx = Math.abs(pos1.getX() - pos2.getX());
        double dz = Math.abs(pos1.getZ() - pos2.getZ());
        return Math.sqrt(dx * dx+ dz * dz);
    }

    /**
     *
     * @param pos1 from position
     * @param pos2 to position
     * @return the distance between the two points
     */
    public static double calculate3DDistance(BlockPos pos1, BlockPos pos2) {
        double dx = Math.abs(pos1.getX() - pos2.getX());
        double dy = Math.abs(pos1.getY() - pos2.getY());
        double dz = Math.abs(pos1.getZ() - pos2.getZ());
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     *
     * @param blocks map of blocks
     * @return the position of the block with the lowest position
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
