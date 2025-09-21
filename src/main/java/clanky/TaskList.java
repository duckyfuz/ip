package clanky;

import java.util.ArrayList;
import clanky.tasks.Task;

/**
 * Manages a collection of tasks for the Clanky application.
 * Provides methods to add, remove, retrieve, and query tasks.
 * Uses one-based indexing for user-friendly task numbering.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskManager with an empty task list.
     */
    TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if there are no tasks, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The total number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the most recently added task.
     *
     * @return The latest task in the list.
     */
    public Task getLatestTask() {
        return tasks.get(size() - 1);
    }

    /**
     * Returns the task at the specified one-based index.
     *
     * @param index The one-based index of the task to retrieve.
     * @return The task at the specified position.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);  // note that we use one-based indexing here
    }

    /**
     * Adds a new task to the end of the task list.
     *
     * @param task The task to add to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified one-based index.
     *
     * @param index The one-based index of the task to remove.
     */
    public void removeTask(int index) {
        tasks.remove(index - 1);  // note that this is also one-based indexing
    }

    /**
     * Finds all tasks that contain the specified keyword in their description.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks that match the search criteria.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Finds all tasks that contain the specified keyword and returns them with their indices.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A formatted string with matching tasks and their indices.
     */
    public String findTasksWithIndices(String keyword) {
        StringBuilder result = new StringBuilder();
        boolean foundAny = false;
        
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                if (foundAny) {
                    result.append("\n");
                }
                result.append((i + 1)).append(".").append(task.toString());
                foundAny = true;
            }
        }
        
        return result.toString();
    }
}
