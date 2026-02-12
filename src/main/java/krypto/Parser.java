package krypto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import krypto.commands.AddCommand;
import krypto.commands.Command;
import krypto.commands.DeleteCommand;
import krypto.commands.ExitCommand;
import krypto.commands.FindCommand;
import krypto.commands.ListCommand;
import krypto.commands.MarkCommand;
import krypto.commands.SortCommand;
import krypto.tasks.Deadline;
import krypto.tasks.Event;
import krypto.tasks.Todo;

/**
 * Parses user input to create specific Command objects.
 * Handles the interpretation of command words and arguments.
 */
public class Parser {

    /**
     * The expected date format for user input (d-M-yyyy).
     * Accepts dates like "2-12-2025" or "02-12-2025".
     */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d-M-yyyy");

    /**
     * Parses the full command string into a specific Command object.
     * Identifies the command word and delegates to specific preparation methods.
     *
     * @param fullCommand The full line of input entered by the user.
     * @return A Command object corresponding to the user's input.
     * @throws KryptoException If the command is unknown or arguments are invalid.
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
            return prepareTodo(arguments);
        case "deadline":
            return prepareDeadline(arguments);
        case "event":
            return prepareEvent(arguments);
        case "sort":
            return new SortCommand();
        default:
            throw new KryptoException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the argument string as a 0-based index.
     *
     * @param args The string containing the task number.
     * @return The zero-based index.
     * @throws KryptoException If the argument is not a valid integer.
     */
    private static int parseIndex(String args) throws KryptoException {
        try {
            return Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new KryptoException("Invalid task number.");
        }
    }

    /**
     * Prepares a Todo task from the arguments.
     *
     * @param args The description of the todo.
     * @return An AddCommand containing the new Todo.
     * @throws KryptoException If the description is empty.
     */
    private static Command prepareTodo(String args) throws KryptoException {
        if (args.isEmpty()) {
            throw new KryptoException("Description cannot be empty.");
        }
        return new AddCommand(new Todo(args));
    }

    /**
     * Prepares a Deadline task from the arguments.
     * Parses the date using the custom d-M-yyyy format.
     *
     * @param args The arguments containing description and date (split by /by).
     * @return An AddCommand containing the new Deadline.
     * @throws KryptoException If the format is invalid or the date cannot be parsed.
     */
    private static Command prepareDeadline(String args) throws KryptoException {
        String[] parts = args.split(" /by ");
        if (parts.length < 2) {
            throw new KryptoException("Invalid deadline format. Use: deadline <desc> /by <d-M-yyyy>");
        }
        try {
            LocalDate date = LocalDate.parse(parts[1], INPUT_FORMAT);
            return new AddCommand(new Deadline(parts[0], date));
        } catch (DateTimeParseException e) {
            throw new KryptoException("Invalid date format. Please use dd-mm-yyyy (e.g., 02-12-2025)");
        }
    }

    /**
     * Prepares an Event task from the arguments.
     * Parses the start and end dates using the custom d-M-yyyy format.
     *
     * @param args The arguments containing description, start date, and end date.
     * @return An AddCommand containing the new Event.
     * @throws KryptoException If the format is invalid or dates cannot be parsed.
     */
    private static Command prepareEvent(String args) throws KryptoException {
        String[] parts = args.split(" /from | /to ");
        if (parts.length < 3) {
            throw new KryptoException("Invalid event format. Use: event <desc> /from <date> /to <date>");
        }
        try {
            LocalDate fromDate = LocalDate.parse(parts[1], INPUT_FORMAT);
            LocalDate toDate = LocalDate.parse(parts[2], INPUT_FORMAT);
            return new AddCommand(new Event(parts[0], fromDate, toDate));
        } catch (DateTimeParseException e) {
            throw new KryptoException("Invalid date format. Please use dd-mm-yyyy (e.g., 02-12-2025)");
        }
    }
}
