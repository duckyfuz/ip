public class Event extends ToDo {
    protected String duration;

    public Event() {
        super();
        duration = "";
    }

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }
}
