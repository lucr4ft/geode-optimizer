package net.ddns.lucraft.geodeoptimizer.fabric.cmds;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.ddns.lucraft.geodeoptimizer.core.GeodeOptimizerCore;
import net.ddns.lucraft.geodeoptimizer.core.Position;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;

public class SetPositionCommand implements Command<ServerCommandSource> {

    private final GeodeOptimizerCore core = GeodeOptimizerCore.getInstance();

    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        // check if the player exists
        assert MinecraftClient.getInstance().player != null;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        // get location the player is currently standing
        BlockPos pos = player.getBlockPos();
        Position location = new Position(pos.getX(), pos.getY(), pos.getZ());
        // check if pos 1 or 2 should be updated
        if (getInteger(context,"position") == 1) {
            core.setFirstLocation(location);
            MinecraftClient.getInstance().player.sendSystemMessage(new LiteralText(
                            String.format("§afirst position set to [%d, %d, %d]",
                                    pos.getX(),
                                    pos.getY(),
                                    pos.getZ())),
                    Util.NIL_UUID);
        } else {
            core.setSecondLocation(location);
            MinecraftClient.getInstance().player.sendSystemMessage(new LiteralText(
                            String.format("§asecond position set to [%d, %d, %d]",
                                    pos.getX(),
                                    pos.getY(),
                                    pos.getZ())),
                    Util.NIL_UUID);
        }
        return 0;
    }
}
