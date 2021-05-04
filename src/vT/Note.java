package vT;

public class Note {

    private int wert;
    private int position;
    private int vorzeichen;
    private String bezeichnung;

    public void setAll(int wert, int position, int vorzeichen, String bezeichnung) {
        this.wert = wert;
        this.position = position;
        this.vorzeichen = vorzeichen;
        this.bezeichnung = bezeichnung;
    }

    public int getWert() {
        return wert;
    }

    public void setWert(int wert) {
        this.wert = wert;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getVorzeichen() {
        return vorzeichen;
    }

    public void setVorzeichen(int vorzeichen) {
        this.vorzeichen = vorzeichen;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}