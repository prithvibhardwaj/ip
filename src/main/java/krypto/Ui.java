package krypto;

import java.util.ArrayList;
import java.util.Scanner;

import krypto.tasks.Task;

/**
 * Handles all interactions with the user.
 * Responsible for reading user input and printing messages to the console.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Creates a new Ui instance and initializes the scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Generates the welcome message.
     * @return The welcome string.
     */
    public String showWelcome() {
        return "Hello! I'm Krypto.\nWhat can I do for you today?";
    }

    /**
     * Prints the welcome message to the console (Legacy method).
     * @param messages The messages to print.
     */
    public void showWelcome(String... messages) {
        for (String m : messages) {
            System.out.println(m);
        }
    }

    /**
     * Generates the goodbye message.
     * @return The goodbye string.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Formats an error message.
     * @param messages The error details.
     * @return The formatted error string.
     */
    public String showError(String... messages) {
        StringBuilder sb = new StringBuilder("OOPS!!! ");
        for (String m : messages) {
            sb.append(m).append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * Returns a standard loading error message.
     * @return The error string.
     */
    public String showLoadingError() {
        return "Error loading data from file. Starting with an empty list.";
    }

    /**
     * Formats the list of tasks.
     * @param tasks The list of tasks to display.
     * @return The formatted list string.
     */
    public String showList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "Your task list is empty.";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Formats the message when a task is added.
     * @param task The task that was added.
     * @param size The new size of the list.
     * @return The formatted success message.
     */
    public String showTaskAdded(Task task, int size) {
        return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Formats the message when a task is deleted.
     * @param task The task that was removed.
     * @param size The new size of the list.
     * @return The formatted success message.
     */
    public String showTaskDeleted(Task task, int size) {
        return "Noted. I've removed this task:\n  " + task
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Formats the message when a task is marked as done.
     * @param task The task marked.
     * @return The success message.
     */
    public String showMarked(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Formats the message when a task is marked as not done.
     * @param task The task unmarked.
     * @return The success message.
     */
    public String showUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Formats the list of found tasks matching a keyword.
     * @param foundTasks The list of matching tasks.
     * @return The formatted list string.
     */
    public String showFoundTasks(ArrayList<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            return "No matching tasks found.";
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            sb.append((i + 1)).append(".").append(foundTasks.get(i)).append("\n");
        }
        return sb.toString();
    }
}
