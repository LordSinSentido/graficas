
package rotacion;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculos implements Runnable {
    double [][] homogeneas = {{0, 0, 0}, {0, 0, 0}, {0, 0, 1}};
    double [][] coordenadasIniciales = {{-150, 150, 1}, {150, 150, 1}, {150, -150, 1}, {-150, -150, 1}};
    double [][] coordenadasFinales = new double [3][5];

    public double [][] getCoordenadasFinales() {
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
                for(int i = 0; i < 360; i++) {
                    homogeneas[0][0] = Math.cos(Math.toRadians(i));
                    homogeneas[0][1] = -Math.sin(Math.toRadians(i));
                    homogeneas[1][0] = Math.sin(Math.toRadians(i));
                    homogeneas[1][1] = Math.cos(Math.toRadians(i));
                    calcularCoordenadas();
                    sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(traslacion.Dibujo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
