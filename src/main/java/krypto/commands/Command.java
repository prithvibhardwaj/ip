package krypto.commands;

import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;
import krypto.KryptoException;
import krypto.tasks.Task;

public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws KryptoException;

    public boolean isExit() {
        return false;
    }
}

