package v6;

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


    public Note(int notenInTakt, int position) {

        this.notenInTakt = notenInTakt;
        this.position = position;

        switch (notenInTakt) {
            case 1:
                // Ganze Note
                img = new Image(getClass().getResource("/resources/bilder_noten/GanzeNote.png").toExternalForm());
                break;
            case 2:
                // Halbe Note
                if (position <= 12)
                    img = new Image(getClass().getResource("/resources/bilder_noten/HalbenoteOben.png").toExternalForm());
                else
                    img = new Image(getClass().getResource("/resources/bilder_noten/HalbenoteUnten.png").toExternalForm());
                break;
            case 4:
                // Viertel Note
                if (position <= 12) {
                    System.out.println("Pos is smaller than 12");
                    img = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteOben.png").toExternalForm());
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                }else {
                    img = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteUnten.png").toExternalForm());
                    imageView.setImage(img);
                    imageView.setFitHeight(34);
                    imageView.setFitWidth(11);
                }
                break;
            case 8:
                // Achtel Note
                if (position <= 12)
                    img = new Image(getClass().getResource("/resources/bilder_noten/AchtelnoteOben.png").toExternalForm());
                else
                    img = new Image(getClass().getResource("/resources/bilder_noten/AchtelnoteUnten.png").toExternalForm());
                break;
            case 16:
                // Halbe Note
                if (position <= 12)
                    img = new Image(getClass().getResource("/resources/bilder_noten/SechzehntelnoteOben.png").toExternalForm());
                else
                    img = new Image(getClass().getResource("/resources/bilder_noten/SechzehntelnoteUnten.png").toExternalForm());
                break;
        }
    }

    public ImageView getImageView() {
        return imageView;
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
