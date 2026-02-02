/**
 * Represents a task that needs to be done before a specific date.
 */
package krypto.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate byDate;
    protected String byString;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.byDate = by;
        this.byString = null;
    }

    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
        this.byDate = null;
    }

    @Override
    public String toString() {
        String dateDisplay;
        if (byDate != null) {
            dateDisplay = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            dateDisplay = byString;
        }
        return "[D]" + super.toString() + " (by: " + dateDisplay + ")";
    }

    public String toFileFormat() {
        String dateSave = (byDate != null) ? byDate.toString() : byString;
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dateSave;
    }
}
