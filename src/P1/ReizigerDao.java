package P1;

import java.util.ArrayList;
import java.util.List;

public interface ReizigerDao{
    List<Reiziger> findAll();
    List<Reiziger> findByGBdatum(String GBdatum);
    Reiziger save(Reiziger reiziger);
    Reiziger update(Reiziger oldReiziger, Reiziger newReiziger);
    boolean delete(Reiziger reiziger);
}