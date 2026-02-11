package krypto.commands;

import java.util.ArrayList;

import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;
import krypto.tasks.Task;

/**
 * Represents a command to find tasks by a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     * Searches for tasks matching the keyword and displays them.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface to handle output.
     * @param storage The storage object (unused in this command).
     * @return The formatted list of found tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.find(keyword);
        return ui.showFoundTasks(foundTasks);
    }
}
