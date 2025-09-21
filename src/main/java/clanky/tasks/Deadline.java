package clanky.tasks;

/**
 * Represents a task with a deadline.
 * Extends Task to include a due date that is displayed in the task representation.
 * Displayed with the "[D]" type icon.
 */
public class Deadline extends Task {
    protected String dueDate;

    /**
     * Constructs a new Deadline with empty description and due date.
     */
    public Deadline() {
        super();
        dueDate = "";
    }

    /**
     * Constructs a new Deadline with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param dueDate The due date for the task.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns the type icon for Deadline tasks.
     *
     * @return "[D]" to indicate this is a Deadline task.
     */
    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    /**
     * Returns additional details showing the due date.
     *
     * @return A formatted string containing the due date in the format " (by: dueDate)".
     */
    @Override
    public String getAdditionalDetails() {
        return " (by: " + dueDate + ")";
    }
}
