package clanky;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;

import clanky.tasks.Task;

public class PersistenceManager {
    TaskManager taskManager;
    private static final String FILE_NAME = "tasks.txt";

    PersistenceManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    private Path getFilePath() {
        String currentDir = System.getProperty("user.dir");
        return Paths.get(currentDir, FILE_NAME);
    }

    public void loadData() {
        Path filePath = getFilePath();
        if (Files.exists(filePath)) {
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = Task.fromString(line);
                    taskManager.addTask(task);
                }
            } catch (Exception e) {
                System.out.println("Error while loading data from " + filePath);
            }
        }
    }

    public void storeData() {
        Path filePath = getFilePath();
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (int i = 1; i <= taskManager.size(); i++) {
                Task task = taskManager.getTask(i);
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error while storing data to " + filePath);
        }
    }
}
