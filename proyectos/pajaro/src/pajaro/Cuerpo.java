
package pajaro;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cuerpo implements Runnable {
    double [][] homogeneas = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
    double [][] coordenadasIniciales = {{-25, 70, 1}, {0, -10, 1}, {-30, -15, 1}, {-65, 40, 1}, {30, -10, 1}, {25, 65, 1}, {-25, 100, 1}, {0, -10, 1}, {10, 20, 1}, {-25, 80, 1}, {-45, 125, 1}, {10, 150, 1}, {24, 90, 1}, {10, 100, 1}, {-70, 100, 1}, {-70, 145, 1}, {-60, 120, 1}, {-30, 145, 1}, {-50, 150, 1}, {-40, 30, 1}, {-40, -15, 1}, {-70, -25, 1}, {-30, -25, 1}, {-35, -20, 1}};
    double [][] coordenadasFinales = new double [3][coordenadasIniciales.length];
    double mov = 0;

    public double[][] getCoordenadasFinales() {
        return coordenadasFinales;
    }

    public double getMov() {
        return mov;
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
                for(mov = 0; mov < 5; mov = mov + 0.25) {
                    homogeneas[0][2] = 0;
                    homogeneas[1][2] = mov;
                    calcularCoordenadas();
                    sleep(17);
                }
                
                for(mov = 5; mov > 0; mov = mov - 0.5) {
                    homogeneas[0][2] = 0;
                    homogeneas[1][2] = mov;
                    calcularCoordenadas();
                    sleep(17);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Cola.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
