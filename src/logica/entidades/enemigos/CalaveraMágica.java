package logica.entidades.enemigos;

public class CalaveraMágica extends NaveEnemigo {
    protected static final int PUNTOS_DEL_ENEMIGO = 20;

    public CalaveraMágica(int x, int y, int numeroOleada) {
        super(x, y, PUNTOS_DEL_ENEMIGO, numeroOleada);
    }


}
