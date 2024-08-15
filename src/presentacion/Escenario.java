package presentacion;

import javax.swing.*;

public class Escenario extends JFrame {

    public Escenario() {

        setTitle("Space Invaders");
        setSize(800, 600);// 800 y 600
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PanelDeJuego gamePanel = new PanelDeJuego(this);
        setContentPane(gamePanel);
        setVisible(true);
    }

    public Escenario(PanelDeJuegoData panelDeJuegoData) {
        setTitle("Space Invaders");
        setSize(800, 600);// 800 y 600
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PanelDeJuego gamePanel = new PanelDeJuego(this, panelDeJuegoData);
        setContentPane(gamePanel);
        setVisible(true);
    }

}
