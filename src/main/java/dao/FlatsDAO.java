package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface FlatsDAO {
     void add(String address,String district, int area, int quantityRooms, int price, int phone);
     ResultSet searchPrice(String min, String max) throws SQLException;
     ResultSet searchQuantity(String min, String max) throws SQLException;
     ResultSet showAll() throws SQLException;

}
