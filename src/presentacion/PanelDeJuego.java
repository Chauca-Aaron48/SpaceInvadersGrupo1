package presentacion;

import logica.controlesDeSistema.ControladorDeTeclas;
import logica.enjambre.EnjambreDeCalaverasMágicas;
import logica.enjambre.EnjambreDeGatosPlatillos;
import logica.entidades.Barrera;
import logica.entidades.enemigos.*;
import logica.entidades.modificadores.*;
import logica.enjambre.Enjambre;
import logica.enjambre.EnjambreDePlatillosMalos;
import logica.entidades.jugador.NaveJugador;
import logica.proyectiles.Proyectil;
import logica.proyectiles.ProyectilDelEnemigo;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

public class PanelDeJuego extends JPanel implements ActionListener, Serializable {

    public static final int ANCHO = 800, ALTO = 600;
    private Timer temporizador;
    private NaveJugador nave;
    private Enjambre enjambre1, enjambre2, enjambre3;
    private ArrayList<NaveEnemigo> enemigos;
    private int posicioInicialDelEnemigoEnX = 50;
    private int posicioInicialDelEnemigoEnY = 50;
    private Pintor pintor;
    private ArrayList<Modificador> modificadores;
    private JFrame jFrame;
    private int numeroOleada;
    private ReproductorMúsica reproductorDeMúsica;
    private AdministradorGeneral administradorGeneral;
    private ControladorDeTeclas controladorDeTeclas;
    private PanelDeJuegoData panelDeJuegoData;
    private VentanaDePausa ventanaDePausa;
    private ArrayList<Barrera> barreras;
    private ReproductorMúsica reproductorDeExplosionJugador;
    private ReproductorMúsica reproductorDeExplosionEnemigo;


    public PanelDeJuego(JFrame jFrame) {
        this.jFrame = jFrame;
        enemigos = new ArrayList<>();
        administradorGeneral = new AdministradorGeneral(this);
        iniciarPanel();
        this.panelDeJuegoData = new PanelDeJuegoData();
        ventanaDePausa = new VentanaDePausa(this, panelDeJuegoData);
        pintor = new Pintor(this);
    }

    public PanelDeJuego(JFrame jFrame, PanelDeJuegoData panelDeJuegoData) {
        this.jFrame = jFrame;
        enemigos = new ArrayList<>();
        administradorGeneral = new AdministradorGeneral(this);
        this.panelDeJuegoData = panelDeJuegoData;
        this.cargarPartida();
        iniciarPanel();
        ventanaDePausa = new VentanaDePausa(this, panelDeJuegoData);
        pintor = new Pintor(this);
    }

    private void cargarPartida() {
        administradorGeneral.cargarPartida(panelDeJuegoData);
        enjambre1 = panelDeJuegoData.cargarEnjambre1();
        enjambre2 = panelDeJuegoData.cargarEnjambre2();
        enjambre3 = panelDeJuegoData.cargarEnjambre3();
        nave = panelDeJuegoData.cargarNaveJugador();
        numeroOleada = panelDeJuegoData.obtenerNumeroDeOleada();
        enemigos.addAll(enjambre1.obtenerEnjambreDeEnemigos());
        enemigos.addAll(enjambre2.obtenerEnjambreDeEnemigos());
        enemigos.addAll(enjambre3.obtenerEnjambreDeEnemigos());
        barreras = panelDeJuegoData.cargarBarreras();
    }


    private void iniciarPanel() {
        setFocusable(true);
        setSize(ANCHO, ALTO);
        addKeyListener(new TAdapter());
        if (nave == null) {
            nave = new NaveJugador();
            agregarEnemigos();
            numeroOleada = 0;
            barreras = new ArrayList<>();
            barreras.add( new Barrera(200, 400, 64, 24));
            barreras.add(new Barrera(500, 400, 64, 24));
        }
        temporizador = new Timer(10, this);
        temporizador.start();
        modificadores = new ArrayList<>();

        reproductorDeMúsica = new ReproductorMúsica("src/Presentacion/MúsicaYSonido/SonidoIntensoPanelJuego.wav");
        reproductorDeMúsica.bucle();
        reproductorDeExplosionJugador = new ReproductorMúsica("src/Presentacion/MúsicaYSonido/ExplosionJugador.wav");
        reproductorDeExplosionEnemigo = new ReproductorMúsica("src/Presentacion/MúsicaYSonido/ExplosionEnemigo.wav");

        controladorDeTeclas = new ControladorDeTeclas(nave, this);
    }

