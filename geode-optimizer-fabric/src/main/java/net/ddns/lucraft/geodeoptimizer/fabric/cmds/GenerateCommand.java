package net.ddns.lucraft.geodeoptimizer.fabric.cmds;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.ddns.lucraft.geodeoptimizer.core.Block;
import net.ddns.lucraft.geodeoptimizer.core.GeodeOptimizerCore;
import net.ddns.lucraft.geodeoptimizer.core.Position;
import net.ddns.lucraft.geodeoptimizer.fabric.renderer.BlockPreviewRenderer;
import net.ddns.lucraft.geodeoptimizer.fabric.util.BlockConverter;
import net.ddns.lucraft.geodeoptimizer.fabric.util.Converter;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class GenerateCommand implements Command<ServerCommandSource> {

    private final GeodeOptimizerCore core = GeodeOptimizerCore.getInstance();

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        // calculate coordinates
        Position pos1 = core.getPos1();
        Position pos2 = core.getPos2();

        int fromX = Math.min(pos1.getX(), pos2.getX());
        int fromY = Math.min(pos1.getY(), pos2.getY());
        int fromZ = Math.min(pos1.getZ(), pos2.getZ());
        int toX = Math.max(pos1.getX(), pos2.getX());
        int toY = Math.max(pos1.getY(), pos2.getY());
        int toZ = Math.max(pos1.getZ(), pos2.getZ());

        World world = MinecraftClient.getInstance().world;
        BlockPos.Mutable mutableBlockPos = new BlockPos.Mutable();
        ArrayList<Position> positions = new ArrayList<>();

        // world could be null
        assert world != null;

        // loop through every block between pos1 & pos2
        // and check if a block is a budding amethyst
        // if there is a budding amethyst, then the position
        // is retrieved and added to positions
        for (int x = fromX; x < toX; x++) {
            for (int y = fromY; y < toY; y++) {
                for (int z = fromZ; z < toZ; z++) {
                    mutableBlockPos.set(x, y, z);
                    if (world.getBlockState(mutableBlockPos).getBlock() == Blocks.BUDDING_AMETHYST) {
                        positions.add(new Position(x, y, z));
                    }
                }
            }
        }

        // call generate method in GeodeOptimizerCore
        ArrayList<Block> blocks = core.generate(positions);

        // convert Block to BlockPos + BlockState
        // then render a semitransparent preview for the client

        // the rendered block is client-side only!
        for (Block block : blocks) {
            BlockPos pos = Converter.convertPositionToBlockPos(block.getPosition());
            BlockState state = BlockConverter.convertBlockToBlockState(block);

            BlockPreviewRenderer.getInstance().renderBlock(pos, state);
        }

        return 0;
    }
}
