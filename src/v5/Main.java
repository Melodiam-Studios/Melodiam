package v5;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Main extends Application {

    private int row = 0;
    private int column = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        Controller controller = loader.<Controller>getController();

        Scene scene = new Scene(root, 600  , 400);

        //root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        scene.getStylesheets().add("v5/style.css");
        primaryStage.setTitle("Melodiam v5");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        controller.addFile();

        //liest tastatureingabe ein
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controller.keyPresed(scene, event);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
