package v5;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * For each element of Note a new Image of the note gets placed in the GUI.
 * The properties of the Note then can help the program to detect the type of note
 * @author Silas Demez
 */
@SuppressWarnings("ConstantConditions")
public class Note extends Element{

    // fürs b -1, für keins 0, fürs hashtag 1


    /**
     * Variable responsible for showing the Vorzeichen of the note.
     * It gets set by the constructor-called function {@link Note#changeNote()} when a new note is placed
     */
    @FXML
    private ImageView vorzeichenView = new ImageView();

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
     * The function {@link Note#changeNote()}} gets the vorzeichen from this variable and tells the program wich vorzeichen is chosen
     */


    private Takt takt = null;
    private int taktID = 0;

    private int wert;
    private int vorzeichen = 2;         // -1 = b Vorzeichen, 0 = kein Vorzeichen, 1 = Kreuzvorzeichen
    private int anzeigenVorzeichen; // 0 = nicht anzeigen, 1 = Vorzeichen anzeigen, 2 = Auflösezeichen anzeigen
    private String bezeichnung;



    /**
     * Constructor for the note element.
     *
     * @param notenInTakt describes the type of note - for viertelNote --> 4 - for halbeNote --> 2 - ...
     * @param position describes the vertical position - 23 possible positions --> based on the position the tone of the note can be determined
     * @param vorzeichen Variable responsible for identification of vorzeichen.The function {@link Note#changeNote()}} gets the vorzeichen from this variable and tells the program wich vorzeichen is chosen
     */
    public Note(int notenInTakt, int position, int vorzeichen, int taktID) {

        this.notenInTakt = notenInTakt;
        this.position = position;
        this.vorzeichen =  vorzeichen;
        this.taktID = taktID;

        takt = Controller.getTakt(taktID);

        // adjust the view of the note based on the properties that were set before
        changeNote();
    }

    public Note(int taktID) {

        this.notenInTakt = 8;
        this.position = -100;
        this.vorzeichen =  0;
        this.taktID = taktID;
    }

    /**
     * adjusts the view of the note based on the properties of the class {@link Note}
     * sets the different images for the notes and also sets the images when there is a vorzeichen
     */
    private void changeNote(){

        takt = Controller.getTakt(taktID);

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
                imageView.setY(notenOffsetY);
                // if there is a vorzeichen it gets set in the vorzeichenSetzen function
                if (vorzeichen > (-2) && vorzeichen < 2) vorzeichenSetzen();
                break;
            case 2:
                // Halbe Note
                if (position <= 12) {       // when position is smaller than 12 --> HalbeNoteOben
                    img = new Image(getClass().getResource("/resources/bilder_noten/HalbenoteOben.png").toExternalForm());
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                    imageView.setX(0);
                    notenOffsetY = 25;
                    imageView.setY(notenOffsetY);
                }else {         // when position is bigger than 12 --> HalbeNoteUnten
                    img = new Image(getClass().getResource("/resources/bilder_noten/HalbenoteUnten.png").toExternalForm());
                    notenOffsetY = 0;
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                }
                // if there is a vorzeichen it gets set in the vorzeichenSetzen function
                if (vorzeichen > (-2) && vorzeichen < 2) vorzeichenSetzen();
                break;
            case 4:
                // Viertel Note
                if (position <= 12) {       // when position is smaller than 12 --> ViertelNoteOben
                    System.out.println("Pos is smaller than 12");
                    img = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteOben.png").toExternalForm());
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                    imageView.setX(0);
                    notenOffsetY = 25;
                    imageView.setY(notenOffsetY);
                }else {         // when position is bigger than 12 --> ViertelNoteUnten
                    img = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteUnten.png").toExternalForm());
                    notenOffsetY = 0;
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                }
                // if there is a vorzeichen it gets set in the vorzeichenSetzen function
                if (vorzeichen > (-2) && vorzeichen < 2) vorzeichenSetzen();
                break;
            case 8:
                // Achtel Note
                if (position <= 12) {       // when position is smaller than 12 --> AchtelnoteOben
                    img = new Image(getClass().getResource("/resources/bilder_noten/AchtelnoteOben.png").toExternalForm());
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                    imageView.setX(0);
                    notenOffsetY = 25;
                    imageView.setY(notenOffsetY);
                }else {         // when position is bigger than 12 --> AchtelnoteUnten
                    img = new Image(getClass().getResource("/resources/bilder_noten/AchtelnoteUnten.png").toExternalForm());
                    notenOffsetY = 0;
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(19);
                }
                // if there is a vorzeichen it gets set in the vorzeichenSetzen function
                if (vorzeichen > (-2) && vorzeichen < 2) vorzeichenSetzen();
                break;
            case 16:
                // Halbe Note
                if (position <= 12) {       // when position is smaller than 12 --> SechzehntelnoteOben
                    img = new Image(getClass().getResource("/resources/bilder_noten/SechzehntelnoteOben.png").toExternalForm());
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                    imageView.setX(0);
                    notenOffsetY = 25;
                    imageView.setY(notenOffsetY);
                }else {         // when position is bigger than 12 --> SechzehntelnoteUnten
                    img = new Image(getClass().getResource("/resources/bilder_noten/SechzehntelnoteUnten.png").toExternalForm());
                    notenOffsetY = 0;
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(19);
                }
                // if there is a vorzeichen it gets set in the vorzeichenSetzen function
                if (vorzeichen > (-2) && vorzeichen < 2) vorzeichenSetzen();
                break;
        }
    }

