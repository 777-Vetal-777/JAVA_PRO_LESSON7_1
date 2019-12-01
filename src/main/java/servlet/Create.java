package servlet;

import java.sql.*;

public class Create {
    public static void createDatabase(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?serverTimezone=Europe/Kiev","root","Pro100vetal");

            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE IF not exists Flats");
            statement.execute("USE flats");
            statement.execute("CREATE TABLE flats(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, address VARCHAR(20) NOT NULL, district VARCHAR(20) NOT NULL, area INT NOT NULL, quantityRooms INT NOT NULL, price INT NOT NULL, phone INT NOT NULL)");


        } catch (SQLException e) {
            e.printStackTrace();
        }






    }
}
