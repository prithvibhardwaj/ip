import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<Task> tasks = new ArrayList<>();

        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println("____________________________________________________________");
            try {
                if (command.equals("list")) {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
                } else if (command.startsWith("mark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    tasks.get(index).markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(index));
                } else if (command.startsWith("unmark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    tasks.get(index).markAsUndone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(index));
                } else if (command.startsWith("delete")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task removed = tasks.remove(index);
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removed);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (command.startsWith("todo")) {
                    if (command.length() <= 5) {
                        throw new KryptoException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String description = command.substring(5);
                    tasks.add(new Todo(description));
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (command.startsWith("deadline")) {
                    if (!command.contains(" /by ")) {
                        throw new KryptoException("OOPS!!! Deadline must have a /by date.");
                    }
                    String[] parts = command.substring(9).split(" /by ");
                    tasks.add(new Deadline(parts[0], parts[1]));
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (command.startsWith("event")) {
                    if (!command.contains(" /from ") || !command.contains(" /to ")) {
                        throw new KryptoException("OOPS!!! Event must have /from and /to.");
                    }
                    String[] parts = command.substring(6).split(" /from ");
                    String[] times = parts[1].split(" /to ");
                    tasks.add(new Event(parts[0], times[0], times[1]));
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new KryptoException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (KryptoException e) {
                System.out.println(" " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                 System.out.println(" OOPS!!! That task number doesn't exist.");
            }

            System.out.println("____________________________________________________________");
            command = scanner.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

class KryptoException extends Exception {
    public KryptoException(String message) { super(message); }
}

class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) { this.description = description; this.isDone = false; }
    public String getStatusIcon() { return (isDone ? "X" : " "); }
    public void markAsDone() { this.isDone = true; }
    public void markAsUndone() { this.isDone = false; }
    @Override
    public String toString() { return "[" + getStatusIcon() + "] " + description; }
}

class Todo extends Task {
    public Todo(String description) { super(description); }
    @Override
    public String toString() { return "[T]" + super.toString(); }
}

class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) { super(description); this.by = by; }
    @Override
    public String toString() { return "[D]" + super.toString() + " (by: " + by + ")"; }
}

class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) { super(description); this.from = from; this.to = to; }
    @Override
    public String toString() { return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")"; }
}