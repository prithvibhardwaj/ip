package krypto.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be done before a specific date.
 */
public class Deadline extends Task {

    protected LocalDate byDate;
    protected String byString;

    /**
     * Constructs a Deadline with a LocalDate object.
     *
     * @param description The description of the deadline.
     * @param by          The date of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.byDate = by;
        this.byString = null;
    }

    /**
     * Constructs a Deadline with a raw string date (legacy support).
     *
     * @param description The description of the deadline.
     * @param by          The date string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
        this.byDate = null;
    }

    /**
     * Formats the Deadline for file storage.
     *
     * @return A string formatted for saving to a text file.
     */
    @Override
    public String toFileFormat() {
        String dateSave = (byDate != null) ? byDate.toString() : byString;
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dateSave;
    }

    /**
     * Returns the string representation of the Deadline.
     *
     * @return The string representation including type, status, and due date.
     */
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
}
