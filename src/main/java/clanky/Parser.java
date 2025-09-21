package clanky;

import java.util.Set;

/**
 * Parses user commands and extracts relevant information for task management.
 * Supports parsing commands with flags like /by, /from, and /to for specifying 
 * due dates, start times, and end times respectively.
 */
public class Parser {
    private final String command;

    public String action;
    public String detail = "";
    public String dueDate = "";
    public String startTime = "";
    public String endTime = "";

    private String curContext = "description";

    /**
     * Constructs a CommandParser with the given command string.
     *
     * @param command The raw command string to be parsed.
     */
    Parser(String command) {
        this.command = command;
    }

    /**
     * Parses the command string and populates the action and detail fields.
     * Recognizes context-switching flags (/by, /from, /to) and handles content appropriately.
     */
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

    /**
     * Handles switching the current parsing context based on flag tokens.
     * Changes where subsequent content will be stored.
     *
     * @param newContextFlag The flag indicating the new context (/by, /from, or /to).
     */
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

    /**
     * Appends content to the appropriate field based on the current parsing context.
     * Adds spaces between words when appending to existing content.
     *
     * @param newContent The content token to append to the current context field.
     */
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
