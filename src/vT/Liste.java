package vT;

public class Liste {

    public static String arr [][] = {
        {"39", "2", "-1", "es"},
        {"40", "2", "0", "e"},
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
        {"48", "7", "0", "c1"},
        {"49", "7", "1", "cis1"},
        {"49", "8", "-1", "des1"},
        {"50", "8", "0", "d1"},
        {"51", "8", "1", "dis1"},
        {"51", "9", "-1", "es1"},
        {"52", "9", "0", "e1"},
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
        {"60", "14", "0", "c2"},
        {"61", "14", "1", "cis2"},
        {"61", "15", "-1", "des2"},
        {"62", "15", "0", "d2"},
        {"63", "15", "1", "dis2"},
        {"63", "16", "-1", "es2"},
        {"64", "16", "0", "e2"},
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
        {"72", "21", "0", "c3"},
        {"73", "21", "1", "cis3"},
        {"73", "22", "-1", "des3"},
        {"74", "22", "0", "d3"},
        {"75", "22", "1", "dis3"},
        {"75", "23", "-1", "es3"},
        {"76", "23", "0", "e3"},
        {"77", "24", "0", "f3"},
        {"78", "24", "1", "fis3"},
    };

    public void werteAusfuellen() {
    /*
        Scanner myObj = new Scanner(System.in);

        String eingabePosition;
        String eingabeVorzeichen;

        // Enter username and press Enter
        System.out.println("Enter Position:");
        eingabePosition = myObj.nextLine();
        System.out.println("Enter Vorzeichen:");
        eingabeVorzeichen = myObj.nextLine();

        System.out.println("Position is: " + eingabePosition);
        System.out.println("Vorzeichen is: " + eingabeVorzeichen);
    */
        String eingabePosition = "5";
        String eingabeVorzeichen = "1";

        for (int i = 0; i < arr.length; i++){
            System.out.println("POSITION: " + eingabePosition + " VORZEICHEN: " +eingabeVorzeichen);
            if((arr[i][1] == eingabePosition) && (arr[i][2] == eingabeVorzeichen)){
                System.out.println(arr[i][0] + " " + arr[i][3]);
                break;
            }
        }

        //System.out.println();
    }
}
