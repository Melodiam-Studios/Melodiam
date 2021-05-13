package audio;

import org.jfugue.player.Player;
import v5.Note;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Runnable r1=new PlayMelody(72,60,"Piano");

        Thread t=new Thread(r1);
        t.start();

    }

}
