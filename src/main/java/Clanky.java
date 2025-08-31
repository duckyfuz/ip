import java.util.Scanner;

public class Clanky {
    static String bot_name = "Clanky";
    static Scanner clankyScanner = new Scanner(System.in);

    static ToDo[] tasks = new ToDo[100];
    static int tasksCount = 0;

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

        String[] commandParts = command.split(" ");
        String action = commandParts[0];

        switch (action) {
        case "bye":
            status = 1;
            printWithSeparators("Bye! Don't come back.");
            break;
        case "list":
            StringBuilder allTasks = new StringBuilder();
            for (int i = 0; i < tasksCount; i++) {
                allTasks.append((i + 1)).append(". ").append(tasks[i].toString());
                if (i != tasksCount - 1) {
                    allTasks.append("\n");
                }
            }
            printWithSeparators(allTasks.toString());
            break;
        case "mark":
        case "unmark":
            int taskIndex;
            try {
                taskIndex = Integer.parseInt(commandParts[1]) - 1;
            } catch (Throwable t) {
                taskIndex = -1;
            }
            
            if (taskIndex == -1 || taskIndex >= tasksCount) {
                handleInvalidCommand();
                break;
            }

            if (action.equals("mark")) {
                tasks[taskIndex].markAsDone();
                printWithSeparators("Nice! I've marked this task as done:\n" + tasks[taskIndex].toString());
            } else {
                tasks[taskIndex].markAsNotDone();
                printWithSeparators("Ok. I've marked this task as not done yet:\n" + tasks[taskIndex].toString());
            }
            break;
        case "todo":
            tasks[tasksCount] = new ToDo(command);
            tasksCount++;
            printWithSeparators("added: " + command);
            break;
        case "deadline":
            tasks[tasksCount] = new Deadline(command, "");
            tasksCount++;
            printWithSeparators("added: " + command);
            break;
        case "event":
            tasks[tasksCount] = new Event(command, "");
            tasksCount++;
            printWithSeparators("added: " + command);
            break;
        default:
            handleInvalidCommand();
        }
        return status;
    }

    private static void handleInvalidCommand() {
        printWithSeparators("I don't understand you and it's your fault. Try again.");
    }

    private static void printWithSeparators(String line) {
        String sep_line = "____________________________________________________________";
        System.out.println(sep_line);
        System.out.println(line);
        System.out.println(sep_line);
    }
}
