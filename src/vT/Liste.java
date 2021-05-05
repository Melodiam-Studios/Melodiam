package vT;

import java.util.Scanner;

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
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
    };
    public static void main(String[] args) {

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
