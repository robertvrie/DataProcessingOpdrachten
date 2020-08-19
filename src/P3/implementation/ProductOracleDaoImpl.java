package P3.implementation;

import P3.dao.OracleBaseDao;
import P3.dao.ProductDao;
import P3.domain.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductOracleDaoImpl extends OracleBaseDao implements ProductDao {

    public ArrayList<Product> findAll() throws SQLException {
        ArrayList<Product> producten = new ArrayList<>();
        String findAllProductsQuery = "select productnummer from product";
        PreparedStatement findAllProducts = getConnection().prepareStatement(findAllProductsQuery);
        ResultSet rs = findAllProducts.executeQuery();
        while(rs.next()){
            producten.add(findProduct(rs.getInt(1)));
        }
        return producten;
    }

    public Product findProduct(int productnummer) throws SQLException {
        String findProductsQuery = "select * from product where productnummer = ?";
        String findKaartnummersQuery = "select kaartnummer from ov_chipkaart_product where productnummer = ?";
        ArrayList<Integer> kaartNummers = new ArrayList<>();

        PreparedStatement findProduct = getConnection().prepareStatement(findProductsQuery);
        findProduct.setInt(1, productnummer);
        ResultSet rs = findProduct.executeQuery();
        rs.next();
        PreparedStatement findAllKaartnummers = getConnection().prepareStatement(findKaartnummersQuery);
        findAllKaartnummers.setInt(1, rs.getInt(1));
        ResultSet rsKaarten = findAllKaartnummers.executeQuery();
        while(rsKaarten.next()){
            kaartNummers.add(rsKaarten.getInt(1));
        }
        Product product = new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4), kaartNummers);
        return product;
    }

    public Product save(Product product) throws SQLException{
        String insertProductQuery = "insert into product values(?,?,?,?)";
        PreparedStatement insertProduct = getConnection().prepareStatement(insertProductQuery);
        insertProduct.setInt(1, product.getProductnummer());
        insertProduct.setString(2, product.getProductNaam());
        insertProduct.setString(3, product.getBeschrijving());
        insertProduct.setDouble(4, product.getPrijs());
        insertProduct.execute();
        return product;
    }

    public boolean delete(Product product) throws SQLException {
        String deleteKaartProductQuery = "delete from ov_chipkaart_product where productnummer = ?";
        PreparedStatement deleteKaartProduct = getConnection().prepareStatement(deleteKaartProductQuery);
        deleteKaartProduct.setInt(1, product.getProductnummer());

        String deleteProductQuery = "delete from product where productnummer = ?";
        PreparedStatement deleteProduct = getConnection().prepareStatement(deleteProductQuery);
        deleteProduct.setInt(1, product.getProductnummer());

        return !deleteKaartProduct.execute() && !deleteProduct.execute();
    }

    //De update statement werkt alleen waardes bij voor het product, dit heeft niks met de relatie te maken,
    //Het productnummer veranderd ook niet bij het updaten van de database,
    //Aan het einde wordt het kaartnummer van de nieuwe kaart object geupdated naar het kaartnummer van het oude kaart object
    public Product update(Product oudeProduct, Product nieuweProduct) throws SQLException{
        String updateProductQuery = "update product set productnaam = ?, beschrijving = ?, prijs = ? where productnummer = ?";
        PreparedStatement updateProduct = getConnection().prepareStatement(updateProductQuery);
        updateProduct.setString(1, nieuweProduct.getProductNaam());
        updateProduct.setString(2, nieuweProduct.getBeschrijving());
        updateProduct.setDouble(3, nieuweProduct.getPrijs());
        updateProduct.setInt(4, oudeProduct.getProductnummer());
        updateProduct.executeQuery();
        nieuweProduct.setProductnummer(oudeProduct.getProductnummer());
        return nieuweProduct;
    }
}