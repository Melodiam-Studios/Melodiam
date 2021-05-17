package v6;

import java.util.ArrayList;

public class Notenblatt {

    private static int tonleiter = 0;
    private static ArrayList<Note> allNotes = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Noten: " + allNotes;
    }
}