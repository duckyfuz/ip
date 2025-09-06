package clanky;

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
}