    public void agregarEnemigos() {

        enjambre1 = new EnjambreDePlatillosMalos(1, 10, numeroOleada, new PlatilloMalo(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY, numeroOleada));
        enjambre2 = new EnjambreDeCalaverasMágicas(2, 10, numeroOleada, new CalaveraMágica(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY, numeroOleada));
        enjambre3 = new EnjambreDeGatosPlatillos(2, 10, numeroOleada, new GatoPlatillo(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY, numeroOleada));

        enjambre1.agregarEnjambre(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY);
        enemigos.addAll(enjambre1.obtenerEnjambreDeEnemigos());

        enjambre2.agregarEnjambre(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY + 55);
        enemigos.addAll(enjambre2.obtenerEnjambreDeEnemigos());

        enjambre3.agregarEnjambre(posicioInicialDelEnemigoEnX, posicioInicialDelEnemigoEnY + 165);
        enemigos.addAll(enjambre3.obtenerEnjambreDeEnemigos());
    }

    @Override
    public void paintComponent(Graphics g) {
        pintor.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        pintor.actualizar();
        try {
            administradorGeneral.iniciarCompetencias(modificadores, nave, enemigos, barreras, enjambre1, enjambre2, enjambre3);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        administradorGeneral.verficarExistenciaEnemigos(enemigos);
        panelDeJuegoData.actualizarDatos(obtenerPosicionesEnjambreUno(), obtenerPosicionesEnjambreDos(), obtenerPosicionesEnjambreTres(),
                obtenerPosicionEnXNave(), obtenerVidasJugador(), getPuntajeTotal(), numeroOleada, barreras);

    }

    public void limpiarProyectilesDeLaVentana() {
        nave.obtenerProyectiles().clear();
        List<ProyectilDelEnemigo> proyectilesAEliminar = new ArrayList<>();
        for (NaveEnemigo enemigo : enemigos) {
            List<ProyectilDelEnemigo> proyectilesEnemigo = enemigo.obtenerProyectiles();
            proyectilesAEliminar.addAll(proyectilesEnemigo);
        }
        for (ProyectilDelEnemigo proyectil : proyectilesAEliminar) {
            proyectil.setVisible(false);
        }
        for (NaveEnemigo enemigo : enemigos) {
            enemigo.obtenerProyectiles().clear();
        }

    }

    public void pausaDeReaparicion() {
        pintor.permitirDibujarExplosion();
        temporizador.stop();
        Timer pausaParaReaparicion = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nave.volverAlPuntoDeRespawn();
                temporizador.start();
                pintor.prohibirPintarExplosion();
            }
        });
        pausaParaReaparicion.setRepeats(false);
        pausaParaReaparicion.start();
    }

    public ArrayList<int[]> obtenerPosicionesEnjambreUno() {
        ArrayList<int[]> posicionesEnjambreUno = new ArrayList<>();
        for (NaveEnemigo enemigo : enemigos) {
            if (enemigo instanceof PlatilloMalo) {
                int[] listaTemporalEnjambreUno = {enemigo.obtenerPosicionEnX(), enemigo.obtenerPosicionEnY()};
                posicionesEnjambreUno.add(listaTemporalEnjambreUno);
            }
        }
        return posicionesEnjambreUno;
    }

    public ArrayList<int[]> obtenerPosicionesEnjambreDos() {
        ArrayList<int[]> posicionesEnjambreDos = new ArrayList<>();
        for (NaveEnemigo enemigo : enemigos) {
            if (enemigo instanceof CalaveraMágica) {
                int[] listaTemporalEnjambreDos = {enemigo.obtenerPosicionEnX(), enemigo.obtenerPosicionEnY()};
                posicionesEnjambreDos.add(listaTemporalEnjambreDos);
            }
        }
        return posicionesEnjambreDos;
    }

    public ArrayList<int[]> obtenerPosicionesEnjambreTres() {
        ArrayList<int[]> posicionesEnjambreTres = new ArrayList<>();
        for (NaveEnemigo enemigo : enemigos) {
            if (enemigo instanceof GatoPlatillo) {
                int[] listaTemporalEnjambreTres = {enemigo.obtenerPosicionEnX(), enemigo.obtenerPosicionEnY()};
                posicionesEnjambreTres.add(listaTemporalEnjambreTres);
            }
        }
        return posicionesEnjambreTres;
    }

    public ArrayList<int[]> obtenerPosicionesProyectiles() {
        ArrayList<int[]> posicionesProyectilJugadores = new ArrayList<>();
        for (Proyectil proyectil : nave.obtenerProyectiles()) {
            int[] listaTemporalProyectiles = {proyectil.obtenerPosicionEnX(), proyectil.obtenerPosicionEnY()};
            posicionesProyectilJugadores.add(listaTemporalProyectiles);
        }
        return posicionesProyectilJugadores;
    }

    public ArrayList<int[]> obtenerPosicionesProyectilesEnemigos() {
        ArrayList<int[]> posicionesProyectilesEnemigos = new ArrayList<>();
        for (NaveEnemigo enemigo : enemigos) {
            for (ProyectilDelEnemigo proyectil : enemigo.obtenerProyectiles()) {
                int[] listaTemporalProyectilesEnemigos = {proyectil.obtenerPosicionEnX(), proyectil.obtenerPosicionEnY()};
                posicionesProyectilesEnemigos.add(listaTemporalProyectilesEnemigos);
            }
        }
        return posicionesProyectilesEnemigos;
    }

    public int obtenerPosicionEnXNave() {
        return nave.obtenerPosicionEnX();
    }

    public int obtenerPosicionEnYNave() {
        return nave.obtenerPosicionEnY();
    }

    public ArrayList<int[]> obtenerPosicionesModificadores() {
        ArrayList<int[]> posicionesModificadores = new ArrayList<>();
        for (Modificador modificador : modificadores) {
            posicionesModificadores.add(modificador.obtenerPosicion());
        }
        return posicionesModificadores;
    }

    public void agregarModificador(Modificador modificador) {
        if (modificador != null) {
            modificadores.add(modificador);
        }
    }

    public void detenerMusica() {
        reproductorDeMúsica.detener();
    }

    public void aumentarNumeroOleada() {
        numeroOleada++;
    }

    public int obtenerVidasJugador() {
        return nave.obtenerVidasDisponibles();
    }

    public ArrayList<Modificador> obtenerModificadores() {
        return modificadores;
    }

    public void reproducirExplosionJugador() {
        reproductorDeExplosionJugador.reproducir();
    }

    public void reproducirExplosionEnemigo() {
        reproductorDeExplosionEnemigo.reproducir();
    }

    public ArrayList<Barrera> obtenerBarreras() {
        return barreras;
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            controladorDeTeclas.teclaLiberada(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            controladorDeTeclas.teclaPresionada(e);
        }
    }

    public void pausarJuego() {
        reproductorDeMúsica.detener();
        temporizador.stop();
        add(ventanaDePausa);
        ventanaDePausa.setBounds(0, 0, ANCHO, ALTO);
        ventanaDePausa.setVisible(true);
        repaint();
    }

    public void reanudarJuego() {
        reproductorDeMúsica.reproducir();
        remove(ventanaDePausa);
        temporizador.start();
        repaint();
    }

    public JFrame getJFrame() {
        return jFrame;
    }

    public int getPuntajeTotal() {
        return administradorGeneral.obtenerPuntuaciones();
    }
}