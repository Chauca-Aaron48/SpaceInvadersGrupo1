package presentacion;

import logica.entidades.Barrera;
import logica.entidades.modificadores.*;
import logica.entidades.enemigos.NaveEnemigo;
import logica.entidades.jugador.NaveJugador;
import logica.proyectiles.Proyectil;
import logica.puntaje.Puntaje;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public class Pintor extends JPanel {


    PanelDeJuego panel;
    private VentanaPuntuaciones ventanaPuntuaciones;
    private VentanaFinDeJuego ventanaFinDeJuego;
    private Menú ventanaMenu;

    private Image fondo;
    private Image imagenNaveJugador;
    private Image imagenNaveEnemigoUno;
    private Image imagenNaveEnemigoDos;
    private Image imagenNaveEnemigoTres;
    private Image imagenProyectil;
    private Image imagenProyectilEnemigo;
    private Image imagenPuntuaciones;
    private Image imagenModificadorVidaExtra;
    private Image imagenFinDelJuego;
    private Image imagenMenu;
    private Image imagenModificadorVelocidadAumentada;
    private Image imagenModificadorVelocidadDisparoJugador;
    private Image imagenModificadorPolitecnico;
    private Image imagenExplosionJugador;
    private Image imagenBarreraCleanCode;
    private boolean seDebePintarExplosion;


    public Pintor(PanelDeJuego panel) {
        this.panel = panel;
        seDebePintarExplosion = false;
        imagenNaveJugador = new ImageIcon(Objects.requireNonNull(NaveJugador.class.getResource("/imagenesJuego/Jugador/ModeloNaveJugador.gif"))).getImage();
        imagenExplosionJugador = new ImageIcon(Objects.requireNonNull(NaveJugador.class.getResource("/imagenesJuego/Jugador/ExplosionJugador.png"))).getImage();
        imagenProyectil = new ImageIcon(Objects.requireNonNull(Proyectil.class.getResource("/imagenesJuego/Proyectiles/ProyectilJugador.png"))).getImage();
        imagenProyectilEnemigo = new ImageIcon(Objects.requireNonNull(Proyectil.class.getResource("/imagenesJuego/Proyectiles/ProyectilEnemigo.png"))).getImage();
        imagenNaveEnemigoUno = new ImageIcon(Objects.requireNonNull(NaveEnemigo.class.getResource("/imagenesJuego/Enemigos/GifEnemigoUno.gif"))).getImage();
        imagenNaveEnemigoDos = new ImageIcon(Objects.requireNonNull(NaveEnemigo.class.getResource("/imagenesJuego/Enemigos/GifEnemigoDos.gif"))).getImage();
        imagenNaveEnemigoTres = new ImageIcon(Objects.requireNonNull(NaveEnemigo.class.getResource("/imagenesJuego/Enemigos/GifEnemigoTres.gif"))).getImage();
        imagenModificadorVidaExtra = new ImageIcon(Objects.requireNonNull(Modificador.class.getResource("/imagenesJuego/Modificadores/modificadorVidaExtra.png"))).getImage();
        imagenModificadorVelocidadAumentada = new ImageIcon(Objects.requireNonNull(Modificador.class.getResource("/imagenesJuego/Modificadores/modificadorVelocidadJugador.png"))).getImage();
        imagenModificadorVelocidadDisparoJugador = new ImageIcon(Objects.requireNonNull(Modificador.class.getResource("/imagenesJuego/Modificadores/modificadorVelocidadDisparoJugador.png"))).getImage();
        imagenModificadorPolitecnico = new ImageIcon(Objects.requireNonNull(Modificador.class.getResource("/imagenesJuego/Modificadores/modificadorPolitecnico.png"))).getImage();
        imagenBarreraCleanCode = new ImageIcon(Objects.requireNonNull(Modificador.class.getResource("/imagenesJuego/Barreras/EscudoCleanCode.png"))).getImage();
        fondo = new ImageIcon(Objects.requireNonNull(PanelDeJuego.class.getResource("/imagenesJuego/Fondos/FondoEscena.gif"))).getImage();
    }

    public Pintor(VentanaPuntuaciones panelPuntuaciones) {
        imagenPuntuaciones = new ImageIcon(Objects.requireNonNull(VentanaPuntuaciones.class.getResource("/imagenesJuego/Fondos/FondoPuntuaciones.gif"))).getImage();
        this.ventanaPuntuaciones = panelPuntuaciones;
    }

    public Pintor(VentanaFinDeJuego finDeJuego) {
        imagenFinDelJuego = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenesJuego/Fondos/FondoGameOver.png"))).getImage();
        this.ventanaFinDeJuego = finDeJuego;
    }

    public Pintor(Menú menu) {
        imagenMenu = new ImageIcon(Objects.requireNonNull(Menú.class.getResource("/imagenesJuego/Fondos/FondoMenu.gif"))).getImage();
        this.ventanaMenu = menu;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (ventanaMenu != null) {
            mensajeInicial(g);
        } else if (panel != null) {
            dibujarFondo(g);
            dibujarPuntajesEnPantalla(g);
            dibujarNave(g);
            dibujarEnemigos(g);
            dibujarModificadores(g);
            dibujarProyectiles(g);
            dibujarProyectilesEnemigos(g);
            dibujarVidasEnPantalla(g);
            dibujarExplosionJugador(g);
            dibujarBarreras(g);
        } else if (ventanaPuntuaciones != null) {
            dibujarContenidoDePuntajes(g);
        } else if (ventanaFinDeJuego != null) {
            mensajeFinal(g);
        }
        Toolkit.getDefaultToolkit().sync();

    }

    private void dibujarBarreras(Graphics g) {
        for (Barrera barrera : panel.obtenerBarreras()) {
            g.drawImage(imagenBarreraCleanCode, barrera.obtenerPosicionEnX(), barrera.obtenerPosicionEnY(), this);
        }
    }

    private void dibujarPuntajesEnPantalla(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("Puntuacion: ", 25, 25);
        g.drawString(String.valueOf(panel.getPuntajeTotal()), 130, 25);
    }

    private void dibujarVidasEnPantalla(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("Vidas: ", 25, 50);
        g.drawString(String.valueOf(panel.obtenerVidasJugador()), 130, 50);
    }

    private void dibujarContenidoDePuntajes(Graphics g) {
        if (ventanaPuntuaciones == null) {
            return;
        }

        g.drawImage(imagenPuntuaciones, 0, 0, 520, 800, this);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("PUNTUACIONES", 180, 50);
        int inicioDeLosPuntajes = 100;

        List<Puntaje> puntuaciones = ventanaPuntuaciones.getPuntuaciones();
        int diezMejoresJugadores = Math.min(puntuaciones.size(), 10);


        for (int i = 0; i < diezMejoresJugadores; i++) {
            Puntaje puntuacion = puntuaciones.get(i);
            String nombre = puntuacion.getNombre();
            String puntos = String.valueOf(puntuacion.getPuntaje());
            g.drawString(nombre, 80, inicioDeLosPuntajes);
            g.drawString(puntos, getWidth() - 125, inicioDeLosPuntajes);
            inicioDeLosPuntajes += 30;
        }
    }

    private void dibujarModificadores(Graphics g) {
        ArrayList<Modificador> modificadores = panel.obtenerModificadores();
        ArrayList<int[]> posiciones = panel.obtenerPosicionesModificadores();

        for (int i = 0; i < modificadores.size(); i++) {
            Modificador modificador = modificadores.get(i);
            int[] posicion = posiciones.get(i);

            if (modificador instanceof VidaExtra) {
                g.drawImage(imagenModificadorVidaExtra, posicion[0], posicion[1], this);
            } else if (modificador instanceof VelocidadDeDisparoAumentada) {
                g.drawImage(imagenModificadorVelocidadDisparoJugador, posicion[0], posicion[1], this);
            } else if (modificador instanceof VelocidadAumentada) {
                g.drawImage(imagenModificadorVelocidadAumentada, posicion[0], posicion[1], this);
            } else if (modificador instanceof Politecnico) {
                g.drawImage(imagenModificadorPolitecnico, posicion[0], posicion[1], this);
            }
        }
    }

    private void dibujarProyectilesEnemigos(Graphics g) {
        for (int[] arregloPosiciones : panel.obtenerPosicionesProyectilesEnemigos()) {
            g.drawImage(imagenProyectilEnemigo, arregloPosiciones[0], arregloPosiciones[1], this);
        }

    }

    private void dibujarEnemigos(Graphics g) {
        dibujarEnjambreUno(g);
        dibujarEnjambreDos(g);
        dibujarEnjambreTres(g);
    }

    private void dibujarEnjambreUno(Graphics g) {
        for (int[] arregloPosiciones : panel.obtenerPosicionesEnjambreUno()) {
            g.drawImage(imagenNaveEnemigoUno, arregloPosiciones[0], arregloPosiciones[1], this);
        }
    }

    private void dibujarEnjambreDos(Graphics g) {
        for (int[] arregloPosiciones : panel.obtenerPosicionesEnjambreDos()) {
            g.drawImage(imagenNaveEnemigoDos, arregloPosiciones[0], arregloPosiciones[1], this);
        }
    }

    private void dibujarEnjambreTres(Graphics g) {
        for (int[] arregloPosiciones : panel.obtenerPosicionesEnjambreTres()) {
            g.drawImage(imagenNaveEnemigoTres, arregloPosiciones[0], arregloPosiciones[1], this);
        }
    }

    private void dibujarProyectiles(Graphics g) {
        for (int[] arregloPosiciones : panel.obtenerPosicionesProyectiles()) {
            g.drawImage(imagenProyectil, arregloPosiciones[0], arregloPosiciones[1], this);
        }
    }

    private void dibujarNave(Graphics g) {
        g.drawImage(imagenNaveJugador, panel.obtenerPosicionEnXNave() + 10, panel.obtenerPosicionEnYNave(), null);
    }

    private void dibujarExplosionJugador(Graphics g) {
        if (seDebePintarExplosion) {
            g.drawImage(imagenExplosionJugador, panel.obtenerPosicionEnXNave() + 10, panel.obtenerPosicionEnYNave(), null);
        }
    }

    public void permitirDibujarExplosion() {
        seDebePintarExplosion = true;
    }

    public void prohibirPintarExplosion() {
        seDebePintarExplosion = false;
    }

    private void dibujarFondo(Graphics g) {
        g.drawImage(fondo, 0, 0, 800, 600, null);
    }

    public void actualizar() {
        repaint();
    }

    public void mensajeInicial(Graphics g) {
        g.drawImage(imagenMenu, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 50));
        g.drawString("SPACE INVADERS", 150, 100);

    }

    private void mensajeFinal(Graphics g) {
        g.drawImage(imagenFinDelJuego, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 50));
        g.drawString("GAME OVER", 25, 180);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Britannic Bold", Font.BOLD, 20));
        g.drawString("INGRESA", 360, 280);
        g.setColor(Color.WHITE);
        g.drawString("TU NOMBRE", 445, 280);
    }


}
