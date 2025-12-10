package dbmysql.oracle.practiva1_dao_alumnos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import dbmysql.oracle.practiva1_dao_alumnos.ConexionDB;

public class AlumnoDAO {
    public boolean insertarAlumno(Alumno alumno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;

        try {
            conn = ConexionDB.getConexion();

            String sql = "INSERT INTO alumno (numExpediente, nombre, ciclo, dni, telefono) " +
                    "VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, alumno.getNumExpediente());
            pstmt.setString(2, alumno.getNombre());
            pstmt.setString(3, alumno.getCiclo());
            pstmt.setString(4, alumno.getDni());
            pstmt.setString(5, alumno.getTelefono());

            // Ejecutar inserción
            int filas = pstmt.executeUpdate();
            resultado = (filas > 0);

            if (resultado) {
                System.out.println("✓ Alumno insertado correctamente");
            }

        } catch (SQLException e) {
            System.err.println("✗ Error al insertar alumno");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, null);
        }

        return resultado;
    }

    /**
     * Modifica los datos de un alumno existente
     * @param alumno Objeto Alumno con los nuevos datos (el numExpediente identifica al alumno)
     * @return true si se modificó correctamente, false en caso contrario
     */
    public boolean modificarAlumno(Alumno alumno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;

        try {
            // Obtener conexión
            conn = ConexionBD.getConexion();

            // Preparar sentencia SQL
            String sql = "UPDATE alumno SET nombre = ?, ciclo = ?, dni = ?, telefono = ? " +
                    "WHERE numExpediente = ?";
            pstmt = conn.prepareStatement(sql);

            // Establecer parámetros
            pstmt.setString(1, alumno.getNombre());
            pstmt.setString(2, alumno.getCiclo());
            pstmt.setString(3, alumno.getDni());
            pstmt.setString(4, alumno.getTelefono());
            pstmt.setString(5, alumno.getNumExpediente());

            // Ejecutar actualización
            int filas = pstmt.executeUpdate();
            resultado = (filas > 0);

            if (resultado) {
                System.out.println("✓ Alumno modificado correctamente");
            } else {
                System.out.println("⚠ No se encontró el alumno con expediente: " + alumno.getNumExpediente());
            }

        } catch (SQLException e) {
            System.err.println("✗ Error al modificar alumno");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, null);
        }

        return resultado;
    }

    /**
     * Obtiene todos los alumnos de un ciclo específico
     * @param ciclo Nombre del ciclo (DAM, ASIR, DAW, etc.)
     * @return Collection con los alumnos del ciclo especificado
     */
    public Collection<Alumno> getAlumnoPorCiclo(String ciclo) {
        Collection<Alumno> alumnos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtener conexión
            conn = ConexionBD.getConexion();

            // Preparar sentencia SQL
            String sql = "SELECT * FROM alumno WHERE ciclo = ? ORDER BY numExpediente";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ciclo);

            // Ejecutar consulta
            rs = pstmt.executeQuery();

            // Procesar resultados
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setNumExpediente(rs.getString("numExpediente"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setCiclo(rs.getString("ciclo"));
                alumno.setDni(rs.getString("dni"));
                alumno.setTelefono(rs.getString("telefono"));

                alumnos.add(alumno);
            }

            System.out.println("✓ Consulta ejecutada: " + alumnos.size() + " alumno(s) encontrado(s)");

        } catch (SQLException e) {
            System.err.println("✗ Error al obtener alumnos por ciclo");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, rs);
        }

        return alumnos;
    }

    /**
     * Obtiene todos los alumnos cuyo nombre contiene el texto especificado
     * @param nombre Texto a buscar en el nombre (búsqueda parcial, case-insensitive)
     * @return Collection con los alumnos que coinciden con el nombre
     */
    public Collection<Alumno> getAlumnoPorNombre(String nombre) {
        Collection<Alumno> alumnos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtener conexión
            conn = ConexionBD.getConexion();

            // Preparar sentencia SQL con búsqueda parcial
            String sql = "SELECT * FROM alumno WHERE UPPER(nombre) LIKE UPPER(?) ORDER BY numExpediente";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + nombre + "%");

            // Ejecutar consulta
            rs = pstmt.executeQuery();

            // Procesar resultados
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setNumExpediente(rs.getString("numExpediente"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setCiclo(rs.getString("ciclo"));
                alumno.setDni(rs.getString("dni"));
                alumno.setTelefono(rs.getString("telefono"));

                alumnos.add(alumno);
            }

            System.out.println("✓ Consulta ejecutada: " + alumnos.size() + " alumno(s) encontrado(s)");

        } catch (SQLException e) {
            System.err.println("✗ Error al obtener alumnos por nombre");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, rs);
        }

        return alumnos;
    }

    /**
     * Obtiene un alumno específico por su número de expediente
     * @param numExpediente Número de expediente del alumno
     * @return Objeto Alumno si se encuentra, null en caso contrario
     */
    public Alumno getAlumnoPorExpediente(String numExpediente) {
        Alumno alumno = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtener conexión
            conn = ConexionBD.getConexion();

            // Preparar sentencia SQL
            String sql = "SELECT * FROM alumno WHERE numExpediente = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, numExpediente);

            // Ejecutar consulta
            rs = pstmt.executeQuery();

            // Procesar resultado (solo debe haber uno)
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setNumExpediente(rs.getString("numExpediente"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setCiclo(rs.getString("ciclo"));
                alumno.setDni(rs.getString("dni"));
                alumno.setTelefono(rs.getString("telefono"));

                System.out.println("✓ Alumno encontrado");
            } else {
                System.out.println("⚠ No se encontró alumno con expediente: " + numExpediente);
            }

        } catch (SQLException e) {
            System.err.println("✗ Error al obtener alumno por expediente");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, rs);
        }

        return alumno;
    }

    /**
     * Elimina todos los alumnos cuyo nombre contiene el texto especificado
     * @param nombre Texto a buscar en el nombre para eliminar (búsqueda parcial)
     * @return Número de alumnos eliminados
     */
    public int borrarAlumnosPorNombre(String nombre) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int filasEliminadas = 0;

        try {
            // Obtener conexión
            conn = ConexionBD.getConexion();

            // Preparar sentencia SQL
            String sql = "DELETE FROM alumno WHERE UPPER(nombre) LIKE UPPER(?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + nombre + "%");

            // Ejecutar eliminación
            filasEliminadas = pstmt.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("✓ " + filasEliminadas + " alumno(s) eliminado(s) correctamente");
            } else {
                System.out.println("⚠ No se encontraron alumnos con ese nombre");
            }

        } catch (SQLException e) {
            System.err.println("✗ Error al borrar alumnos");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, null);
        }

        return filasEliminadas;
    }

    /**
     * Elimina un alumno específico por su número de expediente
     * @param numExpediente Número de expediente del alumno a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean borrarAlumno(String numExpediente) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;

        try {
            // Obtener conexión
            conn = ConexionBD.getConexion();

            // Preparar sentencia SQL
            String sql = "DELETE FROM alumno WHERE numExpediente = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, numExpediente);

            // Ejecutar eliminación
            int filas = pstmt.executeUpdate();
            resultado = (filas > 0);

            if (resultado) {
                System.out.println("✓ Alumno eliminado correctamente");
            } else {
                System.out.println("⚠ No se encontró el alumno con expediente: " + numExpediente);
            }

        } catch (SQLException e) {
            System.err.println("✗ Error al borrar alumno");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, null);
        }

        return resultado;
    }

    /**
     * Obtiene todos los alumnos de la base de datos
     * @return Collection con todos los alumnos
     */
    public Collection<Alumno> getAllAlumnos() {
        Collection<Alumno> alumnos = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Obtener conexión
            conn = ConexionBD.getConexion();

            // Preparar sentencia SQL
            String sql = "SELECT * FROM alumno ORDER BY numExpediente";
            stmt = conn.createStatement();

            // Ejecutar consulta
            rs = stmt.executeQuery(sql);

            // Procesar resultados
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setNumExpediente(rs.getString("numExpediente"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setCiclo(rs.getString("ciclo"));
                alumno.setDni(rs.getString("dni"));
                alumno.setTelefono(rs.getString("telefono"));

                alumnos.add(alumno);
            }

            System.out.println("✓ Consulta ejecutada: " + alumnos.size() + " alumno(s) total(es)");

        } catch (SQLException e) {
            System.err.println("✗ Error al obtener todos los alumnos");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) ConexionBD.cerrarConexion(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return alumnos;
    }

    /**
     * Método auxiliar privado para cerrar los recursos de base de datos
     * @param conn Connection a cerrar
     * @param pstmt PreparedStatement a cerrar
     * @param rs ResultSet a cerrar
     */
    private void cerrarRecursos(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                ConexionBD.cerrarConexion(conn);
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al cerrar recursos");
            e.printStackTrace();
        }
    }
}