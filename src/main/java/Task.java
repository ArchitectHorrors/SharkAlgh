public class Task {
    private int Id;
    private String name;
    private Priority priority;
    private int daysRemaining;

    public Task(int taskId, String taskName, Priority priority, int daysRemaining){
        this.Id = taskId;
        this.name = taskName;
        this.daysRemaining = daysRemaining;
        this.priority = priority;
    }

    public Task(){}

    @Override
    public String toString(){
        return String.format("id: %d%nname: %s%npriority: %s%ndays remaining: %d", Id, name, priority, daysRemaining);
    }

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public int getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(int daysRemaining) {
        this.daysRemaining = daysRemaining;
    }
}
