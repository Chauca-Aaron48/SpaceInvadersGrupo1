package presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class VentanaFinDeJuego extends JFrame implements ActionListener {
    private final int puntajeDelPartida;
    private JButton reintentarBoton, botonGuardarYSalir, botonVolverAlMenu;
    private JTextField nombreDelJugador;
    private ReproductorMúsica reproductorMusica;

    public VentanaFinDeJuego(int puntajeDelJugador) {
        this.puntajeDelPartida = puntajeDelJugador;
        setTitle("Fin de Juego");
        setSize(600, 475);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panelFinDeJuego = new Pintor(this);
        panelFinDeJuego.setBounds(0, 0, getWidth(), getHeight());

        reintentarBoton = new JButton("Reintentar");
        reintentarBoton.setBounds(50, 350, 200, 50);
        reintentarBoton.addActionListener(this);
        add(reintentarBoton);


        nombreDelJugador = new JTextField();
        nombreDelJugador.setBounds(325, 300, 200, 30);
        add(nombreDelJugador);


        botonGuardarYSalir = new JButton("Guardar Puntaje");
        botonGuardarYSalir.setBounds(325, 350, 200, 50);
        botonGuardarYSalir.addActionListener(this);
        add(botonGuardarYSalir);

        botonVolverAlMenu = new JButton("Volver Al Menu");
        botonVolverAlMenu.setBounds(50, 250, 200, 50);
        botonVolverAlMenu.addActionListener(this);
        add(botonVolverAlMenu);

        add(panelFinDeJuego);
        setVisible(true);

        reproductorMusica = new ReproductorMúsica("src/Presentacion/MúsicaYSonido/SonidoFinDeJuego.wav");
        reproductorMusica.reproducir();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == botonGuardarYSalir) {
            mostrarVentanaFinal();
        } else if (e.getSource() == reintentarBoton) {
            new Escenario();
            this.dispose();
        } else if (e.getSource() == botonVolverAlMenu) {
            new Menú();
            this.dispose();
        }
    }

    private void mostrarVentanaFinal() {

        String nombre = nombreDelJugador.getText().replaceAll("\\s+", "");

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre válido.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Puntuaciones/PuntuacionesMejoresJugadores", true))) {

            writer.write(nombre + " " + puntajeDelPartida);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Nombre y puntaje guardados exitosamente");

            new Menú();
            this.dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el nombre y puntaje");
        }
    }
}

