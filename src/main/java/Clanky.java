import java.util.Scanner;

public class Clanky {
    static String bot_name = "Clanky";
    static String sep_line = "____________________________________________________________";
    static Scanner clankyScanner = new Scanner(System.in);

    static Task[] tasks = new Task[100];
    static int tasksCount = 0;

    public static void main(String[] args) {
        System.out.println(sep_line);
        System.out.println("Hello! I'm " + bot_name + ".");
        System.out.println("What can I do for you?");
        System.out.println(sep_line);
        executeMainLoop();
    }

    private static void executeMainLoop() {
        String command;
        while (true) {
            command = clankyScanner.nextLine();

            int status = handleCommand(command);
            if (status != 0) {
                break;
            }
        }
    }

    private static int handleCommand(String command) {
        int status = 0;
        switch (command) {
        case "bye":
            status = 1;
            System.out.println(sep_line);
            System.out.println("Bye! Don't come back.");
            System.out.println(sep_line);
            break;
        case "list":
            System.out.println(sep_line);
            for (int i = 0; i < tasksCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i].getDescriptionWithStatusIcon());
            }
            System.out.println(sep_line);
            break;
        default:
            tasks[tasksCount] = new Task(command);
            tasksCount++;
            System.out.println(sep_line);
            System.out.println("added: " + command);
            System.out.println(sep_line);
        }
        return status;
    }
}
