package net.lucraft.geodeoptimizer.core.blocks;

import net.lucraft.geodeoptimizer.core.Identifier;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Blocks {

    private static final ArrayList<Block> blocks;

    public static final Block AIR = new AirBlock();
    public static final Block OBSERVER = new ObserverBlock();
    public static final Block PISTON = new PistonBlock();
    public static final Block BUDDING_AMETHYST = new BuddingAmethystBlock();

    static {
        blocks = new ArrayList<>();
        blocks.add(AIR);
        blocks.add(OBSERVER);
        blocks.add(PISTON);
        blocks.add(BUDDING_AMETHYST);
    }

    public static Block byId(Identifier identifier) {
        AtomicReference<Block> b = new AtomicReference<>();
        blocks.forEach(block -> {
            if (block.getIdentifier().equals(identifier)) {
                b.set(block);
            }
        });
        return b.get();
    }
}
