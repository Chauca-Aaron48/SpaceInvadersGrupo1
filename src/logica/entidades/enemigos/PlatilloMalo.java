package logica.entidades.enemigos;

public class PlatilloMalo extends NaveEnemigo {
    protected static final int PUNTOS_DEL_ENEMIGO = 30;

    public PlatilloMalo(int x, int y, int numeroOleada) {
        super(x, y, PUNTOS_DEL_ENEMIGO, numeroOleada);
    }

}
