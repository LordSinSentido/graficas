package lineas.medio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Principal extends JFrame {
    BufferedImage buffer;
    Graphics2D recta;
    
    public Principal() {
        setTitle("Línea de punto medio");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        recta = (Graphics2D) buffer.createGraphics();
    }
    
    public void medio(int x0, int y0, int x1, int y1, Color color) {
        // En esta práctica se movió el punto de origen al centro de la ventana para comportarse como un plano cartesiano común.
        
        buffer.setRGB(0, 0, color.getRGB());
        int x = x0, y = y0;
        int dy = y1 - y0, dx = x1 - x0;
        int incXpos, incYpos;
        int incXneg, incYneg;
                
        if(dy >= 0) {
            incYpos = 1;
        } else {
            dy = -dy;
            incYpos = -1;
        }
        
        if(dx >= 0) {
            incXpos = 1;
        } else {
            dx = -dx;
            incXpos = -1;
        }
        
        if(dx >= dy) {
            incYneg = 0;
            incXneg = incXpos;
        } else {
            incYneg = incYpos;
            incXneg = 0;
            int aux = dx;
            dx = dy;
            dy = aux;
        }
        
        int avanceNegativo = 2 * dy, av = avanceNegativo - dx, avancePositivo = av - dx;
        
        for(int i = 0; x < x1; i++) {
            this.getGraphics().drawImage(buffer, 250 + x, 250 - y, this);
            if(av >= 0) {
                x = x + incXpos;
                y = y + incYpos;
                av = av + avancePositivo;
            } 
            
            if(av < 0) {
                x = x + incXneg;
                y = y + incYneg;
                av = av + avanceNegativo;
            }
        }   
    }
    
    public void paint(Graphics graphic) {
        super.paint(graphic);
        medio(-30,-20,40,36,Color.blue);
    }
    
    public static void main(String[] args) {
        new Principal();
    }
}
