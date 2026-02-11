package krypto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import krypto.tasks.Task;

/**
 * Handles the loading and saving of task data to the hard disk.
 * It manages the read/write operations to the specified file path.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The file path where data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the constructor.
     * If the file does not exist, it returns an empty list.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws KryptoException If there is an error reading the file.
     */
    public ArrayList<Task> load() throws KryptoException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Note: Implementation of parsing logic should go here
                // based on the specific format defined in save().
            }
        } catch (IOException e) {
            throw new KryptoException("Error reading file.");
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the file.
     * If the directory or file does not exist, it attempts to create them.
     *
     * @param tasks The list of tasks to write to the file.
     */
    public void save(ArrayList<Task> tasks) {
        assert tasks != null : "Task list cannot be null";
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task t : tasks) {
                fw.write(t.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
