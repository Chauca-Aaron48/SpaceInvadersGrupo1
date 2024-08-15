package logica.entidades;

import logica.entidades.modificadores.Modificador;

import java.awt.*;


public abstract class Nave extends Entidad {
    public Nave(int posicionEnX, int posicionEnY, int velocidad, int ancho, int alto) {
        super(posicionEnX, posicionEnY, velocidad, ancho, alto);
    }

    protected abstract void disparar();

    public Rectangle obtenerHitBox() {
        return super.obtenerHitbox();
    }

    public abstract void aplicarModificador(Modificador modificador);

}