package P3.dao;

import P3.domain.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDao {
    List<Reiziger> findAll() throws SQLException;
    Reiziger findReiziger(int reizigersId) throws SQLException;
    List<Reiziger> findByGBdatum(String GBdatum) throws SQLException;
    Reiziger save(Reiziger reiziger) throws SQLException;
    Reiziger update(Reiziger oldReiziger, Reiziger newReiziger) throws SQLException;
    boolean delete(Reiziger reiziger) throws SQLException;
}