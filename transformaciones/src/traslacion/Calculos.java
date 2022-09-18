
package traslacion;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculos implements Runnable{
    int [][] homogeneas = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
    int [][] coordenadasIniciales = {{0, 0, 1}, {50, 0, 1}, {50, 50, 1}, {0, 50, 1}};
    int [][] coordenadasFinales = new int [3][4];

    public int[][] getCoordenadasFinales() {
        return coordenadasFinales;
    }
    
    public void calcularCoordenadas() {
        for(int k = 0; k < 4; k++) {
            for(int j = 0; j < 3; j++) {
                int sumatoria = 0;
                for(int i = 0; i < 3; i++) {
                    sumatoria += homogeneas[j][i] * coordenadasIniciales[k][i];
                }
                coordenadasFinales[j][k] = sumatoria;
            }
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                for(int i = 0; i < 500; i = i + 1) {
                    homogeneas[0][2] = i;
                    homogeneas[1][2] = i;
                    calcularCoordenadas();
                    sleep(1000);
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Dibujo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
