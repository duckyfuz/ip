package clanky.tasks;

public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event() {
        super();
        startTime = "";
        endTime = "";
    }

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String getAdditionalDetails() {
        return " (from: " + startTime + " to: " + endTime + ")";
    }
}
