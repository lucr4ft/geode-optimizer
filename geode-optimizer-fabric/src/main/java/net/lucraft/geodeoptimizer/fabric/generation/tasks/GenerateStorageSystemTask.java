package net.lucraft.geodeoptimizer.fabric.generation.tasks;

import net.lucraft.geodeoptimizer.fabric.generation.util.GenerationContext;

public class GenerateStorageSystemTask implements Task {
    @Override
    public void run(GenerationContext context) {
        if (!context.getOptions().generateStorageSystem())
            return;
        // generate simple + expandable storage system
    }
}
