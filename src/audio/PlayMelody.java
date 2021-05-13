package audio;

import org.jfugue.player.Player;
import v5.Note;

import java.util.ArrayList;

public class PlayMelody implements Runnable{
    Integer takte;
    int tempo;
    String instrument;
    public PlayMelody(int takte, int tempo, String instrument){
        this.takte=takte;
        this.tempo=tempo;
        this.instrument=instrument;
    }


    @Override
    public void run() {
        Player player = new Player();
        player.play(takte.toString()+"w");

    }
}
