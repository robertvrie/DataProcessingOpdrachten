package P3.implementation;

import P3.dao.OracleBaseDao;
import P3.dao.OvChipKaartDao;
import P3.dao.OvChipkaartProductDao;
import P3.dao.ProductDao;
import P3.domain.OvChipkaart;
import P3.domain.Product;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class OvChipkaartProductOracleDaoImpl extends OracleBaseDao implements OvChipkaartProductDao{
    ProductDao productDao = new ProductOracleDaoImpl();
    OvChipKaartDao ovChipkaartDao = new OvChipkaartOracleDaoImpl();

    //kaartProductNummer is er alleen zodat er constant een nieuwe relatie kan worden gemaakt.
    public boolean assignKaartProduct(Product product, OvChipkaart ovChipkaart, int kaartProductNummer) throws SQLException {
        product.getKaarten().add(ovChipkaart.getKaartnummer());
        ovChipkaart.getProducten().add(product.getProductnummer());
        String saveKaartProductQuery = "insert into ov_chipkaart_product values(?,?,?,?,?)";
        PreparedStatement saveKaartProduct = getConnection().prepareStatement(saveKaartProductQuery);
        saveKaartProduct.setInt(1, kaartProductNummer);
        saveKaartProduct.setInt(2, ovChipkaart.getKaartnummer());
        saveKaartProduct.setInt(3, product.getProductnummer());
        saveKaartProduct.setString(4, "Actief");
        saveKaartProduct.setDate(5, Date.valueOf("2020-08-10"));
        return !saveKaartProduct.execute();
    }

    public Product removeKaartProduct(Product product, OvChipkaart ovChipkaart) throws SQLException {
        ArrayList<Integer> kaartNummers = product.getKaarten();
        ArrayList<Integer> productNummers = ovChipkaart.getProducten();
        for(Iterator<Integer> it = kaartNummers.iterator(); it.hasNext();){
            int kaartNummer = it.next();
            if(ovChipkaart.getKaartnummer() == kaartNummer){
                it.remove();
            }
        }
        for(Iterator<Integer> it = productNummers.iterator(); it.hasNext();){
            int productNummer = it.next();
            if(product.getProductnummer() == productNummer){
                it.remove();
            }
        }
        String deleteKaartProductQuery = "delete from ov_chipkaart_product where kaartnummer = ? AND productnummer = ?";
        PreparedStatement deleteKaartProduct = getConnection().prepareStatement(deleteKaartProductQuery);
        deleteKaartProduct.setInt(1, ovChipkaart.getKaartnummer());
        deleteKaartProduct.setInt(2, product.getProductnummer());
        deleteKaartProduct.execute();
        return product;
    }
}
