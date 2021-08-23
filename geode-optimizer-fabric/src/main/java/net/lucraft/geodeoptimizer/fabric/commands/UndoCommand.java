package net.lucraft.geodeoptimizer.fabric.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

public class UndoCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        return 0;
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("go").then(literal("undo").requires((source) -> {
            try {
                return source.getPlayer().isCreative();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return false;
            }
        }).executes(new UndoCommand())));
    }
}
