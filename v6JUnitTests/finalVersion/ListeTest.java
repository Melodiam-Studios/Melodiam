package finalVersion;

import org.junit.jupiter.api.Test;
import finalVersion.model.Liste;

import static org.junit.jupiter.api.Assertions.*;

class ListeTest {

    Liste l = new Liste();
    String testArr[][] = l.arr;

    @Test
    void werteAusfuellenTest() {
        String arrTest [][] = l.arr;

        int count = 0;
        int posizion = 2;
        int vorzeichen = -1;
        //goes through all posibilitys
        for(int possibilities = 0; possibilities <= 68; possibilities++){
            if(possibilities%3 == 0 && possibilities != 0){
                posizion++;
            }

            //richtige stelle im array finden
            for (int i = 0; i < arrTest.length; i++) {
                if ((new Integer(arrTest[i][1]) == posizion) && (new Integer(arrTest[i][2]) == vorzeichen)) {
                    count++;
                    break;
                }
            }
            if (vorzeichen >= 1){
                vorzeichen = -1;
            }
            else vorzeichen++;
        }
        assertEquals(69, count, "One or more possibilities are not working");
    }
}