package net.ddns.lucraft.geodeoptimizer.fabric;

import net.ddns.lucraft.geodeoptimizer.fabric.commands.CommandExecutor;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import static net.minecraft.server.command.CommandManager.literal;

/**
 *
 */
public class Registry {

    public static void registerCommand(String cmd, CommandExecutor executor) {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal(cmd).executes(context -> {
                System.out.println("foo");
                return 1;
            }));
        });
    }

    /**
     *
     * @param item
     */
    public static void registerItem(Item item) {

    }

    /**
     *
     * @param block
     */
    public static void registerBlock(Block block) {

    }

    public static void registerEvent() {}

}
