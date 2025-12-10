package dbmysql.oracle.practiva1_dao_alumnos;

import java.util.Collection;

public interface AlumnoInterface {
    void insertarAlumno(Alumno alumno) throws Exception;

    void actualizarAlumno(Alumno alumno) throws Exception;

    Collection<Alumno> getAlumnoPorCiclo(String ciclo) throws Exception;

    Collection<Alumno> getAlumnoPorNombre(String nombre) throws Exception;

    Alumno getAlumnoPorId(int id) throws Exception;

    int borrarAlumnosPorNombre(String nombre) throws Exception;
}