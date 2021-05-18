package v6;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.util.Objects;

public class controllerPreOptionWindow {

    @FXML
    Button openMainWindow;

    /*public Controller2() throws IOException {
        /*FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        Parent root = loader.load();
        Stage secondStage = new Stage();
        secondStage.setScene(new Scene((Parent) loader.load()));*/

        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        Stage stage = new Stage();
        //stage.initOwner(btn1.getScene().getWindow());
        stage.setScene(new Scene((Parent) loader.load()));


        Controller2 controller2 = loader.getController();
    }*/

    @FXML
    private void newWindow(){
        //button pressed when file should be created
        System.out.println("works");

        try{
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("mainView.fxml")));
            Parent root = loader.load();

            Stage primaryStage = new Stage();

            JMetro jMetro=new JMetro(Style.LIGHT);

            Controller controller = loader.<Controller>getController();

            Image image = new Image("/resources/melodiam_testicon.png");
            primaryStage.getIcons().add(image);

            Scene scene = new Scene(root, 600  , 400);


            jMetro.setScene(scene);



            //root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());

            //Open main Window
            root.getStylesheets().add("v6/lightmode.css");
            primaryStage.setTitle("Melodiam v6");
            primaryStage.setScene(jMetro.getScene());
            primaryStage.setMaximized(true);
            primaryStage.show();

            controller.addFile();
            controller.addIntervalle();
            controller.addInstrumente();


        }catch (Exception e){
            e.printStackTrace();
        }
        //close pre option Window
        Stage preOptionStage = (Stage) openMainWindow.getScene().getWindow();
        preOptionStage.close();

    }

}
