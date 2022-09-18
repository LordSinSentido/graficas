
package escalacion;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculos implements Runnable {
    double [][] homogeneas = {{0, 0, 0}, {0, 0, 0}, {0, 0, 1}};
    double [][] coordenadasIniciales = {{0, 0, 1}, {50, 0, 1}, {50, 50, 1}, {0, 50, 1}};
    double [][] coordenadasFinales = new double [3][4];

    public double[][] getCoordenadasFinales() {
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
                for(double i = 1; i < 9; i = i + 0.1) {
                    homogeneas[0][0] = i; // Escalamiento en X
                    homogeneas[1][1] = i; // Escalamiento en Y
                    calcularCoordenadas();
                    sleep(10);
                }
                for(double i = 9; i > 1; i = i - 0.1) {
                    homogeneas[0][0] = i; // Escalamiento en X
                    homogeneas[1][1] = i; // Escalamiento en Y
                    calcularCoordenadas();
                    sleep(10);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(traslacion.Dibujo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
