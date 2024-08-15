package logica.entidades.jugador;

import logica.entidades.modificadores.Modificador;
import logica.entidades.modificadores.VelocidadDeDisparoAumentada;
import logica.movimiento.MovimientoNaveJugador;
import logica.entidades.Nave;
import logica.proyectiles.ProyectilDelJugador;
import presentacion.ReproductorMúsica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class NaveJugador extends Nave {

    public static final int ANCHO_NAVE = 64;
    public int numeroDeVidas;
    private MovimientoNaveJugador movimiento;
    private final List<ProyectilDelJugador> proyectiles;
    private boolean puedeDisparar;
    private Timer temporizadorDisparo;
    private ReproductorMúsica sonidoDisparo;
    private int velocidadDisparo;
    private int velocidadDisparoInicial = 500;
    private java.util.Timer timer;

    public NaveJugador() {
        super(350, 500, 4, 64, 64);
        proyectiles = new ArrayList<>();
        movimiento = new MovimientoNaveJugador();
        numeroDeVidas = 3;
        velocidadDisparo = velocidadDisparoInicial;
        timer = new java.util.Timer();
        iniciarNave();
    }

    private void iniciarNave() {
        puedeDisparar = true;
        sonidoDisparo = new ReproductorMúsica("src/Presentacion/MúsicaYSonido/DisparoNave.wav");
        actualizarTemporizador();
    }

    private void actualizarTemporizador() {
        temporizadorDisparo = new Timer(velocidadDisparo, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puedeDisparar = true;
                temporizadorDisparo.stop();
            }
        });
    }

    public void mover() {
        movimiento.mover(this);
    }

    public void fijarDistanciaDesplazada(int distanciaDesplazada) {
        movimiento.fijarDistanciaDesplazada(distanciaDesplazada);
    }

    public void aplicarModificador(Modificador modificador) {
        //numeroDeVidas += 1;
        modificador.aplicarEfecto(this);
        if (modificador instanceof VelocidadDeDisparoAumentada) {
            Timer timer = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    restablecerVelocidadDeDisparo();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public List<ProyectilDelJugador> obtenerProyectiles() {
        return proyectiles;
    }

    public void disparar() {
        if (puedeDisparar) {
            proyectiles.add(new ProyectilDelJugador(obtenerPosicionEnX() + (ANCHO_NAVE / 2) - 8, obtenerPosicionEnY(), 10));
            sonidoDisparo.reproducir();
            puedeDisparar = false;
            temporizadorDisparo.setDelay(velocidadDisparo);
            temporizadorDisparo.start();
        }
    }

    public void volverAlPuntoDeRespawn() {
        this.fijarNuevaPosicionEnX(350);
    }

    public int obtenerVidasDisponibles() {
        return numeroDeVidas;
    }

    public void aumentarVelocidadDeDisparo(int velocidadAumentada) {
        this.velocidadDisparo = velocidadAumentada;
        actualizarValores();
    }

    public void restablecerVelocidadDeDisparo() {
        this.velocidadDisparo = velocidadDisparoInicial;
        actualizarValores();
    }

    public void actualizarValores() {
        if (temporizadorDisparo != null) {
            temporizadorDisparo.setDelay(velocidadDisparo);
            actualizarTemporizador();
        }
    }

    public void actualizarNumeroDeVidas(int numeroDeVidasActualizado) {
        numeroDeVidas = numeroDeVidasActualizado;
    }
}
