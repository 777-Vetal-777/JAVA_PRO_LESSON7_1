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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
@WebServlet("/showAll")
public class ShowAll extends HttpServlet {
    private static final String STR = "<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        DbProperties dbProperties = new DbProperties();
        ConnectionFactory connectionFactory = new ConnectionFactory(dbProperties.getUrl(), dbProperties.getUser(), dbProperties.getPassword());
        try (Connection connection = connectionFactory.getConnection(); PrintWriter printWriter = resp.getWriter()) {
            FlatsDAO flatsDAOlmpl = new FlatsDAOlmpl(connection);
            try (ResultSet resultSet = flatsDAOlmpl.showAll()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                printALl(printWriter, resultSet, resultSetMetaData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void printALl(PrintWriter printWriter, ResultSet resultSet, ResultSetMetaData resultSetMetaData) throws SQLException {
        printWriter.println("<html><head><title>Quantity</title></head><body><table border='3'cellpadding='20'>");
        printWriter.println("<tr>");
        for (int i = 2; i <= resultSetMetaData.getColumnCount(); i++) {
            printWriter.println("<td><h4> " + resultSetMetaData.getColumnName(i) + "</h4></td>");
        }
        printWriter.println("</tr>");
        while (resultSet.next()){
            printWriter.println(String.format(STR,resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7)));
        }
        printWriter.println("</table><br/><h2><a href='index.jsp'>exit</a></h2></body></html>");

    }
}

