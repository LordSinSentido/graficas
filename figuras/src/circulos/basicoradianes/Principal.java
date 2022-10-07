package circulos.basicoradianes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Principal extends JFrame {
    BufferedImage buffer;
    Graphics2D recta;
    
    public Principal() {
        setTitle("Círculo básico con radianes");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        recta = (Graphics2D) buffer.createGraphics();
    }
    
    public void basico(double x, double y, double xr, double yr, Color color) {
        // En esta práctica se movió el punto de origen al centro de la ventana para comportarse como un plano cartesiano común.
        
        buffer.setRGB(0, 0, color.getRGB());
        double radio = Math.sqrt(Math.pow(x - xr, 2) + Math.pow(y - yr, 2));
        
        for(double i = 0; i < 360; i = i + 0.1) {
            double xc = Math.round(x + radio * Math.sin(Math.toRadians(i)));
            double yc = Math.round(y + radio * Math.cos(Math.toRadians(i)));            
            this.getGraphics().drawImage(buffer, 250 + (int) xc, 250 - (int) yc, this);
        }
    }
    
    public void paint(Graphics graphic) {
        super.paint(graphic);
        basico(50, 20, 60, 105, Color.magenta);
    } 
    
    public static void main(String[] args) {
        new Principal();
    }
}
