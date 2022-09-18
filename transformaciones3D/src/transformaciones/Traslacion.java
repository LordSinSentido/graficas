
package transformaciones;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Traslacion extends Figura implements Runnable {
    private double[][] auxCoordenadas2D;
    
    public Traslacion(double[][] coordenadas3D, double[] vectorDireccion, int factorEscala) {
        super(coordenadas3D, vectorDireccion, factorEscala);
    }

    public double[][] getAuxCoordenadas2D() {
        return auxCoordenadas2D;
    }
    
    @Override
    public void run() {
        double[] punto = {0,0,0};
        
        while(true) {
            try {
                
                for(double i = -5; i < 5; i = i + 0.1) {
                    punto[0] = i;
                    punto[1] = i;
                    auxCoordenadas2D = proyectar(trasladar(punto));
                    sleep(17);
                }
                
                for(double i = 5; i > -5; i = i - 0.1) {
                    punto[0] = i;
                    punto[1] = i;
                    auxCoordenadas2D = proyectar(trasladar(punto));
                    sleep(17);
                }
                
                sleep(17);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dibujo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
