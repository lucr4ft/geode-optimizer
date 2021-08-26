package net.lucraft.geodeoptimizer.fabric.generation.schematics;

import net.lucraft.geodeoptimizer.fabric.util.Dimension;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

public record Schematic(String name, Map<BlockPos, BlockState> blocks, Dimension dimension) {

    /**
     * @param world the world to place the schematic in
     * @param pos   the position where to place the schematic
     */
    public void place(World world, BlockPos pos) {
        blocks.forEach((key, value) -> world.setBlockState(pos.add(key), value));
    }

    /**
     * @return the name of the {@link Schematic}
     */
    public String getName() {
        return name;
    }

    /**
     * @return the blocks contained in the {@link Schematic}
     */
    public Map<BlockPos, BlockState> getBlocks() {
        return blocks;
    }

    /**
     * @return the dimension of the schematic
     */
    public Dimension getDimension() {
        return dimension;
    }

}
