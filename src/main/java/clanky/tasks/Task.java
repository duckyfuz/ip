package clanky.tasks;

/**
 * Abstract base class representing a task in the Clanky application.
 * All tasks have a description and a completion status, and can be displayed
 * with type-specific icons and additional details.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with empty description and not done status.
     */
    public Task() {
        this.description = "";
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified description and not done status.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the type-specific icon for this task.
     * Must be implemented by subclasses to provide their unique identifier.
     *
     * @return A string representing the task type (e.g., "[T]", "[D]", "[E]").
     */
    public abstract String getTypeIcon();

    /**
     * Returns the completion status icon for this task.
     *
     * @return "[X]" if the task is done, "[ ]" if not done.
     */
    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]"; // mark done task with X
    }

    /**
     * Returns the description of this task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns additional details specific to the task type.
     * Default implementation returns an empty string.
     * Can be overridden by subclasses to provide type-specific information.
     *
     * @return Additional details to append to the task string representation.
     */
    public String getAdditionalDetails() {
        return "";
    }

    /**
     * Returns the string representation of this task.
     * Combines the type icon, status icon, description, and additional details.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        return (getTypeIcon() + getStatusIcon() + " " + description + getAdditionalDetails());
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Creates a Task object from its string representation.
     * Parses the task type, completion status, description, and type-specific details.
     *
     * @param taskString The string representation of a task.
     * @return A Task object of the appropriate subtype.
     * @throws IllegalArgumentException If the task type is not recognized.
     */
    public static Task fromString(String taskString) {
        String type = taskString.substring(0, 3); // First three characters represent [Type]
        boolean isDone = taskString.charAt(4) == 'X';
        int descStart = 7; // After "[T] [ ] " or "[T] [X] "
        String descriptionEnd = taskString.contains(" (") ? taskString.substring(descStart, taskString.indexOf(" (")) : taskString.substring(descStart);

        switch (type) {
        case "[T]":
            ToDo todo = new ToDo(descriptionEnd);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;

        case "[D]":
            String dueDate = taskString.substring(taskString.indexOf("(by: ") + 5, taskString.lastIndexOf(")"));
            Deadline deadline = new Deadline(descriptionEnd, dueDate);
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;

        case "[E]":
            String[] eventDetails = taskString.substring(taskString.indexOf("(from: ") + 7, taskString.lastIndexOf(")")).split(" to: ");
            Event event = new Event(descriptionEnd, eventDetails[0], eventDetails[1]);
            if (isDone) {
                event.markAsDone();
            }
            return event;

        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }
}