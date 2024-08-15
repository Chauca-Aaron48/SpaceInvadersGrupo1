package logica.movimiento;

import logica.entidades.enemigos.NaveEnemigo;
import logica.entidades.Entidad;

import java.util.ArrayList;

public class MovimientoEnjambre implements Movimiento {

    public static final int LIMITE_UNIDADES_DESCENDIDAS = 16;
    public static final int POSICION_MINIMA_EN_X = 0;
    public static final int POSICION_MAXIMA_EN_X = 732;
    public static final int DIRECCION_DERECHA = 1;
    public static final int DIRECCION_IZQUIERDA = 0;
    private final ArrayList<NaveEnemigo> enjambre;
    Movimiento movimientoIzquierda;
    Movimiento movimientoDerecha;
    Movimiento movimientoAbajo;
    private int direccion;
    private boolean descendiendo;
    private int unidadesDescendidas;


    public MovimientoEnjambre(ArrayList<NaveEnemigo> enjambre) {
        this.movimientoDerecha = new MovimientoDerecha();
        this.movimientoIzquierda = new MovimientoIzquierda();
        this.movimientoAbajo = new MovimientoAbajo();
        this.direccion = DIRECCION_DERECHA;
        this.descendiendo = false;
        this.unidadesDescendidas = 0;
        this.enjambre = enjambre;
    }

    @Override
    public void mover(Entidad enemigo) {
        boolean cambiarDireccion = false;

        if (descendiendo) {
            for (NaveEnemigo naveEnemigo : enjambre) {
                movimientoAbajo.mover(naveEnemigo);
            }
            unidadesDescendidas++;
            if (unidadesDescendidas >= LIMITE_UNIDADES_DESCENDIDAS) {
                descendiendo = false;
                unidadesDescendidas = 0;
            }
            return;
        }

        for (NaveEnemigo naveEnemigo : enjambre) {
            if (direccion == DIRECCION_DERECHA) {
                movimientoIzquierda.mover(naveEnemigo);
            } else {
                movimientoDerecha.mover(naveEnemigo);
            }

            if (naveEnemigo.obtenerPosicionEnX() <= POSICION_MINIMA_EN_X || naveEnemigo.obtenerPosicionEnX() > POSICION_MAXIMA_EN_X) {
                cambiarDireccion = true;
            }
        }

        if (cambiarDireccion) {
            direccion = (direccion == DIRECCION_DERECHA) ? DIRECCION_IZQUIERDA : DIRECCION_DERECHA;
            descendiendo = true;
        }
    }

}
