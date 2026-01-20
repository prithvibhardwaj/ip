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
        System.out.println(" Hello! I'm Krypto\n What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        // Inside main, before the while loop:
        String[] tasks = new String[100];
        int count = 0;

        // Inside the while loop, replace the echo logic with:
        if (command.equals("list")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                System.out.println(" " + (i + 1) + ". " + tasks[i]);
            }
            System.out.println("____________________________________________________________");
        } else {
            tasks[count] = command;
            count++;
            System.out.println("____________________________________________________________");
            System.out.println(" added: " + command);
            System.out.println("____________________________________________________________");
        }
        while (!command.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(" " + command);
            System.out.println("____________________________________________________________");
            command = scanner.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}