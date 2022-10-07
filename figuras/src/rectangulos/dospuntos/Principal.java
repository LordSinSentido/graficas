
package rectangulos.dospuntos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Principal extends JFrame {
    BufferedImage buffer;
    Graphics2D recta;
    
    int xPrimerPunto = -100, yPrimerPunto = 50;
    int xSegundoPunto = 100, ySegundoPunto = -50;
    
    public Principal() {
        setTitle("Rectángulo a dos puntos");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }
    
        public void crearLinea(float xi, float yi, float xf, float yf) {
        // En esta práctica se movió el punto de origen al centro de la ventana para comportarse como un plano cartesiano común.
        
        buffer.setRGB(0, 0, Color.black.getRGB());
        float dx = xf - xi;
        float dy = yf - yi;
        float steps = 0;
        if(Math.abs(dx) >= Math.abs(dy)) {
            steps = Math.abs(dx);
        } else {
            steps = Math.abs(dy);
        }
        float xinc = dx / steps;
        float yinc = dy / steps;
        float x = xi;
        float y = yi;
        this.getGraphics().drawImage(buffer, 250 + Math.round(x), 250 - Math.round(y), this);
        for(int i = 1; i <= steps; i++) {
            x = x + xinc;
            y = y + yinc;
            this.getGraphics().drawImage(buffer, 250 + Math.round(x), 250 - Math.round(y), this);
        }
    }
    
    public void paint(Graphics graphic) {
        super.paint(graphic);
        crearLinea(xPrimerPunto, yPrimerPunto, xPrimerPunto, ySegundoPunto);
        crearLinea(xPrimerPunto, ySegundoPunto, xSegundoPunto, ySegundoPunto);
        crearLinea(xSegundoPunto, ySegundoPunto, xSegundoPunto, yPrimerPunto);
        crearLinea(xSegundoPunto, yPrimerPunto, xPrimerPunto, yPrimerPunto);
    } 
    
    public static void main(String[] args) {
        new Principal();
    }
}
