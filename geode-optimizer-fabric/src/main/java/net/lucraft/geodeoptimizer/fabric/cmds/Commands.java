package net.lucraft.geodeoptimizer.fabric.cmds;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

import static com.mojang.brigadier.arguments.BoolArgumentType.bool;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class Commands {
    public static void register() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> {
            // go pos <1-2>
            dispatcher.register(literal("go").then(literal("pos").then(argument("position", integer(1, 2)).executes(new SetPositionCommand()))));
            // go gen
            dispatcher.register(literal("go").then(literal("gen").executes(new GenerateCommand())));
            // go preview <true/false>
            dispatcher.register(literal("go").then(literal("preview").then(argument("preview", bool()).executes(new ShowPreviewCommand()))));
            // go set
            dispatcher.register(literal("go").then(literal("set").executes(new PlaceCommand())));
            // go undo
            dispatcher.register(literal("go").then(literal("undo").executes(new UndoCommand())));
            // test
            dispatcher.register(literal("go").then(literal("test").executes(new TestCommand())));
        }));
    }
}
