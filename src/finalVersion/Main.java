package finalVersion;

import javafx.application.Application;
import javafx.stage.Stage;
import finalVersion.controller.CreatePreOptionWindow;


public class Main extends Application {

    private int row = 0;
    private int column = 0;

    /**
     * Creates a new PreOptionWindow
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        CreatePreOptionWindow cPreOptionWindow = new CreatePreOptionWindow(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
