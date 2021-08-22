package net.lucraft.geodeoptimizer.fabric;

import com.google.common.base.Stopwatch;
import net.lucraft.geodeoptimizer.fabric.exceptions.GenerationException;
import net.lucraft.geodeoptimizer.fabric.tasks.TaskList;
import net.lucraft.geodeoptimizer.fabric.util.GenerationContext;
import net.lucraft.geodeoptimizer.fabric.util.MessageUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static net.lucraft.geodeoptimizer.fabric.GeodeOptimizerFabric.PREFIX;

public class GeodeOptimizer {

    private static final GeodeOptimizer instance = new GeodeOptimizer();

    public static GeodeOptimizer getInstance() {
        return instance;
    }

    private final TaskList taskList = new TaskList();

    private BlockPos pos1, pos2;
    private final HashMap<BlockPos, BlockState> blocks = new HashMap<>();

    private GeodeOptimizer() {}

    public void initialize() {
        taskList.addAll(context -> System.out.println(),
        context -> {
            for (BlockPos pos : context.getPositions()) {
                context.getBlocks().put(BlockPos.ORIGIN, Blocks.FLOWERING_AZALEA_LEAVES.getDefaultState());
            }
        });
    }

    /**
     *
     * @throws GenerationException
     */
    public void generate() throws GenerationException {
        if (pos1 == null) {
            throw new GenerationException("first position not specified");
        } else if (pos2 == null) {
            throw new GenerationException("second position not specified");
        }

        blocks.clear();

        int fromX = Math.min(pos1.getX(), pos2.getX());
        int fromY = Math.min(pos1.getY(), pos2.getY());
        int fromZ = Math.min(pos1.getZ(), pos2.getZ());
        int toX = Math.max(pos1.getX(), pos2.getX());
        int toY = Math.max(pos1.getY(), pos2.getY());
        int toZ = Math.max(pos1.getZ(), pos2.getZ());

        World world = MinecraftClient.getInstance().world;
        BlockPos.Mutable mutableBlockPos = new BlockPos.Mutable();

        ArrayList<BlockPos> positions = new ArrayList<>();

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
                        BlockPos pos = mutableBlockPos.toImmutable();
                        positions.add(pos);
                        blocks.put(pos, Blocks.BUDDING_AMETHYST.getDefaultState());
                    }
                }
            }
        }

        if (positions.size() == 0) {
            throw new GenerationException("no budding amethyst found in specified area!");
        }

        // here comes the important part:
        // the program will loop through all budding-amethyst (ba) positions
        // and if possible it will insert observers and pistons

        // run tasks


        MessageUtil.sendMessage(new LiteralText(PREFIX + "§aStarting generation"));



        GenerationContext context = new GenerationContext(positions, blocks);
        Stopwatch watch = Stopwatch.createStarted();

        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).run(context);
            MessageUtil.sendMessage(new LiteralText(String.format(PREFIX + "§aTask %d out %d complete §7[§6%d%%§7]", i + 1, taskList.size(), (int) (100 * ((double) (i + 1) / (double) taskList.size())))));
        }

        System.out.println(blocks.get(BlockPos.ORIGIN).getBlock());

        watch.stop();

        MessageUtil.sendMessage(new LiteralText(PREFIX + String.format("§aGeneration finished in %d ms", watch.elapsed(TimeUnit.MILLISECONDS))));
        MessageUtil.sendMessage(new LiteralText(PREFIX + "§aUse §7/go preview §6true §a to show a preview of the generated layout"));
    }

    public BlockPos getPos1() {
        return pos1;
    }

    public BlockPos getPos2() {
        return pos2;
    }

    public void setPos1(BlockPos pos1) {
        this.pos1 = pos1;
    }

    public void setPos2(BlockPos pos2) {
        this.pos2 = pos2;
    }
}
