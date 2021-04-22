package v4;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Controller {
    private int countX = 1;
    private int countY = 1;

    public void hkd() throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("dragableNode.fxml"));

    }

}
