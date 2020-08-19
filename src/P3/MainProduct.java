package P3;

import P3.dao.OvChipKaartDao;
import P3.dao.ProductDao;
import P3.domain.OvChipkaart;
import P3.domain.Product;
import P3.implementation.OvChipkaartOracleDaoImpl;
import P3.implementation.ProductOracleDaoImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


public class MainProduct {
    public static void main (String[] args ) throws SQLException {
        ProductDao productDao = new ProductOracleDaoImpl();
        OvChipKaartDao ovChipKaartDao = new OvChipkaartOracleDaoImpl();

        Product p1 = productDao.findProduct(1);
        OvChipkaart o1 = ovChipKaartDao.findByKaartNummer(46392);

        //Maakt een nieuwe arraylist en zet deze in een nieuw testproduct
        Product p2 = new Product(10, "Test", "Test", 10.0, new ArrayList<>());

        System.out.println(p1);
        System.out.println(productDao.save(p2));
        System.out.println(productDao.delete(p2));

        System.out.println(productDao.update(p2, p1));
        System.out.println(productDao.findAll());

    }
}