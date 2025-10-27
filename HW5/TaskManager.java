import java.io.*;
import java.util.*;

/**
 * Manages tasks and users, allowing tasks to be added and executed based on
 * user priorities.
 */
public class TaskManager {
    /**
     * A priority queue to manage tasks.
     */
    private MinHeap<MyTask> taskQueue;

    /**
     * A list of users with associated priorities.
     */
    private List<MyUser> users;

    /**
     * Counter to generate unique task IDs.
     */
    private int taskIdCounter;

    /**
     * Initializes the TaskManager with an empty task queue and user list.
     */
    public TaskManager() {
        this.taskQueue = new MinHeap<>();
        this.users = new ArrayList<>();
        this.taskIdCounter = 0;
    }

    /**
     * Loads users from a configuration file. Each line in the file represents a
     * user's priority.
     * 
     * @param configFilePath The path to the configuration file.
     */
    public void loadUsers(String configFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(configFilePath))) {
            String line;
            int userId = 0;
            while ((line = reader.readLine()) != null) {
                try {
                    int priority = Integer.parseInt(line.trim());
                    users.add(new MyUser(userId++, priority));
                } catch (NumberFormatException e) {
                    System.err.println("Error: Invalid priority value in configuration file: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Configuration file not found at path: " + configFilePath);
        } catch (IOException e) {
            System.err.println("Error: Unable to read configuration file at path: " + configFilePath);
        }
    }

    /**
     * Retrieves the list of users.
     * 
     * @return A list of users.
     */
    public List<MyUser> getUsers() {
        return users;
    }

    /**
     * Executes all tasks in the task queue, printing each task to the console.
     */
    public void executeTasks() {
        while (!taskQueue.isEmpty()) {
            MyTask task = taskQueue.poll();
            System.out.println(task);
        }
    }

    /**
     * Adds a task for a specific user to the task queue.
     * 
     * @param userId The ID of the user for whom the task is being added.
     */
    public void addTask(int userId) {
        if (userId >= 0 && userId < users.size()) {
            MyUser user = users.get(userId);
            MyTask task = new MyTask(user, taskIdCounter++);
            taskQueue.add(task);
        } else {
            System.err.println("Invalid user ID: " + userId);
        }
    }

    /**
     * The main method to run the TaskManager. Loads users from a configuration file
     * and processes user input to add tasks or execute them.
     * 
     * @param args Command-line arguments. The first argument should be the
     *             configuration file path.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Please provide the configuration file path as a command-line argument.");
            return;
        }

        String configFilePath = args[0];
        TaskManager manager = new TaskManager();
        manager.loadUsers(configFilePath);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();
            if (line.equals("execute")) {
                manager.executeTasks();
                break;
            } else {
                try {
                    int userId = Integer.parseInt(line);
                    manager.addTask(userId);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input.");
                }
            }
        }
        scanner.close();
    }
}