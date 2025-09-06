package clanky;

public class Deadline extends Task {
    protected String dueDate;

    public Deadline() {
        super();
        dueDate = "";
    }

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String getAdditionalDetails() {
        return " (by: " + dueDate + ")";
    }
}
