package logica.controlesDeSistema;

import logica.entidades.jugador.NaveJugador;
import presentacion.PanelDeJuego;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ControladorDeTeclas extends KeyAdapter {
    private NaveJugador naveJugador;
    private PanelDeJuego panel;

    public ControladorDeTeclas(NaveJugador nave, PanelDeJuego panelDeJuego) {
        this.naveJugador = nave;
        this.panel = panelDeJuego;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        teclaLiberada(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        teclaPresionada(e);
    }

    public void teclaPresionada(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (sePulsaTeclaIzquierda(tecla)) {
            naveJugador.fijarDistanciaDesplazada(-(naveJugador.obtenerVelocidad()));
        }

        if (sePulsaTeclaDerecha(tecla)) {
            naveJugador.fijarDistanciaDesplazada(naveJugador.obtenerVelocidad());
        }

        if (sePulsaBarraEspaciadora(tecla)){
            naveJugador.disparar();
        }

        if (sePulsaEscape(tecla)){
            panel.pausarJuego();
        }
    }

    private boolean sePulsaEscape(int tecla) {
        return tecla == KeyEvent.VK_ESCAPE;
    }

    private static boolean sePulsaBarraEspaciadora(int tecla) {
        return tecla == KeyEvent.VK_SPACE;
    }

    private static boolean sePulsaTeclaDerecha(int tecla) {
        return tecla == KeyEvent.VK_RIGHT || tecla == KeyEvent.VK_D;
    }

    private static boolean sePulsaTeclaIzquierda(int tecla) {
        return tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_A;
    }

    public void teclaLiberada(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_RIGHT || tecla == KeyEvent.VK_A || tecla == KeyEvent.VK_D) {
            naveJugador.fijarDistanciaDesplazada(0);
        }
    }

}
