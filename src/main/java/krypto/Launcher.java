package krypto;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * The main entry point to launch the JavaFX application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
