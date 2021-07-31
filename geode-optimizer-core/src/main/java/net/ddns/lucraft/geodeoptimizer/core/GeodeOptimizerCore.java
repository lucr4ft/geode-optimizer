package net.ddns.lucraft.geodeoptimizer.core;

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

    public Position pos1;
    public Position pos2;

    /**
     *
     */
    private GeodeOptimizerCore() {}

    public void initialize() {
        System.out.println("[" + NAME.toUpperCase() + "]: Initializing " + NAME + "...");
        System.out.println("[" + NAME.toUpperCase() + "]: Initialized " + NAME);
    }

    /**
     *
     * @param positions
     * @return
     */
    public Block[] generate(Position[] positions) {
        return new Block[] {
                new Block(pos1, Type.STICKY_PISTON),
                new Block(pos2, Type.PISTON)
        };
    }
}
