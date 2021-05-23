package v6;

import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Speichern {
    public static void Abspeichern(String filePath) {
        Liste.werteAusfuellen();

        String dateiName = Notenblatt.getDateiName();
        int tonleiter = Notenblatt.getTonleiter();
        String instrument = Notenblatt.getInstrument();
        String komponist = Notenblatt.getKomponist();
        double taktart = Notenblatt.getTaktart();
        String notenschluessel = Notenblatt.getNotenschluessel();
        ArrayList<Takt> takte = Notenblatt.getTakte();

        System.out.println(takte);

        File file = new File(filePath);

        try {
            FileWriter fileWriter = new FileWriter(file, true);
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
            int i = 0;
            for (Takt takt : takte) {
                for(Element element : takt.getElements()){
                    bw.write(String.valueOf(element.imageView.getX()));
                    bw.newLine();
                    bw.write(String.valueOf(element.imageView.getY()));
                    bw.newLine();
                    bw.write(String.valueOf(element.vorzeichenView.getX()));
                    bw.newLine();
                    bw.write(String.valueOf(element.vorzeichenView.getY()));
                    bw.newLine();
                    if(element.getClass() == Note.class){
                        bw.write(String.valueOf(((Note) element).getNotenInTakt()));
                        bw.newLine();
                        bw.write(String.valueOf(((Note) element).getPosition()));
                        bw.newLine();
                    }
                    else if(element.getClass() == Pause.class){
                        bw.write(String.valueOf(((Pause) element).getPausenInTakt()));
                        bw.newLine();
                    }
                    //bw.newLine();
                    //bw.newLine();
                    i++;
                }
            }

            /*
            - Arraylist in der reihenfolge
            - elements fu sem imageview x und y und vorzeichen new.x und y (to string)
            - notenintakt/pausenintakt
            - position

            und alles in der reihenfolge
             */

            System.out.println("Written!");
            bw.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Einlesen(String filePath) {
        System.out.println("IM EINLESEN!");

        File file = new File(filePath);
        System.out.println(file);

        ArrayList<String> data = new ArrayList<>();
        BufferedReader br;

        try{
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                data.addAll(Arrays.asList(values));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(data);
        System.out.println("Read!");

        Notenblatt.setDateiName(String.valueOf(data.get(0)));
        Notenblatt.setTonleiter(Integer.parseInt(data.get(1)));
        Notenblatt.setInstrument(String.valueOf(data.get(2)));
        Notenblatt.setKomponist(String.valueOf(data.get(3)));
        Notenblatt.setTaktart(Double.parseDouble(data.get(4)));
        Notenblatt.setNotenschluessel(String.valueOf(data.get(5)));

        System.out.println("DATA: -----> " + data.get(6));

        ArrayList<String> takte = new ArrayList<>(Collections.singleton((data.get(6))));
        System.out.println(takte);
    }
}
