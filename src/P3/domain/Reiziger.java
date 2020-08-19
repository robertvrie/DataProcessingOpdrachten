package P3.domain;

import java.sql.Date;
import java.util.ArrayList;

public class Reiziger {
    private int reizigerId;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date gbdatum;
    private ArrayList<OvChipkaart> kaarten = new ArrayList<>();

    public Reiziger(int reizigerId, String voorletters, String tussenvoegsel, String achternaam, Date gbdatum) {
        this.reizigerId = reizigerId;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.gbdatum = gbdatum;
    }

    public int getReizigerId() {
        return reizigerId;
    }

    public void setReizigerId(int reizigerId) {
        this.reizigerId = reizigerId;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGbdatum() {
        return gbdatum;
    }

    public void setGbdatum(Date gbdatum) {
        this.gbdatum = gbdatum;
    }

    public ArrayList<OvChipkaart> getChipkaart(){ return kaarten; }

    public void setKaarten(OvChipkaart newChipkaart){ kaarten.add(newChipkaart); }


    //De if statements zijn bedoeld voor als een reiziger geen, 1 of meerdere chipkaarten heeft
    public String toString(){
        if(tussenvoegsel == null){
            tussenvoegsel = "";
        }
        else {
            tussenvoegsel = tussenvoegsel + " ";
        }
        String s = voorletters + " " + tussenvoegsel + achternaam + " heeft reizigersId van: " + reizigerId + " en is geboren op " + gbdatum + ".";
        if(kaarten.size() == 0){
            s = s + " Deze reiziger heeft nog geen chipkaart!";
        }
        else if(kaarten.size() == 1){
            s = s + " Heeft 1 chipkaart in bezit: " + kaarten.toString();
        }
        else{
            s = s + " Heeft meerdere chipkaarten in bezit: ";
            for(OvChipkaart o : kaarten){
                s = s + o.toString();
            }
        }
        return s;
    }
}
