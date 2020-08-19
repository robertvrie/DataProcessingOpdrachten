package P3;

import P3.dao.OvChipKaartDao;
import P3.domain.OvChipkaart;
import P3.implementation.OvChipkaartOracleDaoImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainOvChipkaart {
    public static void main (String[] args ) throws SQLException {
        OvChipKaartDao ovChipKaartDao = new OvChipkaartOracleDaoImpl();

        ArrayList<Integer> productNummers = new ArrayList<>();
        OvChipkaart o1 = new OvChipkaart(88888, Date.valueOf("2020-10-10"), 2, 30, 3, productNummers);
        OvChipkaart o2 = new OvChipkaart(99999, Date.valueOf("2020-11-11"), 1, 50, 2, productNummers);

        //Haalt een chipkaart uit de database op het kaartnummer
        OvChipkaart o3 = ovChipKaartDao.findByKaartNummer(35283);
        System.out.println(o3);

        //Haalt alle chipkaarten uit de databse met hetzelfde reizigerid
        ArrayList<OvChipkaart> o4 = ovChipKaartDao.findByReizigerId(5);
        System.out.println(o4);

        //Slaat een kaart op in de database
        //System.out.println(ovChipKaartDao.save(o1));

        //Vervangt alle waarden afgezien het kaartnummer in de database van een kaart.
        //System.out.println(ovChipKaartDao.update(o1,o2));

        //Verwijderd de net geupdated kaart
        //System.out.println(ovChipKaartDao.delete(o2));

        //Laad alle kaarten in
        System.out.println(ovChipKaartDao.findAll());
    }
}