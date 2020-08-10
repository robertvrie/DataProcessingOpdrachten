package P2.implementation;

import P2.dao.OvChipKaartDao;
import P2.domain.OvChipkaart;
import P2.domain.Reiziger;
import P2.dao.ReizigerDao;
import P2.dao.OracleBaseDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDao{
    private OvChipKaartDao ovChipKaartDao = new OvChipkaartOracleDaoImpl();

    //Kan via de query methode alle reizigers ophalen. Alle kaarten die van de reiziger zijn worden in een arraylist erbij geladen
    public List<Reiziger> findAll() throws SQLException {
        ArrayList<Reiziger> reizigers = new ArrayList<>();
        String readQuery = "select * from reiziger";
        PreparedStatement findAll = getConnection().prepareStatement(readQuery);
        ResultSet rs = findAll.executeQuery();
        while(rs.next()){
            Reiziger reiziger = new Reiziger(0,null,null,null,null);
            reiziger.setReizigerId(rs.getInt(1));
            reiziger.setVoorletters(rs.getString(2));
            reiziger.setTussenvoegsel(rs.getString(3));
            reiziger.setAchternaam(rs.getString(4));
            reiziger.setGbdatum(rs.getDate(5));
            for (OvChipkaart kaart : ovChipKaartDao.findByReizigerId(rs.getInt(1))){
                reiziger.setKaarten(kaart);
            }
            reizigers.add(reiziger);
        }
        return reizigers;
    }

    //Vind 1 losse reiziger met het id.
    public Reiziger findReiziger(int id) throws SQLException {
        String readQuery = "select * from reiziger where reizigerid = ?";
        PreparedStatement findOne = getConnection().prepareStatement(readQuery);
        findOne.setInt(1, id);
        ResultSet rs = findOne.executeQuery();
        rs.next();
        Reiziger reiziger = new Reiziger(0,null,null,null,null);
        reiziger.setReizigerId(rs.getInt(1));
        reiziger.setVoorletters(rs.getString(2));
        reiziger.setTussenvoegsel(rs.getString(3));
        reiziger.setAchternaam(rs.getString(4));
        reiziger.setGbdatum(rs.getDate(5));
        for (OvChipkaart kaart : ovChipKaartDao.findByReizigerId(rs.getInt(1))){
            reiziger.setKaarten(kaart);
        }
        return reiziger;
    }

    //Vind een reiziger met de geboortedatum
    public List<Reiziger> findByGBdatum(String GBdatum) throws SQLException {
        ArrayList<Reiziger> reizigers = new ArrayList<>();
        String readQuery = "select * from reiziger where gebortedatum = ?";
        PreparedStatement findAll = getConnection().prepareStatement(readQuery);
        findAll.setDate(1, Date.valueOf(GBdatum));
        ResultSet rs = findAll.executeQuery();
        while(rs.next()){
            Reiziger reiziger = new Reiziger(0,null,null,null,null);
            reiziger.setReizigerId(rs.getInt(1));
            reiziger.setVoorletters(rs.getString(2));
            reiziger.setTussenvoegsel(rs.getString(3));
            reiziger.setAchternaam(rs.getString(4));
            reiziger.setGbdatum(rs.getDate(5));
            for (OvChipkaart kaart : ovChipKaartDao.findByReizigerId(rs.getInt(1))){
                reiziger.setKaarten(kaart);
            }
            reizigers.add(reiziger);
        }
        return reizigers;
    }

    //Slaat een reiziger op
    public Reiziger save(Reiziger reiziger) throws SQLException {
        String insertQuery = "insert into reiziger values(?,?,?,?,?)";
        PreparedStatement saveReiziger = getConnection().prepareStatement(insertQuery);
        saveReiziger.setInt(1, 6);
        saveReiziger.setString(2,reiziger.getVoorletters());
        saveReiziger.setString(3,reiziger.getTussenvoegsel());
        saveReiziger.setString(4,reiziger.getAchternaam());
        saveReiziger.setDate(5 ,reiziger.getGbdatum());
        saveReiziger.execute();
        return reiziger;
    }

    //Vervangt alle gegevens van een reiziger, afgezien van het reizigerid. Het reizigerid van het nieuwe object wordt wel aangepast naar het reizigerid van het oude object.
    public Reiziger update(Reiziger oldReiziger, Reiziger newReiziger) throws SQLException {
        String updateQuery = "update reiziger set voorletters = ?, tussenvoegsel = ?, achternaam = ?, gebortedatum = ? where reizigerid = ?";
        PreparedStatement updateReiziger = getConnection().prepareStatement(updateQuery);
        updateReiziger.setString(1, newReiziger.getVoorletters());
        updateReiziger.setString(2, newReiziger.getTussenvoegsel());
        updateReiziger.setString(3, newReiziger.getAchternaam());
        updateReiziger.setDate(4, newReiziger.getGbdatum());
        updateReiziger.setInt(5,oldReiziger.getReizigerId());
        updateReiziger.execute();
        newReiziger.setReizigerId(oldReiziger.getReizigerId());
        return newReiziger;
    }

    //Verwijderd een reiziger
    public boolean delete(Reiziger reiziger) throws SQLException {
        String deleteQuery = "delete from reiziger where reizigerid = ?";
        PreparedStatement deleteReiziger = getConnection().prepareStatement(deleteQuery);
        deleteReiziger.setInt(1, reiziger.getReizigerId());
        return !deleteReiziger.execute();
    }
}

