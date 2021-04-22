package coordinates;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.awt.*;
import java.util.ArrayList;

public class Controller {

    @FXML ImageView previewImage = new ImageView();

    @FXML
    public void initialize(){
    }

    public Pane createLine(int line_length, boolean needSchluessel){
        Pane pane = new Pane();

        ArrayList<Line> lines = new ArrayList<>();
        Line l1 = new Line();
        Line l2 = new Line();
        Line l3 = new Line();
        Line l4 = new Line();
        Line l5 = new Line();

        lines.add(l1);
        lines.add(l2);
        lines.add(l3);
        lines.add(l4);
        lines.add(l5);

        int i = 10;

        for (Line l:lines) {
            l.setStartX(0);
            l.setStartY(i);
            l.setEndX(line_length);
            l.setEndY(i);
            i += 10;
            pane.getChildren().add(l);
        }

        Line l6 = new Line();
        l6.setStartX(line_length);
        l6.setStartY(10);
        l6.setEndX(line_length);
        l6.setEndY(50);

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

        //pane.setOnMousePressed(this::onMousePressed);
        //pane.setOnMouseMoved(this::onMouseMoved);


        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Controller.this.onMousePressed(mouseEvent, pane);
            }
        });

        pane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Controller.this.onMouseMoved(mouseEvent, pane);
            }
        });

        return pane;

    }

    public void onMouseMoved(MouseEvent mouseEvent, Pane pane) {
        System.out.println("Test");

        Point p = new Point();
        p.x = (int) mouseEvent.getX()-5;
        p.y = (int) mouseEvent.getY()-29;

        System.out.println(p);

        Image image = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteUnten.png").toExternalForm());

        previewImage.setImage(image);

        //ImageView hoveredImage = new ImageView();

        try {
            //pane = (Pane) mouseEvent.getSource();
            pane.getChildren().add(previewImage);
        }catch (Exception exception){
            System.out.println("You exactly touched a line");
        }

        previewImage.setX(p.x);
        previewImage.setY(p.y);
        previewImage.setImage(image);
        previewImage.setFitHeight(34);
        previewImage.setFitWidth(11);

        //Setting the preserve ratio of the image view
        previewImage.setPreserveRatio(true);

        //Instantiating the ColorAdjust class
        ColorAdjust colorAdjust = new ColorAdjust();

        //Setting the contrast value
        colorAdjust.setContrast(0.2);

        //Setting the hue value
        colorAdjust.setHue(-0.05);

        //Setting the brightness value
        colorAdjust.setBrightness(0.2);

        //Setting the saturation value
        colorAdjust.setSaturation(0.8);

        //Applying coloradjust effect to the ImageView node
        previewImage.setEffect(colorAdjust);

    }

    public void onMousePressed(MouseEvent mouseEvent, Pane pane) {

        Point p = new Point();
        p.x = (int) mouseEvent.getX()-5;
        p.y = (int) mouseEvent.getY()-29;

        System.out.println(p);

        Image image = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteUnten.png").toExternalForm());

        ImageView hoveredImage = new ImageView();
        try {
            //Pane pane = (Pane) mouseEvent.getSource();
            pane.getChildren().add(hoveredImage);
        }catch (Exception exception){
            System.out.println("You exactly touched a line");
        }


        hoveredImage.setX(p.x);
        hoveredImage.setY(p.y);
        hoveredImage.setImage(image);
        hoveredImage.setFitHeight(34);
        hoveredImage.setFitWidth(11);
    }

}
