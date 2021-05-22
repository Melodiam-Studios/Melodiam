package v6;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This class is the controller of the PreoptionWindow.
 * It controlls all the actions on the window and opens the Main Window if necessary.
 */
public class ControllerPreOptionWindow implements Initializable {

    /**
     * Button responsible for opening the main Window
     */
    @FXML
    Button openMainWindow;

    /**
     * TextField responsible for reading the file name requested by the user
     */
    @FXML
    TextField dateiName;

    /**
     * TextField responsible for reading the composer Name requested by the user
     */
    @FXML
    TextField komponist;

    /**
     * ChoiceBox responsible for selecting the instrument
     */
    @FXML
    ChoiceBox instrument;

    /**
     * ChoiceBox responsible for selecting the tact
     */
    @FXML
    ChoiceBox taktart;

    /**
     * ChoiceBox responsible for selecting the clef
     */
    @FXML
    ChoiceBox notenSchluessel;

    /**
     * Spinner respinsible for reading the number of tacts requested by the user
     */
    @FXML
    Spinner preAnzahlTakte;

    /**
     * As soon as the corresponding button has been pressed this method opens the main Window with the set values.
     */
    @FXML
    private void newWindow(){

        if (!dateiName.getText().isEmpty()){
            System.out.println(dateiName.getText());
            String tmpDateiName = dateiName.getText();
            Notenblatt.setDateiName(tmpDateiName);
        }

        if (!komponist.getText().isEmpty()){
            String tmpKomponist = komponist.getText();
            Notenblatt.setKomponist(tmpKomponist);
        }

        int tmpTaktAnzahl = (int) preAnzahlTakte.getValue();
        System.out.println(tmpTaktAnzahl);
        Notenblatt.setStartTaktAnzahl(tmpTaktAnzahl);

        String tmpInstrument = instrument.getSelectionModel().getSelectedItem().toString();
        Notenblatt.setInstrument(tmpInstrument);



        String tmpTaktart = taktart.getSelectionModel().getSelectedItem().toString();
        if(tmpTaktart.equals("4/4")){
            Notenblatt.setTaktart(44);
        }
        //3/4 mit 34 usw...

        String tmpNotenschluessel = notenSchluessel.getSelectionModel().getSelectedItem().toString();
        Notenblatt.setNotenschluessel(tmpNotenschluessel);

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
            controller.addHeader();

            for(int i = 0; i < Notenblatt.getStartTaktAnzahl(); i++){
                controller.addPane();
            }

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    controller.keyPresed(scene, event);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        //close pre option Window
        Stage preOptionStage = (Stage) openMainWindow.getScene().getWindow();
        preOptionStage.close();



    }

    /**
     * When the user selects a specific type of gamut (Tonleiter) this method stores it for the main Window in Notenblatt.java
     * @param event which Dur-Button
     */
    @FXML
    public void buttonDurPressed(ActionEvent event){
        Button durButton = (Button) event.getSource();
        String id = durButton.getId();

        //Set Tonleiter
        switch(id){
            case "GDur":
                Notenblatt.setTonleiter(1);
                break;
            case "DDur":
                Notenblatt.setTonleiter(2);
                break;
            case "EDur":
                Notenblatt.setTonleiter(4);
                break;
            case "HDur":
                Notenblatt.setTonleiter(5);
                break;
            case "ADur":
                Notenblatt.setTonleiter(3);
                break;
            case "FisDur":
                Notenblatt.setTonleiter(6);
                break;
            case "FDur":
                Notenblatt.setTonleiter(-1);
                break;
            case "BDur":
                Notenblatt.setTonleiter(-2);
                break;
            case "EsDur":
                Notenblatt.setTonleiter(-3);
                break;
            case "AsDur":
                Notenblatt.setTonleiter(-4);
                break;
            case "DesDur":
                Notenblatt.setTonleiter(-5);
                break;
            case "GesDur":
                Notenblatt.setTonleiter(-6);
                break;
            case "CDur":
                Notenblatt.setTonleiter(0);
                break;
        }
    }

    @FXML
    public void buttonImportPressed(ActionEvent event) throws IOException {
        String path = "C:\\bin";

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")){
            //Betriebssystem ist Windows-basiert
            System.out.println("Windows");
            Runtime.getRuntime().exec("explorer.exe /select, " + path);
        }
        else if (os.contains("osx")){
            //Betriebssystem ist Apple OSX
            System.out.println("Apple");
            Runtime.getRuntime().exec("usr/bin/open " + path);
        }
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
            //Betriebssystem ist Linux/Unix basiert
            System.out.println("Linux");
            Runtime.getRuntime().exec("open /Users/USER/ " + path);
        }
        else{
            System.out.println("FEHLER: Nicht das richtige Betriebssystem!");
        }
    }

    /**
     * This methods starts when the Object of this class is created and has access to the FXML elements of the object.
     * It stores values of The spinners so that they can get easily replaced.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set instruments
        String instrum[] = {
                "Piano",
                "Trumpet",
                "French_Horn"};
        instrument.setItems(FXCollections.observableArrayList(instrum));
        instrument.getSelectionModel().select("Piano");

        //set Taktarten
        String taktA[] = {
                "4/4"};
        taktart.setItems(FXCollections.observableArrayList(taktA));
        taktart.getSelectionModel().select("4/4");

        //set Notenschlüssel
        String notenSchl[] = {
                "Violinschluessel"
                //"Bassschglüssel",
                //"C-Schlüssel"
        };
        notenSchluessel.setItems(FXCollections.observableArrayList(notenSchl));
        notenSchluessel.getSelectionModel().select("Violinschlüssel");
    }
}