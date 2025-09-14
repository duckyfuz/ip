package clanky;

import java.util.ArrayList;
import clanky.tasks.Task;

public class TaskManager {
    private ArrayList<Task> tasks;

    TaskManager() {
        tasks = new ArrayList<>();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task getLatestTask() {
        return tasks.get(size() - 1);
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);  // note that we use one-based indexing here
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index - 1);  // note that this is also one-based indexing
    }
}
