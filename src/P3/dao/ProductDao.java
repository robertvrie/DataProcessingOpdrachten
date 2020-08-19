package P3.dao;

import P3.domain.OvChipkaart;
import P3.domain.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDao {
    ArrayList<Product> findAll() throws SQLException;
    Product findProduct(int productnummer) throws SQLException;
    Product save(Product product) throws SQLException;
    boolean delete(Product product) throws SQLException;
    Product update(Product oudeProduct, Product nieuweProduct) throws SQLException;
}
