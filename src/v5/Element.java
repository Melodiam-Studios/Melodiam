package v5;

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

}
