package krypto.commands;

import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * Displays a goodbye message to the user.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface to handle output.
     * @param storage The storage to save the data.
     * @return The goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    /**
     * Indicates that this command should terminate the application.
     *
     * @return true, as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
