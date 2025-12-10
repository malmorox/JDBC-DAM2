package dbmysql.oracle.practiva1_dao_alumnos;

public class Alumno {
    private int numExpediente;
    private String nombre;
    private String ciclo;
    private String dni;
    private String telefono;

    public Alumno() {
    }

    public Alumno(int numExpediente, String nombre, String ciclo, String dni, String telefono) {
        this.numExpediente = numExpediente;
        this.nombre = nombre;
        this.ciclo = ciclo;
        this.dni = dni;
        this.telefono = telefono;
    }

    public int getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(int numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "numExpediente='" + numExpediente + '\'' +
                ", nombre='" + nombre + '\'' +
                ", ciclo='" + ciclo + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}