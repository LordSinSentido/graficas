package transformaciones;

public class Figura {
    /*Parametros */
    
    protected double[][] coordenadas3D;
    protected double[][] coordenadas2D;
    protected double[] vectorDireccion;
    int factorEscala;
    
    /* Constructor */
    
    public Figura(double[][] coordenadas3D, double[] vectorDireccion, int factorEscala) {
        this.coordenadas3D = coordenadas3D;
        this.vectorDireccion = vectorDireccion;
        this.factorEscala = factorEscala;
    };
    
    /* Metodos */
    
    public double[][] trasladar(double[] puntoADesplazar) {
        double[][] coordenadasTrasladadas = new double[coordenadas3D.length][coordenadas3D[0].length];
        for(int i = 0; i < coordenadas3D.length; i++) {
            coordenadasTrasladadas[i][0] = coordenadas3D[i][0] + puntoADesplazar[0];
            coordenadasTrasladadas[i][1] = coordenadas3D[i][1] + puntoADesplazar[1];
            coordenadasTrasladadas[i][2] = coordenadas3D[i][2] + puntoADesplazar[2];
        }
        return coordenadasTrasladadas;
    }
    
    public double[][] escalar(double[] escalas) {
        double[][] coordenadasTrasladadas = new double[coordenadas3D.length][coordenadas3D[0].length];
        for(int i = 0; i < coordenadas3D.length; i++) {
            coordenadasTrasladadas[i][0] = coordenadas3D[i][0] * escalas[0];
            coordenadasTrasladadas[i][1] = coordenadas3D[i][1] * escalas[1];
            coordenadasTrasladadas[i][2] = coordenadas3D[i][2] * escalas[2];
        }
        return coordenadasTrasladadas;
    }
    
    public double[][] rotarEnX(double angulo) {
        double[][] coordenadasTrasladadas = new double[coordenadas3D.length][coordenadas3D[0].length];
        
        for(int i = 0; i < coordenadas3D.length; i++) {
            coordenadasTrasladadas[i][0] = coordenadas3D[i][0] * Math.cos(Math.toRadians(angulo)) + coordenadas3D[i][2] * Math.sin(Math.toRadians(angulo));
            coordenadasTrasladadas[i][1] = coordenadas3D[i][1];
            coordenadasTrasladadas[i][2] = -coordenadas3D[i][0] * Math.sin(Math.toRadians(angulo)) + coordenadas3D[i][2] * Math.cos(Math.toRadians(angulo));
        }
        return coordenadasTrasladadas;
    }
    
    public double[][] proyectar(double[][] coordenadasAProyectar) {
        double[][] coordenadasProyectadas = new double[coordenadas3D.length][2];
        
        for(int i = 0; i < coordenadasAProyectar.length; i++) {
            for(int j = 0; j < coordenadasAProyectar[0].length - 1; j++) {
                coordenadasProyectadas[i][j] = ( (vectorDireccion[2] * coordenadasAProyectar[i][j]  - vectorDireccion[j] * coordenadasAProyectar[i][2]) / (vectorDireccion[2] - coordenadasAProyectar[i][2]) ) * factorEscala;
            };
        };
        
        return coordenadasProyectadas;
    };
}
