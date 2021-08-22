package net.lucraft.geodeoptimizer.fabric.tasks;

import net.lucraft.geodeoptimizer.fabric.util.GenerationContext;

public interface Task {

    String description = "";

    void run(GenerationContext generationContext);

}
