/**
 * Represents the list of tasks.
 * Contains operations to add, delete, list, and modify tasks (e.g., marking them as done).
 */
package krypto;

import krypto.tasks.Task;
import java.util.ArrayList;

/**
 * Represents the list of tasks.
 * Contains operations to add, delete, find, and retrieve tasks.
 */
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
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The zero-based index of the task to delete.
     * @return The Task object that was removed.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The zero-based index of the task.
     * @return The Task object.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the raw ArrayList of tasks.
     * Used by the ListCommand to display all tasks.
     *
     * @return The ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Searches for tasks that contain the specified keyword in their string representation.
     *
     * @param keyword The string to search for.
     * @return An ArrayList containing only the matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }

    private void validateIndex(int index) throws KryptoException {
        if (index < 0 || index >= tasks.size()) {
            throw new KryptoException("Invalid task number.");
        }
    }
}
