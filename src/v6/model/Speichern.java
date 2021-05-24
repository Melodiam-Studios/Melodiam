package v6.model;

import v6.controller.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Speichern {
    public static void Abspeichern(String filePath) {
        Liste.werteAusfuellen();

        String dateiName = Notenblatt.getDateiName();
        int tonleiter = Notenblatt.getTonleiter();
        String instrument = Notenblatt.getInstrument();
        String komponist = Notenblatt.getKomponist();
        int aktuelleTaktAnzahl = Notenblatt.getAktuelleTaktAnzahl();
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
            bw.write(String.valueOf(aktuelleTaktAnzahl));
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
                        bw.write(String.valueOf(((Note) element).getVorzeichen()));
                        bw.newLine();
                    }
                    else if(element.getClass() == Pause.class){
                        bw.write(String.valueOf(((Pause) element).getPausenInTakt()));
                        bw.newLine();
                        bw.write("-10");    //-10 bei position von pause --> weil es keines dort gibt
                        bw.newLine();
                        bw.write("-10");    //-10 bei vozeichen von pause --> weil es keines dort gibt
                        bw.newLine();
                    }
                    i++;
                }
                bw.write("---");
                bw.newLine();
            }

            System.out.println("Written!");
            bw.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Einlesen(String filePath, ControllerMainWindow controller) {
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
/*
        Path path = Paths.get(filePath);
        long lines = 0;
        try {
            lines = Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        ArrayList<ReadElement> readElements = new ArrayList<>();

        Notenblatt.setDateiName(String.valueOf(data.get(0)));
        Notenblatt.setTonleiter(Integer.parseInt(data.get(1)));
        Notenblatt.setInstrument(String.valueOf(data.get(2)));
        Notenblatt.setKomponist(String.valueOf(data.get(3)));
        Notenblatt.setAktuelleTaktAnzahl(Integer.parseInt(data.get(4)));

        int x = 9;
        int y = 10;
        int z = 5;
        int u = 7;
        int v = 11;

        ArrayList<ArrayList<ReadElement>> arrayListElements = new ArrayList<>();

        ArrayList<Integer> welcheLinie = new ArrayList<>();

        for(int i = 0; i< data.size(); i++){
            if(data.get(i).contains("---")){
                welcheLinie.add(i);
                System.out.println("removing@index" + i);
                data.remove(i);
            }
        }

        while(v <= data.size()){
            int inTakt = Integer.parseInt(data.get(x));
            int position = Integer.parseInt(data.get(y));
            double imageViewX = Double.parseDouble(data.get(z));
            double imageViewY = Double.parseDouble(data.get(z+1));
            double vorzeichenViewX = Double.parseDouble(data.get(u));
            double vorzeichenViewY = Double.parseDouble(data.get(u+1));
            int vorzeichen = Integer.parseInt(data.get(v));

            ReadElement readElement = new ReadElement(inTakt, position, imageViewX, imageViewY, vorzeichenViewX, vorzeichenViewY, vorzeichen);
            readElements.add(readElement);
            System.out.println("AUSGABE IN EINLESEN: " + inTakt + "; " + position + "; " + imageViewX + "; " + imageViewY + "; " + vorzeichenViewX + "; " + vorzeichenViewY + "; " + vorzeichen);

            for (Integer i : welcheLinie){
                if(i == x){
                    System.out.println("In IF");
                    arrayListElements.add(readElements);
                    readElements.clear();
                }
            }

            x = x + 7;
            y = y + 7;
            z = z + 7;
            u = u + 7;
            v = v + 7;
            if(v> data.size()){break;}
        }
        System.out.println(readElements);

        ControllerMainWindow controllerMainWindow = controller;
        System.out.println("CONTROLLER IN EINLESEN: " + controllerMainWindow);
        System.out.println("arrayListElements: " + arrayListElements);
        controller.createFromRead(arrayListElements);
    }
}