public class ToDo extends Task {
    public ToDo() {
        super();
    }

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

//    @Override
//    public String getAdditionalDetails() {
//        return "";
//    }
}
