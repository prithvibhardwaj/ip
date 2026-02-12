package krypto.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be done before a specific date.
 */
public class Deadline extends Task {

    protected LocalDate byDate;

    /**
     * Constructs a Deadline with a LocalDate object.
     *
     * @param description The description of the deadline.
     * @param by          The date of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.byDate = by;
    }

    /**
     * Formats the Deadline for file storage.
     * Uses standard ISO-8601 format (yyyy-MM-dd) for stability.
     *
     * @return A string formatted for saving to a text file.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byDate;
    }

    /**
     * Returns the string representation of the Deadline.
     * Displays in a readable format (e.g., Oct 15 2025).
     *
     * @return The string representation including type, status, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    
    /**
     * Gets the deadline date.
     * @return The LocalDate of the deadline.
     */
    public LocalDate getByDate() {
        return this.byDate;
    }
}
