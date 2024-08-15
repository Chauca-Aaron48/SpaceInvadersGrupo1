package logica.enjambre;

import logica.entidades.enemigos.CalaveraMágica;
import logica.entidades.enemigos.NaveEnemigo;

public class EnjambreDeCalaverasMágicas extends Enjambre {

    public EnjambreDeCalaverasMágicas(int numeroFilas, int numeroColumnas, int numeroOleada, NaveEnemigo enemigo) {
        super(numeroFilas, numeroColumnas, enemigo, numeroOleada);
    }

    @Override
    public void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY) {
        for (int i = 0; i < numeroColumnas; i++) {
            enjambre.add(new CalaveraMágica(posicionEnX + i * 72, posicionEnY, obtenerNumeroDeOleada()));
        }
        if (numeroFilasGenerado < numeroFilas) {
            numeroFilasGenerado++;
            posicionEnY += 60;
            generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
        }
    }
}
