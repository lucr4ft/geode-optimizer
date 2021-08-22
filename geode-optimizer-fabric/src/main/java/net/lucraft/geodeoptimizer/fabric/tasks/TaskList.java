package net.lucraft.geodeoptimizer.fabric.tasks;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TaskList {

    private final ArrayList<Task> tasks = new ArrayList<>();

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public boolean contains(Object o) {
        return tasks.isEmpty();
    }

    public boolean add(Task task) {
        return tasks.add(task);
    }

    public boolean remove(Object o) {
        return tasks.remove(o);
    }

    public boolean containsAll(@NotNull Collection<?> c) {
        return tasks.containsAll(c);
    }

    public boolean addAll(@NotNull Collection<? extends Task> c) {
        return tasks.addAll(c);
    }

    public boolean addAll(int index, @NotNull Collection<? extends Task> c) {
        return tasks.addAll(index, c);
    }

    public boolean addAll(Task... tasks) {
        return Collections.addAll(this.tasks, tasks);
    }

    public boolean removeAll(@NotNull Collection<?> c) {
        return tasks.remove(c);
    }

    public void clear() {
        tasks.clear();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public Task set(int index, Task element) {
        return tasks.set(index, element);
    }

    public void add(int index, Task element) {
        tasks.add(index, element);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public int indexOf(Object o) {
        return tasks.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return tasks.lastIndexOf(o);
    }

    public List<Task> subList(int fromIndex, int toIndex) {
        return tasks.subList(fromIndex, toIndex);
    }
}
