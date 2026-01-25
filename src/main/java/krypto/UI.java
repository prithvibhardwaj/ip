package krypto;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        String logo = " _  __                  _       \n" +
                      "| |/ /                 | |      \n" +
                      "| ' / _ __ _   _ _ __ | |_ ___ \n" +
                      "|  < | '__| | | | '_ \\| __/ _ \\\n" +
                      "| . \\| |  | |_| | |_) | || (_) |\n" +
                      "|_|\\_\\_|   \\__, | .__/ \\__\\___/\n" +
                      "            __/ | |             \n" +
                      "           |___/|_|             ";
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println(" Hello! I'm Krypto");
        System.out.println(" What can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(" " + message);
    }

    public void showLoadingError() {
        System.out.println(" OOPS!!! Problem loading tasks from file.");
    }
}