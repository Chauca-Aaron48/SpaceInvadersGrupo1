package logica.puntaje;


public class Puntaje {
    private int puntaje;
    private String nombre;

    public Puntaje(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }


    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }


}
