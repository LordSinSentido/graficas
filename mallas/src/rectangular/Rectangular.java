
package rectangular;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Rectangular extends Canvas {
    BufferedImage buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    BufferedImage lienzo = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        
    int tamannoRectangulos;
    
    public Rectangular(int tamannoRectangulos) {
        this.tamannoRectangulos = tamannoRectangulos;
        setBounds(0, 0, getWidth(), getHeight());
        setVisible(true);
    }
    
    public void crearLinea(double x1, double y1, double x2, double y2, Color color, double desfaseX, double desfaseY) {
        buffer.setRGB(0, 0, color.getRGB());
        double dx = x2 - x1;
        double dy = y2 - y1;
        double steps = 0;
        
        if(Math.abs(dx) >= Math.abs(dy)) {
            steps = Math.abs(dx);
        } else {
            steps = Math.abs(dy);
        }
        
        double xinc = dx / steps;
        double yinc = dy / steps;
        
        double x = x1;
        double y = y1;
        lienzo.getGraphics().drawImage(buffer, (int) desfaseX + (int) Math.round(x), (int) desfaseY - (int) Math.round(y), this);
        
        for(int i = 1; i <= steps; i++) {
            x = x + xinc;
            y = y + yinc;
            lienzo.getGraphics().drawImage(buffer, (int) desfaseX + (int) Math.round(x), (int) desfaseY - (int) Math.round(y), this);
        }
    }

    @Override
    public void update(Graphics g) {
        double centroEnX = getWidth() / 2;
        double centroEnY = getHeight() / 2;
        
        int ejesEnX = ((int) centroEnX / tamannoRectangulos) + 1;
        int ejesEnY = ((int) centroEnY / tamannoRectangulos) + 1;
        
        int coordenadasEnX[] = new int[ejesEnX];
        int coordenadasEnY[] = new int[ejesEnY];
        
        int ejeActualEnX = 0;
        int ejeActualEnY = 0;        
        
        // Calcula las coordenadas de X en un cuadrante
        int indice = 0;
        while(ejeActualEnX < centroEnX) {
            coordenadasEnX[indice] = ejeActualEnX;
            ejeActualEnX = ejeActualEnX + tamannoRectangulos;
            indice++;
        }
        
        // Calcula las coordenadas de Y en un cuadrante
        indice = 0;
        while(ejeActualEnY < centroEnY) {
            coordenadasEnY[indice] = ejeActualEnY;
            ejeActualEnY = ejeActualEnY + tamannoRectangulos;
            indice++;
        }
        
        int columna = 0;
        int fila = 0;
        
        while(true) {
            if(fila < coordenadasEnX.length - 1) {
                // Primer cuadrante
                crearLinea(coordenadasEnX[fila], coordenadasEnY[columna], coordenadasEnX[fila + 1], coordenadasEnY[columna], Color.black, centroEnX, centroEnY);
                crearLinea(coordenadasEnX[fila], coordenadasEnY[columna], coordenadasEnX[fila], coordenadasEnY[columna + 1], Color.black, centroEnX, centroEnY);
                crearLinea(coordenadasEnX[fila + 1], coordenadasEnY[columna], coordenadasEnX[fila + 1], coordenadasEnY[columna + 1], Color.black, centroEnX, centroEnY);
                crearLinea(coordenadasEnX[fila + 1], coordenadasEnY[columna + 1], coordenadasEnX[fila], coordenadasEnY[columna + 1], Color.black, centroEnX, centroEnY);
                
                // Segundo cuadrante
                crearLinea(-coordenadasEnX[fila], coordenadasEnY[columna], -coordenadasEnX[fila + 1], coordenadasEnY[columna], Color.black, centroEnX, centroEnY);
                crearLinea(-coordenadasEnX[fila], coordenadasEnY[columna], -coordenadasEnX[fila], coordenadasEnY[columna + 1], Color.black, centroEnX, centroEnY);
                crearLinea(-coordenadasEnX[fila + 1], coordenadasEnY[columna], -coordenadasEnX[fila + 1], coordenadasEnY[columna + 1], Color.black, centroEnX, centroEnY);
                crearLinea(-coordenadasEnX[fila + 1], coordenadasEnY[columna + 1], -coordenadasEnX[fila], coordenadasEnY[columna + 1], Color.black, centroEnX, centroEnY);
                
                // Tercer cuadrante
                crearLinea(-coordenadasEnX[fila], -coordenadasEnY[columna], -coordenadasEnX[fila + 1], -coordenadasEnY[columna], Color.black, centroEnX, centroEnY);
                crearLinea(-coordenadasEnX[fila], -coordenadasEnY[columna], -coordenadasEnX[fila], -coordenadasEnY[columna + 1], Color.black, centroEnX, centroEnY);
                crearLinea(-coordenadasEnX[fila + 1], -coordenadasEnY[columna], -coordenadasEnX[fila + 1], -coordenadasEnY[columna + 1], Color.black, centroEnX, centroEnY);
                crearLinea(-coordenadasEnX[fila + 1], -coordenadasEnY[columna + 1], -coordenadasEnX[fila], -coordenadasEnY[columna + 1], Color.black, centroEnX, centroEnY);
                
                // Cuarto cuadrante
                crearLinea(coordenadasEnX[fila], -coordenadasEnY[columna], coordenadasEnX[fila + 1], -coordenadasEnY[columna], Color.black, centroEnX, centroEnY);
                crearLinea(coordenadasEnX[fila], -coordenadasEnY[columna], coordenadasEnX[fila], -coordenadasEnY[columna + 1], Color.black, centroEnX, centroEnY);
                crearLinea(coordenadasEnX[fila + 1], -coordenadasEnY[columna], coordenadasEnX[fila + 1], -coordenadasEnY[columna + 1], Color.black, centroEnX, centroEnY);
                crearLinea(coordenadasEnX[fila + 1], -coordenadasEnY[columna + 1], coordenadasEnX[fila], -coordenadasEnY[columna + 1], Color.black, centroEnX, centroEnY);
                
                fila++;
            } else if (columna < coordenadasEnY.length - 2){
                fila = 0;
                columna++;
            } else {
                break;
            }    
        }
        
        getGraphics().drawImage(lienzo, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }
    
    
}
