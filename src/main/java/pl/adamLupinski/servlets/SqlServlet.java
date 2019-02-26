package pl.adamLupinski.servlets;

import pl.adamLupinski.entity.City;
import pl.adamLupinski.jdbc.JDBCMain;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SqlServlet")
public class SqlServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String param = request.getParameter("get");

        if ("show".equals(param)){
            try{
                List<City> cities = getCities();
                request.setAttribute("cities", cities);
                request.getRequestDispatcher("cityList.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                response.sendError(500);
            }

        } else {
            response.sendError(403);
        }
    }


    private List<City> getCities() throws ClassNotFoundException, SQLException {

        final String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);

        List<City> cityList = null;
        final String query = "SELECT Name, Population FROM city";
        final String dbPath = "jdbc:mysql://localhost:3306/world?useSSL=false&serverTimezone=UTC";
        try (Connection conn = DriverManager.getConnection(dbPath, "Mkocik", "micro$p4c3S");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {

            String cityName = null;
            int cityPopulation = 0;
            cityList = new ArrayList<>();
            while (resultSet.next()){
                cityName = resultSet.getString("Name");
                cityPopulation = resultSet.getInt("Population");
                City city = new City(cityName,cityPopulation);
                cityList.add(city);
            }
        }

        return cityList;

    }

}
