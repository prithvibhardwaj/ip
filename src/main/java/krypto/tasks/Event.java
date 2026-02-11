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
     * Constructs an Event task.
     *
     * @param description The description of the event.
     * @param from        The start date (YYYY-MM-DD).
     * @param to          The end date (YYYY-MM-DD).
     */
    public Event(String description, String from, String to) {
        super(description);
        // Note: This assumes input is strictly YYYY-MM-DD.
        // If parsing fails, the main loop handles the exception.
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Formats the Event for file storage.
     *
     * @return A string formatted for saving to a text file.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + from + " | " + to;
    }

    /**
     * Returns the string representation of the Event.
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
