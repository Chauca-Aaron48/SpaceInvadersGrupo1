package presentacion;

import logica.controlesDeSistema.GestorDePartidas;
import logica.enjambre.Enjambre;
import logica.enjambre.EnjambreDeCalaverasMágicas;
import logica.enjambre.EnjambreDeGatosPlatillos;
import logica.enjambre.EnjambreDePlatillosMalos;
import logica.entidades.Barrera;
import logica.entidades.enemigos.CalaveraMágica;
import logica.entidades.enemigos.GatoPlatillo;
import logica.entidades.enemigos.NaveEnemigo;
import logica.entidades.enemigos.PlatilloMalo;
import logica.entidades.jugador.NaveJugador;
import logica.movimiento.MovimientoEnjambre;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class PanelDeJuegoData implements Serializable {

    private ArrayList<int[]> posicionesEnjambre1;
    private ArrayList<int[]> posicionesEnjambre2;
    private ArrayList<int[]> posicionesEnjambre3;
    private int[] posicionNaveJugador;
    private int puntuacion;
    private int numeroDeVidas;
    private int numeroOleada;
    private ArrayList<Integer> numeroDeVidaDeLasBarreras;

    public PanelDeJuegoData() {
        posicionesEnjambre1 = new ArrayList<>();
        posicionesEnjambre2 = new ArrayList<>();
        posicionesEnjambre3 = new ArrayList<>();
        posicionNaveJugador = new int[2];
        numeroDeVidaDeLasBarreras = new ArrayList<>();

    }

    public void actualizarDatos(ArrayList<int[]> ints, ArrayList<int[]> ints1, ArrayList<int[]> ints2, int i, int i2, int puntajeTotal, int numeroOleada, ArrayList<Barrera> barreras) {

        posicionesEnjambre1 = ints;
        posicionesEnjambre2 = ints1;
        posicionesEnjambre3 = ints2;
        posicionNaveJugador[0] = i;
        posicionNaveJugador[1] = 500;
        puntuacion = puntajeTotal;
        numeroDeVidas = i2;
        this.numeroOleada = numeroOleada;

        for (Barrera barrera : barreras) {
            if (barrera != null) {
                numeroDeVidaDeLasBarreras.add(barrera.obtenerNumeroDeVidas());
            } else {
                numeroDeVidaDeLasBarreras.add(0);
            }
        }

    }

    public void guardarPartida() {

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre para guardar la partida:");


        if (nombre != null && !nombre.isEmpty()) {
            GestorDePartidas gestorDePartidas = new GestorDePartidas();
            gestorDePartidas.guardarPartida(this, nombre);

        }

    }

    public int obtenerPuntaje() {
        return puntuacion;
    }

    public int obtenerNumeroDeOleada() {
        return numeroOleada;
    }

    public ArrayList<NaveEnemigo> generarEnjambre1() {

        ArrayList<NaveEnemigo> enjambre = new ArrayList<>();
        for (int[] posicion : posicionesEnjambre1) {
            enjambre.add(new PlatilloMalo(posicion[0], posicion[1], numeroOleada));
        }
        return enjambre;
    }

    public ArrayList<NaveEnemigo> generarEnjambre2() {

        ArrayList<NaveEnemigo> enjambre = new ArrayList<>();
        for (int[] posicion : posicionesEnjambre2) {
            enjambre.add(new CalaveraMágica(posicion[0], posicion[1], numeroOleada));
        }
        return enjambre;
    }

    public ArrayList<NaveEnemigo> generarEnjambre3() {
        ArrayList<NaveEnemigo> enjambre = new ArrayList<>();
        for (int[] posicion : posicionesEnjambre3) {
            enjambre.add(new GatoPlatillo(posicion[0], posicion[1], numeroOleada));
        }
        return enjambre;
    }

    public NaveJugador cargarNaveJugador() {
        NaveJugador nave = new NaveJugador();
        nave.actualizarNumeroDeVidas(numeroDeVidas);
        nave.fijarNuevaPosicionEnX(posicionNaveJugador[0]);
        nave.fijarNuevaPosicionEnY(posicionNaveJugador[1]);
        return nave;
    }

    public Enjambre cargarEnjambre1() {
        Enjambre enjambre1 = new EnjambreDePlatillosMalos(1, posicionesEnjambre1.size(), numeroOleada, new PlatilloMalo(posicionesEnjambre1.getFirst()[0], posicionesEnjambre1.getFirst()[1], numeroOleada));
        enjambre1.recibirEnjambre(generarEnjambre1());
        enjambre1.recibirMovimiento(new MovimientoEnjambre(enjambre1.obtenerEnjambreDeEnemigos()));

        return enjambre1;
    }

    public Enjambre cargarEnjambre2() {
        Enjambre enjambre2 = new EnjambreDeCalaverasMágicas(2, posicionesEnjambre2.size(), numeroOleada, new CalaveraMágica(posicionesEnjambre2.getFirst()[0], posicionesEnjambre2.getFirst()[1], numeroOleada));
        enjambre2.recibirEnjambre(generarEnjambre2());
        enjambre2.recibirMovimiento(new MovimientoEnjambre(enjambre2.obtenerEnjambreDeEnemigos()));
        return enjambre2;
    }

    public Enjambre cargarEnjambre3() {
        Enjambre enjambre3 = new EnjambreDeGatosPlatillos(2, posicionesEnjambre3.size(), numeroOleada, new GatoPlatillo(posicionesEnjambre3.getFirst()[0], posicionesEnjambre3.getFirst()[1], numeroOleada));
        enjambre3.recibirEnjambre(generarEnjambre3());
        enjambre3.recibirMovimiento(new MovimientoEnjambre(enjambre3.obtenerEnjambreDeEnemigos()));
        return enjambre3;
    }

    public ArrayList<Barrera> cargarBarreras() {
        ArrayList<Barrera> barreras = new ArrayList<>();

        if (numeroDeVidaDeLasBarreras.getFirst() != 0) {
            Barrera barrera0 = new Barrera(200, 400, 64, 24);
            barreras.add(barrera0);
            barrera0.fijarVida(numeroDeVidaDeLasBarreras.getFirst());
        } else if (numeroDeVidaDeLasBarreras.get(1) != 0) {
            Barrera barrera1 = new Barrera(500, 400, 64, 24);
            barreras.add(barrera1);
            barrera1.fijarVida(numeroDeVidaDeLasBarreras.get(1));

        }
        return barreras;
    }
}
