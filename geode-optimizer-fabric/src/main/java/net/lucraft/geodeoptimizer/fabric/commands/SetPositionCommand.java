package net.lucraft.geodeoptimizer.fabric.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.LiteralMessage;
import net.lucraft.geodeoptimizer.fabric.GeodeOptimizer;
import net.lucraft.geodeoptimizer.fabric.util.MessageUtil;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.BlockPos;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.command.argument.BlockPosArgumentType.blockPos;
import static net.minecraft.command.argument.BlockPosArgumentType.getLoadedBlockPos;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class SetPositionCommand {

    /**
     *
     * @param dispatcher the command dispatcher
     */
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("go").then(literal("pos")
                .then(argument("position", integer(1, 2))
                        .suggests((context, builder) -> builder.suggest(1, new LiteralMessage("set the first position"))
                                .suggest(2, new LiteralMessage("set the second position")).buildFuture())
                        .executes(context -> execute(getInteger(context, "position"), context.getSource().getPlayer().getBlockPos()))
                        .then(argument("block_pos", blockPos())
                                .executes(context -> execute(getInteger(context, "position"), getLoadedBlockPos(context, "block_pos")))))));
    }

    /**
     *
     * @param set set pos 1 or 2 (range[1,2])
     * @param pos the position to be set
     * @return {@link Command#SINGLE_SUCCESS}
     */
    private static int execute(int set, BlockPos pos) {
        if (set == 1) {
            GeodeOptimizer.getInstance().setPos1(pos);
            MessageUtil.sendMessage(String.format("§afirst position set to [%s]", pos.toShortString()));
        } else {
            GeodeOptimizer.getInstance().setPos2(pos);
            MessageUtil.sendMessage(String.format("§asecond position set to [%s]", pos.toShortString()));
        }
        return Command.SINGLE_SUCCESS;
    }
}
