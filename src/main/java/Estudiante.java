public class Estudiante {

    private String Id;
    private String Nombre;
    private String Apellido;

    public Estudiante(String id, String nombre, String apellido) {
        Id = id;
        Nombre = nombre;
        Apellido = apellido;
    }

    public String getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

}
