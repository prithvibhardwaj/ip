package krypto.commands;

import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;
import javafx.application.Platform;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

