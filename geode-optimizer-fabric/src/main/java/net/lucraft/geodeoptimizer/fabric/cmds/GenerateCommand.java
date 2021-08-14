package net.lucraft.geodeoptimizer.fabric.cmds;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.lucraft.geodeoptimizer.fabric.GeodeOptimizer;
import net.lucraft.geodeoptimizer.fabric.exceptions.GenerationException;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class GenerateCommand implements Command<ServerCommandSource> {

    private final GeodeOptimizer core = GeodeOptimizer.getInstance();

    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        // call generate method in GeodeOptimizerCore
        try {
            core.generate();
        } catch (GenerationException e) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendSystemMessage(new LiteralText(e.getMessage()), Util.NIL_UUID);
        }
        return 0;
    }
}
