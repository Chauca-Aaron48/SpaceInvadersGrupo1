package logica.movimiento;

import logica.entidades.Entidad;

public class MovimientoNaveJugador implements Movimiento {
    private int distanciaDesplazada;
    private final int LIMITE_DERECHO = 785;
    private final int ANCHO_NAVE = 64;

    private boolean estaEnElLimiteIzquierdo(Entidad entidad) {
        return entidad.obtenerPosicionEnX() < 0;
    }

    private boolean estaEnElLimiteDerecho(Entidad entidad) {
        return entidad.obtenerPosicionEnX() > LIMITE_DERECHO - ANCHO_NAVE;
    }

    @Override
    public void mover(Entidad entidad) {
        if (estaEnElLimiteDerecho(entidad)) {
            entidad.fijarNuevaPosicionEnX(LIMITE_DERECHO - ANCHO_NAVE);
        }
        if (estaEnElLimiteIzquierdo(entidad)) {
            entidad.fijarNuevaPosicionEnX(0);
        }
        entidad.fijarNuevaPosicionEnX(entidad.obtenerPosicionEnX() + distanciaDesplazada);
    }

    public void fijarDistanciaDesplazada(int distanciaDesplazada) {
        this.distanciaDesplazada = distanciaDesplazada;
    }
}
