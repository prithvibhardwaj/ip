package krypto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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
     * @throws KryptoException If there is an error reading the file or parsing the data.
     */
    public ArrayList<Task> load() throws KryptoException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    continue;
                }
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task = null;
                switch (type) {
                case "T":
                    task = new krypto.tasks.Todo(description);
                    break;
                case "D":
                    if (parts.length >= 4) {
                        LocalDate byDate = LocalDate.parse(parts[3]);
                        task = new krypto.tasks.Deadline(description, byDate);
                    }
                    break;
                case "E":
                    if (parts.length >= 5) {
                        LocalDate fromDate = LocalDate.parse(parts[3]);
                        LocalDate toDate = LocalDate.parse(parts[4]);
                        task = new krypto.tasks.Event(description, fromDate, toDate);
                    }
                    break;
                default:
                    System.out.println("Unknown task type found in file: " + type);
                    break;
                }
                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
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
    public void save(ArrayList<Task> tasks) throws KryptoException {
        try {
            File f = new File(this.filePath);
            if (f.getParentFile() != null && !f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new KryptoException("Error saving file: " + e.getMessage());
        }
    }
}
