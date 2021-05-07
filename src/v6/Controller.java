package v6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class Controller {

    GridPane mainInputPane;

    @FXML ImageView previewImage = new ImageView();
    int offset = -15;

    @FXML
    AnchorPane anchorP;

    @FXML
    Button bAdd;

    @FXML
    Button bRem;

    @FXML
    Button ganzeNote;

    @FXML
    Button halbeNote;

    @FXML
    Button viertelNote;

    @FXML
    Button achtelNote;

    @FXML
    Button sechzehntelNote;

    @FXML
    Button ganzePause;

    @FXML
    Button halbePause;

    @FXML
    Button viertelPause;

    @FXML
    Button achtelPause;

    @FXML
    Button sechzehntelPause;

    private int row = 0;
    private int column = 0;
    private int lenghtPane = 275;
    private int hightPane = 200;
    public static int notenInTakt = 0;

    //Takte werden hier gespeichert
    private ArrayList<Pane> storeLines = new ArrayList<>();

    //Takte werden der reihe nach dem GridPane hinzugefügt
    private void drawPane(ArrayList<Pane> arr, GridPane root) {
        int column = 0, row = 0;

        root.getChildren().clear();
        for (Pane line : arr) {
            root.add(line, column, row);

            column++;
            if (column % 4 == 0) {
                row++;
                column = 0;

            }
        }
    }

    //Grid Pane hinzufügen
    public void addFile(){
        mainInputPane = new GridPane();
        anchorP.getChildren().add(mainInputPane);
    }

    //Schaut welcher Key gedrückt worden ist und löscht oder fügt neue elemente dan hinzu
    public void keyPresed(Scene scene, KeyEvent event){
        if (event.getCode() == KeyCode.LEFT && storeLines.size() > 0){
            remPane();
        }

        if (event.getCode() == KeyCode.RIGHT){
            addPane();
        }
    }

    //Schaut welcher Button gedrückt worden ist und löscht oder fügt neue elemente dan hinzu
    public void buttonPressd(ActionEvent event){
        Button btn = (Button) event.getSource();
        String id = btn.getId();

        if (id.equals(bAdd.getId())){
            addPane();
        }
        else if (id.equals(bRem.getId()) && storeLines.size() > 0){
            remPane();
        }
    }

    //Pane aus storeLines löschen
    private void remPane() {
        if (column >= 0) column--;
        if (column < 0) {
            row--;
            column = 3;
        }
        storeLines.remove(storeLines.size()-1);
        drawPane(storeLines, mainInputPane);
    }

    //neues Pane in storeLines Speicheren
    private void addPane() {
        if (column >= 4){
            row++;
            column = 0;
        }

        mainInputPane.addColumn(column);
        mainInputPane.addRow(row);

        RowConstraints rowWith = new RowConstraints(100);
        mainInputPane.getRowConstraints().add(rowWith);

        if (column == 0 ){
            ColumnConstraints columnWith = new ColumnConstraints(300);
            mainInputPane.getColumnConstraints().add(columnWith);
        }else {
            ColumnConstraints columnWith = new ColumnConstraints(lenghtPane);
            mainInputPane.getColumnConstraints().add(columnWith);
        }


        if (column == 0){
            Takt takt = new Takt(true);
            storeLines.add(takt.getPane());
            drawPane(storeLines, mainInputPane);
        }else{
            Takt takt = new Takt(false);
            storeLines.add(takt.getPane());
            drawPane(storeLines, mainInputPane);
        }
        column++;
    }

    public void onButtonNoten(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String id = btn.getId();

        //Noten und Bausen Buttons
        if(id.equals(ganzeNote.getId())){
            notenInTakt=1;
        }else if(id.equals(halbeNote.getId())){
            notenInTakt=2;
        }else if(id.equals(viertelNote.getId())){
            notenInTakt=4;
        }else if(id.equals(achtelNote.getId())){
            notenInTakt=8;
        }else if(id.equals(sechzehntelNote.getId())){
            notenInTakt=16;
        }else if(id.equals(ganzePause.getId())){
            notenInTakt=1*5;
        }else if(id.equals(halbePause.getId())){
            notenInTakt=2*5;
        }else if(id.equals(viertelPause.getId())){
            notenInTakt=4*5;
        }else if(id.equals(achtelPause.getId())){
            notenInTakt=8*5;
        }else if(id.equals(sechzehntelPause.getId())){
            notenInTakt=16*5;
        }

        //vorzeichen buttons
        //b Vorzeichen
        if (id.equals("bVorzeichen")){
            setVorzeichen("bV");
        }
        //kreuzvorzeichen
        else if (id.equals("kVorzeichen")){
            setVorzeichen("kV");
        }
        //auflösungsvorzeichen
        else if (id.equals("aVorzeichen")){
            setVorzeichen("aV");
        }
    }

    private void setVorzeichen(String vz) {
        String vorzeichen = vz;
        //wenn auch eine note ausgewählt ist
        if (notenInTakt == 1 || notenInTakt == 2 ||notenInTakt == 4 ||notenInTakt == 8 ||notenInTakt == 16){

        }
    }
}
