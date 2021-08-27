package net.lucraft.geodeoptimizer.fabric.generation.tasks;

import net.lucraft.geodeoptimizer.fabric.GeodeOptimizerFabric;
import net.lucraft.geodeoptimizer.fabric.generation.GenerationContext;
import net.lucraft.geodeoptimizer.fabric.generation.GeneratorUtil;
import net.lucraft.geodeoptimizer.fabric.util.MessageUtil;
import net.minecraft.util.math.BlockPos;

public class CalculateAFKPositionTask implements Task {
    @Override
    public void run(GenerationContext context) {
        // calculate optimal afk position in random-tick range

        int lowX = Integer.MAX_VALUE, lowY = 256, lowZ = Integer.MAX_VALUE;
        int highX = Integer.MIN_VALUE, highY = 0, highZ = Integer.MIN_VALUE;

        for (var pos : context.getPositions()) {
            lowX = Math.min(lowX, pos.getX());
            lowY = Math.min(lowY, pos.getY());
            lowZ = Math.min(lowZ, pos.getZ());
            highX = Math.max(highX, pos.getX());
            highY = Math.max(highY, pos.getY());
            highZ = Math.max(highZ, pos.getZ());
        }

        // all budding amethysts are between low and high (inclusive)
        // they should all be in the 128 blocks random-tick range

        // bruteforce the lowest delta distance between all budding amethyst positions and all
        // block positions within [(lowX, lowY, lowZ), (highX, highY, highZ)] (inclusive)

        int lowestDeltaDistance = Integer.MAX_VALUE;
        BlockPos lowestDeltaDistancePosition = BlockPos.ORIGIN;
        BlockPos.Mutable currentPos = new BlockPos.Mutable();

        for (int x = lowX; x < highX; x++) {
            for (int y = lowY; y < highY; y++) {
                for (int z = lowZ; z < highZ; z++) {
                    int distances = 0;
                    currentPos.set(x, y, z);
                    for (var pos : context.getPositions()) {
                        distances += (int) GeneratorUtil.calculate3DDistance(pos, currentPos);
                    }
                    if (distances / context.getPositions().size() < lowestDeltaDistance) {
                        lowestDeltaDistance = distances / context.getPositions().size();
                        lowestDeltaDistancePosition = currentPos.toImmutable();
                    }
                }
            }
        }

        // Todo: implement better version -> this can be optimized
        // check if a different position is better (contains more or all blocks)

        // check if all budding amethyst are within the 128 range to the previously calculated optimal position
        for (var pos : context.getPositions()) {
            if (!pos.isWithinDistance(lowestDeltaDistancePosition, 128)) {
                MessageUtil.sendMessage(String.format("%s§4ERROR: §cbudding amethyst at [%s] does not fit into the 128 block random-tick range", GeodeOptimizerFabric.PREFIX, pos.toShortString()));
            }
        }

        BlockPos middle = GeneratorUtil.findMiddle(new BlockPos(lowX, lowY, lowZ), new BlockPos(highX, highY, highZ));

        context.setAfkPosition(GeneratorUtil.findMiddle(lowestDeltaDistancePosition, middle));
    }
}
