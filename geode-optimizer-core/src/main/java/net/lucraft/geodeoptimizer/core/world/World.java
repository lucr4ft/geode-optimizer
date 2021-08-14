package net.lucraft.geodeoptimizer.core.world;

import net.lucraft.geodeoptimizer.core.blocks.Block;
import net.lucraft.geodeoptimizer.core.blocks.Blocks;
import net.lucraft.geodeoptimizer.core.blocks.Position;

import java.util.HashMap;

public class World {

    private HashMap<Position, Block> blocks;

    public World() {
        blocks = new HashMap<>();
    }

    public World(HashMap<Position, Block> blocks) {
        this.blocks = blocks;
    }

    public Block getBlock(Position pos) {
        Block b = blocks.get(pos);
        return b == null ? Blocks.AIR : b;
    }

    public void setBlock(Position pos, Block block) {
        blocks.put(pos, block);
    }
}
