import lombok.SneakyThrows;

import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;

public class CMDInterface {
    private final TaskRepository repository = new TaskRepository();
    private final SecureRandom random = new SecureRandom();
    Scanner scanner = new Scanner(System.in);

    void startMenu() {
        Action.CHOICE choice;
        do {
            System.out.println("""
                                        
                    Select a command for the program:\s
                    1. Add new task
                    2. View available tasks.
                    3. Edit task.
                    4. Delete task.
                    0. Close program.
                    """);
            choice = Action.CHOICE.intValueOf(scanner.nextInt());
            if (choice == Action.CHOICE.ADD) {
                addTask();
            }
            if (choice == Action.CHOICE.SHOW) {
                showTasks();
            }
            if (choice == Action.CHOICE.EDIT) {
                System.out.print("\nEnter task id: ");
                int id = scanner.nextInt();
                editTask(id);
            }
            if (choice == Action.CHOICE.DELETE) {
                System.out.print("\nEnter task id: ");
                int id = scanner.nextInt();
                delete(id);
            }
        } while (choice != Action.CHOICE.EXIT);
    }

    private void editTask(int id) {
        Action.EDIT choice;
        System.out.println("\nYou have choose \n" + repository.get(id).toString());
        do {
            System.out.println("""
                                        
                    Select a command for the program:\s
                    1. Edit name
                    2. Edit priority.
                    3. Edit days remaining.
                    0. Exit edit mode.
                    """);
            choice = Action.EDIT.intValueOf(scanner.nextInt());
            if (choice == Action.EDIT.NAME) {
                String name;
                System.out.print("\nEnter new task name: ");
                name = scanner.next();
                name += scanner.nextLine();
                repository.editName(id, name);
            }
            if (choice == Action.EDIT.PRIORITY) {
                Priority priority = setPriority();
                repository.editPriority(id, priority);
            }
            if (choice == Action.EDIT.DAYS) {
                int daysRemaining;
                System.out.print("\nEnter new days remaining for task: ");
                daysRemaining = scanner.nextInt();
                repository.editDaysRemaining(id,daysRemaining);
            }
        } while (choice != Action.EDIT.EXIT);
    }

    private void delete(int id){
        System.out.println("\nYou have choose \n" + repository.get(id).toString());
        repository.delete(id);
    }

    private void showTasks() {
        List<Task> readTasks = repository.read();
        for (Task task : readTasks) {
            System.out.println(task + "\n");
        }
    }

    @SneakyThrows
    void addTask() {
        String name;
        int days = 0;

        String NAME_CMD = "\nEnter a name for the new task: ";
        System.out.print(NAME_CMD);
        name = scanner.next();
        name += scanner.nextLine();

        Priority priority = setPriority();

        String REMAINING_DAYS_CMD = "\nEnter the number of days remaining until the task is completed: ";
        System.out.print(REMAINING_DAYS_CMD);
        try {
            days = scanner.nextInt();
        } catch (Exception e) {
            System.out.print("\nYOU'RE AN IDIOT");
        }

        Task task = new Task(Math.abs(random.nextInt()), name, priority, days);

        if (repository.create(task)) {
            System.out.print("\nYou have successfully added a task: \n");
            System.out.print(task);
        } else {
            System.out.println("\nTask could not be added.");
        }
    }

    Priority setPriority(){
        String stringifiedPriority = "";
        Priority priority = Priority.UNDEFINED;
        String PRIORITY_CMD = "\nSpecify the priority level: ";
        System.out.print(PRIORITY_CMD);

        try {
            stringifiedPriority = scanner.nextLine();
            priority = Priority.valueOf(stringifiedPriority.toUpperCase());
        } catch (IllegalArgumentException e) {
            switch (stringifiedPriority.charAt(0)) {
                case 'H', 'h': {
                    char rodger = 'N';
                    System.out.println("\nDid you mean 'HIGH'?");
                    System.out.print("[y] Yes. / [N] No. ");
                    rodger = scanner.nextLine().charAt(0);
                    if (rodger == 'Y') {
                        priority = Priority.HIGH;
                    }
                }
                break;
                case 'M', 'm': {
                    char rodger = 'N';
                    System.out.println("\nDid you mean 'MEDIUM'?");
                    System.out.print("[y] Yes. / [N] No. ");
                    rodger = scanner.nextLine().charAt(0);
                    if (rodger == 'Y') {
                        priority = Priority.MEDIUM;
                    }
                }
                case 'L', 'l': {
                    char rodger = 'N';
                    System.out.println("\nDid you mean 'LOW'?");
                    System.out.print("[y] Yes. / [N] No. ");
                    rodger = scanner.nextLine().charAt(0);
                    if (rodger == 'Y') {
                        priority = Priority.LOW;
                    }
                }
                break;
            }
        }

        return priority;
    }
}