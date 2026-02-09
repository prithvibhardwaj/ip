package krypto.commands;

import java.util.Comparator;
import krypto.*;
import krypto.tasks.Task;

public class SortCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTasks().sort(Comparator.comparing(Task::getDescription));
        storage.save(tasks.getTasks());
        return "I've sorted your tasks alphabetically!";
    }
}