
package transformaciones;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rotacion extends Figura implements Runnable {
    private double[][] auxCoordenadas2D;
    
    public Rotacion(double[][] coordenadas3D, double[] vectorDireccion, int factorEscala) {
        super(coordenadas3D, vectorDireccion, factorEscala);
    }

    public double[][] getAuxCoordenadas2D() {
        return auxCoordenadas2D;
    }
    
    @Override
    public void run() {
        double angulo = 0;
        
        while(true) {
            try {
                
                for(double i = 0; i < 180; i = i + 1) {
                    auxCoordenadas2D = proyectar(rotarEnX(i));
                    sleep(17);
                }
                
                for(double i = 180; i > 0; i = i - 1) {
                    auxCoordenadas2D = proyectar(rotarEnX(i));
                    sleep(17);
                }
                
                sleep(17);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dibujo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
