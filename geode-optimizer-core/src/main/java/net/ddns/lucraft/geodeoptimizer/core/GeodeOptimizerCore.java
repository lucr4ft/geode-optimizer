package net.ddns.lucraft.geodeoptimizer.core;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 */
public class GeodeOptimizerCore {

    /**
     *
     */
    private static final GeodeOptimizerCore instance = new GeodeOptimizerCore();

    static final String NAME = "geode-optimizer-core";

    /**
     *
     * @return
     */
    public static GeodeOptimizerCore getInstance() {
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
    private GeodeOptimizerCore() {}

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
    public ArrayList<Block> generate(ArrayList<Position> positions) {
        ArrayList<Block> blocks = new ArrayList<>();
        blocks.add(new Block(pos1, Type.PISTON));
        blocks.add(new Block(pos2, Type.STICKY_PISTON));
        return blocks;
    }

    // setter

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

    public Position getPos1() {
        return pos1;
    }

    public Position getPos2() {
        return pos2;
    }
}
