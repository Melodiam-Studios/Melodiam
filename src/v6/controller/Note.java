package v6.controller;

import com.sun.javafx.geom.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import v6.model.Liste;

/**
 * For each element of Note a new Image of the note gets placed in the GUI.
 * The properties of the Note then can help the program to detect the type of note
 * @author Silas Demez
 */
@SuppressWarnings("ConstantConditions")
public class Note extends Element {

    /**
     * describes the type of note - for viertelNote --> 4 - for halbeNote --> 2 - ...
     */
    private int notenInTakt;
    /**
     * describes the vertical position - 23 possible positions --> based on the position the tone of the note can be determined
     */
    private int position;

    /**
     * Every image of the different notes needs to be adjusted in its y coordinate.
     * With this variable the y offset gets defined
     */
    private int notenOffsetY;
    /**
     * Variable responsible for identification of vorzeichen.
     * The function {@link Note#changeNote(boolean)}} gets the vorzeichen from this variable and tells the program wich vorzeichen is chosen
     */


    private int wert;
    private int vorzeichen = 2;         // -1 = b Vorzeichen, 0 = Kein Vorzeichen, 1 = Kreuzvorzeichen  2 = Nicht definiert
    private int anzeigenVorzeichen = 1; // 0 = nicht anzeigen, 1 = Vorzeichen anzeigen, 2 = Auflösezeichen anzeigen
    private String bezeichnung;


    private int diffPos;

    /**
     * Constructor for the note element.
     *
     * @param notenInTakt describes the type of note - for viertelNote --> 4 - for halbeNote --> 2 - ...
     * @param position describes the vertical position - 23 possible positions --> based on the position the tone of the note can be determined
     * @param vorzeichen Variable responsible for identification of vorzeichen.The function {@link Note#changeNote(boolean)}} gets the vorzeichen from this variable and tells the program wich vorzeichen is chosen
     */
    public Note(int notenInTakt, int position, boolean positionUmsetzen, int vorzeichen, Point2D coordinatesOfNote) {

        this.notenInTakt = notenInTakt;
        if (positionUmsetzen)   this.position = Liste.positionumsetzen(position);
        else this.position = position;
        this.vorzeichen =  vorzeichen;
        this.coordinatesOfNote = coordinatesOfNote;

        // adjust the view of the note based on the properties that were set before
        changeNote(false);
    }

