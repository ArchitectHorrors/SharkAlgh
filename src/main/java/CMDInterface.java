import java.security.SecureRandom;
import java.util.Scanner;

public class CMDInterface {
    private final TaskRepository repository = new TaskRepository();
    private final SecureRandom random = new SecureRandom();
    Scanner scanner = new Scanner(System.in);
    int choice;

    int startMenu() {
        do {
            System.out.println("Select a command for the program: " + "\n"
                    + "1. Add new task" + "\n" + "2. View available tasks.");
            choice = scanner.nextInt();
            if (choice == 1){
                addTask();
            }

        } while (choice != 1 && choice != 2);
        return choice;
    }

    void addTask() {
        String name;
        String stringifiedPriority = "";
        Priority priority = Priority.UNDEFINED;
        int days = 0;

        String NAME_CMD = "Enter a name for the new task: ";
        System.out.println(NAME_CMD);
        name = scanner.next();
        name += scanner.nextLine();

        String PRIORITY_CMD = "Specify the priority level: ";
        System.out.println(PRIORITY_CMD);

        try {
            stringifiedPriority = scanner.nextLine();
            priority = Priority.valueOf(stringifiedPriority);
        } catch (IllegalArgumentException e) {
            switch (stringifiedPriority.charAt(0)) {
                case 'H': {
                    char rodger = 'N';
                    System.out.println("Did you mean 'HIGH'?");
                    System.out.println("[Y] Yes." + "\n" + "[N] No.");
                    rodger = scanner.nextLine().charAt(0);
                    if (rodger == 'Y') {
                        priority = Priority.HIGH;
                    }
                }
                break;
                case 'M': {
                    char rodger = 'N';
                    System.out.println("Did you mean 'MEDIUM'?");
                    System.out.println("[Y] Yes." + "\n" + "[N] No.");
                    rodger = scanner.nextLine().charAt(0);
                    if (rodger == 'Y') {
                        priority = Priority.MEDIUM;
                    }
                }
                case 'L': {
                    char rodger = 'N';
                    System.out.println("Did you mean 'LOW'?");
                    System.out.println("[Y] Yes." + "\n" + "[N] No.");
                    rodger = scanner.nextLine().charAt(0);
                    if (rodger == 'Y') {
                        priority = Priority.LOW;
                    }
                }
                break;
            }
        }

        String REMAINING_DAYS_CMD = "Enter the number of days remaining until the task is completed: ";
        System.out.println(REMAINING_DAYS_CMD);
        try {
            days = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("YOU'RE AN IDIOT");
        }

        Task task = new Task(Math.abs(random.nextInt()), name, priority, days);

        if (repository.addTask(task)){
            System.out.println("You have successfully added a task: ");
            System.out.println(task.toString());
        }
        else {
            System.out.println("Task could not be added.");
        }
    }
}