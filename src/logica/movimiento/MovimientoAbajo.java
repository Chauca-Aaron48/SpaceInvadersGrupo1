package logica.movimiento;

import logica.entidades.Entidad;

public class MovimientoAbajo implements Movimiento {

    @Override
    public void mover(Entidad entidad) {
        entidad.fijarNuevaPosicionEnY(entidad.obtenerPosicionEnY() + entidad.obtenerVelocidad());
    }

}
