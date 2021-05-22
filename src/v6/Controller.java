package v6;

import audio.PlayMelody;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.jfugue.pattern.Pattern;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
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
    Button bPlay;

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
    Spinner tempo;

    @FXML
    Button bTrans;

    @FXML
    Label titel;

    @FXML
    Label komponist;

    @FXML
    MenuItem miQuit;

    @FXML
    MenuItem miNew;

    private int row = 0;
    private int column = 0;
    private int lenghtPane = 275;
    private int lenghtFirstsPane = 300;
    private int hightPane = 250;
    private static int id = 0;
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
        if (event.getCode() == KeyCode.D && storeLines.size() > 0){
            remPane();
        }

        if (event.getCode() == KeyCode.A){
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
        else if (id.equals(bPlay.getId())){
            playSong();
        }
    }

    //Pane aus storeLines und aus dem Notenblatt löschen
    private void remPane() {
        if (column >= 0) {
            column--;
        }
        if (column < 0) {
            row--;
            column = 3;
        }

        storeLines.remove(storeLines.size()-1);
        drawPane(storeLines, mainInputPane);
        Notenblatt.remTakt();
        id--;
    }

    //neues Pane in storeLines Speicheren
    public void addPane() {
        if (column >= 4){
            row++;
            column = 0;
        }

        mainInputPane.addColumn(column);
        mainInputPane.addRow(row);

        RowConstraints rowWith = new RowConstraints(150);
        mainInputPane.getRowConstraints().add(rowWith);

        if (column == 0 ){
            ColumnConstraints columnWith = new ColumnConstraints(lenghtFirstsPane);
            mainInputPane.getColumnConstraints().add(columnWith);
        }else {
            ColumnConstraints columnWith = new ColumnConstraints(lenghtPane);
            mainInputPane.getColumnConstraints().add(columnWith);
        }

        if (column == 0){
            Takt takt = new Takt(true, id);
            storeLines.add(takt.getPane());
            drawPane(storeLines, mainInputPane);
            Notenblatt.addTakt(takt);
            //Tonleiter ImageView hinzufügen
            ImageView imageViewTonleiter = new ImageView();
        }else{
            Takt takt = new Takt(false, id);
            storeLines.add(takt.getPane());
            drawPane(storeLines, mainInputPane);
            Notenblatt.addTakt(takt);
        }
        column++;
        id++;
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
        instrumente.getSelectionModel().select(Notenblatt.getInstrument());
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

        //ArrayList<Note> noten = Notenblatt.getNotes();

        Liste.werteAusfuellen();

        intervall = intervalle.getSelectionModel().getSelectedIndex() - 12;
        intervall *= -1;

        for (Takt takt:Notenblatt.getTakte()) {
            System.out.println(takt.toString());
        }

        Transponieren.hauptTrans(intervall);

        for (Takt takt:Notenblatt.getTakte()) {
            System.out.println(takt.toString());
        }

        erneuereAnsicht();

    }

    private void playSong(){

        Liste.werteAusfuellen();

        Pattern melody = new Pattern();
        melody.setInstrument(Notenblatt.getInstrument());
        melody.setTempo((int) tempo.getValue());

        int wert=0;
        String bezeichnung = "", laenge = "";

        /*Noten: mit Wert und Länge, z.B. "64q"
        Pause: "R" und Länge, z.B. "Rq"

        Noten-, Pausenlänge:
        w = ganze
        h = halbe
        q = viertel
        i = achtel
        s = sechzehntel
         */

        for (Takt takt : Notenblatt.getTakte()) {
            for (Element element : takt.getElements()) {
                if (element.getClass() == Note.class) {
                    wert = ((Note) element).getWert();
                    switch (((Note) element).getNotenInTakt()) {
                        case 1: laenge="w"; break;
                        case 2: laenge="h"; break;
                        case 4: laenge="q"; break;
                        case 8: laenge="i"; break;
                        case 16: laenge="s"; break;
                    }
                    bezeichnung = String.valueOf(wert) + laenge;
                    melody.add(bezeichnung);
                }
                else if (element.getClass() == Pause.class) {
                    switch (((Pause) element).getPausenInTakt()) {
                        case 5: laenge="w"; break;
                        case 10: laenge="h"; break;
                        case 20: laenge="q"; break;
                        case 40: laenge="i"; break;
                        case 80: laenge="s"; break;
                    }
                    bezeichnung = "R" + laenge;
                    melody.add(bezeichnung);
                }
            }
        }

        PlayMelody playMelody = new PlayMelody(melody);
        Thread t = new Thread(playMelody,"AudioPlayback");
        t.start();
    }

    //Titel angeben und Komponist
    public void addHeader(){
        String textTitel = Notenblatt.getDateiName();
        String textKomponist = Notenblatt.getKomponist();

        titel.setText(textTitel);
        komponist.setText(textKomponist);
    }

    //Löscht alle Panes und erstellt neue mit den Takten vom Notenblatt
    public void erneuereAnsicht(){

        System.out.println(Notenblatt.getTakte().size());
        for (int i=0; i<(Notenblatt.getTakte().size());i++) {
            remPaneTran();
            System.out.println("Takt wird gelöscht");
        }

        for (Takt takt: Notenblatt.getTakte()) {
            System.out.println(takt.getElements());
            System.out.println(takt.getId());
            addPaneTrans(takt.getElements(), takt.getId());
        }
    }

    //Pane aus storeLines löschen
    private void remPaneTran() {
        if (column >= 0) {
            column--;
        }
        if (column < 0) {
            row--;
            column = 3;
        }

        storeLines.remove(storeLines.size()-1);
        drawPane(storeLines, mainInputPane);
        id--;
    }

    //neues Pane in storeLines Speicheren
    public void addPaneTrans(ArrayList<Element> elements, int idNeu) {
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

        Takt takt;

        if (column == 0){
            takt = new Takt(true, idNeu);
            takt.setElements(elements);
            storeLines.add(takt.getPane());
            drawPane(storeLines, mainInputPane);
            //Tonleiter ImageView hinzufügen
            ImageView imageViewTonleiter = new ImageView();
        }else{
            takt = new Takt(false, idNeu);
            takt.setElements(elements);
            storeLines.add(takt.getPane());
            drawPane(storeLines, mainInputPane);
        }
        column++;
        id++;

        for (Element element: elements) {
            if (element instanceof Note){
                Note n1 = (Note) element;
                
            }
        }
    }

    @FXML
    public void shutDownMain(){
        if ("miQuit".equals(miQuit.getId())){
            mainInputPane.getScene().getWindow().hide();
        }
    }

    @FXML
    public void newWindow(){
        if("miNew".equals(miNew.getId())){
            shutDownMain();

        }
    }

    @FXML
    public void SaveFile() throws IOException { //woas no net ob des geat
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        fileChooser.setBackground(Color.red);

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        }
    }
}
