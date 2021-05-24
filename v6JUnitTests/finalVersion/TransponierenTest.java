package finalVersion;

import com.sun.javafx.geom.Point2D;
import org.junit.jupiter.api.Test;
import finalVersion.controller.Note;
import finalVersion.model.Notenblatt;
import finalVersion.model.Transponieren;

import static org.junit.jupiter.api.Assertions.*;

//Funktioniert jetzt nicht mehr weil es beim erstellen der Note auch gleichzeitig eine View ertstellt und somit auf.
class TransponierenTest {

    int tonleiter;

    @Test
    void erneuereAnzeigenTest() {
        //alle möglichkeiten durch gehen

        Note note = new Note(4,1, true, 0, new Point2D());
        note.setAll(39, 1, -1, 0, "es");
        Notenblatt.setTonleiter(2);

        Transponieren.erneuereAnzeigen(note);
        assertEquals(note.getAnzeigenVorzeichen(), 1);

    }
}