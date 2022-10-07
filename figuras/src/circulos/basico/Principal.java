
package circulos.basico;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Principal extends JFrame {
    BufferedImage buffer;
    Graphics2D recta;
    
    public Principal() {
        setTitle("Círculo básico");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }
    
    public void basico(double xc, double yc, double xr, double yr, Color color) {
        // En esta práctica se movió el punto de origen al centro de la ventana para comportarse como un plano cartesiano común.
        
        buffer.setRGB(0, 0, color.getRGB());
        double radio = Math.sqrt(Math.pow(xc - xr, 2) + Math.pow(yc - yr, 2));
        double x, y;
        
        for(x = xc - radio; x < xc + radio; x++) {
            y = yc + Math.sqrt(Math.pow(radio, 2) - Math.pow(x - xc, 2));
            this.getGraphics().drawImage(buffer, 250 + (int) x, 250 - (int) y, this);
            y = yc - Math.sqrt(Math.pow(radio, 2) - Math.pow(x - xc, 2));
            this.getGraphics().drawImage(buffer, 250 + (int) x, 250 - (int) y, this);
        }
        
    }
    
    public void paint(Graphics graphic) {
        super.paint(graphic);
        basico(0, 0, 150, 50, Color.red);
    } 
    
    public static void main(String[] args) {
        new Principal();
    }
}
