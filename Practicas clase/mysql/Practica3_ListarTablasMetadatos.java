package dbmysql.mysql;

import java.sql.*;

public class Practica3_ListarTablasMetadatos {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Obteniendo conexión...");

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/bd_alumnos","root","");
        DatabaseMetaData md = c.getMetaData();

        ResultSet rs = md.getTables(null, null, null, null);

        System.out.println("Tablas de la base de datos:");

        while (rs.next()) {
            System.out.println(rs.getString("TABLE_NAME"));
        }

        rs.close();
        c.close();
    }
}
