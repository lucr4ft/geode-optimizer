package net.lucraft.geodeoptimizer.fabric.generation.schematics;

import java.io.IOException;
import java.util.Objects;

public class Schematics {

    public static final Schematic GROWTH_COUNTER_SCHEMATIC;

    private static final ClassLoader CLASS_LOADER;

    static {
        CLASS_LOADER = Thread.currentThread().getContextClassLoader();

        GROWTH_COUNTER_SCHEMATIC = loadFromResources("growth_counter");
    }

    public static Schematic loadFromResources(String schematicName) {
        try {
            String lines = new String(Objects.requireNonNull(CLASS_LOADER.getResourceAsStream(String.format("schematics/%s.geodematic", schematicName))).readAllBytes());
            System.out.println("LINES: " + lines);

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
