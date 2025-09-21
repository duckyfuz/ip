package clanky;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;

import clanky.tasks.Task;

/**
 * Handles data persistence for the Clanky application.
 * Manages loading tasks from and saving tasks to a file in the current directory.
 * Uses the tasks.txt file for storage.
 */
public class Storage {
    TaskList taskList;
    private static final String FILE_NAME = "tasks.txt";

    /**
     * Constructs a PersistenceManager associated with the given TaskManager.
     *
     * @param taskList The TaskManager instance to load data into and save data from.
     */
    Storage(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the file path for the tasks storage file in the current directory.
     *
     * @return Path object pointing to the tasks.txt file.
     */
    private Path getFilePath() {
        String currentDir = System.getProperty("user.dir");
        return Paths.get(currentDir, FILE_NAME);
    }

    /**
     * Loads task data from the storage file into the TaskManager.
     * If the file doesn't exist, no tasks are loaded.
     * Prints an error message if loading fails.
     */
    public void loadData() {
        Path filePath = getFilePath();
        if (Files.exists(filePath)) {
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = Task.fromString(line);
                    taskList.addTask(task);
                }
            } catch (Exception e) {
                System.out.println("Error while loading data from " + filePath);
            }
        }
    }

    /**
     * Saves all tasks from the TaskManager to the storage file.
     * Each task is written as a separate line using its string representation.
     * Prints an error message if saving fails.
     */
    public void storeData() {
        Path filePath = getFilePath();
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (int i = 1; i <= taskList.size(); i++) {
                Task task = taskList.getTask(i);
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error while storing data to " + filePath);
        }
    }
}
