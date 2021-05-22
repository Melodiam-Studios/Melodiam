package v6;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Speichern {

    public static void Abspeichern(String file) {
        //Liste.werteAusfuellen();  //Muss man noch machen, aber wenn man es dazu lässt funktioniert das schreiben indie csv datei nicht mehr

        String dateiName = Notenblatt.getDateiName();
        int tonleiter = Notenblatt.getTonleiter();
        String instrument = Notenblatt.getInstrument();
        String komponist = Notenblatt.getKomponist();
        double taktart = Notenblatt.getTaktart();
        String notenschluessel = Notenblatt.getNotenschluessel();
        ArrayList<Takt> takte = Notenblatt.getTakte();

        System.out.println(takte);

        File file2 = new File(file);

        try {
            FileWriter fileWriter = new FileWriter(file2, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            bw.write(dateiName);
            bw.newLine();
            System.out.println("TONLEITER: " + tonleiter);
            bw.write(tonleiter);    //!!
            bw.newLine();
            bw.write(instrument);
            bw.newLine();
            bw.write(komponist);
            bw.newLine();
            System.out.println("TAKTART: " + taktart);
            bw.write(String.valueOf(taktart));
            bw.newLine();
            bw.write(notenschluessel);
            bw.newLine();
            for(int i=0;i<takte.size();i++)
            {
                bw.write(takte.get(i+1)+","+takte.get(i++));
                bw.newLine();
            }
            /*
            fileWriter.append(dateiName + "\n");
            fileWriter.append(instrument + "\n");
            fileWriter.append(komponist + "\n");
            fileWriter.append(notenschluessel + "\n");
            */

            System.out.println("Written!");
            bw.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Einlesen(){

    }
}
