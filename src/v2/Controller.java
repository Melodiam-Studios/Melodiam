package v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.event.MouseEvent;
import java.io.File;

public class Controller {



    public void onMouseEntered(javafx.scene.input.MouseEvent mouseEvent) {
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("note.png").toExternalForm());
        ImageView hoveredImage = (ImageView) mouseEvent.getSource();
        hoveredImage.setImage(image);
        //hoveredImage.fitHeightProperty();
        hoveredImage.setFitHeight(34);
        hoveredImage.setFitWidth(23);
    }

    public void onMouseExcited(javafx.scene.input.MouseEvent mouseEvent) {
        ImageView hoveredImage = (ImageView) mouseEvent.getSource();
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("white.png").toExternalForm());
        hoveredImage.setImage(image);
    }
}
