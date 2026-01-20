import java.util.Scanner;

public class Krypto {
    public static void main(String[] args) {
        String logo = " _  __                  _       \n" +
                      "| |/ /                 | |      \n" +
                      "| ' / _ __ _   _ _ __ | |_ ___ \n" +
                      "|  < | '__| | | | '_ \\| __/ _ \\\n" +
                      "| . \\| |  | |_| | |_) | || (_) |\n" +
                      "|_|\\_\\_|   \\__, | .__/ \\__\\___/\n" +
                      "            __/ | |             \n" +
                      "           |___/|_|             ";

        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Krypto");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int count = 0;

        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println("____________________________________________________________");
            if (command.equals("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks[index].markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[index]);
            } else if (command.startsWith("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks[index].markAsUndone();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[index]);
            } else {
                tasks[count] = new Task(command);
                count++;
                System.out.println(" added: " + command);
            }
            System.out.println("____________________________________________________________");
            command = scanner.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}