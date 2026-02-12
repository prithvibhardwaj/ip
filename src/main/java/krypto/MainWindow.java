package krypto;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Krypto krypto;
    private Image userImage;
    private Image kryptoImage;

    /**
     * Initializes the scroll pane to scroll automatically to the bottom.
     * Loads user and bot images using a robust URL method.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        try {
            // Load images via URL string for better compatibility
            String userUrl = Objects.requireNonNull(this.getClass()
                .getResource("/images/DaUser.png")).toExternalForm();
            String kryptoUrl = Objects.requireNonNull(this.getClass()
                .getResource("/images/DaDuke.png")).toExternalForm();

            userImage = new Image(userUrl);
            kryptoImage = new Image(kryptoUrl);
        } catch (NullPointerException e) {
            // Fallback if images are missing
            System.out.println("Images not found. Chat will proceed without profile pictures.");
            userImage = null;
            kryptoImage = null;
        }
    }

    /**
     * Sets the Krypto instance for this window.
     *
     * @param d The Krypto instance.
     */
    public void setKrypto(Krypto d) {
        krypto = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getKryptoDialog(krypto.getWelcomeMessage(), kryptoImage)
        );
    }

    /**
     * Handles the user input event.
     * Creates dialog boxes for user input and Krypto's reply and appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = krypto.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKryptoDialog(response, kryptoImage)
        );
        userInput.clear();
    }
}
