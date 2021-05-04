package v6;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pause {
    @FXML Image img;
    @FXML ImageView imageView = new ImageView();

    int pausenInTakt;
    int offsetY;


    public Pause(int pausenInTakt) {

        this.pausenInTakt = pausenInTakt;

        changeNote();
    }

    public void changeNote(){

        switch (pausenInTakt) {
            case 5:
                // Ganze Pause
                img = new Image(getClass().getResource("/resources/bilder_noten/Halbe-Ganze_Pause.png").toExternalForm());
                offsetY = 0;
                imageView.setImage(img);
                imageView.setFitHeight(10);
                imageView.setFitWidth(16);
                imageView.setY(offsetY);
                break;
            case 10:
                // Halbe Pause
                img = new Image(getClass().getResource("/resources/bilder_noten/Halbe-Ganze_Pause.png").toExternalForm());
                imageView.setImage(img);
                imageView.setFitHeight(34);
                imageView.setFitWidth(11);
                imageView.setX(0);
                offsetY = 25;
                imageView.setY(offsetY);
                break;
            case 20:
                // Viertel Pause
                System.out.println("Pos is smaller than 12");
                img = new Image(getClass().getResource("/resources/bilder_noten/ViertelnoteOben.png").toExternalForm());
                imageView.setImage(img);
                imageView.setFitHeight(34);
                imageView.setFitWidth(11);
                imageView.setX(0);
                offsetY = 25;
                imageView.setY(offsetY);
                break;
            case 40:
                // Achtel Note
                img = new Image(getClass().getResource("/resources/bilder_noten/AchtelnoteOben.png").toExternalForm());
                imageView.setImage(img);
                imageView.setFitHeight(34);
                imageView.setFitWidth(11);
                imageView.setX(0);
                offsetY = 25;
                imageView.setY(offsetY);
                break;
            case 80:
                // Halbe Note
                img = new Image(getClass().getResource("/resources/bilder_noten/SechzehntelnoteOben.png").toExternalForm());
                imageView.setImage(img);
                imageView.setFitHeight(34);
                imageView.setFitWidth(11);
                imageView.setX(0);
                offsetY = 25;
                imageView.setY(offsetY);
                break;
        }
    }
/*
    public void setNote(int notenInTakt, int position){
        this.notenInTakt = notenInTakt;
        this.position = position;
        changeNote();
    }

 */

    public ImageView getImageView() {
        return imageView;
    }

    public int getOffsetY() {
        return offsetY;
    }

    @Override
    public String toString() {
        return "Pause{" +
                "img=" + img +
                ", imageView=" + imageView +
                ", pausenInTakt=" + pausenInTakt +
                ", offsetY=" + offsetY +
                '}';
    }
}
