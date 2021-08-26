package net.lucraft.geodeoptimizer.fabric.generation.util;

import net.lucraft.geodeoptimizer.fabric.GeodeOptimizer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class GenerationContext {

    private final List<BlockPos> positions;
    private final Map<BlockPos, BlockState> blocks;
    private final GenerationOptions options;

    private BlockPos afkPosition;

    /**
     * <p>Using this constructor {@link GenerationContext#options} will be set to {@link GenerationOptions#DEFAULT}</p>
     * @param positions a {@link List} of {@link BlockPos} positions where the block is a {@link Blocks#BUDDING_AMETHYST}
     * @param blocks a {@link Map} of blocks in the specified cuboid (with {@link GeodeOptimizer#getPos1()} and {@link GeodeOptimizer#getPos2()} as boundaries)
     */
    public GenerationContext(@NotNull List<BlockPos> positions, @NotNull Map<BlockPos, BlockState> blocks) {
        this.positions = positions;
        this.blocks = blocks;
        this.options = GenerationOptions.DEFAULT;
    }

    /**
     *
     * @param positions a {@link List} of {@link BlockPos} positions where the block is a {@link Blocks#BUDDING_AMETHYST}
     * @param blocks a {@link Map} of blocks in the specified cuboid (with {@link GeodeOptimizer#getPos1()} and {@link GeodeOptimizer#getPos2()} as boundaries)
     * @param options the {@link GenerationOptions} for the generation
     */
    public GenerationContext(@NotNull List<BlockPos> positions, @NotNull Map<BlockPos, BlockState> blocks, @NotNull GenerationOptions options) {
        this.positions = positions;
        this.blocks = blocks;
        this.options = options;
    }

    /**
     *
     * @return the {@link List} of {@link Blocks#BUDDING_AMETHYST} {@link BlockPos} positions
     */
    @NotNull
    public List<BlockPos> getPositions() {
        return positions;
    }

    /**
     *
     * @return the {@link Map} of blocks of the current generation
     */
    @NotNull
    public Map<BlockPos, BlockState> getBlocks() {
        return blocks;
    }

    /**
     *
     * @return the {@link GenerationOptions} for the current generation
     */
    @NotNull
    public GenerationOptions getOptions() {
        return options;
    }

    /**
     *
     * @return if generated, the optimal afk position for the current generation, else {@code null}
     */
    @Nullable
    public BlockPos getAfkPosition() {
        return afkPosition;
    }

    /**
     *
     * @param afkPosition the optimal afk position
     */
    public void setAfkPosition(@NotNull BlockPos afkPosition) {
        this.afkPosition = afkPosition;
    }
}
