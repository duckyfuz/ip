public class TaskManager {
    public Task[] tasks;
    public int tasksCount = 0;

    TaskManager(int maxTasks) {
        tasks = new Task[maxTasks];
    }

    public boolean isEmpty() {
        return tasksCount == 0;
    }
}
