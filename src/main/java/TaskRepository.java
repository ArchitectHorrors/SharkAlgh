import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class TaskRepository {
    boolean addTask(Task task) {
        JSONObject objectDetails = new JSONObject();
        objectDetails.put("Id", task.getId());
        objectDetails.put("name", task.getName());
        objectDetails.put("priority", task.getPriority().toString());
        objectDetails.put("daysRemaining", task.getDaysRemaining());

        JSONObject object = new JSONObject();
        object.put("task", objectDetails);

        return saveInFile(object);
    }

    void readTasks() {
    }

    private boolean saveInFile(JSONObject object) {
        final String PATH = "C:\\Java\\NewAlgh\\src\\main\\java\\tasks\\tasks.txt";
        JSONParser jsonParser = new JSONParser();
        List<Task> taskList = new ArrayList<>();
        JSONArray tasks = new JSONArray();

        try (FileWriter writer = new FileWriter(PATH);
             FileReader reader = new FileReader(PATH);
             BufferedReader br = new BufferedReader(reader)) {
            boolean ready = br.ready();
            String line = br.readLine();
            if (ready && line == null) {
                tasks.add(object);
                writer.write(tasks.toJSONString());
                writer.flush();
                return true;
            }
            //Read JSON file (task list)
            Object obj = jsonParser.parse(reader);
            tasks = (JSONArray) obj;

//            for (Object task : tasks) {
//                //cast to Task type
//                taskList.add(parseTaskObject((JSONObject) task));
//            }

            tasks.add(object);
            writer.write(tasks.toJSONString());
            writer.flush();
            return true;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Parses object read from file to {@link Task} object
     *
     * @param object object to be parsed
     * @return new Task object
     */
    private static Task parseTaskObject(JSONObject object) {
        JSONObject taskObject = (JSONObject) object.get("task");

        int Id = (int) taskObject.get("Id");
        String name = (String) taskObject.get("name");
        Priority priority = (Priority) taskObject.get("priority");
        int daysRemaining = (int) taskObject.get("daysRemaining");

        return new Task(Id, name, priority, daysRemaining);
    }
}