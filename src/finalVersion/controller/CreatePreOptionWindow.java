package finalVersion.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *Class CreatePreOptionWindow is responisble for creating a new PreOptionWindow.
 * Its used when the programm starts and when the user wants to create a new Window.
 */
public class CreatePreOptionWindow {

    /**
     * The constructor is used to open the PreOptionWindow fast.
     * @param primaryStage
     * @throws IOException
     */
    public CreatePreOptionWindow(Stage primaryStage) throws IOException {

        Parent root2 = FXMLLoader.load(getClass().getResource("/finalVersion/view/preOptionWindowView.fxml"));
        Scene scene2 = new Scene(root2 , 660, 444);

        //root2.getStylesheets().add("v6/lightmode.css");
        Image image = new Image("/resources/melodiam_testicon.png");
        primaryStage.getIcons().add(image);

        primaryStage.setResizable(true);
        primaryStage.setScene(scene2);
        primaryStage.show();
    }

}
