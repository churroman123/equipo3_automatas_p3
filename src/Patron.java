
public class Patron {
    private String nombre,regexp;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRegexp() {
        return regexp;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    public Patron(String nombre, String regexp) {
        this.nombre = nombre;
        this.regexp = regexp;
    }

    @Override
    public String toString() {
        return nombre + ":" + regexp;
    }
    
}
