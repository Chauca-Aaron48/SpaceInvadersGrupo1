package logica.entidades.modificadores;

import logica.entidades.jugador.NaveJugador;
import logica.entidades.Nave;

public class VidaExtra extends Modificador {

    public VidaExtra(int posicionEnX, int posicionEnY) {
        super(posicionEnX, posicionEnY);
    }

    public void aplicarEfecto(Nave nave) {
        ((NaveJugador) nave).actualizarNumeroDeVidas(((NaveJugador) nave).obtenerVidasDisponibles() + 1);
    }


}
