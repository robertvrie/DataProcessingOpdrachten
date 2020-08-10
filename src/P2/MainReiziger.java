package P2;

import P2.dao.ReizigerDao;
import P2.domain.Reiziger;
import P2.implementation.ReizigerOracleDaoImpl;

import java.sql.Date;
import java.sql.SQLException;

public class MainReiziger {
    public static void main (String[] args ) throws SQLException {

        ReizigerDao reizigerDao = new ReizigerOracleDaoImpl();
        System.out.println(reizigerDao.findAll().get(0));
        Reiziger r1 = new Reiziger(6,"RJ",null,"Vrieling", Date.valueOf("1997-07-30"));
        Reiziger r2 = new Reiziger(7,"Andrej",null,"Roskic", Date.valueOf("1996-07-30"));
        System.out.println(reizigerDao.save(r1));
        System.out.println(reizigerDao.update(r1,r2));
        System.out.println(reizigerDao.delete(r2));
        System.out.println(reizigerDao.findByGBdatum("1998-08-11"));
        System.out.println(reizigerDao.findReiziger(2));
    }
}
