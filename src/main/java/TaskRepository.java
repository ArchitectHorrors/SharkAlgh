import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String PATH;
    private List<Task> tasks = new ArrayList<>();

    public TaskRepository(final String path){
        PATH = path;
    }

    public boolean create(Task task) {
        assignTasks();
        tasks.add(task);
        return save();
    }

    List<Task> read() {
        return assignTasks() ? this.tasks : new ArrayList<>();
    }

    public Task get(int id){
        assignTasks();
        Optional<Task> first = tasks.stream().filter(task -> task.getId() == id).findFirst();
        return first.orElseGet(Task::new);
    }

    public void editName(int id, String newName){
        Optional<Task> first = tasks.stream().filter(task -> task.getId() == id).findFirst();
        if (first.isPresent()){
            Task task = first.get();
            task.setName(newName);
            save();
        }
    }

    public void editDaysRemaining(int id, int newDays){
        Optional<Task> first = tasks.stream().filter(task -> task.getId() == id).findFirst();
        if (first.isPresent()){
            Task task = first.get();
            int index = tasks.indexOf(task);
            task.setDaysRemaining(newDays);
            tasks.add(index, task);
            save();
        }
    }

    public void editPriority(int id, Priority newPriority){
        Optional<Task> first = tasks.stream().filter(task -> task.getId() == id).findFirst();
        if (first.isPresent()){
            Task task = first.get();
            int index = tasks.indexOf(task);
            task.setPriority(newPriority);
            tasks.add(index, task);
            save();
        }
    }

    public void delete(int id){
        List<Task> read = read();
        if (!read.isEmpty()){
            this.tasks.removeIf(task -> task.getId() == id);
            save();
        }
    }

    private boolean save(){
        try {
            mapper.writeValue(Paths.get(PATH).toFile(), tasks);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean assignTasks() {
        try {
            this.tasks = mapper.readValue(
                    new File(PATH),
                    mapper.getTypeFactory().constructCollectionType(List.class, Task.class)
            );
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}