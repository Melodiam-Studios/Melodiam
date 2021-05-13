package v5;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pause extends Element{

    int pausenInTakt;
    int offsetY;


    public Pause(int pausenInTakt) {

        this.pausenInTakt = pausenInTakt;

        changePause();
    }

    public void changePause(){

        switch (pausenInTakt) {
            case 5:
                // Ganze Pause
                img = new Image(getClass().getResource("/resources/bilder_noten/Halbe-Ganze_Pause.png").toExternalForm());
                imageView.setImage(img);
                imageView.setFitHeight(10);
                imageView.setFitWidth(16);
                offsetY = 0;
                imageView.setY(offsetY);
                break;
            case 10:
                // Halbe Pause
                img = new Image(getClass().getResource("/resources/bilder_noten/Halbe-Ganze_Pause.png").toExternalForm());
                imageView.setImage(img);
                imageView.setFitHeight(10);
                imageView.setFitWidth(16);
                offsetY = 5;
                imageView.setY(offsetY);
                break;
            case 20:
                // Viertel Pause
                img = new Image(getClass().getResource("/resources/bilder_noten/Viertelpause.png").toExternalForm());
                imageView.setImage(img);
                imageView.setFitHeight(30);
                imageView.setFitWidth(9);
                offsetY = -3;
                imageView.setY(offsetY);
                break;
            case 40:
                // Achtel Note
                img = new Image(getClass().getResource("/resources/bilder_noten/Achtelpause.png").toExternalForm());
                imageView.setImage(img);
                imageView.setFitHeight(20);
                imageView.setFitWidth(11);
                offsetY = 1;
                imageView.setY(offsetY);
                break;
            case 80:
                // Halbe Note
                img = new Image(getClass().getResource("/resources/bilder_noten/Sechzehntelpause.png").toExternalForm());
                imageView.setImage(img);
                imageView.setFitHeight(20);
                imageView.setFitWidth(11);
                offsetY = 1;
                imageView.setY(offsetY);
                break;
        }
    }

    public void setPause(int pausenInTakt){
        this.pausenInTakt = pausenInTakt;
        changePause();
    }



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
