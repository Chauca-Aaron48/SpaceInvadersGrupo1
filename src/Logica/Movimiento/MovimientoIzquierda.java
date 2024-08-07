package Logica.Movimiento;

import Logica.Entidades.Entidad;

public class MovimientoIzquierda implements Movimiento{
    private int direccion;

    public MovimientoIzquierda() {
        this.direccion = 1;
    }

    @Override
    public void mover(Entidad entidad) {
        entidad.fijarNuevaPosicionEnX(entidad.obtenerPosicionEnX() + entidad.obtenerVelocidad() * -direccion);
    }

}
