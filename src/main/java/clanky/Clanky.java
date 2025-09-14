package clanky;

import java.util.Scanner;

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

public class Clanky {
    static String bot_name = "Clanky";
    static Scanner clankyScanner = new Scanner(System.in);
    static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        printWithSeparators("Hello! I'm " + bot_name + ".\n" + "What can I do for you?");
        executeMainLoop();
    }

    private static void executeMainLoop() {
        String command;
        while (true) {
            command = clankyScanner.nextLine().trim();

            try {
                int status = handleCommand(command);
                if (status != 0) {
                    break;
                }
            } catch (UnknownCommandException e) {
                printWithSeparators("I don't understand you and it's your fault. Try again with a valid command.");
            } catch (NonExistantTaskError e) {
                printWithSeparators("That task does not exist. Learn to count before trying again.");
            } catch (MissingDetailException e) {
                printWithSeparators("You're missing some details. Add them before trying again.");
            } catch (MissingDueDateException e) {
                printWithSeparators("You're missing your due date. Add your due date after a /by flag.");
            } catch (MissingStartTimeException e) {
                printWithSeparators("You're missing your start time. Add your start time after a /from flag.");
            } catch (MissingEndTimeException e) {
                printWithSeparators("You're missing your end time. Add your end time after a /to flag.");
            } catch (ClankyException e) {
                printWithSeparators("I have no idea what you did wrong, but you did something wrong.");
            }
        }
    }

    private static int handleCommand(String command) throws ClankyException {
        int status = 0;

        CommandParser parser = new CommandParser(command);
        parser.parseCommand();

        switch (parser.action) {
        case "bye":
            status = 1;
            printWithSeparators("Bye! Don't come back.");
            break;
        case "list":
            if (taskManager.isEmpty()) {
                printWithSeparators("No tasks! Go touch grass.");
                break;
            }
            StringBuilder allTasks = new StringBuilder();
            for (int i = 1; i <= taskManager.size(); i++) {
                allTasks.append((i)).append(". ").append(taskManager.getTask(i));
                if (i != taskManager.size()) {
                    allTasks.append("\n");
                }
            }
            printWithSeparators(allTasks.toString());
            break;
        case "mark":
        case "unmark":
        case "delete":
            System.out.println(parser.action);
            int userFriendlyIndex;  // one-based indexing
            try {
                userFriendlyIndex = Integer.parseInt(parser.detail);
            } catch (Throwable t) {
                userFriendlyIndex = -1;
            }

            if (userFriendlyIndex == -1 || userFriendlyIndex - 1 >= taskManager.size()) {
                throw new NonExistantTaskError();
            }

            if (parser.action.equals("mark")) {
                taskManager.getTask(userFriendlyIndex).markAsDone();
                printWithSeparators("Nice! I've marked this task as done:\n" + taskManager.getTask(userFriendlyIndex));
            } else if (parser.action.equals("unmark")) {
                taskManager.getTask(userFriendlyIndex).markAsNotDone();
                printWithSeparators("Ok. I've marked this task as not done yet:\n" + taskManager.getTask(userFriendlyIndex));
            } else if (parser.action.equals("delete")) {
                Task deletedTask = taskManager.getTask(userFriendlyIndex);
                taskManager.removeTask(userFriendlyIndex);
                printWithSeparators("Can. I've removed this task:\n" + deletedTask);
            } else {
                throw new UnknownCommandException();
            }
            break;
        case "todo":
        case "deadline":
        case "event":
            handleAddTask(parser);
            printWithSeparators("added: " + taskManager.getLatestTask() + "\nNow you have " + taskManager.size() + " tasks.");
            break;
        default:
            throw new UnknownCommandException();
        }
        return status;
    }

    private static void handleAddTask(CommandParser parser) throws ClankyException {
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
        taskManager.addTask(newTask);
    }

    private static void printWithSeparators(String line) {
        String sep_line = "____________________________________________________________";
        System.out.println(sep_line);
        System.out.println(line);
        System.out.println(sep_line);
    }
}
