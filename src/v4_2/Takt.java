package v4_2;

import com.sun.javafx.geom.Point2D;
import javafx.fxml.FXML;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Takt {
    int n = 0;

    Pane pane = new Pane();
    ArrayList<Point2D> sechzehntelPositions = new ArrayList<>(fillList(16));
    ArrayList<Point2D> achtelPositions = new ArrayList<>(fillList(8));
    ArrayList<Point2D> viertelPositions = new ArrayList<>(fillList(4));
    ArrayList<Point2D> halbePositions = new ArrayList<>(fillList(2));
    ArrayList<Point2D> ganzePositions = new ArrayList<>(fillList(1));

    ArrayList<ImageView> notesAsImages = new ArrayList<>();
    @FXML
    ImageView previewImage = new ImageView();
    int offset = -15;

    public Takt(int line_length, boolean needSchluessel){

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

        int i = 35;

        if (needSchluessel){
            Image image = new Image(getClass().getResource("/resources/bilder_noten/Violinschluessel.png").toExternalForm());
            ImageView notenSchluessel = new ImageView(image);
            pane.getChildren().add(notenSchluessel);
            int vio_size = 40;
            notenSchluessel.setFitHeight(vio_size * 1.705);
            notenSchluessel.setFitWidth(vio_size);
            notenSchluessel.setY(-15 + i);
            notenSchluessel.setX(-10);
        }

        Line l6 = new Line();
        l6.setStartX(line_length);
        l6.setStartY(i);
        l6.setEndX(line_length);
        l6.setEndY(i+40);

        for (Line l:lines) {
            l.setStartX(0);
            l.setStartY(i);
            l.setEndX(line_length);
            l.setEndY(i);
            i += 10;
            pane.getChildren().add(l);
        }

        pane.getChildren().add(l6);

        pane.setOnMousePressed(this::onMousePressed);
        pane.setOnMouseMoved(this::onMouseMoved);

/*
        ArrayList<Point2D> finalListsWithPossiblePositions1 = listsWithPossiblePositions;
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Takt.this.onMousePressed(mouseEvent, pane, finalListsWithPossiblePositions1);
            }
        });

        ArrayList<Point2D> finalListsWithPossiblePositions = listsWithPossiblePositions;
        pane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Takt.this.onMouseMoved(mouseEvent, pane, finalListsWithPossiblePositions);
            }
        });


 */
    }

    public ArrayList<Point2D> fillList(int notenInTakt) {
        ArrayList<Point2D> listsWithPossiblePositions = new ArrayList<>();
        notenInTakt += 1;
        float length = 100;
        float height = 115;
        float zeilen = height / 23;
        float spalten = length / notenInTakt;
        for (int i = 1; i <notenInTakt; i++) {
            for (int e = 0; e < 23; e++) {
                listsWithPossiblePositions.add(new Point2D(i * spalten + offset, e * zeilen));
            }
        }

        return listsWithPossiblePositions;
    }

    public Pane getPane() {
        return pane;
    }

    public Point2D objektFang(Point2D p, int notenInTakt) {

        ArrayList<Point2D> listsWithPossiblePositions = new ArrayList<>();

        switch (notenInTakt) {
            case 1:
                listsWithPossiblePositions = ganzePositions;
                break;
            case 2:
                listsWithPossiblePositions = halbePositions;
                break;
            case 4:
                listsWithPossiblePositions = viertelPositions;
                break;
            case 8:
                listsWithPossiblePositions = achtelPositions;
                break;
            case 16:
                listsWithPossiblePositions = sechzehntelPositions;
                break;
        }

        double shortestDistance = 100;
        Point2D returnPoint = new Point2D();

        for (Point2D point2D : listsWithPossiblePositions) {
            //System.out.println("Point2D: " + point2D);
            //System.out.println("p: " + p);

            double distance = Math.sqrt(Math.pow(Math.abs(point2D.x - p.x), 2) + Math.pow(Math.abs(point2D.y - p.y), 2));
            //System.out.println("Distance" + distance);
            if (distance <= shortestDistance) {
                shortestDistance = distance;
                returnPoint.x = point2D.x;
                returnPoint.y = point2D.y;
                //System.out.println("Shortest Distance: " + shortestDistance);
            }
        }
        //System.out.println("Returning the point: " + p);
        return returnPoint;
    }

    public void onMouseMoved(javafx.scene.input.MouseEvent mouseEvent) {

        Point2D p = new Point2D();
        p.x = (float) mouseEvent.getX();
        p.y = (float) mouseEvent.getY();
        //System.out.println("Before: " + p);
        p.x = p.x +5;
        p.y = p.y+offset;
        //System.out.println("After: " + p);

        Image image = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteUnten.png").toExternalForm());

        previewImage.setImage(image);

        //ImageView hoveredImage = new ImageView();

        try {
            //pane = (Pane) mouseEvent.getSource();
            pane.getChildren().add(previewImage);
        }catch (Exception exception){
            //System.out.println("You exactly touched a line");
        }

        if (n == 1)
            p = objektFang(p, 8);
        else
            p = objektFang(p, 4);

        previewImage.setX(p.x);
        previewImage.setY(p.y+offset);

        previewImage.setFitHeight(34);
        previewImage.setFitWidth(11);

        //Setting the preserve ratio of the image view
        //previewImage.setPreserveRatio(true);
        //Instantiating the ColorAdjust class
        ColorAdjust colorAdjust = new ColorAdjust();
        //Setting the contrast value
        colorAdjust.setContrast(-1);
        //Setting the hue value
        //colorAdjust.setHue(-0.05);
        //Setting the brightness value
        //colorAdjust.setBrightness(0.7);
        //Setting the saturation value
        //colorAdjust.setSaturation(0.8);
        //Applying coloradjust effect to the ImageView node
        previewImage.setEffect(colorAdjust);
        previewImage.setImage(image);

    }

    public void onMousePressed(javafx.scene.input.MouseEvent mouseEvent) {

        if (mouseEvent.getButton() == MouseButton.SECONDARY)
            onRightClick(mouseEvent);
        else {
            Point2D p = new Point2D();
            p.x = (float) mouseEvent.getX();
            p.y = (float) mouseEvent.getY();
            System.out.println("Before: " + p);
            p.x = p.x +5;
            p.y = p.y+offset;
            System.out.println("After: " + p);

            Image image = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteUnten.png").toExternalForm());

            ImageView hoveredImage = new ImageView();
            try {
                //Pane pane = (Pane) mouseEvent.getSource();
                pane.getChildren().add(hoveredImage);
            }catch (Exception exception){
                System.out.println("You exactly touched a line");
            }

            if (n == 1)
                p = objektFang(p, 1);
            else
                p = objektFang(p, 4);



            hoveredImage.setX(p.x);
            hoveredImage.setY(p.y+offset);
            hoveredImage.setImage(image);
            hoveredImage.setFitHeight(34);
            hoveredImage.setFitWidth(11);
            notesAsImages.add(hoveredImage);
        }
    }

    public void onRightClick(javafx.scene.input.MouseEvent mouseEvent){
        System.out.println("Right Click");

        Point2D p = new Point2D();
        p.x = (float) mouseEvent.getX();
        p.y = (float) mouseEvent.getY();

        //p = objektFang(p, )
        double shortestDistance = 100;
        Point2D returnPoint = new Point2D();
        ImageView img = new ImageView();

        for (ImageView imageView : notesAsImages) {
            Point2D point2D = new Point2D(((float) imageView.getX()), ((float) imageView.getY()));
            //System.out.println("Point2D: " + point2D);
            //System.out.println("p: " + p);

            double distance = Math.sqrt(Math.pow(Math.abs(point2D.x - p.x), 2) + Math.pow(Math.abs(point2D.y - p.y), 2));
            //System.out.println("Distance" + distance);
            if (distance <= shortestDistance) {
                shortestDistance = distance;
                img = imageView;
                //System.out.println("Shortest Distance: " + shortestDistance);
            }
        }

        img.setVisible(false);
    }
}
