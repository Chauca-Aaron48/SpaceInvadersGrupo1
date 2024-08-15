package logica.entidades;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Entidad {
    private int velocidad;
    private double posicionEnX;
    private int posicionEnY;
    private int ancho;
    private int alto;

    public Entidad(int posicionEnX, int posicionEnY, int velocidad, int ancho, int alto) {
        this.posicionEnX = posicionEnX;
        this.posicionEnY = posicionEnY;
        this.velocidad = velocidad;
        this.ancho = ancho;
        this.alto = alto;
    }

    public Entidad(int posicionEnX, int posicionEnY, int ancho, int alto) {
        this.posicionEnX = posicionEnX;
        this.posicionEnY = posicionEnY;
        this.ancho = ancho;
        this.alto = alto;
    }

    public int obtenerPosicionEnX() {
        return (int) posicionEnX;
    }

    public int obtenerPosicionEnY() {
        return posicionEnY;
    }

    public int obtenerVelocidad() {
        return velocidad;
    }

    public void fijarNuevaPosicionEnX(int posicionEnX) {
        this.posicionEnX = posicionEnX;
    }

    public void fijarNuevaPosicionEnY(int posicionEnY) {
        this.posicionEnY = posicionEnY;
    }

    public Rectangle obtenerHitbox() {
        return new Rectangle((int) posicionEnX, posicionEnY, ancho - 10, alto);
    }

    public int obtenerAncho() {
        return ancho;
    }

    public void actualizarVelocidad(int velocidadAumentada) {
        int velocidadTemporal = velocidad;
        velocidad = velocidadAumentada;
        Timer timer = new Timer();
        TimerTask timerDos = new TimerTask() {
            @Override
            public void run() {
                velocidad = velocidadTemporal;
            }
        };
        timer.schedule(timerDos, 5000);
    }

    public boolean estaFueraDelMapa() {
        return posicionEnY > 630 || posicionEnY < -30;
    }

}
