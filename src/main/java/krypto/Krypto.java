/**
 * The main entry point of the Krypto application.
 * Initializes the application components (Ui, Storage, TaskList) and runs the main command loop.
 */

package krypto;

public class Krypto {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Krypto(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (KryptoException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program loop.
     * Shows the welcome message and continuously processes user commands
     * until the "bye" command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            // Parser processes the command and returns true if it's time to exit
            isExit = Parser.parse(fullCommand, tasks, ui, storage);
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Krypto("data/tasks.txt").run();
    }
}