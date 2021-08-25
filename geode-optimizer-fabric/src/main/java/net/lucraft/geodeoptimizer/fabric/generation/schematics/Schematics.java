package net.lucraft.geodeoptimizer.fabric.generation.schematics;

import net.lucraft.geodeoptimizer.fabric.generation.util.GeneratorUtil;
import net.lucraft.geodeoptimizer.fabric.util.Dimension;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Schematics {

    private static final ClassLoader CLASS_LOADER;
    public static final Schematic GROWTH_COUNTER_SCHEMATIC;

    static {
        CLASS_LOADER = Thread.currentThread().getContextClassLoader();
        GROWTH_COUNTER_SCHEMATIC = loadFromResources("growth_counter");
    }

    /**
     * @see Schematics#loadFromBytes(byte[]) 
     * @param schematicName the name of the {@link Schematic}
     * @return a {@link Schematic} loaded from the resource or {@code null} if the resource does not exist
     */
    @Nullable
    public static Schematic loadFromResources(@NotNull String schematicName) {
        try {
            byte[] b = Objects.requireNonNull(CLASS_LOADER.getResourceAsStream(String.format("schematics/%s.geodematic", schematicName))).readAllBytes();
            String data = new String(b);
            // String version = data.split("\n")[0];

            byte[] bytes = data.substring(data.indexOf("\n") + 1).getBytes(StandardCharsets.ISO_8859_1);

            return loadFromBytes(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @implNote  Uses geodematic format {@code v0.0.1} <br>
     * Take a look at the <a href="https://github.com/lucr4ft/geode-optimizer/blob/develop/docs/generation/schematics/geodeschematic-format.md">documentation</a>
     * @apiNote Important: Use {@link String#getBytes(Charset)} with {@link StandardCharsets#ISO_8859_1} for conversion from string to {@code byte[]}
     * @param bytes the {@link Schematic} as {@code byte[]}
     * @return the {@link Schematic} loaded from the {@code byte[]}
     */
    @NotNull
    public static Schematic loadFromBytes(@NotNull byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length).put(bytes).asReadOnlyBuffer();
        Dimension dimension = new Dimension(buffer.getInt(0), buffer.getInt(Integer.BYTES), buffer.getInt(2 * Integer.BYTES));
        buffer.position(3 * Integer.BYTES);

        byte[] indices = new byte[(int) (dimension.volume() * Integer.BYTES)];
        buffer.get(indices);

        ByteBuffer indicesBuffer = ByteBuffer.allocate(indices.length).put(indices);
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < indicesBuffer.capacity(); i += Integer.BYTES) {
            ints.add(indicesBuffer.getInt(i));
        }

        byte[] states = new byte[buffer.remaining()];
        buffer.get(states);

        String[] statesStrings = new String(states).split(";");
        List<BlockState> blockStates = Arrays.stream(statesStrings).map(SchematicUtil::fromString).collect(Collectors.toList());

        HashMap<BlockPos, BlockState> blocks = new HashMap<>();

        AtomicInteger index = new AtomicInteger();
        GeneratorUtil.iterate(BlockPos.ORIGIN, BlockPos.ORIGIN.add(dimension.width(), dimension.height(), dimension.depth()), pos -> {
            int i = index.getAndIncrement();
            int y;
            if ((y = ints.get(i)) != -1) {
                blocks.put(pos, blockStates.get(y));
            }
            return 0;
        });

        return new Schematic(blocks, dimension);
    }
}
