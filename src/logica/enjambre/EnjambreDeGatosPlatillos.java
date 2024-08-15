package logica.enjambre;

import logica.entidades.enemigos.GatoPlatillo;
import logica.entidades.enemigos.NaveEnemigo;

public class EnjambreDeGatosPlatillos extends Enjambre {
    public EnjambreDeGatosPlatillos(int numeroFilas, int numeroColumnas, int numeroOleada, NaveEnemigo enemigo) {
        super(numeroFilas, numeroColumnas, enemigo, numeroOleada);
    }

    @Override
    public void generarEnemigosDelEnjambre(int posicionEnX, int posicionEnY) {
        for (int i = 0; i < numeroColumnas; i++) {
            enjambre.add(new GatoPlatillo(posicionEnX + i * 72, posicionEnY, obtenerNumeroDeOleada()));
        }
        if (numeroFilasGenerado < numeroFilas) {
            numeroFilasGenerado++;
            posicionEnY += 60;
            generarEnemigosDelEnjambre(posicionEnX, posicionEnY);
        }
    }
}
