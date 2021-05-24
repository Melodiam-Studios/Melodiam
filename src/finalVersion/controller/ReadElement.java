package finalVersion.controller;

public class ReadElement {

    protected int inTakt;

    protected int position;

    protected double imageViewX;

    protected double imageViewY;

    protected double vorzeichenViewX;

    protected double vorzeichenViewY;

    protected int vorzeichen;

    public ReadElement(int inTakt, int position, double imageViewX, double imageViewY, double vorzeichenViewX, double vorzeichenViewY, int vorzeichen) {
        //System.out.println("AUSGABE IN READELEMENT: " + inTakt + "; " + position + "; " + imageViewX + "; " + imageViewY + "; " + vorzeichenViewX + "; " + vorzeichenViewY + "; " + vorzeichen);
        this.inTakt = inTakt;
        this.position = position;
        this.imageViewX = imageViewX;
        this.imageViewY = imageViewY;
        this.vorzeichenViewX = vorzeichenViewX;
        this.vorzeichenViewY = vorzeichenViewY;
        this.vorzeichen = vorzeichen;
    }

    @Override
    public String toString() {
        return "ReadElement{" +
                "inTakt=" + inTakt +
                ", position=" + position +
                ", imageViewX=" + imageViewX +
                ", imageViewY=" + imageViewY +
                ", vorzeichenViewX=" + vorzeichenViewX +
                ", vorzeichenViewY=" + vorzeichenViewY +
                ", vorzeichen=" + vorzeichen +
                '}';
    }
}
