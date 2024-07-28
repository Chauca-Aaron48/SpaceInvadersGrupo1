package Logica;

public class MovimientoNaveJugador implements Movimiento {
    private int posicionEnX;
    private final int posicionEnY;
    private int distanciaDesplazada;
    private final int VELOCIDAD = 4;
    private final int LIMITE_DERECHO = 785;
    private final int ANCHO_NAVE = 64;

    public MovimientoNaveJugador(int posicionInicialEnX, int posicionInicialEnY){
        this.posicionEnX = posicionInicialEnX;
        this.posicionEnY = posicionInicialEnY;
    }

    @Override
    public void mover() {
        posicionEnX += distanciaDesplazada;
        if (estaEnElLimiteIzquierdo()) {
            posicionEnX = 0;
        }
        if (estaEnElLimiteDerecho()) {
            posicionEnX = LIMITE_DERECHO - ANCHO_NAVE;
        }
    }

    private boolean estaEnElLimiteDerecho() {
        return posicionEnX < 0;
    }

    private boolean estaEnElLimiteIzquierdo() {
        return posicionEnX > LIMITE_DERECHO - ANCHO_NAVE;
    }

    @Override
    public int obtenerPosicionEnX() {
        return posicionEnX;
    }

    @Override
    public int obtenerPosicionEnY() {
        return posicionEnY;
    }

    public void fijarDistanciaDesplazada(int distanciaDesplazada){
        this.distanciaDesplazada = distanciaDesplazada;
    }
}
