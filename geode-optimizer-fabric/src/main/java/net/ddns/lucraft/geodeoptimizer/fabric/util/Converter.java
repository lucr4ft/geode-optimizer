package net.ddns.lucraft.geodeoptimizer.fabric.util;

import net.ddns.lucraft.geodeoptimizer.core.Position;
import net.minecraft.util.math.BlockPos;

public class Converter {

    public static BlockPos convertPositionToBlockPos(Position pos) {
        return new BlockPos(pos.getX(), pos.getY(), pos.getZ());
    }

}
