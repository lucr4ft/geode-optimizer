package net.lucraft.geodeoptimizer.fabric.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;

import static com.mojang.brigadier.arguments.BoolArgumentType.bool;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ShowPreviewCommand implements Command<ServerCommandSource> {

    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        return 0;
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("go").then(literal("preview")
                        .then(argument("preview", bool()).executes(new ShowPreviewCommand()))
                ).executes(new ShowPreviewCommand())
        );
    }
}
