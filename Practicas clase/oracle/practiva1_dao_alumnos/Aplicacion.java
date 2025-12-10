package dbmysql.oracle.practiva1_dao_alumnos;

import java.util.Collection;
import java.util.Iterator;

public class Aplicacion {

    public static void main(String[] args) {
        AlumnoDAO dao = new AlumnoDAO();

        System.out.println("EJECUCIÓN 1: Alta y Modificación de Alumno");
        ejecucion1(dao);

        System.out.println("EJECUCIÓN 2: Alumnos del ciclo DAM");
        ejecucion2(dao);

        System.out.println("EJECUCIÓN 3: Alumnos llamados Juan");
        ejecucion3(dao);

        System.out.println("EJECUCIÓN 4: Datos del alumno expediente 1");
        ejecucion4(dao);

        System.out.println("EJECUCIÓN 5: Borrar alumnos llamados Juan");
        ejecucion5(dao);
    }

    private static void ejecucion1(AlumnoDAO dao) {
        Alumno nuevoAlumno = new Alumno("105", "ENRIQUETA", "ASIR", "3434343", null);

        System.out.println("Insertando alumno: " + nuevoAlumno);
        boolean insertado = dao.insertarAlumno(nuevoAlumno);

        if (insertado) {
            System.out.println("✓ Alumno dado de alta correctamente\n");

            nuevoAlumno.setNombre("BLANCA");
            nuevoAlumno.setDni("55555555");
            nuevoAlumno.setCiclo("DAM");

            System.out.println("Modificando alumno a: " + nuevoAlumno);
            boolean modificado = dao.modificarAlumno(nuevoAlumno);

            if (modificado) {
                System.out.println("✓ Alumno modificado correctamente");

                Alumno verificacion = dao.getAlumnoPorExpediente("105");
                System.out.println("\nVerificación en BD:");
                System.out.println(verificacion);
            }
        }
    }

    private static void ejecucion2(AlumnoDAO dao) {
        Collection<Alumno> alumnosDAM = dao.getAlumnoPorCiclo("DAM");

        System.out.println("Alumnos matriculados en DAM:");
        System.out.println("Total: " + alumnosDAM.size() + " alumnos\n");

        // Usar Iterator para recorrer la colección
        Iterator<Alumno> iterator = alumnosDAM.iterator();
        int contador = 1;

        while (iterator.hasNext()) {
            Alumno alumno = iterator.next();
            System.out.println(contador + ". " + alumno);
            contador++;
        }

        if (alumnosDAM.isEmpty()) {
            System.out.println("No se encontraron alumnos en el ciclo DAM");
        }
    }

    private static void ejecucion3(AlumnoDAO dao) {
        Collection<Alumno> alumnosJuan = dao.getAlumnoPorNombre("Juan");

        System.out.println("Alumnos llamados Juan:");
        System.out.println("Total: " + alumnosJuan.size() + " alumnos\n");

        Iterator<Alumno> iterator = alumnosJuan.iterator();
        int contador = 1;

        while (iterator.hasNext()) {
            Alumno alumno = iterator.next();
            System.out.println(contador + ". " + alumno);
            contador++;
        }

        if (alumnosJuan.isEmpty()) {
            System.out.println("No se encontraron alumnos llamados Juan");
        }
    }

    private static void ejecucion4(AlumnoDAO dao) {
        Alumno alumno = dao.getAlumnoPorExpediente("1");

        if (alumno != null) {
            System.out.println("Datos del alumno con expediente 1:");
            System.out.println("─────────────────────────────────────────");
            System.out.println("Nº Expediente: " + alumno.getNumExpediente());
            System.out.println("Nombre:        " + alumno.getNombre());
            System.out.println("Ciclo:         " + alumno.getCiclo());
            System.out.println("DNI:           " + alumno.getDni());
            System.out.println("Teléfono:      " + alumno.getTelefono());
            System.out.println("─────────────────────────────────────────");
        } else {
            System.out.println("No se encontró ningún alumno con expediente 1");
        }
    }

    private static void ejecucion5(AlumnoDAO dao) {
        Collection<Alumno> alumnosABorrar = dao.getAlumnoPorNombre("Juan");

        if (!alumnosABorrar.isEmpty()) {
            System.out.println("Alumnos a borrar:");
            Iterator<Alumno> iterator = alumnosABorrar.iterator();
            while (iterator.hasNext()) {
                System.out.println("  - " + iterator.next());
            }
            System.out.println();
        }

        int eliminados = dao.borrarAlumnosPorNombre("Juan");

        if (eliminados > 0) {
            System.out.println("✓ Se eliminaron " + eliminados + " alumno(s) llamado(s) Juan");

            Collection<Alumno> verificacion = dao.getAlumnoPorNombre("Juan");
            if (verificacion.isEmpty()) {
                System.out.println("✓ Verificado: No quedan alumnos llamados Juan en la BD");
            }
        } else {
            System.out.println("No se encontraron alumnos llamados Juan para eliminar");
        }
    }
}