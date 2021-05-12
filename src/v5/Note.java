package v5;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * For each element of Note a new Image of the note gets placed in the GUI.
 * The properties of the Note then can help the program to detect the type of note
 */
public class Note extends Element{

    // fürs b -1, fürs nazikreuz 0, fürs hashtag 1


    /**
     * Variable responsible for showing the Vorzeichen of the note.
     * It gets set by the constructor-called function {@link Note#changeNote()} when a new note is placed
     */
    @FXML
    private ImageView vorzeichenView = new ImageView();

    /**
     * describes the type of note - for viertelNote --> 4 - for halbeNote --> 2
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
     * Every image of the different vorzeichen needs a little x adjustment.
     * With this variable the x offset gets defined
     */
    private int vorzeichenOffsetX;
    /**
     * Every image of the different vorzeichen needs a little y adjustment.
     * With this variable the y offset gets defined
     */
    private int vorzeichenOffsetY;
    /**
     * Variable responsible for identification of vorzeichen.
     * The function {@link Note#changeNote()}} gets the vorzeichen from this variable and tells the program wich vorzeichen is chosen
     */
    private String vorzeichen = null;


    /**
     * Constructor for the note element.
     *
     * @param notenInTakt describes the type of note - for viertelNote --> 4 - for halbeNote --> 2
     * @param position describes the vertical position - 23 possible positions --> based on the position the tone of the note can be determined
     * @param vorzeichen Variable responsible for identification of vorzeichen.The function {@link Note#changeNote()}} gets the vorzeichen from this variable and tells the program wich vorzeichen is chosen
     */
    public Note(int notenInTakt, int position, String vorzeichen) {

        this.notenInTakt = notenInTakt;
        this.position = position;
        this.vorzeichen =  vorzeichen;

        // adjust the view of the note based on the properties that were set before
        changeNote();
    }

    /**
     * adjusts the view of the note based on the properties of the class {@link Note}
     */
    private void changeNote(){

        /*
        switch that distinguished between the different types of notes
        ganzeNote --> 1
        halbeNote --> 2
        viertelNote --> 4
        achtelNote --> 8
        sechzentelNote -->
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
                if (vorzeichen != null) vorzeichenSetzen();
                break;
            case 2:
                // Halbe Note
                if (position <= 12) {
                    img = new Image(getClass().getResource("/resources/bilder_noten/HalbenoteOben.png").toExternalForm());
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                    imageView.setX(0);
                    notenOffsetY = 25;
                    imageView.setY(notenOffsetY);
                }else {
                    img = new Image(getClass().getResource("/resources/bilder_noten/HalbenoteUnten.png").toExternalForm());
                    notenOffsetY = 0;
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                }
                if (vorzeichen != null) vorzeichenSetzen();
                break;
            case 4:
                // Viertel Note
                if (position <= 12) {
                    System.out.println("Pos is smaller than 12");
                    img = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteOben.png").toExternalForm());
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                    imageView.setX(0);
                    notenOffsetY = 25;
                    imageView.setY(notenOffsetY);
                }else {
                    img = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteUnten.png").toExternalForm());
                    notenOffsetY = 0;
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                }
                if (vorzeichen != null) vorzeichenSetzen();
                break;
            case 8:
                // Achtel Note
                if (position <= 12) {
                    img = new Image(getClass().getResource("/resources/bilder_noten/AchtelnoteOben.png").toExternalForm());
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                    imageView.setX(0);
                    notenOffsetY = 25;
                    imageView.setY(notenOffsetY);
                }else {
                    img = new Image(getClass().getResource("/resources/bilder_noten/AchtelnoteUnten.png").toExternalForm());
                    notenOffsetY = 0;
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(19);
                }
                if (vorzeichen != null) vorzeichenSetzen();
                break;
            case 16:
                // Halbe Note
                if (position <= 12) {
                    img = new Image(getClass().getResource("/resources/bilder_noten/SechzehntelnoteOben.png").toExternalForm());
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                    imageView.setX(0);
                    notenOffsetY = 25;
                    imageView.setY(notenOffsetY);
                }else {
                    img = new Image(getClass().getResource("/resources/bilder_noten/SechzehntelnoteUnten.png").toExternalForm());
                    notenOffsetY = 0;
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(19);
                }
                if (vorzeichen != null) vorzeichenSetzen();
                break;
        }
    }

    /**
     *
     */
    private void vorzeichenSetzen() {
        img = null;
        if (vorzeichen.equals("bV")){
            img = new Image(getClass().getResource("/resources/bilder_noten/b-vorzeichen.png").toExternalForm());
        }else if (vorzeichen.equals("kV")){
            img = new Image(getClass().getResource("/resources/bilder_noten/Kreuzvorzeichen.png").toExternalForm());
        }
        else if (vorzeichen.equals("aV")){
            img = new Image(getClass().getResource("/resources/bilder_noten/Auflösungszeichen.png").toExternalForm());
        }
        vorzeichenView.setImage(img);
        vorzeichenView.setFitHeight(34);
        vorzeichenView.setFitWidth(19);

        vorzeichenOffsetX = -15;
        vorzeichenView.setX(vorzeichenOffsetX);

        vorzeichenOffsetY = -20;
        vorzeichenView.setY(vorzeichenOffsetY);

    }

    public void setNote(int notenInTakt, int position){
        this.notenInTakt = notenInTakt;
        this.position = position;
        changeNote();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public ImageView getVorzeichenView() {
        return vorzeichenView;
    }

    public int getNotenOffsetY() {
        return notenOffsetY;
    }

    @Override
    public String toString() {
        return "Note{" +
                "vorzeichenView=" + vorzeichenView +
                ", notenInTakt=" + notenInTakt +
                ", position=" + position +
                ", notenOffsetY=" + notenOffsetY +
                ", vorzeichenOffsetX=" + vorzeichenOffsetX +
                ", vorzeichenOffsetY=" + vorzeichenOffsetY +
                ", vorzeichen='" + vorzeichen + '\'' +
                '}';
    }
}
