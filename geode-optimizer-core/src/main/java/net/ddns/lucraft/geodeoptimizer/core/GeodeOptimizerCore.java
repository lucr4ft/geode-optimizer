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

    /**
     *
     */
    private GeodeOptimizerCore() {}

    public void initialize() {
        System.out.println("[" + NAME.toUpperCase() + "]: Initializing " + NAME + "...");
        System.out.println("[" + NAME.toUpperCase() + "]: Initialized " + NAME);
    }

    public void disable() {

    }
}
