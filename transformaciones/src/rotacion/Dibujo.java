
package rotacion;

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
    
    public void crearLinea(double x1, double y1, double x2, double y2, Color color) {
        buffer.setRGB(0, 0, color.getRGB());
        double dx = x2 - x1;
        double dy = y2 - y1;
        double steps = 0;
        
        if(Math.abs(dx) >= Math.abs(dy)) {
            steps = Math.abs(dx);
        } else {
            steps = Math.abs(dy);
        }
        
        double xinc = dx / steps;
        double yinc = dy / steps;
        
        double x = x1;
        double y = y1;
        this.getGraphics().drawImage(buffer, (int) Math.round(x) + 250, (int) Math.round(y) + 250, this);
        
        for(int i = 1; i <= steps; i++) {
            x = x + xinc;
            y = y + yinc;
            this.getGraphics().drawImage(buffer, (int) Math.round(x) + 250, (int) Math.round(y) + 250, this);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        crearLinea(calculos.getCoordenadasFinales()[0][0], calculos.getCoordenadasFinales()[1][0], calculos.getCoordenadasFinales()[0][1], calculos.getCoordenadasFinales()[1][1], Color.blue);
        crearLinea(calculos.getCoordenadasFinales()[0][1], calculos.getCoordenadasFinales()[1][1], calculos.getCoordenadasFinales()[0][2], calculos.getCoordenadasFinales()[1][2], Color.blue);
        crearLinea(calculos.getCoordenadasFinales()[0][2], calculos.getCoordenadasFinales()[1][2], calculos.getCoordenadasFinales()[0][3], calculos.getCoordenadasFinales()[1][3], Color.blue);
        crearLinea(calculos.getCoordenadasFinales()[0][3], calculos.getCoordenadasFinales()[1][3], calculos.getCoordenadasFinales()[0][0], calculos.getCoordenadasFinales()[1][0], Color.blue);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        paint(g);
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                repaint();
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(traslacion.Dibujo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
