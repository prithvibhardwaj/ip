package krypto;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text, image, and type.
     * Applies styling based on whether it is the user or Krypto speaking.
     *
     * @param text The text to display.
     * @param img  The image to display.
     * @param type The type of speaker ("USER" or "KRYPTO").
     */
    private DialogBox(String text, Image img, String type) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);

        if (img != null) {
            displayPicture.setImage(img);

            // 1. Force the image to fit the 35x35 box exactly
            displayPicture.setFitWidth(50.0);
            displayPicture.setFitHeight(50.0);
            displayPicture.setPreserveRatio(false); // Crucial for non-square images

            // 2. Create the circular clip (Radius = 17.5)
            Circle clip = new Circle(25, 25, 25);
            displayPicture.setClip(clip);
        } else {
            // Fallback: Hide if missing to prevent layout issues
            displayPicture.setVisible(false);
            displayPicture.setManaged(false);
        }

        // Apply CSS classes based on the type
        if (type.equals("USER")) {
            dialog.getStyleClass().add("reply-label");
        } else {
            dialog.getStyleClass().add("text-label");
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for the user (image on the right, blue bubble).
     *
     * @param text The user's input text.
     * @param img  The user's profile image.
     * @return A DialogBox representing the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "USER");
    }

    /**
     * Creates a dialog box for Krypto (image on the left, gray bubble).
     *
     * @param text The response text from Krypto.
     * @param img  Krypto's profile image.
     * @return A DialogBox representing Krypto's response.
     */
    public static DialogBox getKryptoDialog(String text, Image img) {
        var db = new DialogBox(text, img, "KRYPTO");
        db.flip();
        return db;
    }
}
