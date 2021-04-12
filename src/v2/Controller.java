package v2;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.awt.event.MouseEvent;

public class Controller {

    public void onMouseEntered(javafx.scene.input.MouseEvent mouseEvent) {
        Label hoveredLabel = (Label) mouseEvent.getSource();
        hoveredLabel.setText("Hover");
    }

    public void onMouseExcited(javafx.scene.input.MouseEvent mouseEvent) {
        Label hoveredLabel = (Label) mouseEvent.getSource();
        hoveredLabel.setText("TestLabel");
    }
}
