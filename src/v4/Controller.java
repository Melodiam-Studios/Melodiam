package v4;

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

    public Pane createLine(int line_length, boolean needSchluessel){
        Pane pane = new Pane();

        Line l1 = new Line();
        l1.setStartX(0);
        l1.setStartY(10);
        l1.setEndX(line_length);
        l1.setEndY(10);

        Line l2 = new Line();
        l2.setStartX(0);
        l2.setStartY(20);
        l2.setEndX(line_length);
        l2.setEndY(20);

        Line l3 = new Line();
        l3.setStartX(0);
        l3.setStartY(30);
        l3.setEndX(line_length);
        l3.setEndY(30);

        Line l4 = new Line();
        l4.setStartX(0);
        l4.setStartY(40);
        l4.setEndX(line_length);
        l4.setEndY(40);

        Line l5 = new Line();
        l5.setStartX(0);
        l5.setStartY(50);
        l5.setEndX(line_length);
        l5.setEndY(50);

        Line l6 = new Line();
        l6.setStartX(line_length);
        l6.setStartY(10);
        l6.setEndX(line_length);
        l6.setEndY(50);

        pane.getChildren().add(l1);
        pane.getChildren().add(l2);
        pane.getChildren().add(l3);
        pane.getChildren().add(l4);
        pane.getChildren().add(l5);
        pane.getChildren().add(l6);

        if (needSchluessel){
            Image image = new Image(getClass().getResource("/resources/bilder_noten/Violinschluessel.png").toExternalForm());
            ImageView notenSchlüssel = new ImageView(image);
            pane.getChildren().add(notenSchlüssel);
            int vio_size = 40;
            notenSchlüssel.setFitHeight(vio_size * 1.705);
            notenSchlüssel.setFitWidth(vio_size);
            notenSchlüssel.setY(-5);
        }

        pane.setOnMousePressed(this::onMousePressed);

        return pane;

    }

    public void onMouseMoved(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("Test");

        Point p = new Point();
        p.x = (int) mouseEvent.getX()-5;
        p.y = (int) mouseEvent.getY()-29;

        System.out.println(p);

        Image image = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteUnten.png").toExternalForm());

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

    public void onMousePressed(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("Test");

        Point p = new Point();
         p.x = (int) mouseEvent.getX()-5;
         p.y = (int) mouseEvent.getY()-29;

        System.out.println(p);

        Image image = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteUnten.png").toExternalForm());

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
        Image image = new Image(getClass().getResource("note.png").toExternalForm());
        StackPane bo = (StackPane) mouseEvent.getSource();
        System.out.println(bo);
        ImageView hoveredImage = new ImageView();
        hoveredImage.setImage(image);
        //hoveredImage.fitHeightProperty();
        hoveredImage.setFitHeight(34);
        hoveredImage.setFitWidth(23);;
    }

}
