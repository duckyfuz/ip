package clanky.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String getTypeIcon();

    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]"; // mark done task with X
    }

    public String getAdditionalDetails() {
        return "";
    }

    @Override
    public String toString() {
        return (getTypeIcon() + getStatusIcon() + " " + description + getAdditionalDetails());
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

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