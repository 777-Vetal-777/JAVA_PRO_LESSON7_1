package dao;

import com.sun.javaws.progress.PreloaderPostEventListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlatsDAOlmpl implements FlatsDAO {
    private final Connection connection;

    public FlatsDAOlmpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(String address, String district, int area, int quantityRooms, int price, int phone) {
       try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO flats(address, district, area, quantityRooms, price, phone) VALUES(?,?,?,?,?,?)");) {
           preparedStatement.setString(1, address);
           preparedStatement.setString(2, district);
           preparedStatement.setInt(3, area);
           preparedStatement.setInt(4, quantityRooms);
           preparedStatement.setInt(5, price);
           preparedStatement.setInt(6, phone);
           preparedStatement.execute();

       } catch (SQLException e) {
           e.printStackTrace();
       }


    }

    @Override
    public ResultSet searchPrice(String min, String max) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM flats WHERE quantityRooms>= ? AND quantityRooms <= ? ");
        preparedStatement.setString(1,min);
        preparedStatement.setString(2,max);
        ResultSet resultSet = preparedStatement.executeQuery();

        return  resultSet;
    }

    @Override
    public ResultSet searchQuantity(String min, String max) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM flats WHERE price>= ? AND price <= ? ");
            preparedStatement.setString(1,min);
            preparedStatement.setString(2,max);
            ResultSet resultSet = preparedStatement.executeQuery();

        return  resultSet;
    }

    @Override
    public ResultSet showAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM flats");
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
}
