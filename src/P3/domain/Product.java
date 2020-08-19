package P3.domain;

import java.util.ArrayList;

public class Product {
    private int productnummer;
    private String productNaam;
    private String beschrijving;
    private double prijs;
    private ArrayList<Integer> kaarten;

    public Product(int productnummer, String productNaam, String beschrijving, double prijs, ArrayList<Integer> kaarten) {
        this.productnummer = productnummer;
        this.productNaam = productNaam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.kaarten = kaarten;
    }

    public int getProductnummer() { return productnummer; }

    public void setProductnummer(int productnummer) { this.productnummer = productnummer; }

    public String getProductNaam() { return productNaam; }

    public void setProductNaam(String productNaam) { this.productNaam = productNaam; }

    public String getBeschrijving() { return beschrijving; }

    public void setBeschrijving(String beschrijving) { this.beschrijving = beschrijving; }

    public double getPrijs() { return prijs; }

    public void setPrijs(double prijs) { this.prijs = prijs; }

    public ArrayList<Integer> getKaarten() { return kaarten; }

    public void setKaarten(ArrayList<Integer> kaarten) { this.kaarten = kaarten; }

    public String toString(){
        String s = "\n" + "Product " + productnummer + " = " + productNaam + " : " + beschrijving + " Dit kost: " + prijs + ". De kaarten die dit product hebben zijn: " + kaarten;
        return s;
    }
}
