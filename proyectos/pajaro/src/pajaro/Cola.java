
package pajaro;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cola implements Runnable {
    double [][] homogeneas = {{0, 0, 0}, {0, 0, 0}, {0, 0, 1}};
    double [][] coordenadasIniciales = {{0, 0, 1}, {10, -180, 1}, {0, -220, 1}, {-10, -180, 1}, {0, -110, 1}, {10, -240, 1}, {-10, -240, 1}, {0, -242, 1}, {0, -230, 1}, {0, -247, 1}};
    double [][] coordenadasFinales = new double [3][10];

    public double[][] getCoordenadasFinales() {
        return coordenadasFinales;
    }
    
    public void calcularCoordenadas() {
        for(int k = 0; k < 10; k++) {
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
                for(double i = -10; i < 10; i = i + 0.25) {
                    homogeneas[0][0] = Math.cos(Math.toRadians(i));
                    homogeneas[0][1] = -Math.sin(Math.toRadians(i));
                    homogeneas[1][0] = Math.sin(Math.toRadians(i));
                    homogeneas[1][1] = Math.cos(Math.toRadians(i));
                    calcularCoordenadas();
                    sleep(17);
                }
                
                for(double i = 10; i > -10; i = i - 0.25) {
                    homogeneas[0][0] = Math.cos(Math.toRadians(i));
                    homogeneas[0][1] = -Math.sin(Math.toRadians(i));
                    homogeneas[1][0] = Math.sin(Math.toRadians(i));
                    homogeneas[1][1] = Math.cos(Math.toRadians(i));
                    calcularCoordenadas();
                    sleep(17);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Cola.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
