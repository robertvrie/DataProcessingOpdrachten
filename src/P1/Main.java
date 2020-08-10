package P1;

import java.sql.Date;

public class Main {
    public static void main (String[] args ) {
        ReizigerDao reizigerDao = new ReizigerOracleDaoImpl();

        //Alle Reizigers aanmaken en opslaan
        Reiziger R1 = new Reiziger("Janpeterszoon", Date.valueOf("1999-10-10"));
        reizigerDao.save(R1);
        Reiziger R2 = new Reiziger("Henk", Date.valueOf("1998-10-10"));
        reizigerDao.save(R2);
        Reiziger R3 = new Reiziger("Klaas", Date.valueOf("1997-10-10"));
        reizigerDao.save(R3);
        Reiziger R4 = new Reiziger("Willem", Date.valueOf("1996-10-10"));
        reizigerDao.save(R4);
        Reiziger R5 = new Reiziger("Maurits", Date.valueOf("1996-10-10"));
        reizigerDao.save(R5);

        //Alle reizigers ophalen met naam, geboortedatum en object code
        for(int i=0; i < reizigerDao.findAll().size(); i++) {
            System.out.println(reizigerDao.findAll().get(i).getNaam());
            System.out.println(reizigerDao.findAll().get(i).getGbdatum());
            System.out.println(reizigerDao.findAll().get(i));
            System.out.println("------------");
        }

        //Eerste regel vind gebruiker met unieke geboortedatum, 2de regel vind 2 gebruikers met dezelfde geboortedatum
        System.out.println("----Zoeken op datum----");
        System.out.println(reizigerDao.findByGBdatum("1999-10-10"));
        System.out.println(reizigerDao.findByGBdatum("1996-10-10"));

        System.out.println("----Reiziger vervangen----");
        Reiziger R6 = new Reiziger("Pieter", Date.valueOf("2000-10-10"));
        System.out.println("R3 wordt nu vervangen voor R6");
        System.out.println("R3 = " + R3);
        System.out.println("R6 = " + R6);
        System.out.println(reizigerDao.update(R3,R6));
        System.out.println(reizigerDao.findAll());

        System.out.println("----Reiziger verwijderen----");
        System.out.println(reizigerDao.delete(R6));
        System.out.println(reizigerDao.findAll());
    }
}