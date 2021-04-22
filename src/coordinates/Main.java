package coordinates;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import v4.Controller;

public class Main extends Application {





    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        Controller controller = new Controller();
        Pane root = new Pane();
        primaryStage.setTitle("Melodiam");
        primaryStage.setScene(new Scene(root, 600, 60));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
