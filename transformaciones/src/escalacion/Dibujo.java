
package escalacion;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dibujo extends Canvas implements Runnable {
    Calculos calculos = new Calculos();
    Thread calcular = new Thread(calculos);
    BufferedImage buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    
    public Dibujo() {
        setBounds(0, 0, 500, 500);
        setVisible(true);
        calcular.start();
    }
    
    public void crearLinea(float x1, float y1, float x2, float y2, Color color) {
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
        this.getGraphics().drawImage(buffer, Math.round(x), Math.round(y), this);
        
        for(int i = 1; i <= steps; i++) {
            x = x + xinc;
            y = y + yinc;
            this.getGraphics().drawImage(buffer, Math.round(x), Math.round(y), this);
        }
    }

    @Override
    public void paint(Graphics g) {
        setLocation(50, 50);
        super.paint(g);
        crearLinea((int) calculos.getCoordenadasFinales()[0][0], (int) calculos.getCoordenadasFinales()[1][0], (int) calculos.getCoordenadasFinales()[0][1], (int) calculos.getCoordenadasFinales()[1][1], Color.blue);
        crearLinea((int) calculos.getCoordenadasFinales()[0][1], (int) calculos.getCoordenadasFinales()[1][1], (int) calculos.getCoordenadasFinales()[0][2], (int) calculos.getCoordenadasFinales()[1][2], Color.blue);
        crearLinea((int) calculos.getCoordenadasFinales()[0][2], (int) calculos.getCoordenadasFinales()[1][2], (int) calculos.getCoordenadasFinales()[0][3], (int) calculos.getCoordenadasFinales()[1][3], Color.blue);
        crearLinea((int) calculos.getCoordenadasFinales()[0][3], (int) calculos.getCoordenadasFinales()[1][3], (int) calculos.getCoordenadasFinales()[0][0], (int) calculos.getCoordenadasFinales()[1][0], Color.blue);
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                repaint();
                sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(traslacion.Dibujo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
