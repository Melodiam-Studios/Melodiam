package finalVersion.controller;

import com.sun.javafx.geom.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * For each element of Pause a new Image of the pause gets placed in the GUI.
 * The properties of the Pause then can help the program to detect the type of pause
 */
public class Pause extends Element {

    /**
     * describes the type of pause - for viertelPause --> 4 - for halbePause --> 2 - ...
     */
    int pausenInTakt;
    /**
     * Every image of the different pauses needs to be adjusted in its y coordinate.
     * With this variable the y offset gets defined
     */
    int offsetY;

    float xAchse;

    public Pause(int pausenInTakt, Point2D coordinatesOfNote) {

        this.pausenInTakt = pausenInTakt;
        this.coordinatesOfNote = coordinatesOfNote;

        changePause();
    }

    /**
     * sets the different images for the pauses
     */
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
                // Sechzehntelpause
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

    public int getPausenInTakt() {
        return pausenInTakt;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public float getxAchse() {
        return xAchse;
    }

    public void setxAchse(float xAchse) {
        this.xAchse = xAchse;
    }

    public void setImageViewCoords(Point2D point2D){
        imageView.setX(point2D.x);
        imageView.setY(point2D.y);
        System.out.println("Setting the coords of the imageview to x:" + imageView.getX() + " and y:" + imageView.getY());
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
