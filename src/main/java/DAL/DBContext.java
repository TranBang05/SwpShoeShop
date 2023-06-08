package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    public Connection connection;

    public DBContext() {
        try {
            String user = "root";
            String pass = "bang0501@b";
            String url = "jdbc:mysql://localhost:3306/Shoeshope";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            System.out.println("Connect Fail: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Connect Fail: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        DBContext a = new DBContext();
        System.out.println(a.connection);
    }
}
