package krypto.commands;

import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * Retrieves all tasks from the task list and displays them.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface to handle output.
     * @param storage The storage object (unused in this command).
     * @return The formatted list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks.getTasks());
    }
}
