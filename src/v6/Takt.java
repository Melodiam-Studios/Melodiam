package v6;

import com.sun.javafx.geom.Point2D;
import javafx.fxml.FXML;
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
     * DeadZone defines the amount of space that is free in the very first takt
     */
    int deadZone = 80;

    /**
     * Variable responsible for setting the length of the takt
     */
    float line_length = 275;
    /**
     * Variable responsible for setting the length of the first takt in a row
     */
    float first_line_length = 355;
    /**
     * Variable responsible for setting the height of the takt
     */
    float height = 115;
    /**
     * variable for knowing which type of note or pause is chosen.
     * initial value is just randomly chosen
     */
    int notenInTakt = 10;
    /**
     * Variable responsible for the vorzeichen distinguishing
     * Kreuzvorzeichen --> 1
     * B.Vorzeichen --> 0
     * kein Vorzeichen --> -1
     */
    int vorzeichen = 3;
    int id;

    double belegt = 0;

    /**
     * Main Pane on which the lines, Notes and Takte get placed
     */
    Pane pane = new Pane();

    ImageView imageViewTonleiter = new ImageView();


    ArrayList<Point2D> sechzehntelPositions = new ArrayList<>(fillList(16, false));    // list has the possible positions for a sechzehntelNote in a Takt
    ArrayList<Point2D> achtelPositions = new ArrayList<>(fillList(8, false));          // list has the possible positions for a achtelNote in a Takt
    ArrayList<Point2D> viertelPositions = new ArrayList<>(fillList(4, false));         // list has the possible positions for a viertelNote in a Takt
    ArrayList<Point2D> halbePositions = new ArrayList<>(fillList(2, false));           // list has the possible positions for a halbeNote in a Takt
    ArrayList<Point2D> ganzePositions = new ArrayList<>(fillList(1, false));           // list has the possible positions for a ganzeNote in a Takt

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
    Note previewNote = new Note(notenInTakt, 0, vorzeichen, new Point2D(0, 0));
    /**
     * Pause responsible for the preview of Pausen
     */
    Pause previewPause = new Pause(notenInTakt, new Point2D(0, 0));
    /**
     * offsets the element a little
     */
    int offsetY = -30;

    /**
     * When a new Takt element gets created it draws the lines where the notes get placed
     *
     * @param isFirstTakt for placing the Notenschlüssel, Takt, Tonleiter --> isFirstTakt == true --> Notenschluessel, Takt and Tonleiter gets placed
     */
    public Takt(boolean isFirstTakt, int id) {

        this.id = id;

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
        Line l6 = new Line();

        if (isFirstTakt) {        // places the Violinschluessel and resizes it, place Tonleiter, place Takt

            ganzePositions = fillList(1, true);
            halbePositions = fillList(2, true);
            viertelPositions = fillList(4, true);
            achtelPositions = fillList(8, true);
            sechzehntelPositions = fillList(16, true);


            Image image = new Image(getClass().getResource("/resources/bilder_noten/Violinschluessel.png").toExternalForm());
            ImageView notenSchluessel = new ImageView(image);
            pane.getChildren().add(notenSchluessel);
            int vio_size = 40;
            notenSchluessel.setFitHeight(vio_size * 1.705);
            notenSchluessel.setFitWidth(vio_size);
            notenSchluessel.setY(-15 + i);
            notenSchluessel.setX(-10);

            //Takt PNG hinzufügen
            Image imageTkat = new Image(getClass().getResource("/resources/bilder_takte/44-Takt.PNG").toExternalForm());
            ImageView imageView = new ImageView(imageTkat);

            if (Notenblatt.getTaktart() == 44) {
                pane.getChildren().add(imageView);
            }
            int takt_size = 40;
            imageView.setFitHeight(takt_size);
            imageView.setFitWidth(takt_size / 1.705);
            imageView.setY(35);
            imageView.setX(75);


            drawTonleiter();

            //line beim ersten takt in einer Zeile länger setzen
            // lne at the right end (vertical)
            l6.setStartX(first_line_length);
            l6.setStartY(i);
            l6.setEndX(first_line_length);
            l6.setEndY(i + 40);

        } else {
            // lne at the right end (vertical)
            l6.setStartX(line_length);
            l6.setStartY(i);
            l6.setEndX(line_length);
            l6.setEndY(i + 40);
        }


        // lines that form the notengerüst (horizontal)
        for (Line l : lines) {
            l.setStartX(0);
            l.setStartY(i);
            if (isFirstTakt) l.setEndX(first_line_length);
            else l.setEndX(line_length);
            l.setEndY(i);
            i += 10;
            pane.getChildren().add(l);
        }

        pane.getChildren().add(l6);

        pane.setOnMousePressed(this::onMousePressed);   // set when Mouse is pressed
        pane.setOnMouseMoved(this::onMouseMoved);       // set when Mouse is moved
        pane.setOnMouseExited(this::onMouseExited);     // set when Mouse exits
    }


    public void drawTonleiter(){
        //Tonleiter hinzufügen
        Image imgTonleiter = null;
        switch (Notenblatt.getTonleiter()) {
            case 6:
                imgTonleiter = new Image(getClass().getResource("/resources/bilder_tonleiter/Fis-Dur.png").toExternalForm());
                break;
            case 5:
                imgTonleiter = new Image(getClass().getResource("/resources/bilder_tonleiter/H-Dur.png").toExternalForm());
                break;
            case 4:
                imgTonleiter = new Image(getClass().getResource("/resources/bilder_tonleiter/E-Dur.png").toExternalForm());
                break;
            case 3:
                imgTonleiter = new Image(getClass().getResource("/resources/bilder_tonleiter/A-Dur.png").toExternalForm());
                break;
            case 2:
                imgTonleiter = new Image(getClass().getResource("/resources/bilder_tonleiter/D-Dur.png").toExternalForm());
                break;
            case 1:
                imgTonleiter = new Image(getClass().getResource("/resources/bilder_tonleiter/G-Dur.png").toExternalForm());
                break;
            case 0:
                imgTonleiter = null;
                break;
            case -1:
                imgTonleiter = new Image(getClass().getResource("/resources/bilder_tonleiter/F-Dur.png").toExternalForm());
                break;
            case -2:
                imgTonleiter = new Image(getClass().getResource("/resources/bilder_tonleiter/B-Dur.png").toExternalForm());
                break;
            case -3:
                imgTonleiter = new Image(getClass().getResource("/resources/bilder_tonleiter/Es-Dur.png").toExternalForm());
                break;
            case -4:
                imgTonleiter = new Image(getClass().getResource("/resources/bilder_tonleiter/As-Dur.png").toExternalForm());
                break;
            case -5:
                imgTonleiter = new Image(getClass().getResource("/resources/bilder_tonleiter/Des-Dur.png").toExternalForm());
                break;
            case -6:
                imgTonleiter = new Image(getClass().getResource("/resources/bilder_tonleiter/Ges-Dur.png").toExternalForm());
                break;
        }

        imageViewTonleiter.setImage(imgTonleiter);
        try {
            pane.getChildren().add(imageViewTonleiter);
        }catch (Exception ignored){}


        int tonleiter_size = 80;
        imageViewTonleiter.setFitHeight(tonleiter_size * 1.2);
        imageViewTonleiter.setFitWidth(tonleiter_size);

        if (Notenblatt.getTonleiter() < 0 && Notenblatt.getTonleiter() >= -6) {
            imageViewTonleiter.setY(8);
            imageViewTonleiter.setX(12);
        } else {
            imageViewTonleiter.setY(8);
            imageViewTonleiter.setX(12);
        }

        imageViewTonleiter.setY(8);
        imageViewTonleiter.setX(12);
    }

    /**
     * Function fills the arraylists with the possible positions
     *
     * @param notenInT type of Note or Pause
     * @return list with possible positions
     */
    public ArrayList<Point2D> fillList(int notenInT, boolean isFirstTakt) {

        ArrayList<Point2D> listsWithPossiblePositions = new ArrayList<>();

        if (isFirstTakt){

            notenInT += 1;
            float zeilen = height / 23;
            float spalten = line_length / (float) notenInT;
            for (int i = 1; i < notenInT; i++) {
                for (int e = 0; e < 23; e++) {
                    listsWithPossiblePositions.add(new Point2D(i * spalten + deadZone, e * zeilen));
                }
            }
        }else {
            notenInT += 1;
            float zeilen = height / 23;
            float spalten = line_length / (float) notenInT;
            for (int i = 1; i < notenInT; i++) {
                for (int e = 0; e < 23; e++) {
                    listsWithPossiblePositions.add(new Point2D(i * spalten, e * zeilen));
                }
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
     * @param p        Coordinates of MousePress
     * @param notenInT the type of Note/Pause that is chosen
     * @return the nearest position where the Element can be placed as point
     */
    public Point2D objektFang(Point2D p, int notenInT) {

        ArrayList<Point2D> listsWithPossiblePositions = new ArrayList<>();

        double x;

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

        x = belegt * listsWithPossiblePositions.size() + ((double) 1 / notenInTakt) * listsWithPossiblePositions.size();

        //System.out.println("List length: " + listsWithPossiblePositions.size());
        //System.out.println("X: " + x);


        double shortestDistance = 100;
        Point2D returnPoint = new Point2D(-100000, -100000);


        // wenn eine Note platziert geteilt durch 4 bei viertelnote
        // wenn zwei dann geteilt
        try {
            for (int i = (int) (listsWithPossiblePositions.size() - (listsWithPossiblePositions.size() - x) - ((double) 1 / notenInTakt) * listsWithPossiblePositions.size()); i < listsWithPossiblePositions.size() - (listsWithPossiblePositions.size() - x); i++) {
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

        } catch (Exception e) {
            returnPoint.x = 0;
            returnPoint.y = 0;
        }
        return returnPoint;
    }

    /**
     * @param mouseEvent mouse coordinates
     */
    public void onMouseMoved(javafx.scene.input.MouseEvent mouseEvent) {

        if (belegt == 1) return;

        notenInTakt = Controller.notenInTakt;
        vorzeichen = Controller.vorzeichen;

        Point2D p = new Point2D();
        p.x = (float) mouseEvent.getX();
        p.y = (float) mouseEvent.getY();

        if (notenInTakt % 5 == 0) {
            // Pause
            //System.out.println("Pause");
            p = objektFang(new Point2D(p.x - 10, p.y), notenInTakt / 5);
            //System.out.println("After: " + p);
            previewPause.setPause(notenInTakt);
            previewImageView = previewPause.getImageView();
            previewImageView.setX(p.x);
            int tempOffsetY = previewPause.getOffsetY();
            previewImageView.setY(tempOffsetY + 43);
            //System.out.println("Setting the pause at x: " + previewImage.getX() + ", y: " + previewImage.getY());
            //System.out.println(previewPause.toString());
        } else {
            //Note
            p = objektFang(new Point2D(p.x - 10, p.y), notenInTakt);
            if (p.x == 0 && p.y == 0) {
                previewImageView.setY(-100000);
                previewImageView.setX(-100000);

                previewVorzeichenView.setX(-100000);
                previewVorzeichenView.setY(-100000);
            }

            previewNote.setNote(notenInTakt, (int) (p.y / 5) + 1, vorzeichen);
            previewImageView = previewNote.getImageView();
            previewVorzeichenView = previewNote.getVorzeichenView();

            previewVorzeichenView.setX(previewVorzeichenView.getX() + p.x);
            previewVorzeichenView.setY(previewVorzeichenView.getY() + p.y + offsetY);

            previewImageView.setX(p.x);
            int tempOffsetY = previewNote.getNotenOffsetY();
            previewImageView.setY(tempOffsetY + p.y + offsetY);
        }

        try {
            //pane = (Pane) mouseEvent.getSource();
            pane.getChildren().add(previewImageView);
            pane.getChildren().add(previewVorzeichenView);
        } catch (Exception exception) {
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

            if (belegt == 1 || p.x == -100000 && p.y == -100000) return;

            ImageView imageView;

            if (notenInTakt % 5 == 0) {
                // Pause
                System.out.println("Pause");
                p = objektFang(new Point2D(p.x - 10, p.y), notenInTakt / 5);
                Pause pause = new Pause(notenInTakt, p);
                System.out.println("After: " + p);
                imageView = pause.getImageView();
                imageView.setX(p.x);
                imageView.setY(imageView.getY() + 43);
                System.out.println("Setting the pause at x: " + imageView.getX() + ", y: " + imageView.getY());
                System.out.println(pause.toString());
                pause.setxAchse(p.x);
                elements.add(pause);
                belegt += 1 / ((double) notenInTakt / 5);
                pane.getChildren().add(imageView);
            } else {
                //Note
                placeNote(p);
            }
        }
    }

    public void placeNote(Point2D p) {
        ImageView imageView;
        ImageView vorzeichenView = null;

        //System.out.println("Belegt: " + belegt);
        p = objektFang(new Point2D(p.x - 10, p.y), notenInTakt);
        //System.out.println("After: " + p);
        double temPointY = p.y;
        p.y += offsetY;

        int position = (int) (temPointY / 5) + 1;

        Note note = new Note(notenInTakt, position, vorzeichen, p);
        belegt += (1 / (double) notenInTakt);

        imageView = note.getImageView();
        vorzeichenView = note.getVorzeichenView();

        //System.out.println("vorzeichenView: ");
        //System.out.println(vorzeichenView.getY() + p.y);
        vorzeichenView.setX(vorzeichenView.getX() + p.x);
        vorzeichenView.setY(vorzeichenView.getY() + p.y);

        //System.out.println("yAchse:");
        //System.out.println(imageView.getY() + p.y);

        imageView.setX(imageView.getX() + p.x);
        imageView.setY(imageView.getY() + p.y);

        note.setxAchse(p.x);
        note.setyAchse(p.y);

        position = note.getPosition();
        //System.out.println("Position der Note: " + this.position);
        if (vorzeichen == 2) {
            vorzeichen = 0;

            //System.out.println("Tonleiter: " + Notenblatt.getTonleiter() + " - " + position);
            position = position % 7;
            //System.out.println(position);
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
        //Notenblatt.addNote(note);


        try {
            //Pane pane = (Pane) mouseEvent.getSource();
            pane.getChildren().add(imageView);
            pane.getChildren().add(vorzeichenView);
        } catch (Exception exception) {
            System.out.println("You exactly touched a line");
        }

        renewStaticTakt();

    }

    //Preview löschen wenn man aus dem Takt geht
    private void onMouseExited(javafx.scene.input.MouseEvent mouseEvent) {
        previewVorzeichenView.setImage(null);
        previewImageView.setImage(null);
    }

    public void onRightClick(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("Right Click");

        Point2D p = new Point2D();
        p.x = (float) mouseEvent.getX();
        p.y = (float) mouseEvent.getY();

        //p = objektFang(p, )
        double shortestDistance = 100;
        Point2D returnPoint = new Point2D();


        for (Element element : elements) {

            Point2D point2D = new Point2D(((float) element.imageView.getX()), ((float) element.imageView.getY()));
            //System.out.println("Point2D: " + point2D);
            //System.out.println("p: " + p);

            double distance = Math.sqrt(Math.pow(Math.abs(point2D.x - p.x), 2) + Math.pow(Math.abs(point2D.y - p.y), 2));
            //System.out.println("Distance" + distance);
            if (distance <= shortestDistance) {
                shortestDistance = distance;
                returnPoint = point2D;
                //System.out.println("Shortest Distance: " + shortestDistance);
            }
        }

        //img.setVisible(false);

        for (Element element : elements) {
            Point2D point2D = new Point2D(((float) element.imageView.getX()), ((float) element.imageView.getY()));

            double distance = Math.sqrt(Math.pow(Math.abs(point2D.x - returnPoint.x), 2) + Math.pow(Math.abs(point2D.y - returnPoint.y), 2));
            if (distance == 0) {
                belegt -= 1.0 / notenInTakt;
                elements.remove(element);
                // element.remove from list with notes usw.
                pane.getChildren().remove(element.imageView);
                try {
                    pane.getChildren().remove(element.vorzeichenView);
                } catch (Exception ignored) {
                }

                break;
            }

        }
        //System.out.println(notesAsImages);
    }

    public void erneuereNoten() {

        ImageView imageView;
        ImageView vorzeichenView = null;
        Element element;

        System.out.println(elements);
        System.out.println("Elements size:" + elements.size());

        for (int i = 0; i < elements.size(); i++) {

            System.out.println("Entering for loop for the " + i + " time");

            element = elements.get(i);

            if (element.getClass() == Pause.class) {
                // Pause
                //System.out.println("Pause");
                imageView = ((Pause) element).getImageView();
                imageView.setX(((Pause) element).getxAchse());
                imageView.setY(imageView.getY());
                //System.out.println("Setting the pause at x: " + imageView.getX() + ", y: " + imageView.getY());
                //System.out.println(((Pause) element).toString());
                pane.getChildren().add(imageView);
            } else if (element.getClass() == Note.class) {
                //Note

                Point2D p = ((Note) element).coordinatesOfNote;
                System.out.println("Coordinates of Note: " + p);
                p.y = (float) Liste.posToCords(((Note) element).getPosition());

                System.out.println("Placing Note with point: " + p);
                //placeNote(point2D);


                System.out.println("Belegt: " + belegt);
                p = objektFang(new Point2D(p.x - 10, p.y), notenInTakt);
                System.out.println("After: " + p);
                double temPointY = p.y;
                p.y += offsetY;

                int position = (int) (temPointY / 5) + 1;

                Note note = new Note(notenInTakt, position, vorzeichen, p);
                belegt += (1 / (double) notenInTakt);

                imageView = note.getImageView();
                vorzeichenView = note.getVorzeichenView();

                //System.out.println("vorzeichenView: ");
                //System.out.println(vorzeichenView.getY() + p.y);
                vorzeichenView.setX(vorzeichenView.getX() + p.x);
                vorzeichenView.setY(vorzeichenView.getY() + p.y);

                //System.out.println("yAchse:");
                //System.out.println(imageView.getY() + p.y);

                imageView.setX(imageView.getX() + p.x);
                imageView.setY(imageView.getY() + p.y);

                note.setxAchse(p.x);
                note.setyAchse(p.y);

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


                //Notenblatt.addNote(note);


                try {
                    //Pane pane = (Pane) mouseEvent.getSource();
                    System.out.println("Adding this note: ");
                    System.out.println(note);
                    pane.getChildren().add(imageView);
                    pane.getChildren().add(vorzeichenView);
                } catch (Exception exception) {
                    System.out.println("You exactly touched a line");
                }

                renewStaticTakt();

            }

                /*
                imageView = ((Note) element).getImageView();
                vorzeichenView = ((Note) element).getVorzeichenView();

                float xAchse = ((Note) element).getxAchse();
                int yAchse = 120 - (5 * (((Note) element).getPosition()));

                //System.out.println("Position: " + ((Note) element).getPosition() + "  yAchse: " + yAchse);
                vorzeichenView.setX(vorzeichenView.getX() + xAchse);
                vorzeichenView.setY(yAchse - 20);

                //System.out.println("yAchse=" + yAchse + "   getY=" + imageView.getY());
                //System.out.println(yAchse - (int)imageView.getY() - 5);
                imageView.setX(xAchse);

                if (yAchse >= 60) imageView.setY(yAchse - 30);
                else imageView.setY(yAchse - 5);

                //System.out.println("getY=" + imageView.getY());

                pane.getChildren().add(imageView);
                pane.getChildren().add(vorzeichenView);

                 */
        }
    }

    public void setNotenInTakt(int notenInTakt) {
        this.notenInTakt = notenInTakt;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public void renewStaticTakt() {
        Notenblatt.renewTakt(elements, id);
    }

    @Override
    public String toString() {
        return "Takt{" +
                "elements=" + elements +
                '}';
    }
}