public class ToDo {
    protected String description;
    protected boolean isDone;

    public ToDo() {
        this.description = "";
        this.isDone = false;
    }

    public ToDo(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTypeIcon() {
        return "[T]";
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]"; // mark done task with X
    }

    @Override
    public String toString() {
        return (getTypeIcon() + getStatusIcon() + " " + description);
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }
}