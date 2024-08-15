package logica.controlesDeSistema;

import logica.entidades.Barrera;
import logica.entidades.modificadores.Modificador;
import logica.entidades.enemigos.NaveEnemigo;
import logica.entidades.jugador.NaveJugador;
import logica.entidades.modificadores.Politecnico;
import logica.proyectiles.Proyectil;
import logica.proyectiles.ProyectilDelEnemigo;
import logica.proyectiles.ProyectilDelJugador;
import presentacion.AdministradorGeneral;
import presentacion.VentanaFinDeJuego;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VerificadorDeColisiones {
    private AdministradorGeneral administrador;
    private boolean ventanaFinDeJuegoAbierta = false;
    public int puntajeTotal;

    public VerificadorDeColisiones(AdministradorGeneral administrador) {
        this.administrador = administrador;
        puntajeTotal = 0;
    }

    public void verificarColisiones(NaveJugador nave, ArrayList<NaveEnemigo> enemigos, ArrayList<Modificador> modificadores, ArrayList<Barrera> barreras) {
        Rectangle hitboxNave = nave.obtenerHitbox();
        java.util.List<ProyectilDelJugador> proyectiles = nave.obtenerProyectiles();

        verificarColisionesEnemigosYProyectilesDelJugador(proyectiles, enemigos);
        verificarColisionesJugadorYProyectilEnemigo(hitboxNave, enemigos, nave);
        verificarColisionesProyectilYProyectilEnemigo(proyectiles, enemigos);
        verificarColisionesJugadorModificador(nave, modificadores, enemigos);
        verificarColisionEnemigoConLimiteInferior(enemigos);
        verificarColisionJugadorYNavesEnemigas(hitboxNave, enemigos);
        verificarColisionesProyectilesBarreras(barreras, nave, enemigos);

        verificarEntidadFueraDelMapa(nave, enemigos, modificadores);

    }

    private void verificarEntidadFueraDelMapa(NaveJugador nave, ArrayList<NaveEnemigo> enemigos, ArrayList<Modificador> modificadores) {
        ArrayList<Proyectil> proyectilesAEliminar = new ArrayList<>();
        for (Proyectil proyectil : nave.obtenerProyectiles()) {
            if (proyectil.estaFueraDelMapa()) {
                proyectilesAEliminar.add(proyectil);
            }
        }
        nave.obtenerProyectiles().removeAll(proyectilesAEliminar);


        for (NaveEnemigo naveEnemigo : enemigos) {
            ArrayList<Proyectil> proyectilesAEliminarEnemigos = new ArrayList<>();
            for (Proyectil proyectil : naveEnemigo.obtenerProyectiles()) {
                if (proyectil.estaFueraDelMapa()) {
                    proyectilesAEliminarEnemigos.add(proyectil);
                }
            }
            naveEnemigo.obtenerProyectiles().removeAll(proyectilesAEliminarEnemigos);
        }

        ArrayList<Modificador> modificadoresAEliminar = new ArrayList<>();
        for (Modificador modificador : modificadores) {
            if (modificador.estaFueraDelMapa()) {
                modificadoresAEliminar.add(modificador);
            }
        }
        modificadores.removeAll(modificadoresAEliminar);
    }

    private void verificarColisionesProyectilesBarreras(ArrayList<Barrera> barreras, NaveJugador naveJugador, ArrayList<NaveEnemigo> enemigos) {
        for (NaveEnemigo naveEnemigo : enemigos) {
            ArrayList<Proyectil> proyectilesAEliminar = new ArrayList<>();
            for (Proyectil proyectil : naveEnemigo.obtenerProyectiles()) {

                for (Barrera barrera : barreras) {
                    if (proyectil.obtenerHitbox().intersects(barrera.obtenerHitbox())) {
                        barrera.reducirVida();
                        proyectil.setVisible(false);
                        proyectilesAEliminar.add(proyectil);
                    }
                }
            }
            naveEnemigo.obtenerProyectiles().removeAll(proyectilesAEliminar);
        }

        ArrayList<Proyectil> proyectilesAEliminarJugador = new ArrayList<>();
        for (Proyectil proyectil : naveJugador.obtenerProyectiles()) {
            for (Barrera barrera : barreras) {
                if (proyectil.obtenerHitbox().intersects(barrera.obtenerHitbox())) {
                    barrera.reducirVida();
                    proyectil.setVisible(false);
                    proyectilesAEliminarJugador.add(proyectil);
                }
            }
        }
        naveJugador.obtenerProyectiles().removeAll(proyectilesAEliminarJugador);
    }

    private void verificarColisionEnemigoConLimiteInferior(ArrayList<NaveEnemigo> enemigos) {
        for (NaveEnemigo enemigo : enemigos) {
            if (llegoAlLimiteInferior(enemigo)) {
                generarVentanaFinDelJuego(puntajeTotal);
            }
        }
    }

    private void verificarColisionesJugadorModificador(NaveJugador nave, ArrayList<Modificador> modificadores, ArrayList<NaveEnemigo> enemigos) {

        ArrayList<Modificador> modificadoresPorEliminar = new ArrayList<>();
        for (Modificador modificador : modificadores) {
            if (modificador.obtenerHitbox().intersects(nave.obtenerHitbox())) {
                if (modificador instanceof Politecnico) {
                    for (NaveEnemigo enemigo : enemigos) {
                        enemigo.aplicarModificador(modificador);
                    }
                }
                nave.aplicarModificador(modificador);
                modificadoresPorEliminar.add(modificador);
                modificador.setVisible(false);
            }
        }
        modificadores.removeAll(modificadoresPorEliminar);
    }

    private void verificarColisionesEnemigosYProyectilesDelJugador(List<ProyectilDelJugador> proyectiles, List<NaveEnemigo> enemigos) {
        List<NaveEnemigo> enemigosAEliminar = new ArrayList<>();//almacena a los enemigos a eliminar
        for (Proyectil proyectil : proyectiles) {
            Rectangle hitboxProyectil = proyectil.obtenerHitbox();
            for (int i = 0; i < enemigos.size(); i++) {
                NaveEnemigo enemigo = enemigos.get(i);
                Rectangle hitboxEnemigo = enemigo.obtenerHitBox();
                if (hitboxProyectil.intersects(hitboxEnemigo)) {
                    administrador.reproducirExplosionEnemigo();
                    proyectil.setVisible(false);
                    puntajeTotal += enemigo.getPuntosDelEnemigo();
                    enemigosAEliminar.add(enemigo);//añade al enemigo a la lista
                    administrador.agregarModificador(enemigo.generarModificador());
                }
            }
        }
        enemigos.removeAll(enemigosAEliminar);//elimina a los enemigos en la lista
    }

    private void verificarColisionesJugadorYProyectilEnemigo(Rectangle hitboxNave, List<NaveEnemigo> enemigos, NaveJugador nave) {
        for (NaveEnemigo enemigo : enemigos) {
            List<ProyectilDelEnemigo> proyectilesEnemigo = enemigo.obtenerProyectiles();
            for (ProyectilDelEnemigo proyectilEnemigo : proyectilesEnemigo) {
                Rectangle hitboxProyectilEnemigo = proyectilEnemigo.obtenerHitbox();
                if (hitboxNave.intersects(hitboxProyectilEnemigo)) {
                    administrador.limpiarProyectilesDeLaVentana();
                    administrador.activarPausaDeReaparicion();
                    administrador.reproducirExplosionJugador();
                    nave.numeroDeVidas--;
                    if (nave.numeroDeVidas == 0) {
                        generarVentanaFinDelJuego(getPuntajeTotal());
                        return;
                    }
                    return;
                }
            }
        }
    }

    private void verificarColisionJugadorYNavesEnemigas(Rectangle hitboxNave, List<NaveEnemigo> enemigos) {
        for (NaveEnemigo enemigo : enemigos) {
            Rectangle hitboxEnemigo = enemigo.obtenerHitBox();
            if (hitboxNave.intersects(hitboxEnemigo)) {
                administrador.detenerMusica();
                generarVentanaFinDelJuego(puntajeTotal);
                return;
            }
        }
    }

    public void generarVentanaFinDelJuego(int puntajeTotal) {
        if (!ventanaFinDeJuegoAbierta) {
            administrador.detenerMusica();
            ventanaFinDeJuegoAbierta = true;
            administrador.getJFrame().dispose();
            new VentanaFinDeJuego(puntajeTotal);
        }
    }

    private void verificarColisionesProyectilYProyectilEnemigo(List<ProyectilDelJugador> proyectiles, ArrayList<NaveEnemigo> enemigos) {
        ArrayList<Proyectil> proyectilesPorEliminarJugador = new ArrayList<Proyectil>();
        ArrayList<Proyectil> proyectilesPorEliminarEnemigo = new ArrayList<Proyectil>();

        for (NaveEnemigo enemigo : enemigos) {
            List<ProyectilDelEnemigo> proyectilesEnemigo = enemigo.obtenerProyectiles();
            for (ProyectilDelEnemigo proyectilEnemigo : proyectilesEnemigo) {
                Rectangle hitboxProyectilEnemigo = proyectilEnemigo.obtenerHitbox();
                for (ProyectilDelJugador proyectilJugador : proyectiles) {
                    if (hitboxProyectilEnemigo.intersects(proyectilJugador.obtenerHitbox())) {
                        proyectilJugador.setVisible(false);
                        proyectilesPorEliminarJugador.add(proyectilJugador);
                        proyectilEnemigo.setVisible(false);
                        proyectilesPorEliminarEnemigo.add(proyectilEnemigo);
                    }
                }
            }
            enemigo.obtenerProyectiles().removeAll(proyectilesPorEliminarJugador);
        }
        proyectiles.removeAll(proyectilesPorEliminarJugador);

    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    private boolean llegoAlLimiteInferior(NaveEnemigo enemigo) {
        return enemigo.obtenerPosicionEnY() > 600 - enemigo.obtenerAncho() * 2;
    }

    public void actualizarPuntaje(int i) {
        puntajeTotal = i;
    }
}
