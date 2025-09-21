package clanky;

import clanky.errors.ClankyException;
import clanky.errors.MissingDetailException;
import clanky.errors.MissingDueDateException;
import clanky.errors.MissingEndTimeException;
import clanky.errors.MissingStartTimeException;
import clanky.errors.NonExistantTaskError;
import clanky.errors.UnknownCommandException;
import clanky.tasks.Deadline;
import clanky.tasks.Event;
import clanky.tasks.Task;
import clanky.tasks.ToDo;

/**
 * Main class for the Clanky task management application. 
 * Clanky is a command-line interface chatbot that allows users to manage their tasks.
 * It supports adding todos, deadlines, and events, as well as marking tasks as done/undone,
 * listing tasks, and deleting tasks. Data persistence is handled automatically.
 */
public class Clanky {
    private static final String BOT_NAME = "Clanky";
    private static TaskList taskList = new TaskList();
    private static Storage persMan = new Storage(taskList);
    private static Ui ui = new Ui(BOT_NAME);

    /**
     * Main entry point for the Clanky application.
     * Initializes the application, loads existing data, runs the main command loop,
     * and saves data before exiting.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        ui.showWelcome();
        persMan.loadData();
        executeMainLoop();
        persMan.storeData();
    }

    /**
     * Executes the main command processing loop.
     * Continuously reads user input, parses commands, and handles exceptions.
     * Loop continues until a bye command is received.
     */
    private static void executeMainLoop() {
        String command;
        while (true) {
            command = ui.readCommand();

            try {
                int status = handleCommand(command);
                if (status != 0) {
                    break;
                }
            } catch (ClankyException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Handles a single command from the user.
     * Parses the command and executes the appropriate action (list, mark, unmark, delete, or add tasks).
     *
     * @param command The raw command string input by the user.
     * @return Status code: 0 to continue, 1 to exit the application.
     * @throws ClankyException If there is an error processing the command.
     */
    private static int handleCommand(String command) throws ClankyException {
        int status = 0;

        Parser parser = new Parser(command);
        parser.parseCommand();

        switch (parser.action) {
        case "bye":
            status = 1;
            ui.showGoodbye();
            break;
        case "list":
            if (taskList.isEmpty()) {
                ui.showEmptyTaskList();
                break;
            }
            StringBuilder allTasks = new StringBuilder();
            for (int i = 1; i <= taskList.size(); i++) {
                allTasks.append((i)).append(". ").append(taskList.getTask(i));
                if (i != taskList.size()) {
                    allTasks.append("\n");
                }
            }
            ui.showTaskList(allTasks.toString());
            break;
        case "mark":
        case "unmark":
        case "delete":
            int userFriendlyIndex;  // one-based indexing
            try {
                userFriendlyIndex = Integer.parseInt(parser.detail);
            } catch (Throwable t) {
                userFriendlyIndex = -1;
            }

            if (userFriendlyIndex == -1 || userFriendlyIndex - 1 >= taskList.size()) {
                throw new NonExistantTaskError();
            }

            if (parser.action.equals("mark")) {
                taskList.getTask(userFriendlyIndex).markAsDone();
                ui.showTaskMarkedDone(taskList.getTask(userFriendlyIndex).toString());
            } else if (parser.action.equals("unmark")) {
                taskList.getTask(userFriendlyIndex).markAsNotDone();
                ui.showTaskMarkedNotDone(taskList.getTask(userFriendlyIndex).toString());
            } else if (parser.action.equals("delete")) {
                Task deletedTask = taskList.getTask(userFriendlyIndex);
                taskList.removeTask(userFriendlyIndex);
                ui.showTaskDeleted(deletedTask.toString());
            } else {
                throw new UnknownCommandException();
            }
            break;
        case "todo":
        case "deadline":
        case "event":
            handleAddTask(parser);
            ui.showTaskAdded(taskList.getLatestTask().toString(), taskList.size());
            break;
        default:
            throw new UnknownCommandException();
        }
        return status;
    }

    /**
     * Handles adding a new task based on the parsed command.
     * Creates the appropriate task type (ToDo, Deadline, or Event) and adds it to the task manager.
     *
     * @param parser The CommandParser containing the parsed command details.
     * @throws ClankyException If required task details are missing or the command is invalid.
     */
    private static void handleAddTask(Parser parser) throws ClankyException {
        if (parser.detail.isEmpty()) {
            throw new MissingDetailException();
        }
        Task newTask;
        switch (parser.action) {
        case "todo":
            newTask = new ToDo(parser.detail);
            break;
        case "deadline":
            if (parser.dueDate.isEmpty()) {
                throw new MissingDueDateException();
            }
            newTask = new Deadline(parser.detail, parser.dueDate);
            break;
        case "event":
            if (parser.startTime.isEmpty()) {
                throw new MissingStartTimeException();
            } else if (parser.endTime.isEmpty()) {
                throw new MissingEndTimeException();
            }
            newTask = new Event(parser.detail, parser.startTime, parser.endTime);
            break;
        default:
            throw new UnknownCommandException();
        }
        taskList.addTask(newTask);
    }
}
