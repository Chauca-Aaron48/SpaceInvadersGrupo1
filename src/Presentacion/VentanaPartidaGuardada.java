package Presentacion;

import Logica.ControlesDeSistema.GestorDePartidas;
import Logica.ControlesDeSistema.NoExisteLaPartidaGuardadaException;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class VentanaPartidaGuardada extends JFrame {
    public static final int ANCHO_VENTANA = 800;
    public static final int ALTO_VENTANA = 600;
    private GestorDePartidas gestionDePartidas;
    private PanelDeJuegoData panelDeJuegoData;

    public VentanaPartidaGuardada() throws NoExisteLaPartidaGuardadaException {
        gestionDePartidas = new GestorDePartidas();

        setTitle("Menú guardar partida");
        setSize(ANCHO_VENTANA, ALTO_VENTANA);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panelDeJuegoData = obtenerPartidaGuardada();
        new Escenario(panelDeJuegoData);
    }

    private PanelDeJuegoData obtenerPartidaGuardada() throws NoExisteLaPartidaGuardadaException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la partida guardada:");
        if (nombre != null && !nombre.isEmpty()) {
            try {
                return gestionDePartidas.cargarPartida(nombre);
            } catch (NoExisteLaPartidaGuardadaException e) {
                throw new NoExisteLaPartidaGuardadaException("No existe el archivo");
            }
        } else {
            return null;
        }
    }

    public PanelDeJuegoData obtenerPanelDeJuegoGuardado() {
        return this.panelDeJuegoData;
    }


}
