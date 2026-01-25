package krypto;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    public void delete(int index) {
        Task removed = tasks.remove(index);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removed);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    public void mark(int index) {
        tasks.get(index).markAsDone();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks.get(index));
    }

    public void unmark(int index) {
        tasks.get(index).markAsUndone();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks.get(index));
    }

    public void list() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }

    public int size() {
        return tasks.size();
    }
    
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}