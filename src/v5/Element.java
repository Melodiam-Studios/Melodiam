package v5;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Element makes a generic type that combines {@link Note} and {@link Pause}
 */
public abstract class Element {
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
