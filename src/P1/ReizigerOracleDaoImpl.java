package P1;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReizigerOracleDaoImpl implements ReizigerDao{
    public ArrayList<Reiziger> tempDB = new ArrayList<Reiziger>();

    public List<Reiziger> findAll(){
        List<Reiziger> reizigers = new ArrayList<Reiziger>();
        for(int i=0; i < tempDB.size(); i++){
            reizigers.add((Reiziger) tempDB.get(i));
        }
        return reizigers;
    }

    public List<Reiziger> findByGBdatum(String GBdatum){
        List<Reiziger> reizigers = new ArrayList<Reiziger>();
        Date dateToFind = Date.valueOf(GBdatum);
        for(int i=0; i < tempDB.size(); i++){
            if(tempDB.get(i).getGbdatum().equals(dateToFind)) {
                reizigers.add(tempDB.get(i));
            }
        }
        return reizigers;
    }

    public Reiziger save(Reiziger reiziger){
        tempDB.add(reiziger);
        return reiziger;
    }

    public Reiziger update(Reiziger oldReiziger, Reiziger newReiziger){
        tempDB.set(tempDB.indexOf(oldReiziger),newReiziger);
        return newReiziger;
    }

    public boolean delete(Reiziger reiziger){
        boolean success = false;
        if(tempDB.remove(reiziger)){
            success = true;
        }
        else{
            success = false;
        }
        return success;
    }
}
