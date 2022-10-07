package lineas.DDA;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Principal extends JFrame {
    BufferedImage buffer;
    Graphics2D recta;
    
    public Principal() {
        setTitle("Línea DDA");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        recta = (Graphics2D) buffer.createGraphics();
    }
    
    public void DDA(float x1, float y1, float x2, float y2, Color color) {
        // En esta práctica se movió el punto de origen al centro de la ventana para comportarse como un plano cartesiano común.
        
        buffer.setRGB(0, 0, color.getRGB());
        float dx = x2 - x1;
        float dy = y2 - y1;
        float steps = 0;
        
        if(Math.abs(dx) >= Math.abs(dy)) {
            steps = Math.abs(dx);
        } else {
            steps = Math.abs(dy);
        }
        
        float xinc = dx / steps;
        float yinc = dy / steps;
        
        float x = x1;
        float y = y1;
        this.getGraphics().drawImage(buffer, 250 + Math.round(x), 250 - Math.round(y), this);
        
        for(int i = 1; i <= steps; i++) {
            x = x + xinc;
            y = y + yinc;
            this.getGraphics().drawImage(buffer, 250 + Math.round(x), 250 - Math.round(y), this);
        }
    }
    
    public void paint(Graphics graphic) {
        super.paint(graphic);
        DDA(3, 3, -8, -8, Color.blue);
    } 
    
    public static void main(String[] args) {
        new Principal();
    }
}
