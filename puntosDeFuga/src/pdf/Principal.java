
package pdf;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Principal extends JFrame {
    private BufferedImage bufferedImage;
    private Graphics graphicsPixel;

    public Principal()
    {
        this.setSize(600, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Puntos de fuga");
        this.getContentPane().setBackground(Color.black);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Pixel pixel = new Pixel(bufferedImage);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Pixel.paintBackground(Color.black);
        Proyeccion.dibujarUnPuntoDeFuga(0, 60, 80, 50, Color.black);
        Proyeccion.dibujarDosPuntoDeFuga(80, 160, 80, 50, Color.black);
        Proyeccion.dibujarTresPuntoDeFuga(220, 250, 80, 35, Color.black);
        g.drawImage(bufferedImage, 0, 0, this);
    }
    
    public static void main(String[] args) {
        new Principal();
        
    }
}
