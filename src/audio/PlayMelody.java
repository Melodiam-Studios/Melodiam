package audio;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;

import javax.sound.midi.Sequence;
import java.io.IOException;

public class PlayMelody implements Runnable{
    Player player=new Player();
    Pattern melody=new Pattern();

    public PlayMelody(Pattern melody){
        this.melody=melody;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                if(player.getManagedPlayer().isPaused()){
                    player.getManagedPlayer().resume();
                    throw new Exception();
                }

                System.out.println("Melody has started playing!");
                player.play(melody);

                //System.out.println(player.getManagedPlayer().isFinished());

                throw new Exception(); //stops when Melody is finished

            } catch (Exception e) {
                System.out.println("Audio Thread is stopping and the Player is finishing");
                player.getManagedPlayer().finish();
                Thread.currentThread().interrupt();
            }
        }
    }
    public void pauseMelody(){
        System.out.println("Melody paused!");
        player.getManagedPlayer().pause();

    }
    public void resumeMelody(){
        System.out.println("Melody resumed!");
        player.getManagedPlayer().resume();
    }
}
