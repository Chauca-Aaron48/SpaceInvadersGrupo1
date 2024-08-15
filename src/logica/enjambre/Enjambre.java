package logica.enjambre;

import logica.movimiento.MovimientoEnjambre;
import logica.entidades.enemigos.NaveEnemigo;

import java.util.ArrayList;

public abstract class Enjambre {
    protected int numeroFilas;
    protected final int numeroColumnas;
    protected final NaveEnemigo enemigo;
    ArrayList<NaveEnemigo> enjambre = new ArrayList<>();
    protected int numeroFilasGenerado;
    private int numeroOleada;
    private MovimientoEnjambre movimientoEnjambre;

    public Enjambre(int numeroFilas, int numeroColumnas, NaveEnemigo enemigo, int numeroOleada) {
        this.enemigo = enemigo;
        this.numeroFilas = numeroFilas;
        this.numeroFilasGenerado = 1;
        this.numeroColumnas = numeroColumnas;
        this.numeroOleada = numeroOleada;
        this.movimientoEnjambre = new MovimientoEnjambre(enjambre);
    }

    public void agregarEnjambre(int posicionEnX, int posicionEnY) {
        generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
    }

    public void mover() {
        movimientoEnjambre.mover(enemigo);
    }

    public int obtenerNumeroDeOleada() {
        return numeroOleada;
    }

    public abstract void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY);

    public ArrayList<NaveEnemigo> obtenerEnjambreDeEnemigos() {
        return enjambre;
    }

    public void generarDisparos() {
        for (NaveEnemigo naveEnemigo : enjambre) {
            if (naveEnemigo.debeDisparar()) {
                naveEnemigo.disparar();
            }
        }
    }

    public void recibirEnjambre(ArrayList<NaveEnemigo> enjambre) {
        this.enjambre = enjambre;
    }

    public void recibirMovimiento(MovimientoEnjambre movimientoEnjambre) {
        this.movimientoEnjambre = movimientoEnjambre;
    }
}
