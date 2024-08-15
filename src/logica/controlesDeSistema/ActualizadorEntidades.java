package logica.controlesDeSistema;

import logica.enjambre.Enjambre;
import logica.entidades.Barrera;
import logica.entidades.modificadores.Modificador;
import logica.entidades.enemigos.NaveEnemigo;
import logica.entidades.jugador.NaveJugador;
import logica.proyectiles.Proyectil;
import logica.proyectiles.ProyectilDelEnemigo;
import logica.proyectiles.ProyectilDelJugador;

import java.util.ArrayList;
import java.util.List;

public class ActualizadorEntidades {

    public void actualizarEntidades(NaveJugador nave, ArrayList<NaveEnemigo> enemigos, ArrayList<Barrera> barreras, ArrayList<Modificador> modificadores, Enjambre... enjambres) throws InterruptedException {
        actualizarProyectilesDelEnemigo(enemigos);
        actualizarProyectilesDelJugador(nave);
        actualizarNave(nave);
        actualizarEnjambres(enjambres);
        actualizarModificadores(modificadores);
        actualizarBarrera(barreras);
        for (Enjambre enjambre : enjambres) {
            enjambre.generarDisparos();
        }
    }

    private void actualizarBarrera(ArrayList<Barrera> barreras) {
        ArrayList<Barrera> barrerasPorEliminar = new ArrayList<>();
        for (Barrera barrera : barreras) {
            if (barrera.obtenerNumeroDeVidas() == 0) {
                barrerasPorEliminar.add(barrera);
            }
        }
        barreras.removeAll(barrerasPorEliminar);
    }

    private void actualizarModificadores(ArrayList<Modificador> modificadores) {
        for (Modificador modificador : modificadores) {
            modificador.mover();
        }
    }

    private void actualizarEnjambres(Enjambre[] enjambres) {
        for (Enjambre enjambre : enjambres) {
            enjambre.mover();
        }
    }

    private void actualizarProyectilesDelEnemigo(ArrayList<NaveEnemigo> enemigos) {
        for (NaveEnemigo enemigo : enemigos) {
            List<ProyectilDelEnemigo> proyectiles = enemigo.obtenerProyectiles();
            for (int i = 0; i < proyectiles.size(); i++) {
                Proyectil proyectil = proyectiles.get(i);
                if (proyectil.esVisible()) {
                    proyectil.mover();
                } else {
                    proyectiles.remove(i);
                }
            }
        }
    }

    private void actualizarProyectilesDelJugador(NaveJugador nave) {
        List<ProyectilDelJugador> proyectiles = nave.obtenerProyectiles();
        for (int i = 0; i < proyectiles.size(); i++) {
            Proyectil proyectil = proyectiles.get(i);
            if (proyectil.esVisible()) {
                proyectil.mover();
            } else {
                proyectiles.remove(i);
            }
        }
    }

    private void actualizarNave(NaveJugador nave) {
        nave.mover();
    }


}


