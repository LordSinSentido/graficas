
package relleno;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Cubo extends Figura {
    public Cubo() {
        this.coordenadas3D = new double[][] {{-6,-6,-6}, {-6,-6,6}, {-6,6,-6}, {-6,6,6}, {6,-6,-6}, {6,-6,6}, {6,6,-6}, {6,6,6}};
        this.vectorDireccion = new double[] {0,0,-20};
        this.matrizAdyacencia = new boolean[][]
        {
            {true, true, true, false, true, false, false, false},
            {true, true, false, true, false, true, false, false},
            {true, false, true, true, false, false, true, false},
            {false, true, true, true, false, false, false, true},
            {true, false, false, false, true, true, true, false},
            {false, true, false, false, true, true, false, true},
            {false, false, true, false, true, false, true, true},
            {false, false, false, true, false, true, true, true}
        };
        
        this.factorEscala = 10;
        this.coordenadasDeRelleno3D = new double[][] {{6,0,3}};
        
        double[] traslacion = {10,10,0};
        coordenadas2D = proyectarEnPerspectiva(trasladar(traslacion, coordenadas3D));
        coordenadasDeRelleno2D = proyectarEnPerspectiva(trasladar(traslacion, coordenadasDeRelleno3D));
        this.lienzo = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        crearFigura(Color.white);
        rellenar(90, 90, Color.white, 250, 250);
        rellenar(50, 90, Color.white, 250, 250);
        rellenar(90, 130, Color.white, 250, 250);
        rellenar(50, 130, Color.white, 250, 250);
        rellenar(130, 90, Color.white, 250, 250);
        rellenar(90, 50, Color.white, 250, 250);
        rellenar(130, 50, Color.white, 250, 250);
        crearFigura(Color.magenta);
    }

    public BufferedImage getLienzo() {
        return lienzo;
    }   
}
