package v6;

import java.util.ArrayList;

public class Notenblatt {

    private static int tonleiter = 0;
    private static String instrument = "Piano";
    private static String dateiName = "Default Datei";
    private static String komponist = "Default Komponist";
    private static String notenschluessel = "Violinschlüssel";
    private static double taktart = 1/4;

    private static ArrayList<Note> allNotes = new ArrayList<>();

    public static ArrayList<Takt> getTakte() {
        return takte;
    }

    public static void addTakt(Takt takt) {
        takte.add(takt);
    }

    private static ArrayList<Takt> takte = new ArrayList<>();

    public static void addNote (Note note){
        allNotes.add(note);
    }

    public static ArrayList<Note> getNotes (){
        return allNotes;
    }

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

    @Override
    public String toString() {
        return "Noten: " + allNotes;
    }
}