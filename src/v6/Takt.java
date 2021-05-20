package v6;

import com.sun.javafx.geom.Point2D;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.ArrayList;

/**
 * Class Takt is responsable for every Takt that gets created. For every line a Takt-object gets created.
 * A takt can contain 1 ganzeNote, 2 halbeNote, 4 vierteNote, 8 achtelNote, 16 sechzehntelNote
 * it contains the handlers that catches the mouseClick and the the mouseMoved
 *
 * @author Silas Demez
 */
public class Takt {
    /**
     * Variable responsible for setting the length of the takt
     */
    float line_length = 275;
    /**
     * Variable responsible for setting the height of the takt
     */
    float height = 115;
    /**
     * variable for knowing which type of note or pause is chosen.
     * initial value is just randomly chosen
     */
    int notenInTakt=10;
    /**
     * Variable responsible for the vorzeichen distinguishing
     * Kreuzvorzeichen --> 1
     * B.Vorzeichen --> 0
     * kein Vorzeichen --> -1
     */
    int vorzeichen = 3;

    double belegt = 0;

    FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));      // load the fxml that is responsible for the main graphics

    /**
     * Main Pane on which the lines, Notes and Takte get placed
     */
    Pane pane = new Pane();


    ArrayList<Point2D> sechzehntelPositions = new ArrayList<>(fillList(16));    // list has the possible positions for a sechzehntelNote in a Takt
    ArrayList<Point2D> achtelPositions = new ArrayList<>(fillList(8));          // list has the possible positions for a achtelNote in a Takt
    ArrayList<Point2D> viertelPositions = new ArrayList<>(fillList(4));         // list has the possible positions for a viertelNote in a Takt
    ArrayList<Point2D> halbePositions = new ArrayList<>(fillList(2));           // list has the possible positions for a halbeNote in a Takt
    ArrayList<Point2D> ganzePositions = new ArrayList<>(fillList(1));           // list has the possible positions for a ganzeNote in a Takt

    /**
     * List that contains the Noten and Pausen in the right order
     */
    ArrayList<Element> elements = new ArrayList<>();


    /**
     * ImageView responsible for the Preview of the selected Note or Pause
     */
    @FXML
    ImageView previewImageView = new ImageView();


    /**
     * ImageView responsible for the Preview of the Vorzeichen
     */
    @FXML
    ImageView previewVorzeichenView = new ImageView();

    /**
     * Note responsible for the preview of Noten
     */
    Note previewNote = new Note(notenInTakt, 0, vorzeichen, new Point2D(0,0));
    /**
     * Pause responsible for the preview of Pausen
     */
    Pause previewPause = new Pause(notenInTakt);
    /**
     * offsets the element a little
     */
    int offsetY = -30;

    /**
     * When a new Takt element gets created it draws the lines where the notes get placed
     *
     * @param needSchluessel for placing the Notenschlüssel --> needSchluessel == true --> Notenschluessel gets placed
     */
    public Takt(boolean needSchluessel){

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

        int i = 35;     // offsetY

        if (needSchluessel){        // places the Violinschluessel and resizes it
            Image image = new Image(getClass().getResource("/resources/bilder_noten/Violinschluessel.png").toExternalForm());
            ImageView notenSchluessel = new ImageView(image);
            pane.getChildren().add(notenSchluessel);
            int vio_size = 40;
            notenSchluessel.setFitHeight(vio_size * 1.705);
            notenSchluessel.setFitWidth(vio_size);
            notenSchluessel.setY(-15 + i);
            notenSchluessel.setX(-10);
        }

        // lne at the right end (vertical)
        Line l6 = new Line();
        l6.setStartX(line_length);
        l6.setStartY(i);
        l6.setEndX(line_length);
        l6.setEndY(i+40);

        // lines that form the notengerüst (horizontal)
        for (Line l:lines) {
            l.setStartX(0);
            l.setStartY(i);
            l.setEndX(line_length);
            l.setEndY(i);
            i += 10;
            pane.getChildren().add(l);
        }

        pane.getChildren().add(l6);

        pane.setOnMousePressed(this::onMousePressed);   // set when Mouse is pressed
        pane.setOnMouseMoved(this::onMouseMoved);       // set when Mouse is moved
        pane.setOnMouseExited(this::onMouseExited);       // set when Mouse exits
    }

    /**
     * Function fills the arraylists with the possible positions
     *
     * @param notenInT type of Note or Pause
     * @return list with possible positions
     */
    public ArrayList<Point2D> fillList(int notenInT) {
        ArrayList<Point2D> listsWithPossiblePositions = new ArrayList<>();
        notenInT += 1;
        float zeilen = height / 23;
        float spalten = (float) line_length / (float) notenInT;
        for (int i = 1; i <notenInT; i++) {
            for (int e = 0; e < 23; e++) {
                listsWithPossiblePositions.add(new Point2D(i * spalten, e * zeilen));
            }
        }

        return listsWithPossiblePositions;
    }

    /**
     * @return return the Takt GUI to the Controller or the Note
     */
    public Pane getPane() {
        return pane;
    }

    /**
     * the pane gets set by the Note
     *
     * @param pane pane changed from Note
     */
    public void setPane(Pane pane) {
        this.pane = pane;
    }

    /**
     * Function that only allows the Noten and Pausen in a specific point
     *
     * @param p Coordinates of MousePress
     * @param notenInT the type of Note/Pause that is chosen
     * @param belegt
     * @return the nearest position where the Element can be placed as point
     */
    public Point2D objektFang(Point2D p, int notenInT, double belegt) {

        ArrayList<Point2D> listsWithPossiblePositions = new ArrayList<>();

        double x = 0;

        switch (notenInT) {
            case 1:
                listsWithPossiblePositions = ganzePositions;
                // geteilt durch 1

                break;
            case 2:
                listsWithPossiblePositions = halbePositions;
                // geteilt durch 2
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

        x = belegt * listsWithPossiblePositions.size() + ((double) 1/notenInTakt) * listsWithPossiblePositions.size();

        System.out.println("List length: " + listsWithPossiblePositions.size());
        System.out.println("X: " + x);



        double shortestDistance = 100;
        Point2D returnPoint = new Point2D();
        /*
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

         */

        // wenn eine Note platziert geteilt durch 4 bei viertelnote
        // wenn zwei dann geteilt
        try {
            for (int i = (int) (listsWithPossiblePositions.size()-(listsWithPossiblePositions.size()-x) - ((double) 1/notenInTakt) * listsWithPossiblePositions.size()); i<listsWithPossiblePositions.size()-(listsWithPossiblePositions.size()-x); i++) {
                Point2D point2D = listsWithPossiblePositions.get(i);
                double distance = Math.sqrt(Math.pow(Math.abs(point2D.x - p.x), 2) + Math.pow(Math.abs(point2D.y - p.y), 2));
                //System.out.println("Distance" + distance);
                if (distance <= shortestDistance) {
                    shortestDistance = distance;
                    returnPoint.x = point2D.x;
                    returnPoint.y = point2D.y;
                    //System.out.println("Shortest Distance: " + shortestDistance);
                }
            }

        }catch (Exception e){
            returnPoint.x = 0;
            returnPoint.y = 0;
        }
        return returnPoint;
    }

    /**
     * @param mouseEvent
     */
    public void onMouseMoved(javafx.scene.input.MouseEvent mouseEvent) {

        notenInTakt = Controller.notenInTakt;
        vorzeichen = Controller.vorzeichen;

        Point2D p = new Point2D();
        p.x = (float) mouseEvent.getX();
        p.y = (float) mouseEvent.getY();

        if (notenInTakt % 5 == 0){
            // Pause
            //System.out.println("Pause");
            p = objektFang(new Point2D(p.x-10,p.y), notenInTakt/5, belegt);
            //System.out.println("After: " + p);
            previewPause.setPause(notenInTakt);
            previewImageView = previewPause.getImageView();
            previewImageView.setX(p.x);
            int tempOffsetY = previewPause.getOffsetY();
            previewImageView.setY(tempOffsetY + 43);
            //System.out.println("Setting the pause at x: " + previewImage.getX() + ", y: " + previewImage.getY());
            //System.out.println(previewPause.toString());
        }else{
            //Note
            p = objektFang(new Point2D(p.x-10,p.y), notenInTakt, belegt);
            if (p.x == 0 && p.y == 0)
                previewNote.setNote(notenInTakt,0,vorzeichen);

            previewNote.setNote(notenInTakt,(int) (p.y / 5) + 1, vorzeichen);
            previewImageView = previewNote.getImageView();
            previewVorzeichenView = previewNote.getVorzeichenView();

            previewVorzeichenView.setX(previewVorzeichenView.getX() + p.x);
            previewVorzeichenView.setY(previewVorzeichenView.getY() + p.y + offsetY);

            previewImageView.setX(p.x);
            int tempOffsetY = previewNote.getNotenOffsetY();
            previewImageView.setY(tempOffsetY + p.y+ offsetY);/*
            p = objektFang(new Point2D(p.x-10,p.y), notenInTakt);
            System.out.println("After: " + p);
            Note note = new Note(notenInTakt, (int) (p.y / 5) + 1) ;
            imageView = note.getImageView();
            imageView.setX(p.x);
            imageView.setY(imageView.getY() + p.y + notenOffset);
            System.out.println(note.toString());
            */
        }

        try {
            //pane = (Pane) mouseEvent.getSource();
            pane.getChildren().add(previewImageView);
            pane.getChildren().add(previewVorzeichenView);
        }catch (Exception exception){
            //System.out.println("You exactly touched a line");
        }


        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(-1);
        previewImageView.setEffect(colorAdjust);
        previewVorzeichenView.setEffect(colorAdjust);
        //previewImage.setImage(image);
    }

    public void onMousePressed(javafx.scene.input.MouseEvent mouseEvent) {

        System.out.println("_________________________________________________________");
        System.out.println(this.getPane().toString());
        vorzeichen = Controller.vorzeichen;

        System.out.println("NotenInTakt: " + notenInTakt);

        Point2D p = new Point2D();
        p.x = (float) mouseEvent.getX();
        p.y = (float) mouseEvent.getY();
        System.out.println("Before: " + p);

        if (mouseEvent.getButton() == MouseButton.SECONDARY)
            onRightClick(mouseEvent);
        else {


            ImageView imageView;
            ImageView vorzeichenView = null;

            if (notenInTakt % 5 == 0){
                // Pause
                System.out.println("Pause");
                p = objektFang(new Point2D(p.x-10,p.y), notenInTakt/5, belegt);
                Pause pause = new Pause(notenInTakt);
                System.out.println("After: " + p);
                imageView = pause.getImageView();
                imageView.setX(p.x);
                imageView.setY(imageView.getY() + 43);
                System.out.println("Setting the pause at x: " + imageView.getX() + ", y: " + imageView.getY());
                System.out.println(pause.toString());
                elements.add(pause);
                belegt += 1/( (double) notenInTakt/5);
            }else{
                //Note
                System.out.println("Belegt: " + belegt);
                p = objektFang(new Point2D(p.x-10,p.y), notenInTakt, belegt);
                System.out.println("After: " + p);
                double temPointY = p.y;
                p.y += offsetY;
                
                int position = (int) (temPointY / 5) + 1;

                Note note = new Note(notenInTakt, position, vorzeichen, p);
                belegt += (1/ (double) notenInTakt);

                imageView = note.getImageView();
                vorzeichenView = note.getVorzeichenView();

                vorzeichenView.setX(vorzeichenView.getX() + p.x);
                vorzeichenView.setY(vorzeichenView.getY() + p.y);

                imageView.setX(imageView.getX() + p.x);
                imageView.setY(imageView.getY() + p.y);

                //System.out.println("Position der Note: " + this.position);
                if (vorzeichen == 2) {
                    vorzeichen = 0;

                    switch (Notenblatt.getTonleiter()) {
                        case -6:
                            if (position == 6) vorzeichen = -1;         //b
                            else if (position == 2) vorzeichen = -1;    //es
                            else if (position == 5) vorzeichen = -1;    //as
                            else if (position == 1) vorzeichen = -1;    //des
                            else if (position == 4) vorzeichen = -1;    //ges
                            else if (position == 0) vorzeichen = -1;
                            break;
                        case -5:
                            if (position == 6) vorzeichen = -1;         //b
                            else if (position == 2) vorzeichen = -1;    //es
                            else if (position == 5) vorzeichen = -1;    //as
                            else if (position == 1) vorzeichen = -1;    //des
                            else if (position == 4) vorzeichen = -1;    //ges
                            break;
                        case -4:
                            if (position == 6) vorzeichen = -1;         //b
                            else if (position == 2) vorzeichen = -1;    //es
                            else if (position == 5) vorzeichen = -1;    //as
                            else if (position == 1) vorzeichen = -1;    //des
                            break;
                        case -3:
                            if (position == 6) vorzeichen = -1;         //b
                            else if (position == 2) vorzeichen = -1;    //es
                            else if (position == 5) vorzeichen = -1;    //as
                            break;
                        case -2:
                            if (position == 6) vorzeichen = -1;         //b
                            else if (position == 2) vorzeichen = -1;    //es
                            break;
                        case -1:
                            if (position == 6) vorzeichen = -1;
                            break;
                        case 0:
                            vorzeichen = 0;
                            break;
                        case 1:
                            if (position == 3) vorzeichen = 1;        //fis
                            break;
                        case 2:
                            if (position == 3) vorzeichen = 1;        //fis
                            else if (position == 0) vorzeichen = 1;   //cis
                            break;
                        case 3:
                            if (position == 3) vorzeichen = 1;        //fis
                            else if (position == 0) vorzeichen = 1;   //cis
                            else if (position == 4) vorzeichen = 1;   //gis
                            break;
                        case 4:
                            if (position == 3) vorzeichen = 1;        //fis
                            else if (position == 0) vorzeichen = 1;   //cis
                            else if (position == 4) vorzeichen = 1;   //gis
                            else if (position == 1) vorzeichen = 1;   //dis
                            break;
                        case 5:
                            if (position == 3) vorzeichen = 1;        //fis
                            else if (position == 0) vorzeichen = 1;   //cis
                            else if (position == 4) vorzeichen = 1;   //gis
                            else if (position == 1) vorzeichen = 1;   //dis
                            else if (position == 5) vorzeichen = 1;   //ais
                            break;
                        case 6:
                            if (position == 3) vorzeichen = 1;        //fis
                            else if (position == 0) vorzeichen = 1;   //cis
                            else if (position == 4) vorzeichen = 1;   //gis
                            else if (position == 1) vorzeichen = 1;   //dis
                            else if (position == 5) vorzeichen = 1;   //ais
                            else if (position == 2) vorzeichen = 1;   //eis
                            break;
                    }

                    note.setVorzeichen(vorzeichen);
                }
                //System.out.println(note.toString());

                elements.add(note);
                Notenblatt.addNote(note);
            }

            try {
                //Pane pane = (Pane) mouseEvent.getSource();
                pane.getChildren().add(imageView);
                pane.getChildren().add(vorzeichenView);
            }catch (Exception exception){
                System.out.println("You exactly touched a line");
            }


        }
    }

    //Preview löschen wenn man aus dem Takt geht
    private void onMouseExited(javafx.scene.input.MouseEvent mouseEvent) {
        previewVorzeichenView.setImage(null);
        previewImageView.setImage(null);
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

        for (Element element : elements) {

            Point2D point2D = new Point2D(((float) element.imageView.getX()), ((float) element.imageView.getY()));
            //System.out.println("Point2D: " + point2D);
            //System.out.println("p: " + p);

            double distance = Math.sqrt(Math.pow(Math.abs(point2D.x - p.x), 2) + Math.pow(Math.abs(point2D.y - p.y), 2));
            //System.out.println("Distance" + distance);
            if (distance <= shortestDistance) {
                shortestDistance = distance;
                img = element.imageView;
                returnPoint = point2D;
                //System.out.println("Shortest Distance: " + shortestDistance);
            }
        }

        //img.setVisible(false);

        for (Element element : elements) {
            Point2D point2D = new Point2D(((float) element.imageView.getX()), ((float) element.imageView.getY()));

            double distance = Math.sqrt(Math.pow(Math.abs(point2D.x - returnPoint.x), 2) + Math.pow(Math.abs(point2D.y - returnPoint.y), 2));
            if (distance == 0){
                elements.remove(element);
                // element.remove from list with notes usw.
                pane.getChildren().remove(element.imageView);
                break;
            }

        }
        //System.out.println(notesAsImages);
    }

    public void setNotenInTakt(int notenInTakt) {
        this.notenInTakt = notenInTakt;
    }

    @Override
    public String toString() {
        return "Takt{" +
                "elements=" + elements +
                '}';
    }
}