package dbmysql.oracle.practiva1_dao_alumnos;

import java.sql.*;

public class ConexionDB {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Obteniendo conexión...");

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/bd_alumnos","root","");
        Statement ps = c.createStatement();


    }
}
