package com.company;

public class Auditor {
    String lp;
    String data_wpisania;
    String name;
    String adres;
    String telefon;
    String email;
    String zakres;
    String numer;
    String data_wpisu;
    String data_wykreslenia;

    public Auditor(String lp, String data_wpisania, String name, String adres, String telefon, String email, String zakres, String numer, String data_wpisu, String data_wykreslenia) {
        this.lp = lp;
        this.data_wpisania = data_wpisania;
        this.name = name;
        this.adres = adres;
        this.telefon = telefon;
        this.email = email;
        this.zakres = zakres;
        this.numer = numer;
        this.data_wpisu = data_wpisu;
        this.data_wykreslenia = data_wykreslenia;
    }

    public Auditor() {

    }


    @Override
    public String toString() {
        return "Auditor{" +
                "lp='" + lp + '\'' +
                ", data_wpisania='" + data_wpisania + '\'' +
                ", name='" + name + '\'' +
                ", adres='" + adres + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                ", zakres='" + zakres + '\'' +
                ", numer='" + numer + '\'' +
                ", data_wpisu='" + data_wpisu + '\'' +
                ", data_wykreslenia='" + data_wykreslenia + '\'' +
                '}';
    }

    public String numerytoString() {
        return "Auditor{" +
                "numer='" + numer + '\'' +
                '}';
    }
}
