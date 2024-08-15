package logica.puntaje;

import java.util.Comparator;

public class ComparadorDePuntajes implements Comparator<Puntaje> {

    @Override
    public int compare(Puntaje puntajeUno, Puntaje puntajeDos) {
        return puntajeDos.getPuntaje() - puntajeUno.getPuntaje();
    }
}
