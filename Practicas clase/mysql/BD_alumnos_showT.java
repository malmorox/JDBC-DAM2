package dbmysql.mysql;

import java.sql.*;

public class BD_alumnos_showT {

    public void ListarTablas() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Obteniendo conexión...");

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/bd_alumnos", "root", "");
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SHOW TABLES");

        System.out.println("Tablas de la base de datos:");

        while (rs.next()) {
            System.out.println(rs.getString(1));
        }

        rs.close();
        st.close();
        c.close();
    }
}
