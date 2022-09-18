package relleno;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Figura {
    /*Parametros */
    
    protected double[][] coordenadas3D;
    protected double[][] coordenadasDeRelleno3D;
    protected double[][] coordenadas2D;
    protected double[][] coordenadasDeRelleno2D;
    protected boolean[][] matrizAdyacencia;
    protected double[] vectorDireccion;
    protected int factorEscala;
    
    BufferedImage buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    BufferedImage lienzo;
        
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
    
    public double[][] proyectarEnPerspectiva(double[][] coordenadasAProyectar) {
        double[][] coordenadasProyectadas = new double[coordenadasAProyectar.length][2];
        
        for(int i = 0; i < coordenadasAProyectar.length; i++) {
            for(int j = 0; j < coordenadasAProyectar[0].length - 1; j++) {
                coordenadasProyectadas[i][j] = ( (vectorDireccion[2] * coordenadasAProyectar[i][j]  - vectorDireccion[j] * coordenadasAProyectar[i][2]) / (vectorDireccion[2] - coordenadasAProyectar[i][2]) ) * factorEscala;
            };
        };
        
        return coordenadasProyectadas;
    };
    
    public void crearLinea(double x1, double y1, double x2, double y2, Color color, int desfaseX, int desfaseY) {
        buffer.setRGB(0, 0, color.getRGB());
        double dx = x2 - x1;
        double dy = y2 - y1;
        double steps = 0;
        
        if(Math.abs(dx) >= Math.abs(dy)) {
            steps = Math.abs(dx);
        } else {
            steps = Math.abs(dy);
        };
        
        double xinc = dx / steps;
        double yinc = dy / steps;
        
        double x = x1;
        double y = y1;
        lienzo.getGraphics().drawImage(buffer, desfaseX + (int) Math.round(x), desfaseY - (int) Math.round(y), null);
        
        for(int i = 1; i <= steps; i++) {
            x = x + xinc;
            y = y + yinc;
            lienzo.getGraphics().drawImage(buffer, desfaseX + (int) Math.round(x), desfaseY - (int) Math.round(y), null);
        };
    }
    
    public void rellenar(double x, double y, Color color, int desfaseX, int desfaseY) {
        Color actual = new Color(lienzo.getRGB(desfaseX + (int) x, desfaseY - (int) y));

        if(actual.getRGB() != color.getRGB()) {
            buffer.setRGB(0, 0, color.getRGB());
            lienzo.getGraphics().drawImage(buffer, desfaseX + (int) x, desfaseY - (int) y, null);

            rellenar(x, y - 1, color, desfaseX, desfaseY);
            rellenar(x + 1, y, color, desfaseX, desfaseY);
            rellenar(x, y + 1, color, desfaseX, desfaseY);
            rellenar(x - 1, y, color, desfaseX, desfaseY);
        }
    }
    
    public void crearFigura(Color color) {
        for(int i = 0; i < matrizAdyacencia.length; i++) {
            for(int j = 0; j < matrizAdyacencia.length; j++) {
                if(matrizAdyacencia[i][j] == true) {
                    crearLinea(coordenadas2D[i][0], coordenadas2D[i][1], coordenadas2D[j][0], coordenadas2D[j][1], color, 250, 250);
                }
            }
        }
    }
}
