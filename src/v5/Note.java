package v5;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Note {

    // fürs b -1, fürs nazikreuz 0, fürs hashtag 1

    @FXML
    private Image img;

    @FXML
    private ImageView imageView = new ImageView();

    @FXML
    private ImageView vorzeichenView = new ImageView();

    private int notenInTakt, position, offsetY;
    private String vorzeichen = null;


    public Note(int notenInTakt, int position, String vorzeichen) {

        this.notenInTakt = notenInTakt;
        this.position = position;
        this.vorzeichen =  vorzeichen;
        changeNote();
    }

    public void changeNote(){

        switch (notenInTakt) {
            case 1:
                // Ganze Note
                img = new Image(getClass().getResource("/resources/bilder_noten/GanzeNote.png").toExternalForm());
                offsetY = 25;
                imageView.setImage(img);
                imageView.setFitHeight(10);
                imageView.setFitWidth(16);
                imageView.setY(offsetY);
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
                    offsetY = 25;
                    imageView.setY(offsetY);
                }else {
                    img = new Image(getClass().getResource("/resources/bilder_noten/HalbenoteUnten.png").toExternalForm());
                    offsetY = 0;
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
                    offsetY = 25;
                    imageView.setY(offsetY);
                }else {
                    img = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteUnten.png").toExternalForm());
                    offsetY = 0;
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
                    offsetY = 25;
                    imageView.setY(offsetY);
                }else {
                    img = new Image(getClass().getResource("/resources/bilder_noten/AchtelnoteUnten.png").toExternalForm());
                    offsetY = 0;
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
                    offsetY = 25;
                    imageView.setY(offsetY);
                }else {
                    img = new Image(getClass().getResource("/resources/bilder_noten/SechzehntelnoteUnten.png").toExternalForm());
                    offsetY = 0;
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(19);
                }
                if (vorzeichen != null) vorzeichenSetzen();
                break;
        }
    }

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
        offsetY = 0;
        vorzeichenView.setImage(img);
        vorzeichenView.setFitHeight(34);
        vorzeichenView.setFitWidth(19);
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

    public int getOffsetY() {
        return offsetY;
    }

    @Override
    public String toString() {
        return "Note{" +
                "img=" + img +
                ", imageView=" + imageView +
                ", notenInTakt=" + notenInTakt +
                ", position=" + position +
                '}';
    }
}
