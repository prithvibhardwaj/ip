package krypto.commands;

import krypto.KryptoException;
import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;
import krypto.tasks.Task;

/**
 * Represents a command to mark a task as done or not done.
 */
public class MarkCommand extends Command {
    private final int index;
    private final boolean isMarking;

    /**
     * Constructs a MarkCommand.
     *
     * @param index     The index of the task to mark/unmark (0-based).
     * @param isMarking true to mark as done, false to mark as not done.
     */
    public MarkCommand(int index, boolean isMarking) {
        this.index = index;
        this.isMarking = isMarking;
    }

    /**
     * Executes the mark/unmark command.
     * Updates the status of the task, saves the changes, and displays a message.
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
            Task task = tasks.get(index);
            if (isMarking) {
                task.markAsDone();
                storage.save(tasks.getTasks());
                return ui.showMarked(task);
            } else {
                task.markAsNotDone();
                storage.save(tasks.getTasks());
                return ui.showUnmarked(task);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new KryptoException("The task index is invalid.");
        }
    }
}
