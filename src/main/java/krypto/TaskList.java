package krypto;

import java.util.ArrayList;
import java.util.stream.Collectors;

import krypto.tasks.Task;

/**
 * Represents the list of tasks.
 * Contains operations to add, delete, find, and retrieve tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The zero-based index of the task to delete.
     * @return The Task object that was removed.
     * @throws KryptoException If the index is invalid.
     */
    public Task delete(int index) throws KryptoException {
        assert index >= 0 : "Index cannot be negative";
        validateIndex(index);
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
        return tasks.stream()
                .filter(t -> t.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void validateIndex(int index) throws KryptoException {
        if (index < 0 || index >= tasks.size()) {
            throw new KryptoException("Invalid task number.");
        }
    }
}
