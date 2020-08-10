package P2.dao;

import P2.domain.Reiziger;
import P2.implementation.ReizigerOracleDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ReizigerDao {
    List<Reiziger> findAll() throws SQLException;
    Reiziger findReiziger(int reizigersId) throws SQLException;
    List<Reiziger> findByGBdatum(String GBdatum) throws SQLException;
    Reiziger save(Reiziger reiziger) throws SQLException;
    Reiziger update(Reiziger oldReiziger, Reiziger newReiziger) throws SQLException;
    boolean delete(Reiziger reiziger) throws SQLException;
}