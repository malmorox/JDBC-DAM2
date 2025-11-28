package dbmysql;

import java.sql.*;

public class Practica1A_ListarAlumnos {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Obteniendo conexión...");

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/bd_alumnos","root","");
        Statement ps = c.createStatement();
        ResultSet rs = ps.executeQuery("SELECT * FROM alumnos");

        while(rs.next()){
            String apellidos = rs.getString("apellidos");
            String nombre = rs.getString("nombre");
            String titulacion = rs.getString("titulacion");

            System.out.println("nombre: " + nombre + ", apellidos: " + apellidos + ", titulación: " + titulacion);
        }

        rs.close();
        ps.close();
        c.close();
    }
}
