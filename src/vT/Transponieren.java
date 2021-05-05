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
    private int neueTonleiter;

    public ArrayList<Note> hauptTrans (ArrayList<Note> notenAlt, int intervall, int tonleiter){

        this.intervall = intervall;

        bestimmeTonleiter(tonleiter);

        return notenNeu;
    }

    private void bestimmeTonleiter (int tonleiter){

        String bezeichnungTonl;
        String[][] listeTonl = {
                {"h", "11"},
                {"b", "10"},
                {"a", "9"},
                {"as", "8"},
                {"g", "7"},
                {"fis", "6"},
                {"f", "5"},
                {"e", "4"},
                {"es", "3"},
                {"d", "2"},
                {"des", "1"},
                {"c", "0"},
        };

        String[][] listeVorz = {
                {"fis", "6"},
                {"h", "5"},
                {"e", "4"},
                {"a", "3"},
                {"d", "2"},
                {"g", "1"},
                {"c", "0"},
                {"f", "-1"},
                {"b", "-2"},
                {"es", "-3"},
                {"as", "-4"},
                {"des", "-5"},
                {"ges", "-6"},
        };

        for (int i=0; i<listeVorz.length; i++){
            if (tonleiter == new Integer(listeVorz[i][1])){
                System.out.println(listeVorz[i][0]);
            }
        }
    }
}
