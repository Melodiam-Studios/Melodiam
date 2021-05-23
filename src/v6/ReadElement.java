package v6;

public class ReadElement {

    protected int inTakt;

    protected int position;

    protected double imageViewX;

    protected double imageViewY;

    protected int tonLeiter;

    public ReadElement(int inTakt, int position, double imageViewX, double imageViewY, int tonLeiter) {
        this.inTakt = inTakt;
        this.position = position;
        this.imageViewX = imageViewX;
        this.imageViewY = imageViewY;
        this.tonLeiter = tonLeiter;
    }
}
