
package pajaro;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Destellos implements Runnable {
    double [][] homogeneas = {{0, 0, 0}, {0, 0, 0}, {0, 0, 1}};
    double [][] coordenadasIniciales = {{0, 30, 1}, {5, 5, 1}, {30, 0, 1}, {5, -5, 1}, {0, -30, 1}, {-5, -5, 1}, {-30, 0, 1}, {-5, 5, 1}, {0, 0, 1}};
    double [][] coordenadasFinales = new double [3][coordenadasIniciales.length];

    public double[][] getCoordenadasFinales() {
        return coordenadasFinales;
    }
    
    public void calcularCoordenadas() {
        for(int k = 0; k < coordenadasIniciales.length; k++) {
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
                for(double i = 1; i < 2; i = i + 0.02) {
                    homogeneas[0][0] = i;
                    homogeneas[1][1] = i;
                    calcularCoordenadas();
                    sleep(17);
                }
                
                for(double i = 2; i > 1; i = i - 0.02) {
                    homogeneas[0][0] = i;
                    homogeneas[1][1] = i;
                    calcularCoordenadas();
                    sleep(17);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Cola.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
