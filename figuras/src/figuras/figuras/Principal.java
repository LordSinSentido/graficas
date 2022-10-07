
package figuras.figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Principal extends JFrame {
    BufferedImage buffer;
    Graphics2D recta;
    Color color = Color.black;
    
    public Principal() {
        setTitle("Figuras");
        setSize(650, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }
    
    /* Funciones de las figuras */
    
    /*
    Para esta practica, se utilizara como punto de origen
    la esquina inferior izquierda, para asi, tener mas facilidad
    a la hora de dibujar las figuras en el lienzo.
    */
    
    // Rectas (Se utiliza el algoritmo DDA)
    public void crearLinea(int xi, int yi, int xf, int yf) {
        buffer.setRGB(0, 0, color.getRGB());
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
        this.getGraphics().drawImage(buffer, Math.round(x), 500 - Math.round(y), this);
        for(int i = 1; i <= steps; i++) {
            x = x + xinc;
            y = y + yinc;
            this.getGraphics().drawImage(buffer, Math.round(x), 500 - Math.round(y), this);
        }
    }
    
    // Rectangulos
    public void crearRectangulo(int xi, int yi, int xf, int yf) {
        crearLinea(xi, yi, xf, yi);
        crearLinea(xi, yi, xi, yf);
        crearLinea(xf, yf, xi, yf);
        crearLinea(xf, yf, xf, yi);
    }
    
    // Circulos (Se utiliza el algoritmo de punto medio)
    public void crearCirculo (double xc, double yc, double r) {
        buffer.setRGB(0, 0, color.getRGB());
        double x = 0, y = r, p;
        if(r - (int) r == 0) {
            p = 1 - r;
        } else {
            p = 5 / 4 - r;
        }
        dibujarCiruclo((int) xc, (int) yc, (int) x, (int) y);
        while(x < y) {
            x = x + 1;
            if(p < 0) {
                p = p + 2 * x + 3;
            } else {
                y = y - 1;
                p = p + 2 * (x - y) + 5;
            }
            dibujarCiruclo((int) xc, (int) yc, (int) x, (int) y);
        }
    }
    
    public void dibujarCiruclo(int xc, int yc, int x, int y) {
        this.getGraphics().drawImage(buffer, (xc + x), 500 - (yc + y), this);
        this.getGraphics().drawImage(buffer, (xc + x), 500 - (yc - y), this);
        this.getGraphics().drawImage(buffer, (xc - x), 500 - (yc + y), this);
        this.getGraphics().drawImage(buffer, (xc - x), 500 - (yc - y), this);
        this.getGraphics().drawImage(buffer, (xc + y), 500 - (yc + x), this);
        this.getGraphics().drawImage(buffer, (xc + y), 500 - (yc - x), this);
        this.getGraphics().drawImage(buffer, (xc - y), 500 - (yc + x), this);
        this.getGraphics().drawImage(buffer, (xc - y), 500 - (yc - x), this);
    }
    
    // Elipses (Se utiliza el algoritmo basico)
    public void crearElipse(double x, double y, double rx, double ry) {
        buffer.setRGB(0, 0, color.getRGB());
        for(double i = 0; i < 360; i = i + 0.1) {
            double xc = Math.round(x + rx * Math.sin(Math.toRadians(i)));
            double yc = Math.round(y + ry * Math.cos(Math.toRadians(i)));            
            this.getGraphics().drawImage(buffer, (int) xc, 500 - (int) yc, this);
        }
    }
    
    /* Funcion de pintar */
    public void paint(Graphics graphic) {
        super.paint(graphic);
        
        /* Rectas (x primer punto, y primer punto, x segundo punto, y segundo punto) */
            // Primera recta
            crearLinea(50,400,150,300);
            // Segunda recta
            crearLinea(200,350,300,350);
            // Tercera recta
            crearLinea(450, 400, 350, 300);
            // Cuarta recta
            crearLinea(600, 350, 500, 350);
        
        /* Rectangulos (x primer punto, y primer punto, x segundo punto, y segundo punto) */
            // Primer rectangulo
            crearRectangulo(50, 200, 150, 100);
            // Segundo rectangulo
            crearRectangulo(130, 120, 70, 180);
        
        /* Circulos (x del centro, y del centro, radio) */
            // Primer circulo
            crearCirculo(250, 150, 50);
            // Segundo circulo
            crearCirculo(250, 150, 30);
            // Tercer circulo
            crearCirculo(250, 150, 10);
            
        /* Elipses (x del centro, y del centro, radio en x, radio en y) */
            // Primer elipse
            crearElipse(475, 150, 125, 50);
            // Segundo elipse
            crearElipse(475, 150, 100, 35);
            // Tercer elipse
            crearElipse(475, 150, 75, 20);
            // Cuarto elipse
            crearElipse(475, 150, 50, 5);
    } 
    
    public static void main(String[] args) {
        new Principal();
    }
}
