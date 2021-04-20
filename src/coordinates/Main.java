package coordinates;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {





    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        Controller controller = new Controller();
        Pane root = controller.blabla(600);
        primaryStage.setTitle("Melodiam");
        primaryStage.setScene(new Scene(root, 600, 60));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
