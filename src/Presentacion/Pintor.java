package Presentacion;

import Logica.NaveEnemigoUno;
import Logica.NaveJugador;
import Logica.Proyectil;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class Pintor extends JPanel {
    PanelDeJuego panel;
    private Image fondo;
    private Image imagenNaveJugador;
    private Image imagenNaveEnemigo;
    private Image imagenProyectil;

    public Pintor(PanelDeJuego panel) {
        this.panel = panel;
        imagenNaveJugador = new ImageIcon(Objects.requireNonNull(NaveJugador.class.getResource("/ImagenesJuego/Jugador/ModeloNaveJugador.png"))).getImage();
        imagenProyectil = new ImageIcon(Objects.requireNonNull(Proyectil.class.getResource("/ImagenesJuego/Proyectiles/ProyectilJugador.png"))).getImage();
        imagenNaveEnemigo= new ImageIcon(Objects.requireNonNull(NaveEnemigoUno.class.getResource("/ImagenesJuego/Enemigos/ImagenEnemigoUno.png"))).getImage();
        fondo = new ImageIcon(Objects.requireNonNull(PanelDeJuego.class.getResource("/ImagenesJuego/Fondos/FondoEscena.png"))).getImage();


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarFondo(g);
        dibujarNave(g);
        dibujarEnemigos(g);//dibuja a los enemigos
        dibujarProyectiles(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void dibujarEnemigos(Graphics g) {
        for(int[] arregloPosiciones: panel.obtenerPosicionesEnemigos()){
            g.drawImage(imagenNaveEnemigo, arregloPosiciones[0], arregloPosiciones[1], this);
        }
    }

    private void dibujarProyectiles(Graphics g) {
        for(int[] arregloPosiciones: panel.obtenerPosicionesProyectiles()){
            g.drawImage(imagenProyectil, arregloPosiciones[0], arregloPosiciones[1], this);
        }
    }


    private void dibujarNave(Graphics g) {
        g.drawImage(imagenNaveJugador, panel.obtenerPosicionEnXNave(), panel.obtenerPosicionEnYNave(), null);

    }


    private void dibujarFondo(Graphics g) {
        g.drawImage(fondo, 0, 0, 800, 600, null);
    }


    public void actualizar() {
        repaint();
    }
}
