package P3.implementation;

import P3.dao.OracleBaseDao;
import P3.dao.OvChipKaartDao;
import P3.domain.OvChipkaart;
import P3.domain.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OvChipkaartOracleDaoImpl extends OracleBaseDao implements OvChipKaartDao {

    //Haalt alle ov kaarten op en stopt deze in een lijst, aangezien er alleen een reiziger id in een ov kaart zit, hoeft er geen informatie uit de reiziger tabel te worden gehaald,
    //maar kan de id worden gebruikt om een reiziger op te halen als dat nodig is.
    public ArrayList<OvChipkaart> findAll() throws SQLException {
        ArrayList<OvChipkaart> ovChipkaarten = new ArrayList<>();
        String findAllQuery = "select kaartnummer from ov_chipkaart";
        PreparedStatement findAllKaarten = getConnection().prepareStatement(findAllQuery);
        ResultSet rs = findAllKaarten.executeQuery();
        while(rs.next()){
            ovChipkaarten.add(findByKaartNummer(rs.getInt(1)));
        }
        return ovChipkaarten;
    }

    //TODO alle acties laten gebeuren voordat we het object aanmaken??

    //Vind een kaart met het kaartnummer
    public OvChipkaart findByKaartNummer(int kaartNummer) throws SQLException {
        String findKaartQeury = "select * from ov_chipkaart where kaartnummer = ?";
        String findProductQuery = "select productnummer from ov_chipkaart_product where kaartnummer = ?";
        ArrayList<Integer> productnummers = new ArrayList<>();

        PreparedStatement findKaart = getConnection().prepareStatement(findKaartQeury);
        findKaart.setInt(1,kaartNummer);
        ResultSet rs = findKaart.executeQuery();

        rs.next();

        OvChipkaart ovChipkaart = new OvChipkaart(rs.getInt(1),rs.getDate(2),rs.getInt(3),rs.getInt(4), rs.getInt(5), productnummers);

        PreparedStatement findAllProductNummers = getConnection().prepareStatement(findProductQuery);
        findAllProductNummers.setInt(1, rs.getInt(1));
        ResultSet rsProducten = findAllProductNummers.executeQuery();
        while(rsProducten.next()){
            productnummers.add(rsProducten.getInt(1));
        }
        ovChipkaart.setProducten(productnummers);
        return ovChipkaart;
    }

    //Vind een kaart met het reizigerid, wordt altijd returned als een arraylist in het geval dat er meerdere resultaten zijn
    public ArrayList<OvChipkaart> findByReizigerId(int reizigersId) throws SQLException {
        ArrayList<OvChipkaart> ovChipkaarten = new ArrayList<>();
        String readQuery = "select kaartnummer from ov_chipkaart where reizigerid = ?";
        PreparedStatement findKaarten = getConnection().prepareStatement(readQuery);
        findKaarten.setInt(1,reizigersId);
        ResultSet rs = findKaarten.executeQuery();
        while(rs.next()){
            ovChipkaarten.add(findByKaartNummer(rs.getInt(1)));
        }
        return ovChipkaarten;
    }

    //Slaat een kaart op
    public OvChipkaart save(OvChipkaart kaart) throws SQLException {
        String insertQuery = "insert into ov_chipkaart values(?,?,?,?,?)";
        PreparedStatement saveOvChipkaart = getConnection().prepareStatement(insertQuery);
        saveOvChipkaart.setInt(1, kaart.getKaartnummer());
        saveOvChipkaart.setDate(2,kaart.getGeldigTot());
        saveOvChipkaart.setInt(3,kaart.getKlasse());
        saveOvChipkaart.setDouble(4,kaart.getSaldo());
        saveOvChipkaart.setInt(5, kaart.getReizigerId());
        saveOvChipkaart.execute();
        return kaart;
    }

    //Vervangt alle waarden van een kaart, afgezien van het kaartnummer. Het kaartnummer van het nieuwe object wordt wel aangepast naar het kaartnummer van het oude object.
    public OvChipkaart update(OvChipkaart oudeKaart, OvChipkaart nieuweKaart) throws SQLException{
        String updateQuery = "update ov_chipkaart set geldigtot = ?, klasse = ?, saldo = ?, reizigerid = ? where kaartnummer = ?";
        PreparedStatement updateKaart = getConnection().prepareStatement(updateQuery);
        updateKaart.setDate(1, nieuweKaart.getGeldigTot());
        updateKaart.setInt(2, nieuweKaart.getKlasse());
        updateKaart.setDouble(3, nieuweKaart.getSaldo());
        updateKaart.setInt(4, nieuweKaart.getReizigerId());
        updateKaart.setInt(5, oudeKaart.getKaartnummer());
        updateKaart.executeQuery();
        nieuweKaart.setKaartnummer(oudeKaart.getKaartnummer());
        return nieuweKaart;
    }

    //Verwijderd een kaart
    public boolean delete(OvChipkaart kaart) throws SQLException{
        String deleteKaartQuery = "delete from ov_chipkaart where kaartnummer = ?";
        PreparedStatement deleteKaart = getConnection().prepareStatement(deleteKaartQuery);
        deleteKaart.setInt(1, kaart.getKaartnummer());

        String deleteKaartProductQuery = "delete from ov_chipkaart_product where kaartnummer = ?";
        PreparedStatement deleteKaartProduct = getConnection().prepareStatement(deleteKaartProductQuery);
        deleteKaartProduct.setInt(1, kaart.getKaartnummer());

        return !deleteKaart.execute() && !deleteKaartProduct.execute();
    }
}
