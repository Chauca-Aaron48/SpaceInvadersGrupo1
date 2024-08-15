package logica.proyectiles;

import logica.entidades.Entidad;

public abstract class Proyectil extends Entidad {
    public static final int ANCHO_PROYECTIL = 16;
    public static final int LIMITE = 0;
    private boolean visible;

    public Proyectil(int x, int y, int velocidad) {
        super(x, y, velocidad, ANCHO_PROYECTIL, ANCHO_PROYECTIL);
        visible = true;

    }

    public void mover() {
        fijarNuevaPosicionEnY(obtenerPosicionEnY() - obtenerVelocidad());
        if (proyectilLlegoAlLimite()) {
            visible = false;
        }
    }

    private boolean proyectilLlegoAlLimite() {
        return obtenerPosicionEnY() < LIMITE;
    }


    public boolean esVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


}
