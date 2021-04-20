package coordinates;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

import java.awt.*;

public class Controller {

    @FXML ImageView previewImage;

    @FXML
    public void initialize(){
    }

    public void onMouseEntered(javafx.scene.input.MouseEvent mouseEvent) {
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("note.png").toExternalForm());
        ImageView hoveredImage = (ImageView) mouseEvent.getSource();
        hoveredImage.setImage(image);
        //hoveredImage.fitHeightProperty();
        hoveredImage.setFitHeight(34);
        hoveredImage.setFitWidth(23);
    }

    public void onMouseMoved(javafx.scene.input.MouseEvent mouseEvent) {

        System.out.println("Test");

        Point p = new Point();
        p.x = (int) mouseEvent.getX()-5;
        p.y = (int) mouseEvent.getY()-29;

        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("resources/bilder_noten/ViertelnoteUnten.png").toExternalForm());
        try {
            Pane stackPane = (Pane) mouseEvent.getSource();
            stackPane.getChildren().add(previewImage);
        }catch (Exception exception){
            System.out.println("You exactly touched a line");
            Line stackPane = (Line) mouseEvent.getSource();
            //stackPane.getChildren().add(hoveredImage);        // still needs to be done
        }


        previewImage.setX(p.x);
        previewImage.setY(p.y);
        previewImage.setImage(image);
        previewImage.setFitHeight(34);
        previewImage.setFitWidth(11);

    }

    public void onMousePressed(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("Test");

        Point p = new Point();
         p.x = (int) mouseEvent.getX()-5;
         p.y = (int) mouseEvent.getY()-29;

        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("resources/bilder_noten/ViertelnoteUnten.png").toExternalForm());

        ImageView hoveredImage = new ImageView();
        try {
            Pane stackPane = (Pane) mouseEvent.getSource();
            stackPane.getChildren().add(hoveredImage);
        }catch (Exception exception){
            System.out.println("You exactly touched a line");
            Line stackPane = (Line) mouseEvent.getSource();
            //stackPane.getChildren().add(hoveredImage);        // still needs to be done
        }


        hoveredImage.setX(p.x);
        hoveredImage.setY(p.y);
        hoveredImage.setImage(image);
        hoveredImage.setFitHeight(34);
        hoveredImage.setFitWidth(11);

    }

    public void onMouseMenuRequested(javafx.scene.input.MouseEvent mouseEvent) {
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("note.png").toExternalForm());
        StackPane bo = (StackPane) mouseEvent.getSource();
        System.out.println(bo);
        ImageView hoveredImage = new ImageView();
        hoveredImage.setImage(image);
        //hoveredImage.fitHeightProperty();
        hoveredImage.setFitHeight(34);
        hoveredImage.setFitWidth(23);;
    }

}
