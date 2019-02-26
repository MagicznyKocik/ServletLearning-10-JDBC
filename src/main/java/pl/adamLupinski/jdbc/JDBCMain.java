package pl.adamLupinski.jdbc;


import java.sql.*;

public class JDBCMain {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        final String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);

        // in this step write your own data into your username and password
        final String dbPath = "jdbc:mysql://localhost:3306/world?useSSL=false&serverTimezone=UTC";
        Connection conn = DriverManager.getConnection(dbPath, "{yourusername}", "password");

        Statement statement = conn.createStatement();
        final String query = "SELECT Name, Population FROM city";
        ResultSet resultSet = statement.executeQuery(query);
        String cityName = null;
        int cityPopulation = 0;
        while(resultSet.next()) {
            cityName = resultSet.getString("Name");
            cityPopulation = resultSet.getInt("Population");
            System.out.println(cityName + " " + cityPopulation);
        }

        if(statement != null) {
            statement.close();
        }
        if(resultSet != null) {
            resultSet.close();
        }
        if(conn != null) {
            conn.close();
        }

    }

}
