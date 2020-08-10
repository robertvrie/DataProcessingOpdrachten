package P2.implementation;

import P2.dao.OracleBaseDao;
import P2.dao.OvChipKaartDao;
import P2.dao.ReizigerDao;
import P2.domain.OvChipkaart;

import java.sql.*;
import java.util.ArrayList;

public class OvChipkaartOracleDaoImpl extends OracleBaseDao implements OvChipKaartDao {

    //Haalt alle ov kaarten op en stopt deze in een lijst, aangezien er alleen een reiziger id in een ov kaart zit, hoeft er geen informatie uit de reiziger tabel te worden gehaald,
    //maar kan de id worden gebruikt om een reiziger op te halen als dat nodig is.
    public ArrayList<OvChipkaart> findAll() throws SQLException {
        ArrayList<OvChipkaart> ovChipkaarten = new ArrayList<>();
        String findAllQuery = "select * from ov_chipkaart";
        PreparedStatement findAll = getConnection().prepareStatement(findAllQuery);
        ResultSet rs = findAll.executeQuery();
        while(rs.next()){
            OvChipkaart ovChipkaart = new OvChipkaart(0,null,0,0, 0);
            ovChipkaart.setKaartnummer(rs.getInt(1));
            ovChipkaart.setGeldigTot(rs.getDate(2));
            ovChipkaart.setKlasse(rs.getInt(3));
            ovChipkaart.setSaldo(rs.getInt(4));
            ovChipkaart.setReizigerId(rs.getInt(5));
            ovChipkaarten.add(ovChipkaart);
        }
        return ovChipkaarten;
    }

    //Vind een kaart met het kaartnummer
    public OvChipkaart findByKaartNummer(int kaartNummer) throws SQLException {
        ArrayList<OvChipkaart> ovChipkaarten = new ArrayList<>();
        String readQuery = "select * from ov_chipkaart where kaartnummer = ?";
        PreparedStatement findKaart = getConnection().prepareStatement(readQuery);
        findKaart.setInt(1,kaartNummer);
        ResultSet rs = findKaart.executeQuery();
        rs.next();
        OvChipkaart ovChipkaart = new OvChipkaart(0,null,0,0, 0);
        ovChipkaart.setKaartnummer(rs.getInt(1));
        ovChipkaart.setGeldigTot(rs.getDate(2));
        ovChipkaart.setKlasse(rs.getInt(3));
        ovChipkaart.setSaldo(rs.getInt(4));
        ovChipkaart.setReizigerId(rs.getInt(5));
        return ovChipkaart;
    }

    //Vind een kaart met het reizigerid, wordt altijd returned als een arraylist in het geval dat er meerdere resultaten zijn
    public ArrayList<OvChipkaart> findByReizigerId(int reizigersId) throws SQLException {
        ArrayList<OvChipkaart> ovChipkaarten = new ArrayList<>();
        String readQuery = "select * from ov_chipkaart where reizigerid = ?";
        PreparedStatement findKaarten = getConnection().prepareStatement(readQuery);
        findKaarten.setInt(1,reizigersId);
        ResultSet rs = findKaarten.executeQuery();
        while(rs.next()){
            OvChipkaart ovChipkaart = new OvChipkaart(0,null,0,0, 0);
            ovChipkaart.setKaartnummer(rs.getInt(1));
            ovChipkaart.setGeldigTot(rs.getDate(2));
            ovChipkaart.setKlasse(rs.getInt(3));
            ovChipkaart.setSaldo(rs.getInt(4));
            ovChipkaart.setReizigerId(rs.getInt(5));
            ovChipkaarten.add(ovChipkaart);
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
        return !deleteKaart.execute();
    }
}
