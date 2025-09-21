package clanky.tasks;

/**
 * Represents a simple to-do task with just a description and completion status.
 * Displayed with the "[T]" type icon.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo with empty description.
     */
    public ToDo() {
        super();
    }

    /**
     * Constructs a new ToDo with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the type icon for ToDo tasks.
     *
     * @return "[T]" to indicate this is a ToDo task.
     */
    @Override
    public String getTypeIcon() {
        return "[T]";
    }

//    @Override
//    public String getAdditionalDetails() {
//        return "";
//    }
}
