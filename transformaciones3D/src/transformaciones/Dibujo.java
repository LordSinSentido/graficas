
package transformaciones;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dibujo extends Canvas implements Runnable {
    BufferedImage buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    BufferedImage lienzo;
    
    double[][] coordenadasFigura = new double[][] {{-2,-2,-2}, {-2,-2,2}, {-2,2,-2}, {-2,2,2}, {2,-2,-2}, {2,-2,2}, {2,2,-2}, {2,2,2}};
    double[] vectorDireccion = new double[] {2,4,-10};
    boolean[][] matrizAdyacencia = 
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
    
    Traslacion traslacion = new Traslacion(coordenadasFigura, vectorDireccion, 10);
    Thread trasladar = new Thread(traslacion);
    
    Escalacion escalacion = new Escalacion(coordenadasFigura, vectorDireccion, 10);
    Thread escalar = new Thread(escalacion);
    
    Rotacion rotacion = new Rotacion(coordenadasFigura, vectorDireccion, 10);
    Thread rotar = new Thread(rotacion);
    
    public Dibujo() {
        trasladar.start();
        escalar.start();
        rotar.start();
        
        setBounds(0, 0, 900, 300);
        setVisible(true);
    }
    
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
        lienzo.getGraphics().drawImage(buffer, desfaseX + (int) Math.round(x), desfaseY - (int) Math.round(y), this);
        
        for(int i = 1; i <= steps; i++) {
            x = x + xinc;
            y = y + yinc;
            lienzo.getGraphics().drawImage(buffer, desfaseX + (int) Math.round(x), desfaseY - (int) Math.round(y), this);
        };
    }
    
    @Override
    public void update(Graphics g) {
        lienzo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        /* Traslacion */
        double[][] coordenadasTraslacion = traslacion.getAuxCoordenadas2D();
        for(int i = 0; i < matrizAdyacencia.length; i++) {
            for(int j = 0; j < matrizAdyacencia.length; j++) {
                if(matrizAdyacencia[i][j] == true) {
                    crearLinea(coordenadasTraslacion[i][0], coordenadasTraslacion[i][1], coordenadasTraslacion[j][0], coordenadasTraslacion[j][1], Color.white, 150, 150);
                }
            }
        }
        
        /* Escalacion */
        double[][] coordenadasEscalacion = escalacion.getAuxCoordenadas2D();
        for(int i = 0; i < matrizAdyacencia.length; i++) {
            for(int j = 0; j < matrizAdyacencia.length; j++) {
                if(matrizAdyacencia[i][j] == true) {
                    crearLinea(coordenadasEscalacion[i][0], coordenadasEscalacion[i][1], coordenadasEscalacion[j][0], coordenadasEscalacion[j][1], Color.white, 450, 150);
                }
            }
        }
        
        /* Rotacion */
        double[][] coordenadasRotacion = rotacion.getAuxCoordenadas2D();
        for(int i = 0; i < matrizAdyacencia.length; i++) {
            for(int j = 0; j < matrizAdyacencia.length; j++) {
                if(matrizAdyacencia[i][j] == true) {
                    crearLinea(coordenadasRotacion[i][0], coordenadasRotacion[i][1], coordenadasRotacion[j][0], coordenadasRotacion[j][1], Color.white, 750, 150);
                }
            }
        }
        
        getGraphics().drawImage(lienzo, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }
    
    
    @Override
    public void run() {
        while(true) {
            try {
                repaint();
                sleep(17);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dibujo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
