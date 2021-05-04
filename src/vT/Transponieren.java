package vT;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class Transponieren {

    @FXML
    ChoiceBox intervalle;

    private ArrayList<Note> notenNeu = new ArrayList<>();
    private int intervall;

    public ArrayList<Note> hauptTrans (ArrayList<Note> notenAlt){

        intervall = intervalle.getSelectionModel().getSelectedIndex() - 12;
        intervall *= -1;



        return notenNeu;
    }
}
