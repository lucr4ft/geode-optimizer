package net.lucraft.geodeoptimizer.fabric.generation.schematics;

import net.lucraft.geodeoptimizer.fabric.util.Dimension;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Luca Lewin
 */
public class Schematics {

    private static final ClassLoader CLASS_LOADER;

    public static final Schematic GROWTH_COUNTER_SCHEMATIC;
    public static final Schematic SINGLE_BUDDING_AMETHYST_LAYOUT_SCHEMATIC;
    public static final Schematic TILEABLE_COUNTER_SCHEMATIC;

    static {
        CLASS_LOADER = Thread.currentThread().getContextClassLoader();

        GROWTH_COUNTER_SCHEMATIC = loadFromResources("growth_counter");
        SINGLE_BUDDING_AMETHYST_LAYOUT_SCHEMATIC = loadFromResources("single_budding_amethyst_layout");
        TILEABLE_COUNTER_SCHEMATIC = loadFromResources("tileable_counter");
    }

    /**
     * @see Schematics#loadFromBytes(String, byte[])
     * @param schematicName the name of the {@link Schematic}
     * @return a {@link Schematic} loaded from the resource or {@code null} if the resource does not exist
     */
    @Nullable
    public static Schematic loadFromResources(@NotNull String schematicName) {
        try {
            byte[] b = Objects.requireNonNull(CLASS_LOADER.getResourceAsStream(String.format("schematics/%s.geodematic", schematicName))).readAllBytes();
            String data = new String(b, StandardCharsets.ISO_8859_1);
            // String version = data.split("\n")[0];

            byte[] bytes = data.substring(data.indexOf("\n") + 1).getBytes(StandardCharsets.ISO_8859_1);

            return loadFromBytes(schematicName, bytes);
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
     * @param name the name of the {@link Schematic}
     * @param bytes the {@link Schematic} as {@code byte[]}
     * @return the {@link Schematic} loaded from the {@code byte[]}
     */
    @NotNull
    public static Schematic loadFromBytes(@NotNull String name, byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length).put(bytes).asReadOnlyBuffer();
        Dimension dimension = Dimension.of(buffer.getInt(0), buffer.getInt(Integer.BYTES), buffer.getInt(2 * Integer.BYTES));
        buffer.position(3 * Integer.BYTES);

        byte[] indicesBytes = new byte[(int) (dimension.volume() * Integer.BYTES)];
        buffer.get(indicesBytes);
        ByteBuffer indicesBuffer = ByteBuffer.allocate(indicesBytes.length).put(indicesBytes);

        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < indicesBuffer.capacity(); i += Integer.BYTES) {
            ints.add(indicesBuffer.getInt(i));
        }

        byte[] states = new byte[buffer.remaining()];
        buffer.get(states);

        String[] statesStrings = new String(states).split(";");
        List<BlockState> blockStates = Arrays.stream(statesStrings).map(SchematicUtil::fromString).collect(Collectors.toList());

        HashMap<BlockPos, BlockState> blocks = new HashMap<>();

        int index = 0;
        for (int x = 0; x < dimension.width(); x++) {
            for (int y = 0; y < dimension.height(); y++) {
                for (int z = 0; z < dimension.depth(); z++) {
                    var pos = new BlockPos(x, y, z);
                    int j = ints.get(index++);
                    if (j != -1) {
                        if (j == 0) {
                            blocks.put(pos, Blocks.AIR.getDefaultState());
                        } else {
                            blocks.put(pos, blockStates.get(j - 1));
                        }
                    }
                }
            }
        }

        return new Schematic(name, blocks, dimension);
    }
}
