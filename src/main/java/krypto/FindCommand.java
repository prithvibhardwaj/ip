package krypto.commands;

import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;
import krypto.tasks.Task;
import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.find(keyword);
        return ui.showFoundTasks(foundTasks);
    }
}