package servlet;

import dao.FlatsDAO;
import dao.FlatsDAOlmpl;
import dbProperties.DbProperties;
import share.ConnectionFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/searchQuantity")
public class SearchQuantity extends HttpServlet {
    private static final String STR = " <tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String minRooms = req.getParameter("minRooms");
        String maxRooms = req.getParameter("maxRooms");

        DbProperties dbProperties = new DbProperties();
        ConnectionFactory connectionFactory = new ConnectionFactory(dbProperties.getUrl(), dbProperties.getUser(), dbProperties.getPassword());
        try (Connection connection = connectionFactory.getConnection();
             PrintWriter printWriter = resp.getWriter()) {
            FlatsDAO flatsDAOlmpl = new FlatsDAOlmpl(connection);
            try (ResultSet resultSet = flatsDAOlmpl.searchQuantity(minRooms, maxRooms)) {
               ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    printTable(printWriter, resultSet, resultSetMetaData);

            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }

    public void printTable(PrintWriter printWriter, ResultSet resultSet, ResultSetMetaData resultSetMetaData) throws SQLException {
        printWriter.println("<html><head><title>Quantity</title></head><body><table border='3'cellpadding='20'>");
        printWriter.println("<tr>");
        for (int i = 2; i <= resultSetMetaData.getColumnCount(); i++) {
            printWriter.println("<td><h4>" + resultSetMetaData.getColumnName(i) + "</h4></td>");
        }
        printWriter.println("</tr>");
        while (resultSet.next()) {
            printWriter.println(String.format(STR, resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
        }
        printWriter.println("</table><br/><h2><a href='index.jsp'>exit</a></h2></body></html>");
    }
}
