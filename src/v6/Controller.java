package v6;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class Controller {

    //TO-DO: Im Takt muss ekannt werden ob eine Vorzeichen mt dem Takt zusammen kommt

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

    @FXML
    ChoiceBox intervalle;

    @FXML
    ChoiceBox instrumente;

    @FXML
    Button bTrans;

    private int row = 0;
    private int column = 0;
    private int lenghtPane = 275;
    private int hightPane = 250;
    public static int notenInTakt = 0;
    public static int vorzeichen = 2;

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
        else if (id.equals(bTrans.getId())){
            changeInstrum();
            transponieren();
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

        RowConstraints rowWith = new RowConstraints(150);
        mainInputPane.getRowConstraints().add(rowWith);

        if (column == 0 ){
            ColumnConstraints columnWith = new ColumnConstraints(275);
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

    //erkent Vorzeichen
    public void onButtonVorzeichen(ActionEvent actionEvent){
        ToggleButton btn = (ToggleButton) actionEvent.getSource();
        String id = btn.getId();

        //b Vorzeichen
        if (id.equals("bVorzeichen")){
            if (vorzeichen == 2 || vorzeichen == 1 || vorzeichen == 0){
                vorzeichen = -1;
            }else{
                vorzeichen = 2;
            }

        }
        //kreuzvorzeichen
        else if (id.equals("kVorzeichen")){
            if (vorzeichen == 2 || vorzeichen == -1 || vorzeichen == 0){
                vorzeichen = 1;
            }else{
                vorzeichen = 2;
            }
        }
        //Auflösezeichen
        else if (id.equals("aVorzeichen")){
            if (vorzeichen == 2 || vorzeichen == 1 || vorzeichen == -1){
                vorzeichen = 0;
            }else{
                vorzeichen = 2;
            }
        }
    }

    //erkennt Noten bzw Pasuen
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
            notenInTakt=5;
        }else if(id.equals(halbePause.getId())){
            notenInTakt=10;
        }else if(id.equals(viertelPause.getId())){
            notenInTakt=20;
        }else if(id.equals(achtelPause.getId())){
            notenInTakt=40;
        }else if(id.equals(sechzehntelPause.getId())){
            notenInTakt=80;
        }
    }


    public void addIntervalle(){
        String interv[] = {
                "r. 8",
                "gr. 7",
                "kl. 7",
                "gr. 6",
                "kl. 6",
                "r. 5",
                "ü. 4",
                "r. 4",
                "gr. 3",
                "kl. 3",
                "gr. 2",
                "kl. 2",
                "r. 1",
                "kl. 2 n.u.",
                "gr. 2 n.u.",
                "kl. 3 n.u.",
                "gr. 3 n.u.",
                "r. 4 n.u.",
                "ü. 4 n.u.",
                "r. 5 n.u.",
                "kl. 6 n.u.",
                "gr. 6 n.u.",
                "kl. 7 n.u.",
                "gr. 7 n.u.",
                "r. 8 n.u."};

        intervalle.setItems(FXCollections.observableArrayList(interv));
        intervalle.getSelectionModel().select("r. 1");
    }

    public void addInstrumente(){
        String instrum[] = {
                "Piano",
                "Trumpet",
                "French_Horn"};

        instrumente.setItems(FXCollections.observableArrayList(instrum));
        instrumente.getSelectionModel().select("Piano");
    }

    private void changeInstrum(){

        String instrument = instrumente.getSelectionModel().getSelectedItem().toString();

        System.out.println(instrument);
        if (Notenblatt.getInstrument() == "Piano"){
            switch (instrument){
                case "Trumpet": intervalle.getSelectionModel().select("gr. 2"); break;
                case "French_Horn": intervalle.getSelectionModel().select("r. 5"); break;
            }
        }
        else if (Notenblatt.getInstrument() == "Trumpet"){
            switch (instrument){
                case "Piano": intervalle.getSelectionModel().select("gr. 2 n.u."); break;
                case "French_Horn": intervalle.getSelectionModel().select("r. 4"); break;
            }
        }
        else if (Notenblatt.getInstrument() == "French_Horn"){
            switch (instrument){
                case "Piano": intervalle.getSelectionModel().select("r. 5 n.u."); break;
                case "Trumpet": intervalle.getSelectionModel().select("r. 4 n.u."); break;
            }
        }

        Notenblatt.setInstrument(instrument);
    }

    private void transponieren(){

        int intervall;
        int tonleiter = Notenblatt.getTonleiter();

        ArrayList<Note> noten = Notenblatt.getNotes();

        Liste.werteAusfuellen(noten, tonleiter);

        intervall = intervalle.getSelectionModel().getSelectedIndex() - 12;
        intervall *= -1;

        System.out.println(noten.toString());
        Transponieren.hauptTrans(noten, intervall, tonleiter);
        System.out.println(noten.toString());

        for (Note note:noten) {
            note.setNote(note.getPosition(),note.getVorzeichen());
        }
    }
}
