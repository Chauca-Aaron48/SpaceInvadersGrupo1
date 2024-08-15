package logica.entidades;

public class Barrera extends Entidad {
    private int vidas;

    public Barrera(int posicionEnX, int posicionEnY, int ancho, int alto) {
        super(posicionEnX, posicionEnY, ancho, alto);
        this.vidas = 5;
    }

    public void reducirVida() {
        vidas -= 1;
    }

    public int obtenerNumeroDeVidas() {
        return vidas;
    }

    public void fijarVida(int vidas) {
        this.vidas = vidas;
    }

}
