
package elipses.basico;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Principal extends JFrame {
    BufferedImage buffer;
    Graphics2D recta;
    
    public Principal() {
        setTitle("Elipse básico");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        recta = (Graphics2D) buffer.createGraphics();
    }
    
    public void basico(double x, double y, double rx, double ry, Color color) {
        // En esta práctica se movió el punto de origen al centro de la ventana para comportarse como un plano cartesiano común.
        
        buffer.setRGB(0, 0, color.getRGB());
        
        for(double i = 0; i < 360; i = i + 0.1) {
            double xc = Math.round(x + rx * Math.sin(Math.toRadians(i)));
            double yc = Math.round(y + ry * Math.cos(Math.toRadians(i)));            
            this.getGraphics().drawImage(buffer, 250 + (int) xc, 250 - (int) yc, this);
        }
    }
    
    public void paint(Graphics graphic) {
        super.paint(graphic);
        basico(150, 75, 60, 120, Color.blue);
    } 
    
    public static void main(String[] args) {
        new Principal();
    }
}
