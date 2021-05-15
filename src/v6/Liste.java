package v6;

import java.util.ArrayList;

/**
 * In der Klasse Liste befindet sich die Liste arr[][] mit den ganzen möglichen Noten und eine dazugehörige statische Methode welche Noten vervollständigt.
 */
public class Liste {

    /**
     * Beinhaltet alle möglichen Noten, mit den jeweiligen Werten, Positionen, Vorzeichen und Bezeichnungen.
     * z.B. {"39", "2", "-1", "es"} -> 39=Wert, 2=Position, -1=Vorzeichen, es=Bezeichnung
     */
    public static String arr [][] = {
        {"39", "2", "-1", "es"},
        {"40", "2", "0", "e"},
            {"41", "2", "1", "f"},
            {"40", "3", "-1", "e"},
        {"41", "3", "0", "f"},
        {"42", "3", "1", "fis"},
        {"42", "4", "-1", "ges"},
        {"43", "4", "0", "g"},
        {"44", "4", "1", "gis"},
        {"44", "5", "-1", "as"},
        {"45", "5", "0", "a"},
        {"46", "5", "1", "ais"},
        {"46", "6", "-1", "b"},
        {"47", "6", "0", "h"},
            {"48", "6", "1", "c1"},
            {"47", "7", "-1", "h"},
        {"48", "7", "0", "c1"},
        {"49", "7", "1", "cis1"},
        {"49", "8", "-1", "des1"},
        {"50", "8", "0", "d1"},
        {"51", "8", "1", "dis1"},
        {"51", "9", "-1", "es1"},
        {"52", "9", "0", "e1"},
            {"53", "9", "1", "f1"},
            {"52", "10", "-1", "e1"},
        {"53", "10", "0", "f1"},
        {"54", "10", "1", "fis1"},
        {"54", "11", "-1", "ges1"},
        {"55", "11", "0", "g1"},
        {"56", "11", "1", "gis1"},
        {"56", "12", "-1", "as1"},
        {"57", "12", "0", "a1"},
        {"58", "12", "1", "ais1"},
        {"58", "13", "-1", "b1"},
        {"59", "13", "0", "h1"},
            {"60", "13", "1", "c2"},
            {"59", "14", "-1", "h1"},
        {"60", "14", "0", "c2"},
        {"61", "14", "1", "cis2"},
        {"61", "15", "-1", "des2"},
        {"62", "15", "0", "d2"},
        {"63", "15", "1", "dis2"},
        {"63", "16", "-1", "es2"},
        {"64", "16", "0", "e2"},
            {"65", "16", "1", "f2"},
            {"64", "17", "-1", "e2"},
        {"65", "17", "0", "f2"},
        {"66", "17", "1", "fis2"},
        {"66", "18", "-1", "ges2"},
        {"67", "18", "0", "g2"},
        {"68", "18", "1", "gis2"},
        {"68", "19", "-1", "as2"},
        {"69", "19", "0", "a2"},
        {"70", "19", "1", "ais2"},
        {"70", "20", "-1", "b2"},
        {"71", "20", "0", "h2"},
            {"72", "20", "1", "c3"},
            {"71", "21", "-1", "h2"},
        {"72", "21", "0", "c3"},
        {"73", "21", "1", "cis3"},
        {"73", "22", "-1", "des3"},
        {"74", "22", "0", "d3"},
        {"75", "22", "1", "dis3"},
        {"75", "23", "-1", "es3"},
        {"76", "23", "0", "e3"},
            {"77", "23", "1", "f3"},
            {"76", "24", "-1", "e3"},
        {"77", "24", "0", "f3"},
        {"78", "24", "1", "fis3"},
    };

    /**
     * Hier werden alle weiteren Werte einer Note ausgefüllt, vorausgesetzt, die Noten haben bereits eine Position und ein Vorzeichen.
     * @param noten übergibt die Noten.
     * @param tonleiter übergibt die Tonleiter, damit erneuereAnzeigen aufgerufen werden kann (berechnet ob das Vorzeichen angezeigt wird.)
     */
    public static void werteAusfuellen(ArrayList<Note> noten, int tonleiter) {

        int position, vorzeichen, wert = 0, anzeigenVorzeichen = 0;
        String bezeichnnung = null;

        for (Note note : noten) {

            position = note.getPosition();
            vorzeichen = note.getVorzeichen();

            for (int i = 0; i < arr.length; i++) {
                if ((new Integer(arr[i][1]) == position) && (new Integer(arr[i][2]) == vorzeichen)) {

                    wert = new Integer(arr[i][0]);
                    bezeichnnung = arr[i][3];
                    break;
                }
            }

            note.setAll(wert, position, vorzeichen, anzeigenVorzeichen, bezeichnnung);
        }

        Transponieren.erneuereAnzeigen(noten, tonleiter);
    }
}
