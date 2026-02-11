package krypto.commands;

import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;
import krypto.tasks.Task;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command.
     * Adds the task to the list, saves the updated list to storage,
     * and returns the success message to the UI.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface to handle output.
     * @param storage The storage to save the data.
     * @return The response string to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.save(tasks.getTasks());
        return ui.showTaskAdded(task, tasks.size());
    }
}
