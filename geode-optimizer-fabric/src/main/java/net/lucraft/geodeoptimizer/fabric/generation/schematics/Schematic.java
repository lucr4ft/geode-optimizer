package net.lucraft.geodeoptimizer.fabric.generation.schematics;

import net.lucraft.geodeoptimizer.fabric.generation.util.GeneratorUtil;
import net.lucraft.geodeoptimizer.fabric.util.Dimension;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Schematic {

    private final Map<BlockPos, BlockState> blocks;
    private final Dimension dimension;

    public Schematic(Map<BlockPos, BlockState> blocks, Dimension dimension) {
        this.blocks = blocks;
        this.dimension = dimension;
    }

    /**
     *
     * @param world the world to place the schematic in
     * @param pos the position where to place the schematic
     */
    public void place(World world, BlockPos pos) {
        for (Map.Entry<BlockPos, BlockState> entry : blocks.entrySet()) {
            world.setBlockState(pos.add(entry.getKey()), entry.getValue());
        }
    }

    /**
     * TODO / FIXME: properly implement this
     * <p>the schematic will be saved in {@code resources/schematics/$name.geodematic}</p>
     * @param name the name of the schematic
     */
    private String save(String name) {
        int width = 0, height = 0, depth = 0;
        for (BlockPos pos : blocks.keySet()) {
            width = Math.max(width, pos.getX());
            height = Math.max(height, pos.getY());
            depth = Math.max(depth, pos.getZ());
        }
        width++; height++; depth++;

        // get number of unique blockstates and store each unique in this list
        List<BlockState> uniqueBlockStates = blocks.values().stream().distinct().toList();

        // convert blockstates to bytes
        byte[] states = uniqueBlockStates.stream()
                .map(SchematicUtil::toString)
                .collect(Collectors.joining(";"))
                .getBytes(StandardCharsets.ISO_8859_1);

        ByteBuffer buffer = ByteBuffer.allocate((3 + (width * height * depth)) * Integer.BYTES + states.length)
                .putInt(width).putInt(height).putInt(depth);

        // create indices
        GeneratorUtil.iterate(BlockPos.ORIGIN, BlockPos.ORIGIN.add(width, height, depth), pos -> {
            if (blocks.containsKey(pos)) {
                buffer.putInt(uniqueBlockStates.indexOf(blocks.get(pos)));
            } else {
                buffer.putInt(-1);
            }
            return 0;
        });

        // add states to byte buffer
        buffer.put(states);

        return "version=0.0.1\n" + new String(buffer.array(), StandardCharsets.ISO_8859_1);
    }

    /**
     *
     * @return the dimension of the schematic
     */
    public Dimension getDimension() {
        return dimension;
    }

}
