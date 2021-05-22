package v6;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Speichern {

    public static void Abspeichern(String file) {
        Liste.werteAusfuellen();

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
            bw.write(String.valueOf(tonleiter));
            bw.newLine();
            bw.write(instrument);
            bw.newLine();
            bw.write(komponist);
            bw.newLine();
            bw.write(String.valueOf(taktart));
            bw.newLine();
            bw.write(notenschluessel);
            bw.newLine();
            for (Takt takt : takte) {
                bw.write(String.valueOf(takt));
                //bw.newLine();
            }

            System.out.println("Written!");
            bw.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Einlesen(String file) {
        System.out.println("IM EINLESEN!");

        File file2 = new File(file);
        System.out.println(file2);

        List<List<String>> data = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file2));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(Arrays.asList(values));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(data);
        System.out.println("Read!");
}
}
