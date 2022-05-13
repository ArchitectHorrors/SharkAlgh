import java.security.SecureRandom;
import java.util.Map;
import java.util.Scanner;

public class CMDInterface {
    private final TaskRepository repository = new TaskRepository();
    private final SecureRandom random = new SecureRandom();
    Scanner scanner = new Scanner(System.in);
    int choice;

    void startMenu() {
        do {
            System.out.println("""
                    Select a command for the program:\s
                    1. Add new task
                    2. View available tasks.
                    """);
            choice = scanner.nextInt();
            if (choice == 1){
                addTask();
            }

            if (choice == 2){
                showTasks();
            }
        } while (choice != 1 && choice != 2);
//        return choice;
    }

    private void showTasks() {
//        repository.readTasks();
//        for (Map.Entry<?, ?> entry : tasks.entrySet()){
//            System.out.println(entry.getKey().toString() + " " + entry.getValue().toString());
//        }
    }

    void addTask() {
        String name;
        String stringifiedPriority = "";
        Priority priority = Priority.UNDEFINED;
        int days = 0;

        String NAME_CMD = "\nEnter a name for the new task: ";
        System.out.print(NAME_CMD);
        name = scanner.next();
        name += scanner.nextLine();

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
                    System.out.print("[y] Yes. / [N] No.");
                    rodger = scanner.nextLine().charAt(0);
                    if (rodger == 'Y') {
                        priority = Priority.HIGH;
                    }
                }
                break;
                case 'M', 'm': {
                    char rodger = 'N';
                    System.out.println("\nDid you mean 'MEDIUM'?");
                    System.out.print("[y] Yes. / [N] No.");
                    rodger = scanner.nextLine().charAt(0);
                    if (rodger == 'Y') {
                        priority = Priority.MEDIUM;
                    }
                }
                case 'L', 'l': {
                    char rodger = 'N';
                    System.out.println("\nDid you mean 'LOW'?");
                    System.out.print("[y] Yes. / [N] No.");
                    rodger = scanner.nextLine().charAt(0);
                    if (rodger == 'Y') {
                        priority = Priority.LOW;
                    }
                }
                break;
            }
        }

        String REMAINING_DAYS_CMD = "\nEnter the number of days remaining until the task is completed: ";
        System.out.print(REMAINING_DAYS_CMD);
        try {
            days = scanner.nextInt();
        } catch (Exception e) {
            System.out.print("\nYOU'RE AN IDIOT");
        }

        Task task = new Task(Math.abs(random.nextInt()), name, priority, days);

        if (repository.addTask(task)){
            System.out.print("\nYou have successfully added a task: \n");
            System.out.print(task);
        }
        else {
            System.out.println("\nTask could not be added.");
        }
    }
}