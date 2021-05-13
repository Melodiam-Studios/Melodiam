package vT;

import com.sun.javafx.geom.Point2D;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
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
    ChoiceBox intervalle;

    @FXML
    Button bTrans;

    private int row = 0;
    private int column = 0;
    private int lenghtPane = 275;
    private int hightPane = 200;

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

        if (id.equals("bAdd")){
            addPane();
        }
        else if (id.equals("bRem") && storeLines.size() > 0){
            remPane();
        }
        else if (id.equals(bTrans.getId())){
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

        RowConstraints rowWith = new RowConstraints(100);
        mainInputPane.getRowConstraints().add(rowWith);

        ColumnConstraints columnWith = new ColumnConstraints(lenghtPane);
        mainInputPane.getColumnConstraints().add(columnWith);

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

    private void transponieren(){

        ArrayList<Note> noten = new ArrayList<>();
        Transponieren tran = new Transponieren();
        int intervall;
        int tonleiter = 0;

        Note note1 = new Note();

        note1.setAll(0,2,1, 0, "");
        noten.add(note1);
        Liste.werteAusfuellen(noten,tonleiter);

        //System.out.println(intervalle.getSelectionModel().getSelectedIndex());
        intervall = intervalle.getSelectionModel().getSelectedIndex() - 12;
        intervall *= -1;

        System.out.println(noten.toString());
        tran.hauptTrans(noten, intervall, tonleiter);
        System.out.println(noten.toString());
    }

}
