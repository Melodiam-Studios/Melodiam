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
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    /**
     * GridPane responsible for storing the Panes Takt
     */
    GridPane mainInputPane;

    /**
     * ImageView for the preview of the note
     */
    @FXML ImageView previewImage = new ImageView();
    int offset = -15;

    /**
     * AnchorPane responsible for storing the Gridpane in the Scrollpane
     */
    @FXML
    AnchorPane anchorP;

    /**
     * Button responsible to add Takt on the GridPane mainInputPane
     */
    @FXML
    Button bAdd;

    /**
     * Button responsible to delete Takt on the GridPane mainInputPane
     */
    @FXML
    Button bRem;

    /**
     * Button that is responsible for outputting the written notes musically
     */
    @FXML
    Button bPlay;

    /**
     * Button responsible to select a whole note
     */
    @FXML
    Button ganzeNote;

    /**
     * Button responsible to select a half note
     */
    @FXML
    Button halbeNote;

    /**
     * Button responsible to select a quarter note
     */
    @FXML
    Button viertelNote;

    /**
     * Button responsible to select a eight note
     */
    @FXML
    Button achtelNote;

    /**
     * Button responsible to select a sixteenth note
     */
    @FXML
    Button sechzehntelNote;

    /**
     * Button responsible to select a whole break
     */
    @FXML
    Button ganzePause;

    /**
     * Button responsible to select a half break
     */
    @FXML
    Button halbePause;

    /**
     * Button responsible to select a quarter break
     */
    @FXML
    Button viertelPause;

    /**
     * Button responsible to select a eight break
     */
    @FXML
    Button achtelPause;

    /**
     * Button responsible to select a sixteenth break
     */
    @FXML
    Button sechzehntelPause;

    /**
     * ChoiceBox responsible for selecting the intevalle
     */
    @FXML
    ChoiceBox intervalle;

    /**
     * ChoiceBox responsible for selecting the instrument
     */
    @FXML
    ChoiceBox instrumente;

    /**
     * Spinner responsible for reading the the size of the tempo requested by the user
     */
    @FXML
    Spinner tempo;

    /**
     * Button responsible for transposition
     */
    @FXML
    Button bTrans;

    /**
     * Label responsible to show the Titel on the main Window
     */
    @FXML
    Label titel;

    /**
     * Label responsible to show the composer on the main Window
     */
    @FXML
    Label komponist;

    /**
     * Menu Item responsible to quite the main Window
     */
    @FXML
    MenuItem miQuit;

    /**
     * Menu Item responsible to open a new Window
     */
    @FXML
    MenuItem miNew;

    /**
     * Variable responsible to store the number of rows
     */
    private int row = 0;
    /**
     * Variable responsible to store the number of columns
     */
    private int column = 0;
    /**
     * Variable responsible to store the normal length of a Pane
     */
    private int lenghtPane = 275;
    /**
     * Variable responsible to store the length of the first Pane in a row
     */
    private int lenghtFirstsPane = 300;
    /**
     * Variable responsible to store the hight of a Pane
     */
    private int hightPane = 250;
    private static int id = 0;
    /**
     * Variable responsible to store the values of the notes in a tact
     */
    public static int notenInTakt = 0;
    /**
     * Variable responsible to store the value of the selected accidental
     * b-vorzeichen (flat sign)  = -1, auflösungszeichen (natural sign) = 0, k-vorzeichen (negativ signs) = 1
     */
    public static int vorzeichen = 2;

    /**
     * Stores all the Tacts that are alsow in the GridPane mainInputPane
     */
    private ArrayList<Pane> storeLines = new ArrayList<>();

    /**
     * all Tacts are added on the GridPane
     * @param arr is the ArrayList where all Panes are stored in
     * @param root is the GridPane where all the Panes get stored
     */
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


    /**
     * Adds GridPane on the main Window
     */
    public void addFile(){
        mainInputPane = new GridPane();
        anchorP.getChildren().add(mainInputPane);
    }

    /**
     * if a Key gets pressed this Method loks if it is D to delete a Pane on the GridPane and on the Arraylist, or A to add a Pane on the GridPan and on the ArrayList.
     * @param scene
     * @param event
     */
    public void keyPresed(Scene scene, KeyEvent event){
        if (event.getCode() == KeyCode.D && storeLines.size() > 0){
            remPane();
        }

        if (event.getCode() == KeyCode.A){
            addPane();
        }
    }

    /**
     * If button bAdd is onAction it adds a Pane.
     * If button bRem is onAction it removes a Pane.
     * If button bTrans is onAction it dose the transposition.
     * If button bPlay is onAction it Plays the notes.
     * @param event
     */
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

    /**
     * Removes the last Pane of storeLines and in Notenblatt.
     * Alswo it draws all the Panes new with drawPane()
     */
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

    /**
     * Adds the last Pane of storeLines and in Notenblatt.
     * Alswo it draws all the Panes new with drawPane()
     */
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
        }else{
            Takt takt = new Takt(false, id);
            storeLines.add(takt.getPane());
            drawPane(storeLines, mainInputPane);
            Notenblatt.addTakt(takt);
        }
        column++;
        id++;
    }

    /**
     * If a VorzeichenButton is on Action this Method looks which one was pressed and sets the variable vorzeichen
     * @param actionEvent
     */
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


    /**
     * If a Noten or Pause Button is on Action this Method looks which one was pressed and sets the variable notenInTakt
     * @param actionEvent
     */
    public void onButtonNoten(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String id = btn.getId();

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

    /**
     * Adds values to ChoiceBox intevalle
     */
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

    /**
     * Adds values to ChoiceBox instrumente
     */
    public void addInstrumente(){
        String instrum[] = {
                "Piano",
                "Trumpet",
                "French_Horn"};

        instrumente.setItems(FXCollections.observableArrayList(instrum));
        instrumente.getSelectionModel().select(Notenblatt.getInstrument());
    }

    /**
     * If the instrument is Changed this Method transpositions the notes correctly
     */
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

    /**
     * this Method is used tho transposition the notes correctly
     */
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

    /**
     * Plays the written notes
     */
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

    /**
     * Adds the  Titel and the composer on the main Window
     */
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

    /**
     * If the Menu Item miQuit is onAction the main Window closes
     */
    @FXML
    public void shutDownMain(){
        if ("miQuit".equals(miQuit.getId())){
            mainInputPane.getScene().getWindow().hide();
        }
    }

    /**
     * If the Menu Item miNew is on Action a new preOptionWidow opens
     */
    @FXML
    public void newWindow(){
        if("miNew".equals(miNew.getId())){

            try {
                CreatePreOptionWindow cPoW = new CreatePreOptionWindow(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
            shutDownMain();

        }
    }

    @FXML
    public void SaveFile() throws IOException { //woas no net ob des geat
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Speichern unter");

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            //System.out.println("Speicherort: " + fileToSave.getAbsolutePath());
            String path = fileToSave.getAbsolutePath();
            String fileName = path + ".csv"; //fileName ist der Name + .csv
            //System.out.println("fileName: " + file);
            File newFile = new File(fileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            Speichern.Abspeichern(fileName);
        }
    }
}
