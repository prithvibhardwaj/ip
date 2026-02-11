package krypto.commands;

import java.util.Comparator;

import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;
import krypto.tasks.Task;

/**
 * Represents a command to sort tasks alphabetically by description.
 */
public class SortCommand extends Command {

    /**
     * Executes the sort command.
     * Sorts the tasks alphabetically, saves the new order, and returns a success message.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface to handle output.
     * @param storage The storage to save the sorted list.
     * @return The success message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTasks().sort(Comparator.comparing(Task::getDescription));
        storage.save(tasks.getTasks());
        return "I've sorted your tasks alphabetically!";
    }
}
