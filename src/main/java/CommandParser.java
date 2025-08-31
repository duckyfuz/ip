import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    private final String command;

    public String action;
    Map<String, String> taskDetailsMap = new HashMap<>(Map.of(
        "description", "",
        "dueDate", "",
        "startTime", "",
        "endTime", ""
    ));

    CommandParser (String command) {
        this.command = command;
    }

    public void parseCommand() {
        String[] parts = command.split(" ");

        action = parts[0];
        String curAppendingTo = "description";
        for (int i = 1; i < parts.length; i++) {
            String curToken = parts[i];
            switch (curToken) {
            case "/by":
                curAppendingTo = "dueDate";
                break;
            case "/from":
                curAppendingTo = "startTime";
                break;
            case "/to":
                curAppendingTo = "endTime";
                break;
            default:
                taskDetailsMap.put(curAppendingTo, taskDetailsMap.get(curAppendingTo).isEmpty() ? curToken : taskDetailsMap.get(curAppendingTo) + " " + curToken);
            }
        }
    }
}
