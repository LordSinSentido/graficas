
package nave;

public class Figura {
    /*Parametros */
    
    protected double[][] coordenadas3D;
    protected double[][] coordenadasDeRelleno3D;
    protected double[][] coordenadas2D;
    protected double[][] coordenadasDeRelleno2D;
    protected boolean[][] matrizAdyacencia;
    protected boolean[][] matrizAdyacenciaContorno;
    protected double[] vectorDireccion;
    protected int factorEscala;
        
    /* Metodos */
    
    public double[][] trasladar(double[] puntoADesplazar, double[][] coordenadasADesplzar) {
        double[][] coordenadasTrasladadas = new double[coordenadasADesplzar.length][coordenadasADesplzar[0].length];
        for(int i = 0; i < coordenadasADesplzar.length; i++) {
            coordenadasTrasladadas[i][0] = coordenadasADesplzar[i][0] + puntoADesplazar[0];
            coordenadasTrasladadas[i][1] = coordenadasADesplzar[i][1] + puntoADesplazar[1];
            coordenadasTrasladadas[i][2] = coordenadasADesplzar[i][2] + puntoADesplazar[2];
        }
        return coordenadasTrasladadas;
    }
    
    public double[][] escalar(double[] escalas, double[][] coordenadasADesplzar) {
        double[][] coordenadasTrasladadas = new double[coordenadasADesplzar.length][coordenadasADesplzar[0].length];
        for(int i = 0; i < coordenadasADesplzar.length; i++) {
            coordenadasTrasladadas[i][0] = coordenadasADesplzar[i][0] * escalas[0];
            coordenadasTrasladadas[i][1] = coordenadasADesplzar[i][1] * escalas[1];
            coordenadasTrasladadas[i][2] = coordenadasADesplzar[i][2] * escalas[2];
        }
        return coordenadasTrasladadas;
    }
    
    public double[][] rotarEnX(double angulo, double[][] coordenadasADesplzar) {
        double[][] coordenadasTrasladadas = new double[coordenadasADesplzar.length][coordenadasADesplzar[0].length];
        
        for(int i = 0; i < coordenadasADesplzar.length; i++) {
            coordenadasTrasladadas[i][0] = coordenadasADesplzar[i][0];
            coordenadasTrasladadas[i][1] = coordenadasADesplzar[i][1] * Math.cos(Math.toRadians(angulo)) - coordenadasADesplzar[i][2] * Math.sin(Math.toRadians(angulo));
            coordenadasTrasladadas[i][2] = coordenadasADesplzar[i][1] * Math.sin(Math.toRadians(angulo)) + coordenadasADesplzar[i][2] * Math.cos(Math.toRadians(angulo));
        }
        return coordenadasTrasladadas;
    }
    
    public double[][] rotarEnY(double angulo, double[][] coordenadasADesplzar) {
        double[][] coordenadasTrasladadas = new double[coordenadasADesplzar.length][coordenadasADesplzar[0].length];
        
        for(int i = 0; i < coordenadasADesplzar.length; i++) {
            coordenadasTrasladadas[i][0] = coordenadasADesplzar[i][0] * Math.cos(Math.toRadians(angulo)) + coordenadasADesplzar[i][2] * Math.sin(Math.toRadians(angulo));
            coordenadasTrasladadas[i][1] = coordenadasADesplzar[i][1];
            coordenadasTrasladadas[i][2] = -coordenadasADesplzar[i][0] * Math.sin(Math.toRadians(angulo)) + coordenadasADesplzar[i][2] * Math.cos(Math.toRadians(angulo));
        }
        return coordenadasTrasladadas;
    }
    
    public double[][] rotarEnZ(double angulo, double[][] coordenadasADesplzar) {
        double[][] coordenadasTrasladadas = new double[coordenadasADesplzar.length][coordenadasADesplzar[0].length];
        
        for(int i = 0; i < coordenadasADesplzar.length; i++) {
            coordenadasTrasladadas[i][0] = coordenadasADesplzar[i][0] * Math.cos(Math.toRadians(angulo)) - coordenadasADesplzar[i][1] * Math.sin(Math.toRadians(angulo));
            coordenadasTrasladadas[i][1] = coordenadasADesplzar[i][0] * Math.sin(Math.toRadians(angulo)) + coordenadasADesplzar[i][1] * Math.cos(Math.toRadians(angulo));
            coordenadasTrasladadas[i][2] = coordenadasADesplzar[i][2];
        }
        return coordenadasTrasladadas;
    }
    
    public double[][] proyectarEnPerspectiva(double[][] coordenadasAProyectar) {
        double[][] coordenadasProyectadas = new double[coordenadasAProyectar.length][2];
        
        for(int i = 0; i < coordenadasAProyectar.length; i++) {
            for(int j = 0; j < coordenadasAProyectar[0].length - 1; j++) {
                coordenadasProyectadas[i][j] = ( (vectorDireccion[2] * coordenadasAProyectar[i][j]  - vectorDireccion[j] * coordenadasAProyectar[i][2]) / (vectorDireccion[2] - coordenadasAProyectar[i][2]) ) * factorEscala;
            }
        }
        
        return coordenadasProyectadas;
    }
    
    public double[][] proyectarEnParalelo(double[][] coordenadasAProyectar) {
        double[][] coordenadasProyectadas = new double[coordenadasAProyectar.length][2];
        
        for(int i = 0; i < coordenadasAProyectar.length; i++) {
            for(int j = 0; j < coordenadasAProyectar[0].length - 1; j++) {
                coordenadasProyectadas[i][j] = (coordenadasAProyectar[i][j] - (vectorDireccion[j] / vectorDireccion[2]) * coordenadasAProyectar[i][2]) * factorEscala;
            }
        }
        
        return coordenadasProyectadas;
    }
}
