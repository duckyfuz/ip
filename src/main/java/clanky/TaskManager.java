package clanky;

public class TaskManager {
    private Task[] tasks;
    private int tasksCount = 0;

    TaskManager(int maxTasks) {
        tasks = new Task[maxTasks];
    }

    public boolean isEmpty() {
        return tasksCount == 0;
    }

    public int size() {
        return tasksCount;
    }

    public Task getLatestTask() {
        return tasks[tasksCount - 1];
    }

    public Task getTask(int index) {
        return tasks[index - 1];  // note that we use one-based indexing here
    }

    public void addTask(Task task) {
        tasks[tasksCount++] = task;
    }
}
