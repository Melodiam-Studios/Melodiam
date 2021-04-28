package vL_2;

import com.sun.javafx.geom.Point2D;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Line;
import java.util.ArrayList;

public class Controller {

    @FXML ImageView previewImage = new ImageView();
    int offset = -15;

    @FXML
    ScrollPane scrollP;

    @FXML
    Button b1;

    private int row = 0;
    private int column = 0;

    public void buttonClickHandler(ActionEvent evt){
        addFile();
    }


    private void drawPane(ArrayList<Pane> arr, GridPane root) {
        int column = 0, row = 0;

        root.getChildren().clear();
        for (Pane line : arr) {
            root.add(line, column, row);

            column++;
            if (column % 4 == 0) {
                row++;
                column = 0;

            }
        }
    }

    /*public void addPane(){

        if (event.getCode() == KeyCode.LEFT){
            if (column >= 0) column--;
            if (column < 0) {
                row--;
                column = 3;
            }

            System.out.println("Remove" + column + ", " + row);
            //storeLines.add(controller.createLine(100,true));
            storeLines.remove(storeLines.size()-1);
            drawPane(storeLines, mainInputPane);
        }

        if (event.getCode() == KeyCode.RIGHT){
            System.out.println("Addbefor" + column + ", " + row);

            if (column >= 4){
                row++;
                column = 0;

            }

            System.out.println("Add" + column + ", " + row);

            mainInputPane.addColumn(column);
            mainInputPane.addRow(row);

            //gibt den einzelnen zeilen eine fixe größe so das sie sich nicht verändern können
            RowConstraints rowWith = new RowConstraints(100);
            mainInputPane.getRowConstraints().add(rowWith);

            ColumnConstraints columnWith = new ColumnConstraints(100);
            mainInputPane.getColumnConstraints().add(columnWith);

            if (column == 0){
                storeLines.add(controller.createLine(100,true));
                drawPane(storeLines, mainInputPane);

                //mainInputPane.add(controller.createLine(100,true), column,row);
            }else{

                storeLines.add(controller.createLine(100,false));
                drawPane(storeLines, mainInputPane);
                //mainInputPane.add(controller.createLine(100,false), column,row);
            }
            column++;
        }
    }*/


    public void addFile(){
        System.out.println("test");
        Label label = new Label();
        GridPane mainInputPane = new GridPane();

/*        mainInputPane.addColumn(column);
        mainInputPane.addRow(row);
        mainInputPane.add(label, column, row);
        mainInputPane.add(label,0, 0);
*/
        column = 0;
        row = 0;

        ArrayList<Pane> storeLines = new ArrayList<>();         //alle Panes werden hier gespeichert und es werden dann immer die aufgerufen die es benötigt

        scrollP.setContent(mainInputPane);

    }

    public ArrayList<Point2D> fillList(ArrayList<Point2D> listsWithPossiblePositions) {
        int notenInTakt = 4 + 1;
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

    public Pane createLine(int line_length, boolean needSchluessel){
        ArrayList<Point2D> listsWithPossiblePositions = new ArrayList<Point2D>();
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

        int i = 35;

        if (needSchluessel){
            Image image = new Image(getClass().getResource("/resources/bilder_noten/Violinschluessel.png").toExternalForm());
            ImageView notenSchlüssel = new ImageView(image);
            pane.getChildren().add(notenSchlüssel);
            int vio_size = 40;
            notenSchlüssel.setFitHeight(vio_size * 1.705);
            notenSchlüssel.setFitWidth(vio_size);
            notenSchlüssel.setY(-15 + i);
            notenSchlüssel.setX(-10);
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

        listsWithPossiblePositions = fillList(listsWithPossiblePositions);
        System.out.println(listsWithPossiblePositions);

        pane.getChildren().add(l6);

        //pane.setOnMousePressed(this::onMousePressed);
        //pane.setOnMouseMoved(this::onMouseMoved);


        ArrayList<Point2D> finalListsWithPossiblePositions1 = listsWithPossiblePositions;
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Controller.this.onMousePressed(mouseEvent, pane, finalListsWithPossiblePositions1);
            }
        });

        ArrayList<Point2D> finalListsWithPossiblePositions = listsWithPossiblePositions;
        pane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Controller.this.onMouseMoved(mouseEvent, pane, finalListsWithPossiblePositions);
            }
        });

        return pane;

    }

    public Point2D objektFang(Point2D p, ArrayList<Point2D> listsWithPossiblePositions){
        int notenInTakt = 4;    // viertelNote
        double shortestDistance = 100;
        Point2D returnPoint = new Point2D();

        for (Point2D point2D : listsWithPossiblePositions) {
            //System.out.println("Point2D: " + point2D);
            //System.out.println("p: " + p);
            double distance = Math.sqrt(Math.pow(Math.abs(point2D.x - p.x), 2)  + Math.pow(Math.abs(point2D.y - p.y), 2));
            //System.out.println("Distance" + distance);
            if (distance <= shortestDistance){
                shortestDistance = distance;
                returnPoint.x = point2D.x;
                returnPoint.y = point2D.y;
            }
            //System.out.println("Shortest Distance: " + shortestDistance);
        }

        //System.out.println("Returning the point: " + p);
        return returnPoint;
    }

    public void onMouseMoved(javafx.scene.input.MouseEvent mouseEvent, Pane pane, ArrayList<Point2D> listsWithPossiblePositions) {

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

        p = objektFang(p, listsWithPossiblePositions);

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

    public void onMousePressed(javafx.scene.input.MouseEvent mouseEvent, Pane pane, ArrayList<Point2D> listsWithPossiblePositions ) {

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

        p = objektFang(p, listsWithPossiblePositions);

        hoveredImage.setX(p.x);
        hoveredImage.setY(p.y+offset);
        hoveredImage.setImage(image);
        hoveredImage.setFitHeight(34);
        hoveredImage.setFitWidth(11);
    }

}
