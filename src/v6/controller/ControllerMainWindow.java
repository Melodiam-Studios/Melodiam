package v6.controller;

import audio.PlayMelody;
import com.sun.javafx.geom.Point2D;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.jfugue.pattern.Pattern;
import v6.model.Liste;
import v6.model.Notenblatt;
import v6.model.Speichern;
import v6.model.Transponieren;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ControllerMainWindow {

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
     * Responsible for stopping song!
     */

    @FXML
    Button bStop;
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
     * If the Menu Item miQuit is onAction the main Window closes
     */
    @FXML
    public void shutDownMain(){
        mainInputPane.getScene().getWindow().hide();
    }

    /**
     * If the Menu Item miNew is on Action a new preOptionWidow opens
     */
    @FXML
    public void newWindow(){
        try {
            CreatePreOptionWindow cPoW = new CreatePreOptionWindow(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        shutDownMain();
    }

    @FXML
    public void SaveFile() throws IOException {
        JFrame parentFrame = new JFrame();

        parentFrame.setIconImage((new ImageIcon("C:\\Users\\Alex Hofer\\Documents\\GitHub\\Melodiam\\src\\resources\\melodiam_testicon.png").getImage()));

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Speichern unter");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV (.csv)", "csv");
        fileChooser.addChoosableFileFilter(filter);

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

    @FXML
    public void printPNG(){
        WritableImage image = anchorP.getParent().getParent().getParent().getParent().snapshot(new SnapshotParameters(), null);

        File file = new File(Notenblatt.getDateiName() + ".png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e){
            System.out.println("IO Exeption");
        }
        System.out.println("Finishd");

        /*PrinterJob job = PrinterJob.createPrinterJob();
        if(job != null){
            job.showPrintDialog(anchorP.getScene().getWindow());
            System.out.println(anchorP.getParent().getParent().getParent().getParent());
            job.printPage(anchorP.getParent().getParent().getParent().getParent());
            job.endJob();
        }*/
    }

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
    private int lenghtFirstsPane = 355;
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
    public static int vorzeichen = 0;

    /**
     * Stores all the Tacts that are also in the GridPane mainInputPane
     */
    private ArrayList<Pane> storeLines = new ArrayList<>();

    PlayMelody playMelody=null;
    Thread t=null;


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
        }else if(id.equals(bStop.getId())){
            stopSong();
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

        drawPane(storeLines, mainInputPane);
        Notenblatt.remTakt();
        id--;

        int tmptaktanzahl =  Notenblatt.getAktuelleTaktAnzahl();
        tmptaktanzahl--;
        Notenblatt.setAktuelleTaktAnzahl(tmptaktanzahl);
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

        int tmptaktanzahl =  Notenblatt.getAktuelleTaktAnzahl();
        tmptaktanzahl++;
        Notenblatt.setAktuelleTaktAnzahl(tmptaktanzahl);
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
                vorzeichen = 0;
            }

        }
        //kreuzvorzeichen
        else if (id.equals("kVorzeichen")){
            if (vorzeichen == 2 || vorzeichen == -1 || vorzeichen == 0){
                vorzeichen = 1;
            }else{
                vorzeichen = 0;
            }
        }
        //Auflösezeichen
        else if (id.equals("aVorzeichen")){
            if (vorzeichen == 0 || vorzeichen == 1 || vorzeichen == -1){
                vorzeichen = 2;
            }else{
                vorzeichen = 0;
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
                "Piano",        // C
                "Trumpet",      // B
                "French_Horn",  // F
                "Alto_Sax",     // Es
                "Clarinet",     // B
                "Flute"         // C
        };

        instrumente.setItems(FXCollections.observableArrayList(instrum));
        instrumente.getSelectionModel().select(Notenblatt.getInstrument());
    }

    /**
     * If the instrument is Changed this Method transpositions the notes correctly
     */
    private void changeInstrum(){

        String instrument = instrumente.getSelectionModel().getSelectedItem().toString();
        String vorInstrument = Notenblatt.getInstrument();

        System.out.println(instrument);
        if ((vorInstrument == "Piano") || (vorInstrument == "Flute") ){
            switch (instrument){
                case "Piano":
                case "Flute":
                    if (vorInstrument != instrument) intervalle.getSelectionModel().select("r. 1"); break;
                case "Trumpet":
                case "Clarinet":
                    intervalle.getSelectionModel().select("gr. 2"); break;
                case "French_Horn": intervalle.getSelectionModel().select("r. 5"); break;
                case "Alto_Sax": intervalle.getSelectionModel().select("gr. 6"); break;
            }
        }
        else if ((vorInstrument == "Trumpet") || (vorInstrument == "Clarinet")){
            switch (instrument){
                case "Trumpet":
                case "Clarinet":
                    if (vorInstrument != instrument) intervalle.getSelectionModel().select("r. 1"); break;
                case "Piano":
                case "Flute":
                    intervalle.getSelectionModel().select("gr. 2 n.u."); break;
                case "French_Horn": intervalle.getSelectionModel().select("r. 4"); break;
                case "Alto_Sax": intervalle.getSelectionModel().select("r. 5"); break;
            }
        }
        else if (vorInstrument == "French_Horn"){
            switch (instrument){
                case "Piano":
                case "Flute":
                    intervalle.getSelectionModel().select("r. 5 n.u."); break;
                case "Trumpet":
                case "Clarinet":
                    intervalle.getSelectionModel().select("r. 4 n.u."); break;
                case "Alto_Sax": intervalle.getSelectionModel().select("gr. 2"); break;
            }
        }
        else if (vorInstrument == "Alto_Sax"){
            switch (instrument){
                case "Piano":
                case "Flute":
                    intervalle.getSelectionModel().select("gr. 6 n.u."); break;
                case "Trumpet":
                case "Clarinet":
                    intervalle.getSelectionModel().select("r. 5 n.u."); break;
                case "French_Horn": intervalle.getSelectionModel().select("gr. 2 n.u."); break;
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
        if (playMelody != null) {
            return;
        }
        String instrument = Notenblatt.getInstrument();
        Liste.werteAusfuellen();

        Pattern melody = new Pattern();
        melody.setInstrument(instrument);
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

        /*"Piano",        // C
        "Trumpet",      // B
        "French_Horn",  // F
        "Alto_Sax",     // Es
        "Clarinet",     // B
        "Flute"         // C*/

        for (Takt takt : Notenblatt.getTakte()) {
            for (Element element : takt.getElements()) {
                if (element.getClass() == Note.class) {
                    wert = ((Note) element).getWert();
                    switch (instrument){
                        case "Piano": break;
                        case "Flute": wert = wert + 12; break;
                        case "Trumpet":
                        case "Clarinet":
                            wert = wert - 2; break;
                        case "French_Horn": wert = wert - 7; break;
                        case "Alto_Sax": wert = wert - 9; break;
                    }

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

        playMelody = new PlayMelody(melody);
        t = new Thread(playMelody,"AudioPlayback");
        t.setDaemon(true);
        t.start();

    }

    /**
     * Pauses the melody and sets to null
     */
    public void stopSong(){
        if(playMelody ==null){
            System.out.println("Not playing song!");
            return;
        }
        playMelody.pauseMelody();
        playMelody.run();
        playMelody=null;
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

        ArrayList<Takt> takte =  Notenblatt.getTakte();

        Takt takt = takte.get(0);

        takt.drawTonleiter();

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

    public void createFromRead(ArrayList<ArrayList<ReadElement>> listofElements){
        for (int i=0; i<(Notenblatt.getTakte().size());i++) {
            remPane();
            storeLines.remove(storeLines.size()-1);
            System.out.println("Takt wird gelöscht");
        }

        Notenblatt.clearElements();

        int numberOfTakte = Notenblatt.getAktuelleTaktAnzahl();
        System.out.println("NUMBEROFTAKTE: " + numberOfTakte);
        //readElements.add(new ReadElement(4, 14, 55.0, 40.0, 50.0, 40.0, 0));
        //readElements.add(new ReadElement(4, 13, 120.0, 40.0, 115.0, 40.0 , 0));

        addFile();

        for (int i=1;i<=numberOfTakte; i++){
            addPaneWithNotes(listofElements.get(i-1), i);
        }
    }

    //neues Pane in storeLines Speicheren
    public void addPaneWithNotes(ArrayList<ReadElement> readElements, int idNeu) {
        if (column >= 4){
            row++;
            column = 0;
        }

        System.out.println("TEST: " + column);
        System.out.println("TEST2: " + mainInputPane);
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

        Takt takt;
        int notenbereich = idNeu * 16;  //16
        int notenwerte = 0;
        int tmpNotenWert = 0;

        if (column == 0){
            takt = new Takt(true, idNeu);
            for (ReadElement readElement:readElements) {
                for(int i = 0; i<idNeu;){
                    if(readElement.inTakt>16){
                        tmpNotenWert = tmpNotenWert + readElement.inTakt/5;
                    }
                    else{
                        tmpNotenWert = tmpNotenWert + readElement.inTakt;
                    }
                    if(tmpNotenWert>16){
                        i++;
                    }
                }
                notenwerte = notenwerte + readElement.inTakt;
                System.out.println("ELEMENTS: " + readElement);
                System.out.println("notenwerte/notenbereich" + notenwerte + "    " + notenbereich);
                if(notenwerte >= notenbereich && notenwerte < notenbereich + 16){
                    System.out.println("notenwerte/notenbereich" + notenwerte + "    " + notenbereich);
                    if (readElement.inTakt % 5 == 0){
                        // Pause
                        Pause pause = new Pause(readElement.inTakt, takt.objektFang(new Point2D((float) readElement.imageViewX, (float) readElement.imageViewY), readElement.inTakt));
                        pause.setImageViewCoords(new Point2D((float) readElement.imageViewX, (float) readElement.imageViewY));
                        takt.erneuerePausen(pause);
                        System.out.println("Placing a Pause" + pause.getImageView().getParent());

                    }else{
                        // Note
                        Note note = new Note(readElement.inTakt, readElement.position,false, 0, takt.objektFang(new Point2D((float) readElement.imageViewX, (float) readElement.imageViewY), readElement.inTakt));
                        note.setAnzeigenVorzeichen(1);
                        note.setImageViewCoords(new Point2D((float) readElement.imageViewX, (float) readElement.imageViewY));
                        takt.erneuereNoten(note);
                        System.out.println("Placing a Note" + note.getImageView().getParent());
                    }
                }

            }
            Notenblatt.addTakt(takt);
            //Tonleiter ImageView hinzufügen
            ImageView imageViewTonleiter = new ImageView();

        }else{
            takt = new Takt(false, idNeu);
            for (ReadElement readElement:readElements) {
                if (readElement.inTakt % 5 == 0){
                    // Pause

                }else{
                    // Note
                    Note note = new Note(readElement.inTakt, readElement.position, false, 0, takt.objektFang(new Point2D((float) readElement.imageViewX, (float) readElement.imageViewY), readElement.inTakt));
                    note.setAnzeigenVorzeichen(1);
                    note.setImageViewCoords(new Point2D((float) readElement.imageViewX, (float) readElement.imageViewY));
                    takt.erneuereNoten(note);
                }
            }
            Notenblatt.addTakt(takt);
            //takt.setElements(elements);
        }
        storeLines.add(takt.getPane());
        drawPane(storeLines, mainInputPane);
        column++;
        id++;

    }

}
