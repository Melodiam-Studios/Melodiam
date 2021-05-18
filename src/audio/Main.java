package audio;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Note;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Pattern melody=new Pattern();
        melody.setInstrument("Piano");
        melody.setTempo(60);
        melody.add("70w 71w 62g");
        melody.add("Rw");
        melody.add("70w");


        PlayMelody playMelody=new PlayMelody(melody);
        Thread t=new Thread(playMelody,"AudioPlayback");
        t.start();


        //Thread.sleep(1000);
        //playMelody.pauseMelody();
        //Thread.sleep(2000);
        //playMelody.resumeMelody();
        //t.interrupt();


    }

}
