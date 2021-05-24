package v6.controller;

public class ReadElement {

    protected int inTakt;

    protected int position;

    protected double imageViewX;

    protected double imageViewY;

    protected double vorzeichenViewX;

    protected double vorzeichenViewY;

    protected int tonLeiter;

    protected int vorzeichen;

    public ReadElement(int inTakt, int position, double imageViewX, double imageViewY, double vorzeichenViewX, double vorzeichenViewY, int tonLeiter, int vorzeichen) {
        this.inTakt = inTakt;
        this.position = position;
        this.imageViewX = imageViewX;
        this.imageViewY = imageViewY;
        this.vorzeichenViewX = vorzeichenViewX;
        this.vorzeichenViewY = vorzeichenViewY;
        this.tonLeiter = tonLeiter;
        this.vorzeichen = vorzeichen;
    }
}
