package net.lucraft.geodeoptimizer.fabric;

import net.lucraft.geodeoptimizer.fabric.cmds.Commands;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class GeodeOptimizerFabric implements ModInitializer {

    public static final String MOD_ID = "geode-optimizer-fabric";
    public static final String PREFIX = "§7[§6GeodeOptimizer§7] ";

    public static final Identifier GO_SET_CMD_PACKET_ID = new Identifier(MOD_ID, "packet/set_cmd");
    public static final Identifier GO_UNDO_CMD_PACKET_ID = new Identifier(MOD_ID, "packet/undo_cmd");

    @Override
    public void onInitialize() {
        System.out.println("[" + MOD_ID.toUpperCase() + "]: Initializing...");

        GeodeOptimizer.getInstance().initialize();

        Commands.register();

        System.out.println("[" + MOD_ID.toUpperCase() + "]: Initialized");
    }
}
