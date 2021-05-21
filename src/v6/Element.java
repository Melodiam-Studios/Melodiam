package v6;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * Element makes a generic type that combines {@link Note} and {@link Pause}
 */
public abstract class Element {

    /**
     * Pane where the images get placed
     */
    @FXML
    protected Pane pane;

    /**
     * Image to display the Note/Pause
     */
    @FXML
    protected Image img;

    /**
     * ImageView to display the Note/Pause
     */
    @FXML
    protected ImageView imageView = new ImageView();

    /**
     * Variable responsible for showing the Vorzeichen of the note.
     * It gets set by the constructor-called function changeNote() when a new note is placed
     */
    @FXML
    protected ImageView vorzeichenView = new ImageView();

}
