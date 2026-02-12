package krypto.commands;

import java.time.LocalDate;
import java.util.Comparator;

import krypto.KryptoException;
import krypto.Storage;
import krypto.TaskList;
import krypto.Ui;
import krypto.tasks.Deadline;
import krypto.tasks.Event;
import krypto.tasks.Task;

/**
 * Sorts the tasks in the task list.
 * The sorting order is:
 * 1. Tasks with dates (Deadlines/Events) come before tasks without dates (Todos).
 * 2. Tasks with dates are sorted chronologically (earliest date first).
 * 3. Tasks without dates (or equal dates) are sorted alphabetically by description.
 */
public class SortCommand extends Command {

    /**
     * Executes the sort command.
     * Sorts the list using a custom Comparator and returns the sorted list as a string.
     *
     * @param tasks   The list of tasks to sort.
     * @param ui      The user interface (not used directly for output here).
     * @param storage The storage to save the new order.
     * @return A string representation of the sorted task list.
     * @throws KryptoException If an error occurs during sorting or saving.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KryptoException {
        // 1. Sort the list
        tasks.getTasks().sort(new TaskDateComparator());
        
        // 2. Save the new order to the hard drive
        storage.save(tasks.getTasks());

        // 3. Build the response string manually
        StringBuilder sb = new StringBuilder();
        sb.append("I've sorted your tasks by date (earliest first):\n");
        
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            sb.append(i + 1).append(".").append(tasks.getTasks().get(i)).append("\n");
        }

        return sb.toString();
    }

    /**
     * Returns false as sorting does not exit the application.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * A helper comparator class to handle the custom sorting logic.
     */
    static class TaskDateComparator implements Comparator<Task> {

        @Override
        public int compare(Task t1, Task t2) {
            LocalDate d1 = getDate(t1);
            LocalDate d2 = getDate(t2);

            // Case 1: Both have dates -> Compare Dates
            if (d1 != null && d2 != null) {
                int dateComparison = d1.compareTo(d2);
                if (dateComparison != 0) {
                    return dateComparison;
                }
                // If dates are equal, fallback to alphabetical
                return t1.getDescription().compareToIgnoreCase(t2.getDescription());
            }

            // Case 2: t1 has date, t2 is Todo -> t1 comes first (negative)
            if (d1 != null) {
                return -1;
            }

            // Case 3: t1 is Todo, t2 has date -> t2 comes first (positive)
            if (d2 != null) {
                return 1;
            }

            // Case 4: Both are Todos (no dates) -> Compare Alphabetically
            return t1.getDescription().compareToIgnoreCase(t2.getDescription());
        }

        /**
         * Extracts the relevant date from a Task.
         * Returns null for Todo tasks.
         *
         * @param task The task to extract the date from.
         * @return The LocalDate or null if not applicable.
         */
        private LocalDate getDate(Task task) {
            if (task instanceof Deadline) {
                return ((Deadline) task).getByDate(); 
            } else if (task instanceof Event) {
                return ((Event) task).getFrom(); 
            }
            return null; // Todo tasks have no date
        }
    }
}
