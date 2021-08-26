package net.lucraft.geodeoptimizer.fabric.generation.schematics;

import net.lucraft.geodeoptimizer.fabric.GeodeOptimizer;
import net.lucraft.geodeoptimizer.fabric.generation.util.GeneratorUtil;
import net.lucraft.geodeoptimizer.fabric.util.Dimension;
import net.lucraft.geodeoptimizer.fabric.util.MessageUtil;
import net.lucraft.geodeoptimizer.fabric.util.tuples.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static net.lucraft.geodeoptimizer.fabric.GeodeOptimizerFabric.PREFIX;

/**
 * @apiNote use this class and its methods <b>ONLY</b> for development and <b>NOT</b> deployment
 */
public class SchematicManager {

    public static final List<Schematic> LOADED_SCHEMATICS = new ArrayList<>();

    /**
     *
     * @param name the name of the {@link Schematic}
     */
    public static void load(@NotNull String name) {
        Schematic schematic = Schematics.loadFromResources(name);
        assert schematic != null;
        schematic.getBlocks().forEach((blockPos, blockState) -> System.out.println(blockPos.toShortString() + " | " + blockState));
        MessageUtil.sendMessage("loaded schematic " + schematic.getName());
        LOADED_SCHEMATICS.add(schematic);
    }

    /**
     *
     * @param name the name of the {@link Schematic}
     * @param path the {@link Path} of the file, where the {@link Schematic} should be stored
     * @throws IOException if the path string cannot be converted to a {@link Path}
     */
    public static void save(@NotNull String name, @NotNull Path path) throws IOException {
        Schematic schematic = null;

        for (Schematic s : LOADED_SCHEMATICS) {
            if (s.getName().equals(name)) {
                schematic = s;
            }
        }

        if (schematic == null) {
            MessageUtil.sendMessage(String.format("%s§4Error:§c Schematic with name §7'§c%s§7'§c does not exist!", PREFIX, name));
            return;
        }

        // get number of unique blockstates and store each unique in this list
        List<BlockState> uniqueBlockStates = schematic.getBlocks().values().stream().distinct().toList();

        // convert blockstates to bytes
        byte[] states = uniqueBlockStates.stream()
                .filter(blockState -> !blockState.equals(Blocks.AIR.getDefaultState())) // remove air block
                .map(SchematicUtil::toString)
                .collect(Collectors.joining(";"))
                .getBytes(StandardCharsets.ISO_8859_1);


        int width = schematic.getDimension().width();
        int height = schematic.getDimension().height();
        int depth = schematic.getDimension().depth();

        ByteBuffer buffer = ByteBuffer.allocate((3 + (width * height * depth)) * Integer.BYTES + states.length)
                .putInt(width).putInt(height).putInt(depth);

        // create indices
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    var pos = new BlockPos(x, y, z);
                    if (schematic.getBlocks().containsKey(pos)) {
                        if (!schematic.getBlocks().get(pos).equals(Blocks.AIR.getDefaultState())) {
                            buffer.putInt(uniqueBlockStates.indexOf(schematic.getBlocks().get(pos)));
                        } else {
                            buffer.putInt(0);
                        }
                    } else {
                        buffer.putInt(-1);
                    }
                }
            }
        }

        // add states to byte buffer
        buffer.put(states);

        if (!Files.exists(path))
            Files.createDirectories(path);
        Files.writeString(Path.of("%s\\%s.geodematic".formatted(path.toAbsolutePath(), name)), "version=0.0.1\n" + new String(buffer.array(), StandardCharsets.ISO_8859_1), StandardCharsets.ISO_8859_1);

        MessageUtil.sendMessage(String.format("%s§aSaved schematic §7'§a%s§7'§a to %s", PREFIX, schematic.getName(), path.toAbsolutePath()));
    }

    /**
     * @apiNote only use for development
     * @param name the name of the {@link Schematic}
     * @throws IOException if the path string cannot be converted to a {@link Path}
     */
    public static void save(@NotNull String name) throws IOException {
        save(name, Path.of("C:\\geodeoptimizer\\schematics"));
    }

    /**
     *
     * @param name the name of the new {@link Schematic}
     */
    public static void create(@NotNull String name) {
        if (GeodeOptimizer.getInstance().getPos1() == null || GeodeOptimizer.getInstance().getPos2() == null) {
            MessageUtil.sendMessage("%s§4Error:§c Position one and/or two has not been set".formatted(PREFIX));
            return;
        }

        Pair<BlockPos, BlockPos> minMaxPositions = GeneratorUtil.toMinMaxPositions(GeodeOptimizer.getInstance().getPos1(), GeodeOptimizer.getInstance().getPos2());

        HashMap<BlockPos, BlockState> blocks = new HashMap<>();
        Dimension dim = Dimension.of(
                minMaxPositions.right().getX() - minMaxPositions.left().getX() + 1,
                minMaxPositions.right().getY() - minMaxPositions.left().getY() + 1,
                minMaxPositions.right().getZ() - minMaxPositions.left().getZ() + 1);

        World world = MinecraftClient.getInstance().world;
        assert world != null;

        GeneratorUtil.iterate(minMaxPositions.left(), minMaxPositions.left().add(dim.width(), dim.height(), dim.depth()), pos -> {
            blocks.put(pos.subtract(minMaxPositions.left()), world.getBlockState(pos));
            return 0;
        });

        LOADED_SCHEMATICS.add(new Schematic(name, blocks, dim));
        MessageUtil.sendMessage("%s§aCreated schematic with name §7'§a%s§7'§a".formatted(PREFIX, name));
    }

    /**
     *
     * @param name the name of the {@link Schematic}
     * @param position the {@link BlockPos} where to place the {@link Schematic}
     */
    public static void place(@NotNull String name, @NotNull BlockPos position) {
        Optional<Schematic> optional = LOADED_SCHEMATICS.stream().filter(schematic -> schematic.getName().equals(name)).findFirst();
        if (optional.isEmpty()) {
            return;
        }
        Schematic schematic = optional.get();
        schematic.place(MinecraftClient.getInstance().world, position);
    }

}
