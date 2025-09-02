import java.util.Scanner;

public class Clanky {
    static String bot_name = "Clanky";
    static Scanner clankyScanner = new Scanner(System.in);
    static TaskManager taskManager = new TaskManager(100);

    public static void main(String[] args) {
        printWithSeparators("Hello! I'm " + bot_name + ".\n" + "What can I do for you?");
        executeMainLoop();
    }

    private static void executeMainLoop() {
        String command;
        while (true) {
            command = clankyScanner.nextLine().trim();

            int status = handleCommand(command);
            if (status != 0) {
                break;
            }
        }
    }

    private static int handleCommand(String command) {
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
            int userFriendlyIndex;  // one-based indexing
            try {
                userFriendlyIndex = Integer.parseInt(parser.detail);
            } catch (Throwable t) {
                userFriendlyIndex = -1;
            }

            if (userFriendlyIndex == -1 || userFriendlyIndex - 1 >= taskManager.size()) {
                handleInvalidCommand();
                break;
            }

            if (parser.action.equals("mark")) {
                taskManager.getTask(userFriendlyIndex).markAsDone();
                printWithSeparators("Nice! I've marked this task as done:\n" + taskManager.getTask(userFriendlyIndex));
            } else {
                taskManager.getTask(userFriendlyIndex).markAsNotDone();
                printWithSeparators("Ok. I've marked this task as not done yet:\n" + taskManager.getTask(userFriendlyIndex));
            }
            break;
        case "todo":
        case "deadline":
        case "event":
            handleAddTask(parser);
            printWithSeparators("added: " + taskManager.getLatestTask() + "\nNow you have " + taskManager.size() + " tasks.");
            break;
        default:
            handleInvalidCommand();
        }
        return status;
    }

    private static void handleInvalidCommand() {
        printWithSeparators("I don't understand you and it's your fault. Try again.");
    }

    private static void handleAddTask(CommandParser parser) {
        Task newTask;
        switch (parser.action) {
        case "todo":
            newTask = new ToDo(parser.detail);
            break;
        case "deadline":
            newTask = new Deadline(parser.detail, parser.dueDate);
            break;
        case "event":
            newTask = new Event(parser.detail, parser.dueDate, parser.endTime);
            break;
        default:
            handleInvalidCommand();
            return;
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
