package v5;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Element {
    @FXML
    protected Image img;

    @FXML
    protected ImageView imageView = new ImageView();

}
