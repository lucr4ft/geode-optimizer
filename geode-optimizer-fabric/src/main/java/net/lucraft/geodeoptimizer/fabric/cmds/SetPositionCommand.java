package net.lucraft.geodeoptimizer.fabric.cmds;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.lucraft.geodeoptimizer.fabric.GeodeOptimizer;
import net.lucraft.geodeoptimizer.fabric.util.MessageUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;

public class SetPositionCommand implements Command<ServerCommandSource> {

    private final GeodeOptimizer core = GeodeOptimizer.getInstance();

    @SuppressWarnings("SpellCheckingInspection")
    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        // check if the player exists
        assert MinecraftClient.getInstance().player != null;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        // get location the player is currently standing
        BlockPos pos = player.getBlockPos();

        // check if pos 1 or 2 should be updated
        if (getInteger(context,"position") == 1) {
            core.setPos1(pos);
            MessageUtil.sendMessage(String.format("§afirst position set to [%d, %d, %d]",
                    pos.getX(),
                    pos.getY(),
                    pos.getZ()));
        } else {
            core.setPos2(pos);
            MessageUtil.sendMessage(String.format("§asecond position set to [%d, %d, %d]",
                    pos.getX(),
                    pos.getY(),
                    pos.getZ()));
        }

        return SINGLE_SUCCESS;
    }
}
