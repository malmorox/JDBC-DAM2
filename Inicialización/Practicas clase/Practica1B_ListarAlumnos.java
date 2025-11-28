package dbmysql;

import java.sql.*;

public class Practica1B_ListarAlumnos {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Obteniendo conexión...");

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/bd_empleados","root","");
        Statement ps = c.createStatement();
        ResultSet rs = ps.executeQuery("SELECT * FROM emp");

        while(rs.next()){
            int empno = rs.getInt("EMPNO");
            String ename = rs.getString("ENAME");
            String job = rs.getString("JOB");
            int mgr = rs.getInt("MGR");
            Date hiredate = rs.getDate("HIREDATE");
            double sal = rs.getDouble("SAL");
            double comm = rs.getDouble("COMM");
            int deptno = rs.getInt("DEPTNO");

            System.out.println(
                "EMPNO: " + empno +
                ", ENAME: " + ename +
                ", JOB: " + job +
                ", MGR: " + mgr +
                ", HIREDATE: " + hiredate +
                ", SAL: " + sal +
                ", COMM: " + comm +
                ", DEPTNO: " + deptno
            );
        }

        rs.close();
        ps.close();
        c.close();
    }
}
