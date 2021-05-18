package v6;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller2 {

    public Controller2() throws IOException {
        /*FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        Parent root = loader.load();
        Stage secondStage = new Stage();
        secondStage.setScene(new Scene((Parent) loader.load()));*/

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        Stage stage = new Stage();
        //stage.initOwner(btn1.getScene().getWindow());
        stage.setScene(new Scene((Parent) loader.load()));


        Controller2 controller2 = loader.getController();
    }
}
