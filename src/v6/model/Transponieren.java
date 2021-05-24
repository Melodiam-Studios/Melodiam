package v6.model;

import v6.controller.Element;
import v6.controller.Note;
import v6.controller.Takt;

/**
 * In der Klasse Transponieren kann man die übergebenen Noten und die dazugehörige Tonleiter transponieren lassen.
 * Dies funktioniert mithilfe des übergebenen Intervalls.
 * Möchte man die Noten von einem Instrument zu einem anderen transponieren, so muss man das dazugehörige Intervall berechnen und dieses dann einfach übergeben.
 * Zusätzlich gibt es hier eine Methode, welche im Stande ist zu berechnen ob ein Vorzeichen einer Note angezeign oder nicht angezeigt werden muss, oder ob ein Auflösezeichen angezeigt werden muss.
 */
public class Transponieren {

    /**
     * Diese Methode ist die Hauptmethode welche zum Transponieren aufgerufen werden muss.
     *
     * @param intervall ist das Intervall, um welches transponiert werden soll.
     */
    public static void hauptTrans(int intervall) {

        bestimmeTonleiter(intervall);
        notenTransponieren(intervall);

        //return noten;
    }

    /**
     * Hier wird die neue Tonleiter bestimmt.
     *
     * @param intervall übergibt das Intervall.
     * @return gibt die neue Tonleiter zurück.
     */
    private static void bestimmeTonleiter(int intervall) {

        int tonleiter = Notenblatt.getTonleiter();
        String bezeichnungTonl = "";

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

        //Bezeichnung der alten Tonleiter herausfinden
        for (int i = 0; i < listeVorz.length; i++) {
            if (tonleiter == new Integer(listeVorz[i][1])) {
                bezeichnungTonl = listeVorz[i][0];
            }
        }

        //Alte Tonleiter in der listeTonl suchen und aus dessen Wert das Intervall dazuzählen
        for (int i = 0; i < listeTonl.length; i++) {
            if (bezeichnungTonl == listeTonl[i][0]) {
                tonleiter = new Integer(listeTonl[i][1]);
                tonleiter += intervall;
                tonleiter += 12;
                tonleiter = tonleiter % 12;
            }
        }

        //Bezeichnung der neuen Tonleiter in listeTonl suchen
        for (int i = 0; i < listeTonl.length; i++) {
            if (tonleiter == new Integer(listeTonl[i][1])) {
                bezeichnungTonl = listeTonl[i][0];
            }
        }

        //in listeVorz die dazugehörigen Vorzeichen der neuen Tonleiter herausfinden
        for (int i = 0; i < listeVorz.length; i++) {
            if (bezeichnungTonl == listeVorz[i][0]) {
                tonleiter = new Integer(listeVorz[i][1]);
            }
        }

        System.out.println(bezeichnungTonl + " - " + tonleiter);

        Notenblatt.setTonleiter(tonleiter);
    }

    /**
     * Hier werden alle Noten transponiert
     *
     * @param intervall übergibt das Intervall.
     */
    private static void notenTransponieren(int intervall) {

        int wert, anzeigen;
        boolean exit;
        int tonleiter = Notenblatt.getTonleiter();

        for (Takt takt : Notenblatt.getTakte()) {
            for (Element element : takt.getElements()) {
                if (element.getClass() == Note.class) {

                    System.out.println(((Note) element).getWert());

                    wert = ((Note) element).getWert() + intervall;
                    anzeigen = 1;
                    exit = false;

                    //Falls es den Notenwert ohne Vorzeichen gibt
                    for (int i = 0; i < Liste.arr.length; i++) {
                        if ((wert == new Integer(Liste.arr[i][0])) && (Liste.arr[i][2] == "0")) {
                            ((Note) element).setAll(wert, new Integer(Liste.arr[i][1]), 0, anzeigen, Liste.arr[i][3]);
                            erneuereAnzeigen((Note) element);
                            ((Note) element).setGUI();
                            exit = true;
                            break;
                        }
                    }

                    //Wenn ein Vorzeichen dazukommt
                    if (exit == false) {
                        for (int i = 0; i < Liste.arr.length; i++) {
                            if (wert == new Integer(Liste.arr[i][0])) {
                                if ((tonleiter >= 0) && (Liste.arr[i][2] == "1")) {
                                    ((Note) element).setAll(wert, new Integer(Liste.arr[i][1]), 1, anzeigen, Liste.arr[i][3]);
                                    erneuereAnzeigen((Note) element);
                                    ((Note) element).setGUI();
                                    break;
                                } else if ((tonleiter < 0) && (Liste.arr[i][2] == "-1")) {
                                    ((Note) element).setAll(wert, new Integer(Liste.arr[i][1]), -1, anzeigen, Liste.arr[i][3]);
                                    erneuereAnzeigen((Note) element);
                                    ((Note) element).setGUI();
                                    break;
                                }
                            }
                        }
                    }
                    exit = false;
                }
            }
        }
        //System.out.println(noten.toString());
    }

