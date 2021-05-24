package v6.model;

import v6.controller.Element;
import v6.controller.Takt;

import java.util.ArrayList;

/**
 * In der Klasse Notenblatt werden alle relevanten Informationen statisch gespeichert und bei Änderungen immer aktualisiert.
 * Enthaltene Inforationen: Aktuelle Tonleiter (z.B. C-Dur), Instrument, Dateiname, Komponist, Notenschluessel (z.B. Violinschluessel),
 * Taktart (z.B. 4/4) und die ArrayList mit den ganzen Takten (in welche dann die ganzen Noten und Pausen gespeichert sind)
 */
public class Notenblatt {

    private static int tonleiter = 0;
    private static String instrument = "Piano";
    private static String dateiName = "Default Datei";
    private static String komponist = "Default Komponist";
    private static String notenschluessel = "Violinschluessel";
    private static double taktart = 44;
    private static int startTaktAnzahl = 0;
    private static int aktuelleTaktAnzahl = 0;

    //private static ArrayList<Note> allNotes = new ArrayList<>();
    private static ArrayList<Takt> takte = new ArrayList<>();


    public static void renewTakt (ArrayList<Element> elements, int id) {
        for (Takt takt : takte) {
            if (takt.getId() == id){
                takt.setElements(elements);
            }
        }
    }

    public static ArrayList<Takt> getTakte() {
        return takte;
    }
    public static void addTakt(Takt takt) {
        takte.add(takt);
    }
    public static void remTakt() {
        takte.remove(takte.size() - 1);
    }

    /*public static void addNote (Note note){
        allNotes.add(note);
    }
    public static ArrayList<Note> getNotes (){
        return allNotes;
    }*/

    public static void setTonleiter(int tonleiter) {
        Notenblatt.tonleiter = tonleiter;
    }
    public static int getTonleiter() {
        return tonleiter;
    }

    public static String getInstrument() {
        return instrument;
    }
    public static void setInstrument(String instrument) {
        Notenblatt.instrument = instrument;
    }

    public static void setDateiName(String dateiName) {
        Notenblatt.dateiName = dateiName;
    }
    public static String getDateiName() {
        return dateiName;
    }

    public static void setKomponist(String komponist) {
        Notenblatt.komponist = komponist;
    }
    public static String getKomponist() {
        return komponist;
    }

    public static void setTaktart(double taktart) {
        Notenblatt.taktart = taktart;
    }
    public static double getTaktart() {
        return taktart;
    }

    public static void setNotenschluessel(String notenschluessel) {
        Notenblatt.notenschluessel = notenschluessel;
    }
    public static String getNotenschluessel() {
        return notenschluessel;
    }

    public static void setStartTaktAnzahl(int startTaktAnzahl) {
        Notenblatt.startTaktAnzahl = startTaktAnzahl;
    }
    public static int getStartTaktAnzahl() { return startTaktAnzahl; }

    public static void setAktuelleTaktAnzahl(int aktuelleTaktAnzahl) { Notenblatt.aktuelleTaktAnzahl = aktuelleTaktAnzahl; }
    public static int getAktuelleTaktAnzahl() { return aktuelleTaktAnzahl; }

    public static void clearElements(){
        takte.clear();
    }

    @Override
    public String toString() {
        return "Noten: " + takte;
    }
}