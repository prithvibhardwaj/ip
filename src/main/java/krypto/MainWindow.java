package krypto;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image kryptoImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the scroll pane to scroll automatically to the bottom.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