    /**
     * Erneuert das Attribut vorzeichenAnzeigen für jede einzelne Note.
     * z.B. das Kreuzvorzeichen beim fis wird nicht angezeigt, wenn vorne aufgrund der Tonleiter bereits ein Vorzeichen steht.
     */
    public static void erneuereAnzeigen(Note note) {

        int wert, vorzeichen;
        int anzeigen;
        int tonleiter = Notenblatt.getTonleiter();
/*
        for (Takt takt : Notenblatt.getTakte()) {
            for (Element element : takt.getElements()) {
                if (element.getClass() == Note.class) {

                    */

        wert = note.getWert() % 12;
        vorzeichen = note.getVorzeichen();
        anzeigen = 1;

        if ((vorzeichen == 1) && (tonleiter > 0)) {
            switch (tonleiter) {
                case 1:
                    if (wert == 6) anzeigen = 0;        //fis
                    break;
                case 2:
                    if (wert == 6) anzeigen = 0;        //fis
                    else if (wert == 1) anzeigen = 0;   //cis
                    break;
                case 3:
                    if (wert == 6) anzeigen = 0;        //fis
                    else if (wert == 1) anzeigen = 0;   //cis
                    else if (wert == 8) anzeigen = 0;   //gis
                    break;
                case 4:
                    if (wert == 6) anzeigen = 0;        //fis
                    else if (wert == 1) anzeigen = 0;   //cis
                    else if (wert == 8) anzeigen = 0;   //gis
                    else if (wert == 3) anzeigen = 0;   //dis
                    break;
                case 5:
                    if (wert == 6) anzeigen = 0;        //fis
                    else if (wert == 1) anzeigen = 0;   //cis
                    else if (wert == 8) anzeigen = 0;   //gis
                    else if (wert == 3) anzeigen = 0;   //dis
                    else if (wert == 10) anzeigen = 0;  //ais
                    break;
                case 6:
                    if (wert == 6) anzeigen = 0;        //fis
                    else if (wert == 1) anzeigen = 0;   //cis
                    else if (wert == 8) anzeigen = 0;   //gis
                    else if (wert == 3) anzeigen = 0;   //dis
                    else if (wert == 10) anzeigen = 0;  //ais
                    else if (wert == 5) anzeigen = 0;   //eis
                    break;
            }
        } else if ((vorzeichen == -1) && (tonleiter < 0)) {
            switch (tonleiter) {
                case -1:
                    if (wert == 10) anzeigen = 0;       //b
                    break;
                case -2:
                    if (wert == 10) anzeigen = 0;       //b
                    else if (wert == 3) anzeigen = 0;   //es
                    break;
                case -3:
                    if (wert == 10) anzeigen = 0;       //b
                    else if (wert == 3) anzeigen = 0;   //es
                    else if (wert == 8) anzeigen = 0;   //as
                    break;
                case -4:
                    if (wert == 10) anzeigen = 0;       //b
                    else if (wert == 3) anzeigen = 0;   //es
                    else if (wert == 8) anzeigen = 0;   //as
                    else if (wert == 1) anzeigen = 0;   //des
                    break;
                case -5:
                    if (wert == 10) anzeigen = 0;       //b
                    else if (wert == 3) anzeigen = 0;   //es
                    else if (wert == 8) anzeigen = 0;   //as
                    else if (wert == 1) anzeigen = 0;   //des
                    else if (wert == 6) anzeigen = 0;   //ges
                    break;
                case -6:
                    if (wert == 10) anzeigen = 0;       //b
                    else if (wert == 3) anzeigen = 0;   //es
                    else if (wert == 8) anzeigen = 0;   //as
                    else if (wert == 1) anzeigen = 0;   //des
                    else if (wert == 6) anzeigen = 0;   //ges
                    else if (wert == 11) anzeigen = 0;  //ces
                    break;
                default:
                    break;
            }
        } else if (vorzeichen == 0) {
            switch (tonleiter) {
                case 6:
                    if (wert == 5) anzeigen = 0;        //f
                    else if (wert == 0) anzeigen = 0;   //c
                    else if (wert == 7) anzeigen = 0;   //g
                    else if (wert == 2) anzeigen = 0;   //d
                    else if (wert == 9) anzeigen = 0;   //a
                    else if (wert == 4) anzeigen = 0;   //e
                    break;
                case 5:
                    if (wert == 5) anzeigen = 0;        //f
                    else if (wert == 0) anzeigen = 0;   //c
                    else if (wert == 7) anzeigen = 0;   //g
                    else if (wert == 2) anzeigen = 0;   //d
                    else if (wert == 9) anzeigen = 0;   //a
                    break;
                case 4:
                    if (wert == 5) anzeigen = 0;        //f
                    else if (wert == 0) anzeigen = 0;   //c
                    else if (wert == 7) anzeigen = 0;   //g
                    else if (wert == 2) anzeigen = 0;   //d
                    break;
                case 3:
                    if (wert == 5) anzeigen = 0;        //f
                    else if (wert == 0) anzeigen = 0;   //c
                    else if (wert == 7) anzeigen = 0;   //g
                    break;
                case 2:
                    if (wert == 5) anzeigen = 0;        //f
                    else if (wert == 0) anzeigen = 0;   //c
                    break;
                case 1:
                    if (wert == 5) anzeigen = 0;        //f
                    break;
                case -1:
                    if (wert == 11) anzeigen = 0;       //h
                    break;
                case -2:
                    if (wert == 11) anzeigen = 0;       //h
                    else if (wert == 4) anzeigen = 0;   //e
                    break;
                case -3:
                    if (wert == 11) anzeigen = 0;       //h
                    else if (wert == 4) anzeigen = 0;   //e
                    else if (wert == 9) anzeigen = 0;   //a
                    break;
                case -4:
                    if (wert == 11) anzeigen = 0;       //h
                    else if (wert == 4) anzeigen = 0;   //e
                    else if (wert == 9) anzeigen = 0;   //a
                    else if (wert == 2) anzeigen = 0;   //d
                    break;
                case -5:
                    if (wert == 11) anzeigen = 0;       //h
                    else if (wert == 4) anzeigen = 0;   //e
                    else if (wert == 9) anzeigen = 0;   //a
                    else if (wert == 2) anzeigen = 0;   //d
                    else if (wert == 7) anzeigen = 0;   //g
                    break;
                case -6:
                    if (wert == 11) anzeigen = 0;       //h
                    else if (wert == 4) anzeigen = 0;   //e
                    else if (wert == 9) anzeigen = 0;   //a
                    else if (wert == 2) anzeigen = 0;   //d
                    else if (wert == 7) anzeigen = 0;   //g
                    else if (wert == 0) anzeigen = 0;   //c
                    break;

            }
        }

        note.setAnzeigenVorzeichen(anzeigen);
    }
}

