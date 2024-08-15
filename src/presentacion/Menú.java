package presentacion;

import logica.controlesDeSistema.NoExisteLaPartidaGuardadaException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menú extends JFrame implements ActionListener {
    private final ReproductorMúsica reproductorDeMúsica;
    private JButton botonIniciarJuego, botonSalir, botonPuntuaciones, botonCargarPartida;
    public static final int ANCHO = 768, ALTO = 432;


    public Menú() {
        JPanel panel = new Pintor(this);

        setTitle("SpaceInvaders");
        setSize(ANCHO, ALTO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        botonIniciarJuego = new JButton("Iniciar Juego");
        botonIniciarJuego.setBounds(300, 190, 150, 40);
        botonIniciarJuego.addActionListener(this);
        panel.add(botonIniciarJuego);


        botonPuntuaciones = new JButton("puntuaciones");
        botonPuntuaciones.setBounds(300, 240, 150, 40);
        botonPuntuaciones.addActionListener(this);
        panel.add(botonPuntuaciones);

        botonCargarPartida = new JButton("Cargar Partida");
        botonCargarPartida.setBounds(300, 290, 150, 40);
        botonCargarPartida.addActionListener(this);
        panel.add(botonCargarPartida);

        botonSalir = new JButton("Salir");
        botonSalir.setBounds(300, 340, 150, 40);
        botonSalir.addActionListener(this);
        panel.add(botonSalir);

        add(panel);
        panel.setLayout(null);
        setVisible(true);

        reproductorDeMúsica = new ReproductorMúsica("src/Presentacion/MúsicaYSonido/MusicaMenuJuego.wav");
        reproductorDeMúsica.bucle();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == botonIniciarJuego) {
            abrirVentanaJuego();
        }
        if (e.getSource() == botonPuntuaciones) {
            new VentanaPuntuaciones();
        }
        if (e.getSource() == botonCargarPartida) {
            try {
                new VentanaPartidaGuardada();
            } catch (NoExisteLaPartidaGuardadaException ex) {
                throw new RuntimeException("No se pudo cargar la partida");
            }
        }
        if (e.getSource() == botonSalir) {
            System.exit(0);
        }

    }

    private void abrirVentanaJuego() {
        reproductorDeMúsica.detener();
        new Escenario();
        this.dispose();
    }

}


