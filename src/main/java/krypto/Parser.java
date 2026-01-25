package krypto;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    
    // Returns true if the program should exit
    public static boolean parse(String fullCommand, TaskList taskList, Ui ui, Storage storage) {
        String[] words = fullCommand.split(" ");
        String commandWord = words[0];

        try {
            switch (commandWord) {
            case "bye":
                return true; // Exit the loop
                
            case "list":
                taskList.list();
                break;

            case "mark":
                int markIndex = Integer.parseInt(words[1]) - 1;
                taskList.mark(markIndex);
                storage.save(taskList.getAllTasks());
                break;

            case "unmark":
                int unmarkIndex = Integer.parseInt(words[1]) - 1;
                taskList.unmark(unmarkIndex);
                storage.save(taskList.getAllTasks());
                break;

            case "delete":
                int delIndex = Integer.parseInt(words[1]) - 1;
                taskList.delete(delIndex);
                storage.save(taskList.getAllTasks());
                break;

            case "todo":
                if (fullCommand.trim().length() <= 4) {
                    throw new KryptoException("OOPS!!! The description of a todo cannot be empty.");
                }
                String todoDesc = fullCommand.substring(5);
                taskList.add(new Todo(todoDesc));
                storage.save(taskList.getAllTasks());
                break;

            case "deadline":
                if (!fullCommand.contains(" /by ")) {
                    throw new KryptoException("OOPS!!! Deadline must have a /by date.");
                }
                String[] dParts = fullCommand.substring(9).split(" /by ");
                try {
                    taskList.add(new Deadline(dParts[0], dParts[1])); // Parsing handled in Deadline class
                    storage.save(taskList.getAllTasks());
                } catch (DateTimeParseException e) {
                    ui.showError("OOPS!!! Date format must be yyyy-mm-dd.");
                }
                break;

            case "event":
                if (!fullCommand.contains(" /from ") || !fullCommand.contains(" /to ")) {
                    throw new KryptoException("OOPS!!! Event must have /from and /to.");
                }
                String[] eParts = fullCommand.substring(6).split(" /from ");
                String[] eTimes = eParts[1].split(" /to ");
                try {
                    taskList.add(new Event(eParts[0], eTimes[0], eTimes[1]));
                    storage.save(taskList.getAllTasks());
                } catch (DateTimeParseException e) {
                    ui.showError("OOPS!!! Date format must be yyyy-mm-dd.");
                }
                break;

            default:
                throw new KryptoException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (KryptoException e) {
            ui.showError(e.getMessage());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.showError("OOPS!!! Invalid task number.");
        }
        return false; // Continue the loop
    }
}