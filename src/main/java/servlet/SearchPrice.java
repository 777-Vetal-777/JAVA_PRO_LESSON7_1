package servlet;

import dao.FlatsDAO;
import dao.FlatsDAOlmpl;
import dbProperties.DbProperties;
import share.ConnectionFactory;
import share.Flat;
import share.Flats;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/searchPrice")
public class SearchPrice extends HttpServlet {
    final String ST = "<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String minPrice = req.getParameter("minPrice");
        String maxPrice = req.getParameter("maxPrice");
       DbProperties dbProperties = new DbProperties();
       ConnectionFactory connectionFactory = new ConnectionFactory(dbProperties.getUrl(),dbProperties.getUser(),dbProperties.getPassword());
        try (Connection connection =connectionFactory.getConnection() ;PrintWriter printWriter = resp.getWriter()) {
            FlatsDAO flatsDAOlmpl = new FlatsDAOlmpl(connection);
            try (ResultSet resultSet = flatsDAOlmpl.searchQuantity(minPrice, maxPrice)){
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                printTable(printWriter, resultSet, resultSetMetaData);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void printTable(PrintWriter printWriter, ResultSet resultSet, ResultSetMetaData resultSetMetaData) throws SQLException {
        Flats flats = new Flats();
        while (resultSet.next()) {
            flats.add(new Flat(resultSet.getString("address"), resultSet.getString("district"), resultSet.getString("area"), resultSet.getInt("quantityRooms"), resultSet.getInt("price"), resultSet.getInt("phone")));
        }
        printWriter.println("<html><head><title>Price</title></head><body><table border='3'cellpadding='20'>");
        printWriter.println("<tr>");
        for (int i = 2; i <= resultSetMetaData.getColumnCount(); i++) {
            printWriter.println("<td><h4>" + resultSetMetaData.getColumnName(i) + "</h4></td>");
        }
        printWriter.println("</tr>");
        for (int i = 0; i < flats.size(); i++) {
            printWriter.println(String.format(ST, flats.get(i).getAddress(), flats.get(i).getDistrict(), flats.get(i).getSpace(), flats.get(i).getQuantityRooms(), flats.get(i).getPrice(), flats.get(i).getPhone()));
        }
        printWriter.println("</table><br/><h2><a href='index.jsp'>exit</a></h2></body></html>");
    }
}
