package krypto;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Krypto using FXML.
 */
public class Main extends Application {

    private Krypto krypto = new Krypto();

    /**
     * Starts the primary stage of the application.
     * Loads the MainWindow FXML and sets up the scene.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Krypto");
            stage.setResizable(false);

            // Inject the logic into the controller
            fxmlLoader.<MainWindow>getController().setKrypto(krypto);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
