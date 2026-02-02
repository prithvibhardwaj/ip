package krypto.commands;

import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;
import krypto.KryptoException;
import krypto.tasks.Task;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KryptoException {
        try {
            Task deletedTask = tasks.delete(index);
            storage.save(tasks.getTasks());
            return ui.showTaskDeleted(deletedTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new KryptoException("The task index is invalid.");
        }
    }
}

