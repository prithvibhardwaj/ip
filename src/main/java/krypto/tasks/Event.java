package krypto.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event task with LocalDate objects.
     *
     * @param description The description of the event.
     * @param from        The start date.
     * @param to          The end date.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Formats the Event for file storage.
     * Uses standard ISO-8601 format (yyyy-MM-dd) for stability.
     *
     * @return A string formatted for saving to a text file.
     */
    @Override
    public String toFileFormat() {
        // We use the default .toString() (yyyy-MM-dd) for storage to keep it standard
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + from + " | " + to;
    }

    /**
     * Gets the start date of the event.
     * @return The LocalDate the event starts.
     */
    public LocalDate getFrom() {
        return this.from;
    }
    
    /**
     * Returns the string representation of the Event.
     * Displays in a readable format (e.g., Oct 15 2025).
     *
     * @return The string representation including type, status, and duration.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
