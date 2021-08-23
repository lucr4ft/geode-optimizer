package net.lucraft.geodeoptimizer.fabric.generation;

import com.google.common.base.Stopwatch;
import net.lucraft.geodeoptimizer.fabric.GeodeOptimizer;
import net.lucraft.geodeoptimizer.fabric.exceptions.GenerationException;
import net.lucraft.geodeoptimizer.fabric.generation.tasks.*;
import net.lucraft.geodeoptimizer.fabric.generation.util.GenerationContext;
import net.lucraft.geodeoptimizer.fabric.generation.util.GenerationOptions;
import net.lucraft.geodeoptimizer.fabric.generation.util.GeneratorUtil;
import net.lucraft.geodeoptimizer.fabric.util.MessageUtil;
import net.lucraft.geodeoptimizer.fabric.util.tuples.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static net.lucraft.geodeoptimizer.fabric.GeodeOptimizerFabric.PREFIX;

public class Generator {

    public static final Generator instance = new Generator();

    public static Generator getInstance() {
        return instance;
    }

    private final GeodeOptimizer core = GeodeOptimizer.getInstance();

    private Generator() { }

    /**
     *
     * @throws GenerationException if positions are not specified or if no budding amethyst is found between the two positions
     */
    public void generate(GenerationOptions options) throws GenerationException {
        if (core.getPos1() == null) {
            throw new GenerationException("first position not specified");
        } else if (core.getPos2() == null) {
            throw new GenerationException("second position not specified");
        }

        World world = MinecraftClient.getInstance().world;
        assert world != null;

        BlockPos.Mutable mutableBlockPos = new BlockPos.Mutable();
        ArrayList<BlockPos> positions = new ArrayList<>();
        HashMap<BlockPos, BlockState> blocks = new HashMap<>();


        // loop through every block between pos1 & pos2
        // and check if left block is left budding amethyst
        // if there is left budding amethyst, then the position
        // is retrieved and added to positions

        Pair<BlockPos, BlockPos> minMax = GeneratorUtil.toMinMaxPositions(core.getPos1(), core.getPos2());

        for (int x = minMax.left().getX(); x < minMax.right().getX(); x++) {
            for (int y = minMax.left().getY(); y < minMax.right().getY(); y++) {
                for (int z = minMax.left().getZ(); z < minMax.right().getZ(); z++) {
                    mutableBlockPos.set(x, y, z);
                    if (world.getBlockState(mutableBlockPos).getBlock() == Blocks.BUDDING_AMETHYST) {
                        BlockPos pos = mutableBlockPos.toImmutable();
                        positions.add(pos);
                        blocks.put(pos, Blocks.BUDDING_AMETHYST.getDefaultState());
                    }
                }
            }
        }

/*
        Simpler version

        GeneratorUtil.iterate(core.getPos1(), core.getPos2(), pos -> {
            if (world.getBlockState(pos).getBlock() == Blocks.BUDDING_AMETHYST) {
                positions.add(pos);
                blocks.put(pos, Blocks.BUDDING_AMETHYST.getDefaultState());
            }
            return 0;
        });
*/

        if (positions.size() == 0) {
            throw new GenerationException("no budding amethyst found in specified area!");
        }

        // load tasks
        List<Task> tasks = loadTasksFromOptions(options);

        // start generation
        MessageUtil.sendMessage(PREFIX + "§aStarting generation");

        // setup context + start timer
        GenerationContext context = new GenerationContext(positions, blocks, options);
        Stopwatch watch = Stopwatch.createStarted();

        // run tasks
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).run(context);
            MessageUtil.sendMessage(String.format(
                    PREFIX + "§aTask %d out %d complete §7[§6%d%%§7]",
                    i + 1,
                    tasks.size(),
                    (int) (100 * ((double) (i + 1) / (double) tasks.size()))));
        }

        watch.stop();

        // send generation details to player
        MessageUtil.sendMessage(PREFIX + String.format("§aGeneration finished in %d ms", watch.elapsed(TimeUnit.MILLISECONDS)));
        MessageUtil.sendMessage(PREFIX + "§aUse §7/go preview §6true §ato show a preview of the generated layout");
    }

    /**
     * Important: all tasks need to be in the right order
     * @param options the options
     * @return left list of {@link Task}s
     */
    @NotNull
    private List<Task> loadTasksFromOptions(@NotNull GenerationOptions options) {
        List<Task> tasks = new ArrayList<>();

        // growth counter
        tasks.add(new GenerateGrowthCounterTask());

        // calculate optimal afk position
        tasks.add(new CalculateAFKPositionTask());

        // collection system
        tasks.add(options.useWaterCollectionSystem() ?
                new GenerateWaterCollectionSystemTask() :
                new GenerateHopperCollectionSystemTask());

        // simple storage system
        if (options.generateStorageSystem()) {
            tasks.add(new GenerateStorageSystemTask());
        }

        return tasks;
    }

}
