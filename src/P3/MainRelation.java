package P3;

import P3.dao.OvChipKaartDao;
import P3.dao.OvChipkaartProductDao;
import P3.dao.ProductDao;
import P3.domain.OvChipkaart;
import P3.domain.Product;
import P3.implementation.OvChipkaartOracleDaoImpl;
import P3.implementation.OvChipkaartProductOracleDaoImpl;
import P3.implementation.ProductOracleDaoImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainRelation {
    public static void main (String[] args ) throws SQLException {
        OvChipKaartDao ovChipKaartDao = new OvChipkaartOracleDaoImpl();
        ProductDao productDao = new ProductOracleDaoImpl();
        OvChipkaartProductDao ovChipkaartProductDao = new OvChipkaartProductOracleDaoImpl();

        Product p3 = new Product(20, "Testing", "TestProduct", 20, new ArrayList<>());
        OvChipkaart o2 = new OvChipkaart(69, Date.valueOf("2025-11-11"), 1, 100, 1, new ArrayList<Integer>());

        productDao.save(p3);
        ovChipKaartDao.save(o2);

        //assignKaart voegt eerst het product- en kaartnummer toe aan het respectievelijke object, daarna wordt de methode saveRelation aangeroepen,
        //deze slaat beide nummers op in de ov_chipkaart_product tabel.

        ovChipkaartProductDao.assignKaartProduct(p3,o2, 10);
        System.out.println(productDao.findProduct(p3.getProductnummer()));
        System.out.println(ovChipKaartDao.findByKaartNummer(o2.getKaartnummer()));

        //removeKaart doet het tegenovergestelde van assignKaart, het verwijderd beide nummers van beide objecten en verwijderd daarna de relatie uit de database.
        ovChipkaartProductDao.removeKaartProduct(p3,o2);
        System.out.println(productDao.findProduct(p3.getProductnummer()));
        System.out.println(ovChipKaartDao.findByKaartNummer(o2.getKaartnummer()));

        productDao.delete(p3);
        ovChipKaartDao.delete(o2);
    }
}