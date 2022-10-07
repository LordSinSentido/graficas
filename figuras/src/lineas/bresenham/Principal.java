package lineas.bresenham;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Principal extends JFrame {
    BufferedImage buffer;
    Graphics2D recta;
    
    public Principal() {
        setTitle("Línea de Bresenham");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        recta = (Graphics2D) buffer.createGraphics();
    }
    
    public void bresenham(int x0, int y0, int x1, int y1, Color color) {
        // En esta práctica se movió el punto de origen al centro de la ventana para comportarse como un plano cartesiano común.
        
        buffer.setRGB(0, 0, color.getRGB());
        int x = x0, y = y0;
        int dy = y1 - y0, dx = x1 - x0;
        int incXk, incYk;
        int incXr, incYr;
                
        if(dy >= 0) {
            incYk = 1;
        } else {
            dy = -dy;
            incYk = -1;
        }
        
        if(dx >= 0) {
            incXk = 1;
        } else {
            dx = -dx;
            incXk = -1;
        }
        
        if(dx >= dy) {
            incYr = 0;
            incXr = incXk;
        } else {
            incYr = incYk;
            incXr = 0;
            int aux = dx;
            dx = dy;
            dy = aux;
        }
        
        int avr = 2 * dy, av = avr - dx, avk = av - dx;
        
        do{
            this.getGraphics().drawImage(buffer, 250 + x, 250 - y, this);
            System.out.println(String.valueOf(av) + " ; " + String.valueOf(av));

            if(av >= 0) {
                x = x + incXk;
                y = y + incYk;
                av = av + avk;
            } else {
                x = x + incXr;
                y = y + incYr;
                av = av + avr;
            }
        } while (x != x1);   
    }
    
    public void paint(Graphics graphic) {
        super.paint(graphic);
        bresenham(-100,-200,200,100,Color.magenta);
    }
    
    public static void main(String[] args) {
        new Principal();
    }
}
