package P2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleBaseDao {
    Connection dbConnection = null;
    protected Connection getConnection(){
        {
            try {
                dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.178.94:1521/xepdb1", "ChipkaartOpdracht", "BroederGemeente420");
            } catch (SQLException e) {
                System.out.println("Unable to make connection");
                e.printStackTrace();
            }
        }
        return dbConnection;
    }

    public void closeConnection() throws SQLException {
        dbConnection.close();
    }
}