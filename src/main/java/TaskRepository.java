import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TaskRepository{
    boolean addTask(Task task){
        Map<String, Object> map = new HashMap<>();
        map.put("Id", task.getId());
        map.put("name", task.getName());
        map.put("priority", task.getPriority());
        map.put("daysRemaining", task.getDaysRemaining());

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue((Paths.get("C:\\Java\\NewAlgh\\src\\main\\java\\tasks\\" + task.getName().replace(" ", "_") + ".json")).toFile(), map);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public void saveInFile(){
    }
}