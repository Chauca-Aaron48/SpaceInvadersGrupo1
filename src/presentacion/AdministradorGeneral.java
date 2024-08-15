package presentacion;

import logica.controlesDeSistema.ActualizadorEntidades;
import logica.enjambre.Enjambre;
import logica.entidades.Barrera;
import logica.entidades.modificadores.Modificador;
import logica.entidades.enemigos.NaveEnemigo;
import logica.entidades.jugador.NaveJugador;
import logica.controlesDeSistema.VerificadorDeColisiones;

import javax.swing.*;
import java.util.ArrayList;

public class AdministradorGeneral {

    private VerificadorDeColisiones verificadorDeColisiones;
    private ActualizadorEntidades actualizadorEntidades;
    private PanelDeJuego panelDeJuego;

    public AdministradorGeneral(PanelDeJuego panelDeJuego) {
        this.panelDeJuego = panelDeJuego;
        verificadorDeColisiones = new VerificadorDeColisiones(this);
        actualizadorEntidades = new ActualizadorEntidades();

    }

    public void agregarModificador(Modificador modificadores) {
        panelDeJuego.agregarModificador(modificadores);
    }


    public void detenerMusica() {
        panelDeJuego.detenerMusica();
    }

    public void limpiarProyectilesDeLaVentana() {
        panelDeJuego.limpiarProyectilesDeLaVentana();
    }

    public void activarPausaDeReaparicion() {
        panelDeJuego.pausaDeReaparicion();
    }

    public JFrame getJFrame() {
        return panelDeJuego.getJFrame();
    }

    public void iniciarCompetencias(ArrayList<Modificador> modificadores, NaveJugador naveJugador, ArrayList<NaveEnemigo> enemigos, ArrayList<Barrera> barreras, Enjambre... enjambres) throws InterruptedException {
        try {
            actualizadorEntidades.actualizarEntidades(naveJugador, enemigos, barreras, modificadores, enjambres);
            verificadorDeColisiones.verificarColisiones(naveJugador, enemigos, modificadores, barreras);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void verficarExistenciaEnemigos(ArrayList<NaveEnemigo> enemigos) {
        if (enemigos.isEmpty()) {
            panelDeJuego.agregarEnemigos();
            panelDeJuego.aumentarNumeroOleada();
        }
    }

    public int obtenerPuntuaciones() {
        return verificadorDeColisiones.getPuntajeTotal();
    }


    public void cargarPartida(PanelDeJuegoData panelDeJuegoData) {
        verificadorDeColisiones.actualizarPuntaje(panelDeJuegoData.obtenerPuntaje());
    }

    public void reproducirExplosionJugador() {
        panelDeJuego.reproducirExplosionJugador();
    }

    public void reproducirExplosionEnemigo() {
        panelDeJuego.reproducirExplosionEnemigo();
    }
}
