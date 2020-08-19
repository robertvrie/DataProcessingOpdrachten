package P3.dao;

import P3.domain.OvChipkaart;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OvChipKaartDao {
    ArrayList<OvChipkaart> findAll() throws SQLException;
    OvChipkaart findByKaartNummer(int kaartNummer) throws SQLException;
    ArrayList<OvChipkaart> findByReizigerId(int reizigersId) throws SQLException;
    OvChipkaart save(OvChipkaart kaart) throws SQLException;
    OvChipkaart update(OvChipkaart oudeKaart, OvChipkaart nieuweKaart) throws SQLException;
    boolean delete(OvChipkaart kaart) throws SQLException;
}
