package lab.jee_laboratorium_4.beans;

public class Pracownik {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public float getPensja() {
        return pensja;
    }

    public void setPensja(float pensja) {
        this.pensja = pensja;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    private int id;
    private String nazwisko;
    private float pensja;
    private String firma;
}
