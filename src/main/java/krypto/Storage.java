/**
 * Handles the loading and saving of task data to the hard disk.
 * It manages the read/write operations to the specified file path.
 */

package krypto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import krypto.tasks.Task;

public class Storage {
    private String filePath;

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
                // Simple parser for the saved file format
                // Assumes format: T | 1 | read book
                // You might need to adjust this matching your Level-7 save format
                // logic here depends on how you implemented save in Level 7.
                // This is a placeholder for your load logic.
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
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Create directories if needed
            FileWriter writer = new FileWriter(file);
            for (Task t : tasks) {
                // simple save format example
                writer.write(t.toString() + "\n"); 
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(" OOPS!!! Error saving tasks.");
        }
    }
}
