package logica.entidades.modificadores;

import logica.entidades.jugador.NaveJugador;
import logica.entidades.Nave;

public class VelocidadDeDisparoAumentada extends Modificador {

    public VelocidadDeDisparoAumentada(int posicionEnX, int posicionEnY) {
        super(posicionEnX, posicionEnY);
    }

    public void aplicarEfecto(Nave nave) {
        ((NaveJugador) nave).aumentarVelocidadDeDisparo(10);
    }

}
