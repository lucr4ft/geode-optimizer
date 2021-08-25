package net.lucraft.geodeoptimizer.fabric.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

public class CommandManager {

    /**
     *
     * @param dispatcher the command dispatcher
     * @param dedicated true if the server is a dedicated server, false if the server is an integrated server
     */
    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        SetPositionCommand.register(dispatcher);
        GenerateCommand.register(dispatcher);
        ShowPreviewCommand.register(dispatcher);

        if (!dedicated) {
            PlaceCommand.register(dispatcher);
            UndoCommand.register(dispatcher);
        }
    }
}
