package clanky.tasks;

/**
 * Represents a task that occurs during a specific time period.
 * Extends Task to include start and end times that are displayed in the task representation.
 * Displayed with the "[E]" type icon.
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Constructs a new Event with empty description, start time, and end time.
     */
    public Event() {
        super();
        startTime = "";
        endTime = "";
    }

    /**
     * Constructs a new Event with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the type icon for Event tasks.
     *
     * @return "[E]" to indicate this is an Event task.
     */
    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    /**
     * Returns additional details showing the start and end times.
     *
     * @return A formatted string containing the time range in the format " (from: startTime to: endTime)".
     */
    @Override
    public String getAdditionalDetails() {
        return " (from: " + startTime + " to: " + endTime + ")";
    }
}
