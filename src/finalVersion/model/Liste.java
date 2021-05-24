package finalVersion.model;

import finalVersion.controller.Element;
import finalVersion.controller.Note;
import finalVersion.controller.Takt;

import java.util.ArrayList;

/**
 * In der Klasse Liste befindet sich die Liste arr[][] mit den ganzen möglichen Noten und eine dazugehörige statische Methode welche Noten vervollständigt.
 */
public class Liste {
    private static int n = 0;
    private static int e = 0;

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

    public static ArrayList<Integer> positions = new ArrayList<>();

    public static void createList(){
        positions.add(-10000000);
        positions.add(24);
        positions.add(23);
        positions.add(22);
        positions.add(21);
        positions.add(20);
        positions.add(19);
        positions.add(18);
        positions.add(17);
        positions.add(16);
        positions.add(15);
        positions.add(14);
        positions.add(13);
        positions.add(12);
        positions.add(11);
        positions.add(10);
        positions.add(9);
        positions.add(8);
        positions.add(7);
        positions.add(6);
        positions.add(5);
        positions.add(4);
        positions.add(3);
        positions.add(2);
    }

    public static int positionumsetzen(int position){

        if( n == 0) {
            createList();
            n++;
        }
        //System.out.println("Position counting from above: " + position);
        try {
            position = positions.get(position);
        }catch (Exception ignored){}


        //System.out.println("Real position: " + position);

        return position;
    }

    public static ArrayList<Double> posAndCords = new ArrayList<>();

    public static void setCords(){
        posAndCords.add(-100000.0);
        posAndCords.add(-100000.0);
        posAndCords.add(110.0);
        posAndCords.add(105.0);
        posAndCords.add(100.0);
        posAndCords.add(95.0);
        posAndCords.add(90.0);
        posAndCords.add(85.0);
        posAndCords.add(80.0);
        posAndCords.add(75.0);
        posAndCords.add(70.0);
        posAndCords.add(65.0);
        posAndCords.add(60.0);
        posAndCords.add(55.0);
        posAndCords.add(50.0);
        posAndCords.add(45.0);
        posAndCords.add(40.0);
        posAndCords.add(35.0);
        posAndCords.add(30.0);
        posAndCords.add(25.0);
        posAndCords.add(20.0);
        posAndCords.add(15.0);
        posAndCords.add(10.0);
        posAndCords.add(5.0);
        posAndCords.add(0.0);
    }

    public static double posToCords(int position){
        if( e == 0) {
            setCords();
            e++;
        }
        //System.out.println("Position counting from above: " + position);

        double coords = posAndCords.get(position);

        //System.out.println("Real position: " + position);

        return coords;
    }

    /**
     * Hier werden alle weiteren Werte einer Note ausgefüllt, vorausgesetzt, die Noten haben bereits eine Position und ein Vorzeichen.
     */
    public static void werteAusfuellen() {

        int position, vorzeichen, wert = 0, anzeigenVorzeichen = 0;
        String bezeichnnung = null;

        for (Takt takt : Notenblatt.getTakte()) {
            for (Element element:takt.getElements()) {
                if (element.getClass() == Note.class){

                    position = ((Note) element).getPosition();
                    vorzeichen = ((Note) element).getVorzeichen();

                    for (int i = 0; i < arr.length; i++) {
                        if ((new Integer(arr[i][1]) == position) && (new Integer(arr[i][2]) == vorzeichen)) {

                            wert = new Integer(arr[i][0]);
                            bezeichnnung = arr[i][3];
                            break;
                        }
                    }

                    ((Note) element).setAll(wert, position, vorzeichen, anzeigenVorzeichen, bezeichnnung);
                }
            }
        }

        for (Takt takt : Notenblatt.getTakte()) {
            for (Element element : takt.getElements()) {
                if (element.getClass() == Note.class) {
                    Transponieren.erneuereAnzeigen((Note) element);
                }
            }
        }
    }
}
