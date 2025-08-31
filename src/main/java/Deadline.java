public class Deadline extends ToDo {
    protected String dueDate;

    public Deadline() {
        super();
        dueDate = "";
    }

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }
}
