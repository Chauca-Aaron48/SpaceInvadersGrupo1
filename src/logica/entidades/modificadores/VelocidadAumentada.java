package logica.entidades.modificadores;

import logica.entidades.jugador.NaveJugador;
import logica.entidades.Nave;

public class VelocidadAumentada extends Modificador {

    public VelocidadAumentada(int posicionEnX, int posicionEnY) {
        super(posicionEnX, posicionEnY);
    }

    public void aplicarEfecto(Nave nave) {
        ((NaveJugador) nave).actualizarVelocidad(10);
    }


}