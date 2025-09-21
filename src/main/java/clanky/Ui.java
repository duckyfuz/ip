package clanky;

import java.util.Scanner;

import clanky.errors.ClankyException;
import clanky.errors.MissingDetailException;
import clanky.errors.MissingDueDateException;
import clanky.errors.MissingEndTimeException;
import clanky.errors.MissingStartTimeException;
import clanky.errors.NonExistantTaskError;
import clanky.errors.UnknownCommandException;

/**
 * Handles all user interface interactions for the Clanky application.
 * Manages input reading, output display, and error message formatting.
 */
public class Ui {
    private static final String SEPARATOR_LINE = "____________________________________________________________";
    private Scanner scanner;
    private String botName;

    /**
     * Constructs a new Ui instance with the specified bot name.
     *
     * @param botName The name of the bot to display in messages.
     */
    public Ui(String botName) {
        this.scanner = new Scanner(System.in);
        this.botName = botName;
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        showMessage("Hello! I'm " + botName + ".\n" + "What can I do for you?");
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        showMessage("Bye! Don't come back.");
    }

    /**
     * Reads and returns the next line of user input.
     *
     * @return The trimmed user input string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a message to the user with decorative separator lines.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(SEPARATOR_LINE);
        System.out.println(message);
        System.out.println(SEPARATOR_LINE);
    }

    /**
     * Displays an error message based on the type of exception.
     *
     * @param e The ClankyException that occurred.
     */
    public void showError(ClankyException e) {
        if (e instanceof UnknownCommandException) {
            showMessage("I don't understand you and it's your fault. Try again with a valid command.");
        } else if (e instanceof NonExistantTaskError) {
            showMessage("That task does not exist. Learn to count before trying again.");
        } else if (e instanceof MissingDetailException) {
            showMessage("You're missing some details. Add them before trying again.");
        } else if (e instanceof MissingDueDateException) {
            showMessage("You're missing your due date. Add your due date after a /by flag.");
        } else if (e instanceof MissingStartTimeException) {
            showMessage("You're missing your start time. Add your start time after a /from flag.");
        } else if (e instanceof MissingEndTimeException) {
            showMessage("You're missing your end time. Add your end time after a /to flag.");
        } else {
            showMessage("I have no idea what you did wrong, but you did something wrong.");
        }
    }

    /**
     * Displays the list of tasks when no tasks exist.
     */
    public void showEmptyTaskList() {
        showMessage("No tasks! Go touch grass.");
    }

    /**
     * Displays the list of all tasks.
     *
     * @param taskList The formatted string containing all tasks.
     */
    public void showTaskList(String taskList) {
        showMessage(taskList);
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarkedDone(String task) {
        showMessage("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showTaskMarkedNotDone(String task) {
        showMessage("Ok. I've marked this task as not done yet:\n" + task);
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task The task that was deleted.
     */
    public void showTaskDeleted(String task) {
        showMessage("Can. I've removed this task:\n" + task);
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks after adding.
     */
    public void showTaskAdded(String task, int totalTasks) {
        showMessage("added: " + task + "\nNow you have " + totalTasks + " tasks.");
    }

    /**
     * Displays the results of a task search.
     * Shows matching tasks with their original indices from the full task list.
     *
     * @param matchingTasks The formatted string containing matching tasks with indices.
     */
    public void showSearchResults(String matchingTasks) {
        if (matchingTasks.isEmpty()) {
            showMessage("No matching tasks found.");
        } else {
            showMessage("Here are the matching tasks in your list:\n" + matchingTasks);
        }
    }
}
