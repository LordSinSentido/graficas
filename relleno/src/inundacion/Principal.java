
package inundacion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Principal extends JFrame {
    BufferedImage buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    BufferedImage lienzo = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
    Color fondo = Color.white;
    Color colorDeRelleno = new Color(255, 51, 105);
    
    public Principal() {
        setTitle("Relleno por inundación");
        setSize(500, 500);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void rellenoDeFondo() {
        buffer.setRGB(0, 0, fondo.getRGB());
        for(int i = 0; i < lienzo.getWidth(); i++) {
            for(int j = 0; j < lienzo.getHeight(); j++) {
                lienzo.getGraphics().drawImage(buffer, Math.round(i), Math.round(j), this);
            }
        }
    }
    
    public void crearLinea(float x1, float y1, float x2, float y2) {
        buffer.setRGB(0, 0, Color.black.getRGB());
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
        lienzo.getGraphics().drawImage(buffer, Math.round(x), Math.round(y), this);
        
        for(int i = 1; i <= steps; i++) {
            x = x + xinc;
            y = y + yinc;
            lienzo.getGraphics().drawImage(buffer, Math.round(x), Math.round(y), this);
        }
    }
    
    public void crearPoligono() {
        crearLinea(250, 200, 300, 250);
        crearLinea(300, 250, 280, 300);
        crearLinea(280, 300, 220, 300);
        crearLinea(220, 300, 200, 250);
        crearLinea(200, 250, 250, 200);
    }
    
    public void relleno(int x, int y, Color colorRelleno) {
        Color actual = new Color(lienzo.getRGB(x, y));

        if(actual.getRGB() == fondo.getRGB()) {
            buffer.setRGB(0, 0, colorRelleno.getRGB());
            lienzo.getGraphics().drawImage(buffer, x, y, this);
            
            relleno(x, y - 1, colorRelleno);
            relleno(x + 1, y, colorRelleno);
            relleno(x, y + 1, colorRelleno);
            relleno(x - 1, y, colorRelleno);
        }
    }
    
    public void paint(Graphics graphic) {
        super.paint(graphic);
        
        rellenoDeFondo();
        crearPoligono();
        relleno(240, 250, colorDeRelleno);
        
        this.getGraphics().drawImage(lienzo, 0, 0, this);
    } 
    
    public static void main(String[] args) {
        new Principal();
    }
}
