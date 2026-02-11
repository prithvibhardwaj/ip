package krypto.tasks;

/**
 * Represents a generic task.
 * This is the parent class for specific task types like Todo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a generic Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" if done, " " (space) if not done.
     *
     * @return The status icon string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Formats the task for file storage.
     *
     * @return A string formatted for saving to a text file.
     */
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
