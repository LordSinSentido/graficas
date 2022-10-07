
package circulos.medio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Principal extends JFrame {
    BufferedImage buffer;
    Graphics2D recta;
    
    public Principal() {
        setTitle("Círculo con punto medio");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        recta = (Graphics2D) buffer.createGraphics();
    }
    
    public void medio (double xc, double yc, double r, Color color) {
        // En esta práctica se movió el punto de origen al centro de la ventana para comportarse como un plano cartesiano común.
        
        buffer.setRGB(0, 0, color.getRGB());
        double x = 0, y = r, p;
        if(r - (int) r == 0) {
            p = 1 - r;
        } else {
            p = 5 / 4 - r;
        }
        dibujar((int) xc, (int) yc, (int) x, (int) y);
        while(x < y) {
            x = x + 1;
            
            if(p < 0) {
                p = p + 2 * x + 3;
            } else {
                y = y - 1;
                p = p + 2 * (x - y) + 5;
            }
            dibujar((int) xc, (int) yc, (int) x, (int) y);
        }
    }
    
    public void dibujar(int xc, int yc, int x, int y) {
        this.getGraphics().drawImage(buffer, 250 + (xc + x), 250 - (yc + y), this);
        this.getGraphics().drawImage(buffer, 250 + (xc + x), 250 - (yc - y), this);
        this.getGraphics().drawImage(buffer, 250 + (xc - x), 250 - (yc + y), this);
        this.getGraphics().drawImage(buffer, 250 + (xc - x), 250 - (yc - y), this);
        this.getGraphics().drawImage(buffer, 250 + (xc + y), 250 - (yc + x), this);
        this.getGraphics().drawImage(buffer, 250 + (xc + y), 250 - (yc - x), this);
        this.getGraphics().drawImage(buffer, 250 + (xc - y), 250 - (yc + x), this);
        this.getGraphics().drawImage(buffer, 250 + (xc - y), 250 - (yc - x), this);
    }
    
    public void paint(Graphics graphic) {
        super.paint(graphic);
        medio(-125, -50, 100, Color.red);
    } 
    
    public static void main(String[] args) {
        new Principal();
    }
}
