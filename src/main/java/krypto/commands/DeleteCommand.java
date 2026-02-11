package krypto.commands;

import krypto.KryptoException;
import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;
import krypto.tasks.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted (0-based).
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
     * Removes the task at the specified index, saves the list, and prints a message.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface to handle output.
     * @param storage The storage to save the data.
     * @return The response string to be displayed to the user.
     * @throws KryptoException If the provided index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KryptoException {
        try {
            Task deletedTask = tasks.delete(index);
            storage.save(tasks.getTasks());
            return ui.showTaskDeleted(deletedTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new KryptoException("The task index is invalid.");
        }
    }
}
