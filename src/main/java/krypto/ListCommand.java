package krypto.commands;

import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks.getTasks());
    }
}