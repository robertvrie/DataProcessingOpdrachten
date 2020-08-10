package P2;

import P2.dao.OvChipKaartDao;
import P2.dao.ReizigerDao;
import P2.domain.OvChipkaart;
import P2.domain.Reiziger;
import P2.implementation.OvChipkaartOracleDaoImpl;
import P2.implementation.ReizigerOracleDaoImpl;

import java.sql.Date;
import java.sql.SQLException;

public class MainRelation {
    public static void main (String[] args ) throws SQLException {
        ReizigerDao reizigerDao = new ReizigerOracleDaoImpl();
        OvChipKaartDao ovChipKaartDao = new OvChipkaartOracleDaoImpl();

        //Maakt een nieuwe reiziger door een reizger uit de database te halen.
        Reiziger r1 = reizigerDao.findReiziger(3);

        //Nieuwe chipkaart
        OvChipkaart o1 = new OvChipkaart(64820, Date.valueOf("2021-07-30"), 1, 0, r1.getReizigerId());
        OvChipkaart o2 = ovChipKaartDao.findByKaartNummer(57401);

        //Voegt de chipkaart toe aan de reiziger
        r1.setKaarten(o1);

        System.out.println(o1);
        System.out.println(r1);

        //Vindt de reiziger met het id in een kaart
        reizigerDao.findReiziger(o1.getReizigerId());

        //Haalt alle chipkaarten op
        System.out.println(ovChipKaartDao.findAll());
    }
}