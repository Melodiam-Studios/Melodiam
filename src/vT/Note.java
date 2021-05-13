package vT;

public class Note {

    private int wert;
    private int position;
    private int vorzeichen;         // -1 = b Vorzeichen, 0 = kein Vorzeichen, 1 = Kreuzvorzeichen
    private int anzeigenVorzeichen; // 0 = nicht anzeigen, 1 = Vorzeichen anzeigen, 2 = Auflösezeichen anzeigen
    private String bezeichnung;

    public void setAll(int wert, int position, int vorzeichen, int anzeigenVorzeichen, String bezeichnung) {
        this.wert = wert;
        this.position = position;
        this.vorzeichen = vorzeichen;
        this.anzeigenVorzeichen = anzeigenVorzeichen;
        this.bezeichnung = bezeichnung;
    }

    public int getAnzeigenVorzeichen() {
        return anzeigenVorzeichen;
    }

    public void setAnzeigenVorzeichen(int anzeigenVorzeichen) {
        this.anzeigenVorzeichen = anzeigenVorzeichen;
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

    @Override
    public String toString() {
        return "Note{" +
                "wert=" + wert +
                ", position=" + position +
                ", vorzeichen=" + vorzeichen +
                ", anzeigenVorzeichen=" + anzeigenVorzeichen +
                ", bezeichnung='" + bezeichnung + '\'' +
                '}';
    }
}