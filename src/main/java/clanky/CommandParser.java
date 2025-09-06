package clanky;

import java.util.Set;

public class CommandParser {
    private final String command;

    public String action;
    public String detail = "";
    public String dueDate = "";
    public String startTime = "";
    public String endTime = "";

    private String curContext = "description";

    CommandParser (String command) {
        this.command = command;
    }

    public void parseCommand() {
        String[] parts = command.split(" ");
        action = parts[0];

        for (int i = 1; i < parts.length; i++) {
            String curToken = parts[i];
            Set<String> contentFlags = Set.of("/by", "/from", "/to");
            if (contentFlags.contains(curToken)) {
                handleContextSwitch(curToken);
                continue;
            }
            handleAppendingContent(curToken);
        }
    }

    private void handleContextSwitch (String newContextFlag) {
        switch (newContextFlag) {
        case "/by":
            curContext = "dueDate";
            break;
        case "/from":
            curContext = "startTime";
            break;
        case "/to":
            curContext = "endTime";
            break;
        }
    }

    private void handleAppendingContent (String newContent) {
        switch (curContext) {
        case "description":
            if (!detail.isEmpty()) detail += " ";
            detail += newContent;
            break;
        case "dueDate":
            if (!dueDate.isEmpty()) dueDate += " ";
            dueDate += newContent;
            break;
        case "startTime":
            if (!startTime.isEmpty()) startTime += " ";
            startTime += newContent;
            break;
        case "endTime":
            if (!endTime.isEmpty()) endTime += " ";
            endTime += newContent;
            break;
        default:
            break;
        }
    }
}
