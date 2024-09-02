package alisa.command;

import alisa.exception.AlisaException;
import alisa.Storage;
import alisa.task.TaskList;
import alisa.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlisaException {
        String message = taskList.getList();
        ui.showMessage(message);
        storage.syncFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