    /**
     * vorzeichenSetzen() is responsible for setting the view of the vorzeichen.
     * It sets different images for the different vorzeichen
     */
    private void vorzeichenSetzen() {
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
        switch (vorzeichen) {
            case -1:
                // B-Vorzeichen
                img = new Image(getClass().getResource("/resources/bilder_noten/b-vorzeichen.png").toExternalForm());
                break;
            case 1:
                // Kreuz-Vorzeichen
                img = new Image(getClass().getResource("/resources/bilder_noten/Kreuzvorzeichen.png").toExternalForm());
                break;
            case 0:
                // Auflösungs-Vorzeichen
                img = new Image(getClass().getResource("/resources/bilder_noten/Auflösungszeichen.png").toExternalForm());
                break;
        }

        // set the image to the ImageView
        vorzeichenView.setImage(img);
        // set size of image
        vorzeichenView.setFitHeight(34);
        vorzeichenView.setFitWidth(19);

        // sets the x-offset of the image
        vorzeichenOffsetX = -15;
        vorzeichenView.setX(vorzeichenOffsetX);

        // sets the y-offset of the image
        vorzeichenOffsetY = -20;
        vorzeichenView.setY(vorzeichenOffsetY);

    }

    /**
     * Function for the transponieren part of the program
     *
     * @param notenInTakt describes the type of note - for viertelNote --> 4 - for halbeNote --> 2 - ...
     * @param position describes the vertical position - 23 possible positions --> based on the position the tone of the note can be determined
     */
    public void setNote(int notenInTakt, int position, int vorzeichen){
        this.notenInTakt = notenInTakt;
        this.position = position;
        this.vorzeichen = vorzeichen;
        // calll the function that changes the view
        changeNote();
    }

    /**
     * Function for the transponieren part of the program
     *
     * @param notenInTakt describes the type of note - for viertelNote --> 4 - for halbeNote --> 2 - ...
     * @param position describes the vertical position - 23 possible positions --> based on the position the tone of the note can be determined
     */
    public void setNote(int notenInTakt, int position){
        this.notenInTakt = notenInTakt;
        this.position = position;
        // call the function that changes the view
        changeNote();
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

    @Override
    public String toString() {
        return "Note{" +
                "vorzeichenView=" + vorzeichenView +
                ", notenInTakt=" + notenInTakt +
                ", position=" + position +
                ", notenOffsetY=" + notenOffsetY +
                ", vorzeichen='" + vorzeichen + '\'' +
                '}';
    }
}
