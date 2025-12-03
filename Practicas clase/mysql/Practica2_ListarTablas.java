package dbmysql.mysql;

public class Practica2_ListarTablas {
    public static void main(String[] args) {
        try {
            BD_alumnos_showT bd = new BD_alumnos_showT();
            bd.ListarTablas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
