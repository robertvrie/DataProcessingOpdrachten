package P3.dao;

import P3.domain.OvChipkaart;
import P3.domain.Product;

import java.sql.SQLException;

public interface OvChipkaartProductDao {
    boolean assignKaartProduct(Product product, OvChipkaart ovChipkaart, int kaartProductNummer) throws SQLException;
    Product removeKaartProduct(Product product, OvChipkaart ovChipkaart) throws SQLException;
}
