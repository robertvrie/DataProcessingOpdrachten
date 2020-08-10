package P2.domain;

import java.sql.Date;

public class OvChipkaart {
    private int kaartnummer;
    private Date geldigTot;
    private int klasse;
    private double saldo;
    private int reizigerId;

    public OvChipkaart(int kaartnummer, Date geldigTot, int klasse, double saldo, int reizigerId) {
        this.kaartnummer = kaartnummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reizigerId = reizigerId;
    }

    public int getKaartnummer() {
        return kaartnummer;
    }

    public void setKaartnummer(int kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getReizigerId() { return reizigerId; }

    public void setReizigerId(int reizigerId) { this.reizigerId = reizigerId; }

    public String toString(){
        String s = "Chipkaart " + kaartnummer + " is geldig tot " + geldigTot + " met klasse " + klasse + ", een saldo van " + saldo + " en de reizigiger van deze kaart is nr: " + reizigerId + ", ";
        return s;
    }
}