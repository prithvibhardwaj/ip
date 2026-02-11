package krypto;

import krypto.commands.Command;

/**
 * The main entry point of the Krypto application.
 * Initializes the application components (Ui, Storage, TaskList) and runs the main command loop.
 */
public class Krypto {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs the Krypto application.
     * Initializes the UI, Storage, and TaskList.
     * Loads existing data if available, otherwise starts with an empty list.
     */
    public Krypto() {
        ui = new Ui();
        storage = new Storage("data/krypto.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (KryptoException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The raw input string from the user.
     * @return The response string from the executed command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (KryptoException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Returns the welcome message to be displayed at startup.
     *
     * @return The welcome message string.
     */
    public String getWelcomeMessage() {
        return ui.showWelcome();
    }
}
