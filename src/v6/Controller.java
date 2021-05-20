package v6;

import audio.PlayMelody;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import org.jfugue.pattern.Pattern;

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

    private int row = 0;
    private int column = 0;
    private int lenghtPane = 275;
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
        else if (id.equals(bPlay.getId())){
            playSong();
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
        Notenblatt.remTakt();
        id--;
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
            Takt takt = new Takt(true, id);
            storeLines.add(takt.getPane());
            drawPane(storeLines, mainInputPane);
            addTaktPictur(takt.getPane());
            Notenblatt.addTakt(takt);
        }else{
            Takt takt = new Takt(false, id);
            storeLines.add(takt.getPane());
            drawPane(storeLines, mainInputPane);
            Notenblatt.addTakt(takt);
        }
        column++;
        id++;
        addTonleiter();
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

    }

    private void playSong(){

        Pattern melody = new Pattern();
        melody.setInstrument(Notenblatt.getInstrument());
        melody.setTempo((int) tempo.getValue());

        int wert=0;
        String bezeichnung = "";

        /*melody.add("70w 71w 62g");
        melody.add("Rw");
        melody.add("70w");*/

        for (Takt takt : Notenblatt.getTakte()) {
            for (Element element : takt.getElements()) {
                if (element.getClass() == Note.class) {
                    wert = ((Note) element).getWert();
                    bezeichnung = new String(String.valueOf(wert)) + "q";
                    melody.add(bezeichnung);
                }
                else if (element.getClass() == Pause.class) {
                    //wert = ((Note) element).getWert();
                    bezeichnung = "R" + "q";
                    melody.add(bezeichnung);
                }
            }
        }

        PlayMelody playMelody = new PlayMelody(melody);
        Thread t = new Thread(playMelody,"AudioPlayback");
        t.start();
    }

    //Tonleiter grafisch ändern nur am anfang und beim Transponieren
    public void addTonleiter(){
        int i = 0;
        for (Pane p: storeLines){
            if(i%4 == 0){
                //Tonleiter hinzufügen
                Pane tmpPane = storeLines.get(i);
                Image img = null;
                //Richtiges Bild wählen
                switch (Notenblatt.getTonleiter()){
                    case 6:
                        img = new Image(getClass().getResource("/resources/bilder_tonleiter/Fis-Dur.png").toExternalForm());
                        break;
                    case 5:
                        img= new Image(getClass().getResource("/resources/bilder_tonleiter/H-Dur.png").toExternalForm());
                        break;
                    case 4:
                        img= new Image(getClass().getResource("/resources/bilder_tonleiter/E-Dur.png").toExternalForm());
                        break;
                    case 3:
                        img= new Image(getClass().getResource("/resources/bilder_tonleiter/A-Dur.png").toExternalForm());
                        break;
                    case 2:
                        img= new Image(getClass().getResource("/resources/bilder_tonleiter/D-Dur.png").toExternalForm());
                        break;
                    case 1:
                        img= new Image(getClass().getResource("/resources/bilder_tonleiter/G-Dur.png").toExternalForm());
                        break;
                    case 0:
                        img= null;
                        break;
                    case -1:
                        img= new Image(getClass().getResource("/resources/bilder_tonleiter/F-Dur.png").toExternalForm());
                        break;
                    case -2:
                        img= new Image(getClass().getResource("/resources/bilder_tonleiter/B-Dur.png").toExternalForm());
                        break;
                    case -3:
                        img= new Image(getClass().getResource("/resources/bilder_tonleiter/Es-Dur.png").toExternalForm());
                        break;
                    case -4:
                        img= new Image(getClass().getResource("/resources/bilder_tonleiter/As-Dur.png").toExternalForm());
                        break;
                    case -5:
                        img= new Image(getClass().getResource("/rresources/bilder_tonleiter/Des-Dur.png").toExternalForm());
                        break;
                    case -6:
                        img= new Image(getClass().getResource("/resources/bilder_tonleiter/Ges-Dur.png").toExternalForm());
                        break;
                }
                ImageView imageView = new ImageView(img);
                tmpPane.getChildren().add(imageView);
                int tonleiter_size = 40;
                imageView.setFitHeight(tonleiter_size * 1.705);
                imageView.setFitWidth(tonleiter_size);
                imageView.setY(35);
                imageView.setX(20);

            }
            i++;
        }
    }

    //Takte statisch nur am anfang setzen
    public void addTaktPictur(Pane startTakt){

        Image image = new Image(getClass().getResource("/resources/bilder_takte/44-Takt.PNG").toExternalForm());
        ImageView imageView = new ImageView(image);

        if (Notenblatt.getTaktart() == 44){
            startTakt.getChildren().add(imageView);
        }
        int takt_size = 40;
        imageView.setFitHeight(takt_size);
        imageView.setFitWidth(takt_size / 1.705);
        imageView.setY(35);

        //change x

        imageView.setX(30);



    }

    //Titel angeben und Komponist
    public void addHeader(){
        String textTitel = Notenblatt.getDateiName();
        String textKomponist = Notenblatt.getKomponist();

        titel.setText(textTitel);
        komponist.setText(textKomponist);
    }
}