    /**
     * adjusts the view of the note based on the properties of the class {@link Note}
     * sets the different images for the notes and also sets the images when there is a vorzeichen
     */
    private void changeNote(boolean trans){

        System.out.println("Vorzeichen: " + vorzeichen);

        //pane = ControllerMainWindow.get //

        /*
        switch that distinguished between the different types of notes
        ganzeNote --> 1
        halbeNote --> 2
        viertelNote --> 4
        achtelNote --> 8
        sechzehntelNote --> 16
        */
        switch (notenInTakt) {
            case 1:
                // Ganze Note
                img = new Image(getClass().getResource("/resources/bilder_noten/GanzeNote.png").toExternalForm());
                notenOffsetY = 25;
                imageView.setImage(img);
                imageView.setFitHeight(10);
                imageView.setFitWidth(16);

                break;
            case 2:
                // Halbe Note
                if (position >= 13) {       // when position is smaller than 12 --> HalbeNoteOben
                    img = new Image(getClass().getResource("/resources/bilder_noten/HalbenoteOben.png").toExternalForm());
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                    notenOffsetY = 25;

                }else {         // when position is bigger than 12 --> HalbeNoteUnten
                    img = new Image(getClass().getResource("/resources/bilder_noten/HalbenoteUnten.png").toExternalForm());
                    notenOffsetY = 0;
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);

                }
                break;
            case 4:
                // Viertel Note
                if (position >= 13) {       // when position is smaller than 12 --> ViertelNoteOben
                    img = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteOben.png").toExternalForm());
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                    notenOffsetY = 25;

                    //System.out.println("Setting the ImageView x:? and y:25      x:" + imageView.getX() + " y:" + imageView.getY());
                }else {         // when position is bigger than 12 --> ViertelNoteUnten
                    img = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteUnten.png").toExternalForm());
                    notenOffsetY = 0;
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);

                    //System.out.println("Setting the ImageView x:? and y:0      x:" + imageView.getX() + " y:" + imageView.getY());
                }
                break;
            case 8:
                // Achtel Note
                if (position >= 13) {       // when position is smaller than 12 --> AchtelnoteOben
                    img = new Image(getClass().getResource("/resources/bilder_noten/AchtelnoteOben.png").toExternalForm());
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                    notenOffsetY = 25;

                }else {         // when position is bigger than 12 --> AchtelnoteUnten
                    img = new Image(getClass().getResource("/resources/bilder_noten/AchtelnoteUnten.png").toExternalForm());
                    notenOffsetY = 0;
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(19);

                }
                break;
            case 16:
                // Sechzehntel Note
                if (position >= 13) {       // when position is smaller than 12 --> SechzehntelnoteOben
                    img = new Image(getClass().getResource("/resources/bilder_noten/SechzehntelnoteOben.png").toExternalForm());
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                    notenOffsetY = 25;

                }else {         // when position is bigger than 12 --> SechzehntelnoteUnten
                    img = new Image(getClass().getResource("/resources/bilder_noten/SechzehntelnoteUnten.png").toExternalForm());
                    notenOffsetY = 0;
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(19);

                }
                break;
        }

        imageView.setY(notenOffsetY);
        //System.out.println("Setting the ImageView x:? and y:25      x:" + imageView.getX() + " y:" + imageView.getY());

        // if there is a vorzeichen it gets set in the vorzeichenSetzen function
        if (vorzeichen > (-2) && vorzeichen <= 2) vorzeichenSetzen(trans);

        if (diffPos != 0){
            System.out.println("Das Programm hat gesehen, dass die Note transponiert wurde");
            System.out.println("Setze die Koordinaten der imageview X mit: " + imageView.getX() + " + " + coordinatesOfNote.x);
            System.out.println("Setze die Koordinaten der imageview Y mit: " + imageView.getY() + " + " + coordinatesOfNote.y);
            imageView.setX(coordinatesOfNote.x);
            imageView.setY(imageView.getY() + coordinatesOfNote.y + diffPos * 5 * (-1));
            coordinatesOfNote.y += diffPos * 5 * (-1);
        }else if (trans) {
            //imageView.setX(coordinatesOfNote.x);
            imageView.setY(coordinatesOfNote.y + notenOffsetY);
        }
    }

    /**
     * vorzeichenSetzen() is responsible for setting the view of the vorzeichen.
     * It sets different images for the different vorzeichen
     */
    private void vorzeichenSetzen(boolean trans) {

        System.out.println("Going into the vorzeichensetzen function");
        /**
         * Every image of the different vorzeichen needs a little x adjustment.
         * With this variable the x offset gets defined
         */
        int vorzeichenOffsetX;
        /**
         * Every image of the different vorzeichen needs a little y adjustment.
         * With this variable the y offset gets defined
         */
        int vorzeichenOffsetY;      // img is the image for the vorzeichen
        img = null;

        // in the switch the image gets chosen
        if (vorzeichen == -1 && anzeigenVorzeichen == 1) {// B-Vorzeichen
            img = new Image(getClass().getResource("/resources/bilder_noten/b-vorzeichen.png").toExternalForm());
        } else if (vorzeichen == 1 && anzeigenVorzeichen == 1) {// Kreuz-Vorzeichen
            img = new Image(getClass().getResource("/resources/bilder_noten/Kreuzvorzeichen.png").toExternalForm());
        } else if (anzeigenVorzeichen == 2 && vorzeichen == 0) {// Auflösungs-Vorzeichen
            img = new Image(getClass().getResource("/resources/bilder_noten/Auflösungszeichen.png").toExternalForm());
        }

        // set the image to the ImageView
        vorzeichenView.setImage(img);
        // set size of image
        vorzeichenView.setFitHeight(25);
        vorzeichenView.setFitWidth(9);

        // sets the x-offset of the image
        vorzeichenOffsetX = -10;
        vorzeichenView.setX(vorzeichenOffsetX);

        // sets the y-offset of the image
        vorzeichenOffsetY = 10;
        vorzeichenView.setY(vorzeichenOffsetY);


        if (trans) {
            vorzeichenView.setY(imageView.getY() + vorzeichenOffsetY);
            vorzeichenView.setX(imageView.getX() + vorzeichenOffsetX);
        }

    }

    /**
     * Function for the transponieren part of the program
     *
     * @param notenInTakt describes the type of note - for viertelNote --> 4 - for halbeNote --> 2 - ...
     * @param position describes the vertical position - 23 possible positions --> based on the position the tone of the note can be determined
     */
    public void setNote(int notenInTakt, int position, int vorzeichen){
        this.notenInTakt = notenInTakt;
        this.position = Liste.positionumsetzen(position);
        this.vorzeichen = vorzeichen;
        // calll the function that changes the view
        changeNote(false);
    }

    public void setGUI(){
        System.out.println("The difference in positions is: " + diffPos);
        // calll the function that changes the view
        changeNote(true);
    }

    /**
     * @return image of note to {@link Takt}
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * @return image of vorzeichen to {@link Takt}
     */
    public ImageView getVorzeichenView() {
        return vorzeichenView;
    }

    /**
     * @return needed for the preview of the Note in {@link Takt}
     */
    public int getNotenOffsetY() {
        return notenOffsetY;
    }

    public void setAll(int wert, int position, int vorzeichen, int anzeigenVorzeichen, String bezeichnung) {

        this.diffPos = position - this.position;
        this.wert = wert;
        this.position = position;
        this.vorzeichen = vorzeichen;
        this.anzeigenVorzeichen = anzeigenVorzeichen;
        this.bezeichnung = bezeichnung;
    }

    public int getPosition() {
        return position;
    }

    public int getWert() {
        return wert;
    }

    public int getVorzeichen() {
        return vorzeichen;
    }

    public void setAnzeigenVorzeichen(int anzeigenVorzeichen) {
        this.anzeigenVorzeichen = anzeigenVorzeichen;
    }

    public int getNotenInTakt() {
        return notenInTakt;
    }

    public void setVorzeichen(int vorzeichen) {
        this.vorzeichen = vorzeichen;
    }

    public void setImageViewCoords(Point2D point2D){
        imageView.setX(point2D.x);
        imageView.setY(point2D.y);
        System.out.println("Setting the coords of the imageview to x:" + imageView.getX() + " and y:" + imageView.getY());
    }

    @Override
    public String toString() {
        return "Note{" +
                "vorzeichenView=" + vorzeichenView +
                ", notenInTakt=" + notenInTakt +
                ", position=" + position +
                ", notenOffsetY=" + notenOffsetY +
                ", coordinatesOfNote=" + coordinatesOfNote +
                ", wert=" + wert +
                ", vorzeichen=" + vorzeichen +
                ", anzeigenVorzeichen=" + anzeigenVorzeichen +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", imageViewX=" + imageView.getX() +
                ", imageViewY=" + imageView.getY() +
                '}';
    }
}
