package krypto.tasks;

/**
 * Represents a task without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats the Todo for file storage.
     *
     * @return A string formatted for saving to a text file.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns the string representation of the Todo.
     *
     * @return The string representation including type and status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
