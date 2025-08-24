import java.util.Scanner;

public class Clanky {
    static String bot_name = "Clanky";
    static String sep_line = "____________________________________________________________";
    static Scanner clankyScanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(sep_line);
        System.out.println("Hello! I'm " + bot_name + ".");
        System.out.println("What can I do for you?");
        System.out.println(sep_line);
        executeMainLoop();
        exitChat();
    }

    private static void exitChat() {
        System.out.println(sep_line);
        System.out.println("Bye! Don't come back.");
        System.out.println(sep_line);
    }

    private static void executeMainLoop() {
        String command;
        while (true) {
            command = clankyScanner.nextLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.println(sep_line);
            System.out.println(command);
            System.out.println(sep_line);
        }
    }
}
