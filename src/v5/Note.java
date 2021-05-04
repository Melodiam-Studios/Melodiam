package v5;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Note {

    @FXML
    Image img;

    @FXML
    ImageView imageView = new ImageView();

    int notenInTakt;
    int position;
    int offsetY;


    public Note(int notenInTakt, int position) {

        this.notenInTakt = notenInTakt;
        this.position = position;

        changeNote();
    }

    public void changeNote(){

        switch (notenInTakt) {
            case 1:
                // Ganze Note
                img = new Image(getClass().getResource("/resources/bilder_noten/GanzeNote.png").toExternalForm());
                offsetY = 0;
                imageView.setImage(img);
                imageView.setFitHeight(10);
                imageView.setFitWidth(16);
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
                break;
        }
    }

    public void setNote(int notenInTakt, int position){
        this.notenInTakt = notenInTakt;
        this.position = position;
        changeNote();
    }

    public ImageView getImageView() {
        return imageView;
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
