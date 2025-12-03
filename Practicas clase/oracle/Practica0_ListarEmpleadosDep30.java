package dbmysql.oracle;

import java.sql.*;

public class Practica0_ListarEmpleadosDep30 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");

        System.out.println("Obteniendo conexión...");

        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","dam2d","dam2d");
        Statement ps = con.createStatement();
        ResultSet rs= ps.executeQuery("SELECT * FROM emp WHERE deptno=30");

        System.out.println("Las tablas de las base de datos de alumnos son:");
        while(rs.next()){
            System.out.print("empno: "+rs.getString(1));
            System.out.print("ename: "+rs.getString(2));
            System.out.print("job: "+rs.getString(3));
            System.out.println("");
        }
    }
}
