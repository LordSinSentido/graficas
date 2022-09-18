
package transformaciones;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Escalacion extends Figura implements Runnable {
    private double[][] auxCoordenadas2D;
    
    public Escalacion(double[][] coordenadas3D, double[] vectorDireccion, int factorEscala) {
        super(coordenadas3D, vectorDireccion, factorEscala);
    }

    public double[][] getAuxCoordenadas2D() {
        return auxCoordenadas2D;
    }
    
    @Override
    public void run() {
        double[] punto = {1,1,1};
        
        while(true) {
            try {
                for(double i = 0.5; i < 1.5; i = i + 0.01) {
                    punto[0] = i;
                    punto[1] = i;
                    punto[2] = i;
                    auxCoordenadas2D = proyectar(escalar(punto));
                    sleep(17);
                }
                
                for(double i = 1.5; i > 0.5; i = i - 0.01) {
                    punto[0] = i;
                    punto[1] = i;
                    punto[2] = i;
                    auxCoordenadas2D = proyectar(escalar(punto));
                    sleep(17);
                }
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Dibujo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
