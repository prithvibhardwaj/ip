package krypto.commands;

import krypto.KryptoException;
import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;

/**
 * Represents an abstract command that can be executed by the application.
 * All specific command types inherit from this class.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface to handle interaction.
     * @param storage The storage to save or load data.
     * @return The response string to be displayed to the user.
     * @throws KryptoException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws KryptoException;

    /**
     * Checks if this command should terminate the program.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
