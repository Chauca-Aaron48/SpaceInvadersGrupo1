package logica.entidades.modificadores;

import logica.entidades.enemigos.NaveEnemigo;
import logica.entidades.jugador.NaveJugador;
import logica.entidades.Nave;

public class Politecnico extends Modificador {
    public Politecnico(int posicionEnX, int posicionEnY) {
        super(posicionEnX, posicionEnY);
    }

    public void aplicarEfecto(Nave nave) {
        if (nave instanceof NaveJugador) {
            return;
        }
        ((NaveEnemigo) nave).aumentarProbabilidadDeDisparo();
    }


}
