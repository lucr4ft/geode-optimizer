package net.lucraft.geodeoptimizer.core;

import net.lucraft.geodeoptimizer.core.blocks.Block;
import net.lucraft.geodeoptimizer.core.blocks.Blocks;
import net.lucraft.geodeoptimizer.core.blocks.Position;
import net.lucraft.geodeoptimizer.core.world.World;

import java.util.ArrayList;
import java.util.HashMap;

class Generator {

    private Generator() {}

    public static ArrayList<Block> generate(ArrayList<Position> positions) {
        HashMap<Position, Block> blocks = new HashMap<>();
        positions.forEach(position -> blocks.put(position, Blocks.BUDDING_AMETHYST));
        World world = new World(blocks);

        for (Position pos : positions) {
            if (isCuboidEmpty(world, pos, pos.up(4))) {
                world.setBlock(pos.up(2), Blocks.OBSERVER);
            }
        }

        blocks.values().stream().toList();

        return null;
    }

    private static boolean isCuboidEmpty(World world, Position pos1, Position pos2) {
        int fromX = Math.min(pos1.getX(), pos2.getX());
        int fromY = Math.min(pos1.getY(), pos2.getY());
        int fromZ = Math.min(pos1.getZ(), pos2.getZ());
        int toX = Math.max(pos1.getX(), pos2.getX());
        int toY = Math.max(pos1.getY(), pos2.getY());
        int toZ = Math.max(pos1.getZ(), pos2.getZ());

        for (int x = fromX; x < toX; x++) {
            for (int y = fromY; y < toY; y++) {
                for (int z = fromZ; z < toZ; z++) {
                    if (world.getBlock(new Position(x, y, z)) != Blocks.AIR)
                        return false;
                }
            }
        }
        return true;
    }

}
