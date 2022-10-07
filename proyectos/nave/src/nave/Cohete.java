
package nave;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cohete extends Figura implements Runnable {
    double[][] curva2DA1;
    double[][] curva2DA2;
    
    double[][] curva2DB1;
    double[][] curva2DB2;
    
    double[][] parabrisas2D;
    boolean[][] matrizAdyacenciaParabrisas = new boolean[][]
    {
        {true, true, false, false},
        {false, true, true, false},
        {false, false, true, true},
        {true, false, false, true},
    };
    double[][] parabrisasRelleno2D;
    

    public Cohete() {
        this.coordenadas3D = new double[][]
        {
            {-80,20,20}, {80,20,20}, {80,20,-20}, {-80,20,-20}, // Cuadro superior
            {-80,-20,20}, {80,-20,20}, {80,-20,-20}, {-80,-20,-20}, // Cuadro inferior
            {160,0,4}, {160,0,-4}, // Pico
            {-80,8,20}, {-40,8,20}, {-80,8,28}, // Ala superior izquierda
            {-80,-8,20}, {-40,-8,20}, {-80,-8,28}, // Ala inferior izquierda
            {-80,8,-20}, {-40,8,-20}, {-80,8,-28}, // Ala superior derecha
            {-80,-8,-20}, {-40,-8,-20}, {-80,-8,-28} // Ala inferior derecha
        };
        this.matrizAdyacencia = new boolean[][]
        {
            // Cuadro superior
            {true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            
            // Cuadro inferior
            {false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            
            // Pico
            {false, true, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, true, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false},
            
            // Ala superior izquierda
            {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, true, false, true, false, false, true, false, false, false, false, false, false},
            
            // Ala inferior izquierda
            {false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false},
            
            // Ala superior derecha
            {false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, true, false, false, true},
            
            // Ala inferior derecha
            {false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, true}
        };
        this.matrizAdyacenciaContorno = new boolean[][]
            {
                // Cuadro superior
                {true, true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, true, true, false, false, true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, true, true, false, false, true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false},
                {true, false, false, true, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false},

                // Cuadro inferior
                {true, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, true, false, false, false, true, true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, true, false, false, false, true, true, false, true, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, true, true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false},

                // Pico
                {false, true, false, false, false, true, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, true, false, false, false, true, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false},

                // Ala superior izquierda
                {false, false, false, false, false, false, false, false, false, false, true, true, false, true, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, true, true, false, true, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, true, false, true, false, false, true, false, false, false, false, false, false},

                // Ala inferior izquierda
                {false, false, false, false, false, false, false, false, false, false, true, false, false, true, true, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, true, false, false, true, true, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, true, false, false, false, false, false, false},

                // Ala superior derecha
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, true, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, true, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, true, false, false, true},

                // Ala inferior derecha
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, true, true, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, true, true},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, true}

            };
        
        this.vectorDireccion = new double[] {-100,750,-500};
        this.factorEscala = 1;
        this.coordenadasDeRelleno3D = new double[][] {{0,0,0}, {-79,0,0}, {-79,-8,-27}, {-78,8,27}, {-79,-19,-19}, {-79,19,19}};
    }

    public double[][] getCoordenadas2D() {
        return coordenadas2D;
    }

    public double[][] getCoordenadasDeRelleno2D() {
        return coordenadasDeRelleno2D;
    }

    public double[][] getCurva2DA1() {
        return curva2DA1;
    }

    public double[][] getCurva2DA2() {
        return curva2DA2;
    }

    public double[][] getCurva2DB1() {
        return curva2DB1;
    }

    public double[][] getCurva2DB2() {
        return curva2DB2;
    }

    public double[][] getParabrisas2D() {
        return parabrisas2D;
    }

    public boolean[][] getMatrizAdyacenciaParabrisas() {
        return matrizAdyacenciaParabrisas;
    }

    public double[][] getParabrisasRelleno2D() {
        return parabrisasRelleno2D;
    }

    public boolean[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public boolean[][] getMatrizAdyacenciaContorno() {
        return matrizAdyacenciaContorno;
    }

    @Override
    public void run() {
        double[][] curva = new double[2000][3];
        double[][] parabrisas = new double[][] {{80,20,20}, {160,0,4}, {160,0,-4}, {80,20,-20}};
        double[][] coordenadasDeRellenoParabrisas3D = new double[][] {{120,10,0}};
        
        double[] traslacion = {0,0,0};
        double[] traslacionA = {-60,0,24};
        double[] traslacionB = {-60,0,-24};
        
        int angulo = 0;
        boolean acelerar = true;
        
        for(int i = 0; i < curva.length; i++) {
            curva[i][2] = 0.005 * i * Math.cos(Math.toRadians(i));
            curva[i][1] = 0.005 * i * Math.sin(Math.toRadians(i));
            curva[i][0] = 0.08 * i;
        }
        
        while(true) {
            try {
                coordenadas2D = proyectarEnPerspectiva(trasladar(traslacion, coordenadas3D));
                coordenadasDeRelleno2D = proyectarEnPerspectiva(trasladar(traslacion, coordenadasDeRelleno3D));
                
                curva2DA1 = proyectarEnPerspectiva(trasladar(traslacionA, trasladar(traslacion, rotarEnX(-angulo, rotarEnY(180, curva)))));
                curva2DA2 = proyectarEnPerspectiva(trasladar(traslacionA, trasladar(traslacion, rotarEnX(-angulo + 180, rotarEnY(180, curva)))));
                
                curva2DB1 = proyectarEnPerspectiva(trasladar(traslacionB, trasladar(traslacion, rotarEnX(angulo, rotarEnY(180, curva)))));
                curva2DB2 = proyectarEnPerspectiva(trasladar(traslacionB, trasladar(traslacion, rotarEnX(angulo + 180, rotarEnY(180, curva)))));
                
                parabrisas2D = proyectarEnPerspectiva(trasladar(traslacion, parabrisas));
                parabrisasRelleno2D = proyectarEnPerspectiva(trasladar(traslacion, coordenadasDeRellenoParabrisas3D));
                
                if(angulo < 360) {
                    angulo++;
                } else {
                    angulo = 0;
                }
                
                if(acelerar) {
                    if(traslacion[0] < 300) {
                        traslacion[0] = traslacion[0] + 1.5;
                    } else {
                        acelerar = false;
                    }
                } else {
                    if(traslacion[0] > -400) {
                        traslacion[0] = traslacion[0] - 2.5;
                    } else {
                        acelerar = true;
                    }
                }
                
                sleep(17);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dibujo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
