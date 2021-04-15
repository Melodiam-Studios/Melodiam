package coordinates;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;

public class Controller {


    @FXML
    public void initialize(){
        /*
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("note.png").toExternalForm());;
        ImageView imageView = new ImageView();
        imageView.setImage(image);

         */
    }

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
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("resources/white.png").toExternalForm());
        hoveredImage.setImage(image);
    }

    public void onMouseMoved(javafx.scene.input.MouseEvent mouseEvent) {
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("note.png").toExternalForm());
        AnchorPane bo = (AnchorPane) mouseEvent.getSource();
        ImageView hoveredImage = new ImageView();
        hoveredImage.setImage(image);
        //hoveredImage.fitHeightProperty();
        hoveredImage.setFitHeight(34);
        hoveredImage.setFitWidth(23);;
/*
        Point p = MouseInfo.getPointerInfo().getLocation();
        System.out.println(p);
        p.y = 100;
        p.x = 100;
        hoveredImage.setImage(image);
        hoveredImage.setX(p.x);
        hoveredImage.setY(p.y);
        
 */

    }

}
