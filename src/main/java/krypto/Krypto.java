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
     * Generates a response to the user's input.
     * Parses the command, executes it, and catches any KryptoExceptions
     * to return a friendly error message to the GUI.
     *
     * @param input The user's typed input.
     * @return The string response from Krypto.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (KryptoException e) {
            return "Oops! " + e.getMessage();
        } catch (Exception e) {
            return "A critical error occurred: " + e.getMessage();
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
