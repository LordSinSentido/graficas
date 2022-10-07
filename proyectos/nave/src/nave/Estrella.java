
package nave;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Estrella extends Figura implements Runnable {
    double inicio;
    
    public Estrella(double inicio) {
        this.coordenadas3D = new double[][]
        {
            {0,12,10}, {4,5,10}, {11,3,10}, {6,-3,10}, {7,-10,10}, {0,-7,10}, {-7,-10,10}, {-6,-2,10}, {-11,3,10}, {-4,5,10},
            {0,12,-10}, {4,5,-10}, {11,3,-10}, {6,-3,-10}, {7,-10,-10}, {0,-7,-10}, {-7,-10,-10}, {-6,-3,-10}, {-11,3,-10}, {-4,5,-10}
        };
        this.matrizAdyacencia = new boolean[][]
        {
            {true, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false},
            {false, true, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false},
            {false, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false},
            {false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false},
            {false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false},
            {false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false, false},
            {false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false},
            {false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, false},
            {false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false},
            {true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, true},
            
            {true, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false},
            {false, true, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false},
            {false, false, true, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false},
            {false, false, false, true, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false},
            {false, false, false, false, true, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false},
            {false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, true, true, false, false, false},
            {false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, true, true, false, false},
            {false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, true, true, false},
            {false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, true, true},
            {false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true},
        };
        this.matrizAdyacenciaContorno = new boolean[][]
        {
            {true, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false},
            {false, true, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false},
            {false, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false},
            {false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false},
            {false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false},
            {false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false, false},
            {false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false},
            {false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, false},
            {false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false},
            {true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, true},
            
            {true, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false},
            {false, true, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false},
            {false, false, true, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false},
            {false, false, false, true, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false},
            {false, false, false, false, true, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false},
            {false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, true, true, false, false, false},
            {false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, true, true, false, false},
            {false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, true, true, false},
            {false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, true, true},
            {false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true},
        };
        this.vectorDireccion = new double[] {-2,-1,-3};
        this.factorEscala = 1;
        this.coordenadasDeRelleno3D = new double[][] {{0,11,-9}, {10,3,-9}, {5,-9,-9}, {-10,3,-9}, {-5,-9,-9}};
        this.inicio = inicio;
        
    }

    public double[][] getCoordenadas2D() {
        return coordenadas2D;
    }

    public double[][] getCoordenadasDeRelleno2D() {
        return coordenadasDeRelleno2D;
    }

    public boolean[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }   

    @Override
    public void run() {
        double[] traslacion = {0,0,0};
        double[] escalacion = {1,1,1};
        double angulo = 0;
        boolean crecer = true;
        
        traslacion[0] = inicio;
        escalacion[0] = Math.abs(inicio / 500) + 1;
        angulo = inicio * 5;
        
        while(true) {
            try {
                coordenadas2D = proyectarEnParalelo(trasladar(traslacion, escalar(escalacion, rotarEnZ(angulo, coordenadas3D))));
                
                coordenadasDeRelleno2D = proyectarEnParalelo(trasladar(traslacion, escalar(escalacion, rotarEnZ(angulo, coordenadasDeRelleno3D))));

                if(traslacion[0] > -700) {
                    traslacion[0] = traslacion[0] - 2.5;
                    angulo = angulo - 2.5;
                } else {
                    traslacion[0] = 700;
                    angulo = angulo - 300;
                }

                if(crecer) {
                    if(escalacion[0] < 2) {
                        escalacion[0] = escalacion[0] + 0.01;
                        escalacion[1] = escalacion[0];
                    } else {
                        crecer = false;
                    }
                } else {
                    if(escalacion[0] > 1) {
                        escalacion[0] = escalacion[0] - 0.01;
                        escalacion[1] = escalacion[0];
                    } else {
                        crecer = true;
                    }
                }

                sleep(17);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dibujo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
