/**
 * Interprets user commands and executes the corresponding actions.
 * Acts as the bridge between the user's input and the application's logic.
 */

package krypto;

import krypto.commands.*;
import krypto.tasks.Deadline;
import krypto.tasks.Event;
import krypto.tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parses the full command string and returns the appropriate Command object.
     */
    public static Command parse(String fullCommand) throws KryptoException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return new MarkCommand(parseIndex(arguments), true);

        case "unmark":
            return new MarkCommand(parseIndex(arguments), false);

        case "delete":
            return new DeleteCommand(parseIndex(arguments));

        case "find":
            return new FindCommand(arguments);

        case "todo":
            if (arguments.isEmpty()) {
                throw new KryptoException("The description of a todo cannot be empty.");
            }
            return new AddCommand(new Todo(arguments));

        case "deadline":
            return prepareDeadline(arguments);

        case "event":
            return prepareEvent(arguments);

        case "hello":
            return new Command() {
                public String execute(TaskList t, Ui u, Storage s) {
                    return "Hi there! How can I help?";
                }
            };

        default:
            throw new KryptoException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int parseIndex(String args) throws KryptoException {
        try {
            return Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new KryptoException("Invalid task number.");
        }
    }

    private static Command prepareDeadline(String args) throws KryptoException {
        String[] parts = args.split(" /by ");
        if (parts.length < 2) {
            throw new KryptoException("Invalid deadline format. Use: deadline <desc> /by <date>");
        }
        try {
            LocalDate date = LocalDate.parse(parts[1]);
            return new AddCommand(new Deadline(parts[0], date));
        } catch (DateTimeParseException e) {
            // Fallback for non-standard dates if you prefer, or throw error
            return new AddCommand(new Deadline(parts[0], parts[1]));
        }
    }

    private static Command prepareEvent(String args) throws KryptoException {
        String[] parts = args.split(" /from | /to ");
        if (parts.length < 3) {
            throw new KryptoException("Invalid event format. Use: event <desc> /from <start> /to <end>");
        }
        return new AddCommand(new Event(parts[0], parts[1], parts[2]));
    }
}