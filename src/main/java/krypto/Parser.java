/**
 * Interprets user commands and executes the corresponding actions.
 * Acts as the bridge between the user's input and the application's logic.
 */

package krypto;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import krypto.commands.*;
import krypto.tasks.*;

public class Parser {

    public static Command parse(String fullCommand) throws KryptoException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "bye": return new ExitCommand();
        case "list": return new ListCommand();
        case "mark": return new MarkCommand(parseIndex(arguments), true);
        case "unmark": return new MarkCommand(parseIndex(arguments), false);
        case "delete": return new DeleteCommand(parseIndex(arguments));
        case "find": return new FindCommand(arguments);
        case "todo": return prepareTodo(arguments);
        case "deadline": return prepareDeadline(arguments);
        case "event": return prepareEvent(arguments);
        default: throw new KryptoException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int parseIndex(String args) throws KryptoException {
        try {
            return Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new KryptoException("Invalid task number.");
        }
    }

    private static Command prepareTodo(String args) throws KryptoException {
        if (args.isEmpty()) throw new KryptoException("Description cannot be empty.");
        return new AddCommand(new Todo(args));
    }

    private static Command prepareDeadline(String args) throws KryptoException {
        String[] parts = args.split(" /by ");
        if (parts.length < 2) throw new KryptoException("Invalid deadline format.");
        try {
            return new AddCommand(new Deadline(parts[0], LocalDate.parse(parts[1])));
        } catch (DateTimeParseException e) {
            return new AddCommand(new Deadline(parts[0], parts[1]));
        }
    }

    private static Command prepareEvent(String args) throws KryptoException {
        String[] parts = args.split(" /from | /to ");
        if (parts.length < 3) throw new KryptoException("Invalid event format.");
        return new AddCommand(new Event(parts[0], parts[1], parts[2]));
    }
}
