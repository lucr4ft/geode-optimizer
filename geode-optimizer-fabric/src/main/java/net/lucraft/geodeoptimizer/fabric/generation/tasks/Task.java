package net.lucraft.geodeoptimizer.fabric.generation.tasks;

import net.lucraft.geodeoptimizer.fabric.generation.util.GenerationContext;

public interface Task {

    String description = "";

    void run(GenerationContext generationContext);

}
