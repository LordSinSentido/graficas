package lineas.recta;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Principal extends JFrame {
    BufferedImage buffer;
    Graphics2D recta;
    int primerPuntoX = 50, primerPuntoY = -50;
    int segundoPuntoX = -100, segundoPuntoY = 200;
    
    public Principal() {
        setTitle("Línea recta");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        recta = (Graphics2D) buffer.createGraphics();
    }
    
    public void recta(double x1, double y1, double x2, double y2, Color color) {
        // En esta práctica se movió el punto de origen al centro de la ventana para comportarse como un plano cartesiano común.
        
        buffer.setRGB(0, 0, color.getRGB());
        double pendiente = (y2 - y1) / (x2 - x1);
        for(double  x = x1; x < x2; x++) {
            double y = pendiente * (x - x1) + y1;
            this.getGraphics().drawImage(buffer, 250 + (int) x, 250 - (int) y, this);
        }
    }
    
    public void paint(Graphics graphic) {
        super.paint(graphic);
        if (primerPuntoX < segundoPuntoX) {
            recta(primerPuntoX, primerPuntoY, segundoPuntoX, segundoPuntoY, Color.red);
        } else {
            recta(segundoPuntoX, segundoPuntoY, primerPuntoX, primerPuntoY, Color.red);
        }
    } 
    
    public static void main(String[] args) {
        new Principal();
    }
}