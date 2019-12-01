package servlet;

import dao.FlatsDAO;
import dao.FlatsDAOlmpl;
import dbProperties.DbProperties;
import share.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    private static final String STR = "<html><head><title>Add</title></head><body>%s</body></html>";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address = req.getParameter("address");
        String district = req.getParameter("district");
        String tempArea = req.getParameter("area");
        String tempQuantity = req.getParameter("quantityRooms");
        String tempPhone = req.getParameter("phone");
        String tempPrice = req.getParameter("price");
        int quantityRooms = checkNull(tempQuantity);
        int phone = checkNull(tempPhone);
        int price = checkNull(tempPrice);
        int area = checkNull(tempArea);

        DbProperties dbProperties = new DbProperties();
        ConnectionFactory connectionFactory = new ConnectionFactory(dbProperties.getUrl(),dbProperties.getUser(),dbProperties.getPassword());
        try (Connection connection = connectionFactory.getConnection();PrintWriter printWriter = resp.getWriter();) {
            FlatsDAO flatsDAOlmpl = new FlatsDAOlmpl(connection);
            flatsDAOlmpl.add(address,district,area,quantityRooms,phone,price);
            printWriter.println(String.format(STR,"<h2>FLAT HAS ADDED</h2>"));
            resp.setHeader("Refresh", "2;http://localhost:8080/");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public int checkNull(String str) {
        if (str != null) {
            return Integer.parseInt(str);
        } else {
            return 0;

        }

    }


}
