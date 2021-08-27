package net.lucraft.geodeoptimizer.fabric.generation.tasks;

import net.lucraft.geodeoptimizer.fabric.generation.GenerationContext;
import net.lucraft.geodeoptimizer.fabric.generation.schematics.Schematic;
import net.lucraft.geodeoptimizer.fabric.generation.schematics.Schematics;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

/**
 * @author Luca Lewin
 */
public class GeneratePistonsTask implements Task {

	@Override
	public void run(GenerationContext context) {
		if (context.getPositions().size() == 1) {
			Schematic layout = Schematics.SINGLE_BUDDING_AMETHYST_LAYOUT_SCHEMATIC;
			assert layout != null;

			BlockPos buddingAmethystPosition = context.getPositions().get(0);
			BlockPos offset = buddingAmethystPosition.subtract(new Vec3i(1, 1, 1));

			layout.blocks().forEach((blockPos, blockState) -> context.getBlocks().put(offset.add(blockPos), blockState));

			// debug
//			context.getBlocks().forEach((blockPos, blockState) -> MinecraftClient.getInstance().world.setBlockState(blockPos, blockState));
		}
	}
}
