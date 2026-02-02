package krypto.commands;

import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;
import krypto.KryptoException;
import krypto.tasks.Task;

public class MarkCommand extends Command {
    private final int index;
    private final boolean isMarking;

    public MarkCommand(int index, boolean isMarking) {
        this.index = index;
        this.isMarking = isMarking;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KryptoException {
        try {
            Task task = tasks.get(index);
            if (isMarking) {
                task.markAsDone();
                storage.save(tasks.getTasks());
                return ui.showMarked(task);
            } else {
                task.markAsNotDone();
                storage.save(tasks.getTasks());
                return ui.showUnmarked(task);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new KryptoException("The task index is invalid.");
        }
    }
}
