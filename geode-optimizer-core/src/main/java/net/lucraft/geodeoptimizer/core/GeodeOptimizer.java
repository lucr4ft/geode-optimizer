package net.lucraft.geodeoptimizer.core;

import net.lucraft.geodeoptimizer.core.blocks.Block;
import net.lucraft.geodeoptimizer.core.blocks.Position;
import net.lucraft.geodeoptimizer.core.exceptions.GenerationException;

import java.util.ArrayList;

/**
 *
 */
public class GeodeOptimizer {

    /**
     *
     */
    private static final GeodeOptimizer instance = new GeodeOptimizer();

    static final String NAME = "geode-optimizer-core";

    /**
     *
     * @return
     */
    public static GeodeOptimizer getInstance() {
        return instance;
    }

    // private fields

    private Position pos1 = null;
    private Position pos2 = null;

    // public fields

    public boolean showPreview = false;

    // constructor

    /**
     *
     */
    private GeodeOptimizer() {}

    // public methods

    /**
     *
     */
    public void initialize() {
        System.out.println("[" + NAME.toUpperCase() + "]: Initializing " + NAME + "...");
        System.out.println("[" + NAME.toUpperCase() + "]: Initialized " + NAME);
    }

    /**
     *
     * @param positions
     * @return
     */
    public ArrayList<Block> generate(ArrayList<Position> positions) throws GenerationException {
        // they should never be null, but check to prevent unexpected errors
        if (pos1 == null) {
            throw new GenerationException("§cerror: first position not set!");
        } else if (pos2 == null) {
            throw new GenerationException("§cerror: second position not set!");
        }

        if (positions.size() == 0) {
            throw new GenerationException("§ewarning: no budding amethysts between " + pos1 + " and " + pos2);
        }

        return Generator.generate(positions);
    }

    /**
     *
     * @param pos
     */
    public void setFirstLocation(Position pos) {
        pos1 = pos;
    }

    /**
     *
     * @param pos
     */
    public void setSecondLocation(Position pos) {
        pos2 = pos;
    }

    // getter

    /**
     *
     * @return
     */
    public Position getPos1() {
        return pos1;
    }

    /**
     *
     * @return
     */
    public Position getPos2() {
        return pos2;
    }
}
