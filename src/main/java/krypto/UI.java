/**
 * Handles all interactions with the user.
 * Responsible for reading user input and printing messages to the console.
 */
package krypto;

import java.util.Scanner;
import krypto.tasks.Task;
import java.util.ArrayList;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String showWelcome() {
        return "Hello! I'm Krypto.\nWhat can I do for you today?";
    }

    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    public String showError(String message) {
        return "OOPS!!! " + message;
    }

    public String showLoadingError() {
        return "Error loading data from file. Starting with an empty list.";
    }

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

    public String showTaskAdded(Task task, int size) {
        return "Got it. I've added this task:\n  " + task + 
               "\nNow you have " + size + " tasks in the list.";
    }

    public String showTaskDeleted(Task task, int size) {
        return "Noted. I've removed this task:\n  " + task + 
               "\nNow you have " + size + " tasks in the list.";
    }

    public String showMarked(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    public String showUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

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