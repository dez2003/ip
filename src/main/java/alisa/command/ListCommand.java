package alisa.command;

import alisa.Storage;
import alisa.task.TaskList;
import alisa.Ui;

public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     *
     * Lists all the tasks in taskList.
     *
     * @param taskList List of all the tasks.
     * @param ui Ui that displays messages and interacts with user.
     * @param storage Storage that saves data into a file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String message = taskList.getList();
        ui.showMessage(message);
    }

    /**
     * {@inheritDoc}
     *
     * Indicates that the program should not terminate.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
