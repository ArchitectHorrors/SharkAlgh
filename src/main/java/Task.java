import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
public class Task {
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter
    private final int Id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Priority priority;
    @Getter
    @Setter
    private int daysRemaining;

    public Task(int taskId, String taskName, Priority priority, int daysRemaining){
        this.Id = taskId;
        this.name = taskName;
        this.daysRemaining = daysRemaining;
        this.priority = priority;
    }

    @Override
    public String toString(){
        return String.format("name: %s%npriority: %s%ndays remaining: %d", name, priority, daysRemaining);
    }
}
