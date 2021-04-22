package audio;

import org.jfugue.player.Player;

public class Main {
    public static void main(String[] args) {
        Player player=new Player();

        //https://objectcomputing.com/resources/publications/sett/january-2008-writing-music-in-java-two-approaches
        player.play(
                // "Itsy, bitsy spider, climbed up the water spout."
                "F5w F5i F5q G5i A5q. A5q A5i G5q F5i G5q A5i F5q. Rq. " +
                        // "Down came the rain and washed the spider out."
                        "A5q. A5q Bb5i C6q. C6q. Bb5q A5i Bb5q C6i A5q. Rq. " +
                        // "Out came the sun and dried up all the rain, so the"
                        "F5q. F5q G5i A5q. A5q. G5q F5i G5q A5i F5q. C5q C5i " +
                        // "itsy, bitsy spider went up the spout again."
                        "F5q F5i F5q G5i A5q. A5q A5i G5q F5i G5q A5i F5q. Rq.");
    }
}
