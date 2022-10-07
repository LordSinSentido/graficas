
package pajaro;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Dibujo extends Canvas implements Runnable {
    // Bufers
    BufferedImage buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    BufferedImage lienzo;
    
    // Imagen de fondo
    //ImageIcon imagenDeFondo = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg"));
    
    // Clases e hilos
    Cola cola = new Cola();
    Thread calcularCola = new Thread(cola);
    
    Cuerpo cuerpo = new Cuerpo();
    Thread calcularCuerpo = new Thread(cuerpo);
    
    Pico pico = new Pico();
    Thread calcularPico = new Thread(pico);
    
    Destellos destellos = new Destellos();
    Thread calcularDestellos = new Thread(destellos);
    
    // Colores
    Color colorCola = new Color(2, 118, 215);
    Color colorColaPunta = new Color(47, 65, 219);
    Color colorCuerpo = new Color(68, 195, 176);
    Color colorCabeza = new Color(13, 88, 146);
    Color colorCuello = new Color(236, 205, 122);
    Color colorNuca = new Color(183, 204, 87);
    Color colorPico = new Color(55, 69, 79);
    Color colorOjos = new Color(195, 35, 47);
    Color colorDestellos = new Color(255, 220, 152);
    
    public Dibujo() {
        setBounds(0, 0, 800, 800);
        setVisible(true);

        calcularCola.start();
        calcularCuerpo.start();
        calcularPico.start();
        calcularDestellos.start();
    }
    
    /********************************************************/
    /* Funciones para la creacion de las figuras primitivas */
    /********************************************************/
    
    // Lineas por medio de DDA
    public void crearLinea(double x1, double y1, double x2, double y2, Color color, int desfaseX, int desfaseY) {
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
        lienzo.getGraphics().drawImage(buffer, desfaseX + (int) Math.round(x), desfaseY - (int) Math.round(y), this);
        
        for(int i = 1; i <= steps; i++) {
            x = x + xinc;
            y = y + yinc;
            lienzo.getGraphics().drawImage(buffer, desfaseX + (int) Math.round(x), desfaseY - (int) Math.round(y), this);
        }
    }
    
    // Circulos por medio de punto medio
    public void crearCirculo(double xc, double yc, double r, Color color, int desfaseX, int desfaseY) {
        buffer.setRGB(0, 0, color.getRGB());
        double x = 0, y = r, p;
        if(r - (int) r == 0) {
            p = 1 - r;
        } else {
            p = 5 / 4 - r;
        }
        dibujarCirculo((int) xc, (int) yc, (int) x, (int) y, desfaseX, desfaseY);
        while(x < y) {
            x = x + 1;
            
            if(p < 0) {
                p = p + 2 * x + 3;
            } else {
                y = y - 1;
                p = p + 2 * (x - y) + 5;
            }
            dibujarCirculo((int) xc, (int) yc, (int) x, (int) y, desfaseX, desfaseY);
        }
    }
    
    public void dibujarCirculo(int xc, int yc, int x, int y, int desfaseX, int desfaseY) {
        lienzo.getGraphics().drawImage(buffer, desfaseX + (xc + x), desfaseY - (yc + y), this);
        lienzo.getGraphics().drawImage(buffer, desfaseX + (xc + x), desfaseY - (yc - y), this);
        lienzo.getGraphics().drawImage(buffer, desfaseX + (xc - x), desfaseY - (yc + y), this);
        lienzo.getGraphics().drawImage(buffer, desfaseX + (xc - x), desfaseY - (yc - y), this);
        lienzo.getGraphics().drawImage(buffer, desfaseX + (xc + y), desfaseY - (yc + x), this);
        lienzo.getGraphics().drawImage(buffer, desfaseX + (xc + y), desfaseY - (yc - x), this);
        lienzo.getGraphics().drawImage(buffer, desfaseX + (xc - y), desfaseY - (yc + x), this);
        lienzo.getGraphics().drawImage(buffer, desfaseX + (xc - y), desfaseY - (yc - x), this);
    }
    
    // Ovalo simple
    public void crearOvalo(double x, double y, double rx, double ry, Color color, int desfaseX, int desfaseY) {
        buffer.setRGB(0, 0, color.getRGB());
        
        for(double i = 0; i < 360; i = i + 0.1) {
            double xc = Math.round(x + rx * Math.sin(Math.toRadians(i)));
            double yc = Math.round(y + ry * Math.cos(Math.toRadians(i)));            
            lienzo.getGraphics().drawImage(buffer, desfaseX + (int) xc, desfaseY - (int) yc, this);
        }
    }
    
    /************************************/
    /* Funcion para realizar el relleno */
    /************************************/
    
    // Relleno por inundacion
    public void rellenar(double x, double y, Color color, int desfaseX, int desfaseY) {
        Color actual = new Color(lienzo.getRGB(desfaseX + (int) x, desfaseY - (int) y));
        
        if(actual.getRGB() != color.getRGB()) {
            buffer.setRGB(0, 0, color.getRGB());
            lienzo.getGraphics().drawImage(buffer, desfaseX + (int) x, desfaseY - (int) y, this);
            
            rellenar(x, y - 1, color, desfaseX, desfaseY);
            rellenar(x + 1, y, color, desfaseX, desfaseY);
            rellenar(x, y + 1, color, desfaseX, desfaseY);
            rellenar(x - 1, y, color, desfaseX, desfaseY);
        }
    }
    
    /*****************************************/
    /* Funciones para el dibujado del pajaro */
    /*****************************************/

    // Cola
    public void dibujarCola(int desfaseX, int desfaseY, int desfaseCola) {
        
        // Parte larga
        crearLinea(cola.getCoordenadasFinales()[0][0] + desfaseCola, cola.getCoordenadasFinales()[1][0] + cuerpo.getMov(), cola.getCoordenadasFinales()[0][1] + desfaseCola, cola.getCoordenadasFinales()[1][1] + cuerpo.getMov(), colorCola, desfaseX, desfaseY);
        crearLinea(cola.getCoordenadasFinales()[0][1] + desfaseCola, cola.getCoordenadasFinales()[1][1] + cuerpo.getMov(), cola.getCoordenadasFinales()[0][2] + desfaseCola, cola.getCoordenadasFinales()[1][2] + cuerpo.getMov(), colorCola, desfaseX, desfaseY);
        crearLinea(cola.getCoordenadasFinales()[0][2] + desfaseCola, cola.getCoordenadasFinales()[1][2] + cuerpo.getMov(), cola.getCoordenadasFinales()[0][3] + desfaseCola, cola.getCoordenadasFinales()[1][3] + cuerpo.getMov(), colorCola, desfaseX, desfaseY);
        crearLinea(cola.getCoordenadasFinales()[0][3] + desfaseCola, cola.getCoordenadasFinales()[1][3] + cuerpo.getMov(), cola.getCoordenadasFinales()[0][0] + desfaseCola, cola.getCoordenadasFinales()[1][0] + cuerpo.getMov(), colorCola, desfaseX, desfaseY);
        rellenar(cola.getCoordenadasFinales()[0][4] + desfaseCola, cola.getCoordenadasFinales()[1][4]  + cuerpo.getMov(), colorCola, desfaseX, desfaseY);
        
        // Punta
        crearLinea(cola.getCoordenadasFinales()[0][2] + desfaseCola, cola.getCoordenadasFinales()[1][2] + cuerpo.getMov(), cola.getCoordenadasFinales()[0][5] + desfaseCola, cola.getCoordenadasFinales()[1][5] + cuerpo.getMov(), colorColaPunta, desfaseX, desfaseY);
        crearLinea(cola.getCoordenadasFinales()[0][5] + desfaseCola, cola.getCoordenadasFinales()[1][5] + cuerpo.getMov(), cola.getCoordenadasFinales()[0][6] + desfaseCola, cola.getCoordenadasFinales()[1][6] + cuerpo.getMov(), colorColaPunta, desfaseX, desfaseY);
        crearLinea(cola.getCoordenadasFinales()[0][6] + desfaseCola, cola.getCoordenadasFinales()[1][6] + cuerpo.getMov(), cola.getCoordenadasFinales()[0][2] + desfaseCola, cola.getCoordenadasFinales()[1][2] + cuerpo.getMov(), colorColaPunta, desfaseX, desfaseY);
        rellenar(cola.getCoordenadasFinales()[0][8] + desfaseCola, cola.getCoordenadasFinales()[1][8]  + cuerpo.getMov(), colorColaPunta, desfaseX, desfaseY);
        
        // Remate de la punta
        crearCirculo(cola.getCoordenadasFinales()[0][7] + desfaseCola, cola.getCoordenadasFinales()[1][7]  + cuerpo.getMov(), 10, colorColaPunta, 400, 400);
        rellenar(cola.getCoordenadasFinales()[0][9] + desfaseCola, cola.getCoordenadasFinales()[1][9]  + cuerpo.getMov(), colorColaPunta, desfaseX, desfaseY);
    }
    
    // Cuerpo
    public void dibujarCuerpo(int desfaseX, int desfaseY) {
        
        // Cabeza
        crearOvalo(cuerpo.getCoordenadasFinales()[0][17], cuerpo.getCoordenadasFinales()[1][17], 40, 25, colorCabeza, desfaseX, desfaseY);
        rellenar(cuerpo.getCoordenadasFinales()[0][17], cuerpo.getCoordenadasFinales()[1][17], colorCabeza, desfaseX, desfaseY);
        
        crearLinea(cuerpo.getCoordenadasFinales()[0][10], cuerpo.getCoordenadasFinales()[1][10], cuerpo.getCoordenadasFinales()[0][14], cuerpo.getCoordenadasFinales()[1][14], colorCabeza, desfaseX, desfaseY);
        crearLinea(cuerpo.getCoordenadasFinales()[0][14], cuerpo.getCoordenadasFinales()[1][14], cuerpo.getCoordenadasFinales()[0][15], cuerpo.getCoordenadasFinales()[1][15], colorCabeza, desfaseX, desfaseY);
        rellenar(cuerpo.getCoordenadasFinales()[0][16], cuerpo.getCoordenadasFinales()[1][16], colorCabeza, desfaseX, desfaseY);
        
        // Ojo
        crearCirculo(cuerpo.getCoordenadasFinales()[0][18], cuerpo.getCoordenadasFinales()[1][18], 7, colorOjos, desfaseX, desfaseY);
        rellenar(cuerpo.getCoordenadasFinales()[0][18], cuerpo.getCoordenadasFinales()[1][18], colorOjos, desfaseX, desfaseY);

        crearCirculo(cuerpo.getCoordenadasFinales()[0][18], cuerpo.getCoordenadasFinales()[1][18], 3, colorPico, desfaseX, desfaseY);
        rellenar(cuerpo.getCoordenadasFinales()[0][18], cuerpo.getCoordenadasFinales()[1][18], colorPico, desfaseX, desfaseY);
        
        // Nuca
        crearLinea(cuerpo.getCoordenadasFinales()[0][10], cuerpo.getCoordenadasFinales()[1][10], cuerpo.getCoordenadasFinales()[0][11], cuerpo.getCoordenadasFinales()[1][11], colorNuca, desfaseX, desfaseY);
        crearLinea(cuerpo.getCoordenadasFinales()[0][11], cuerpo.getCoordenadasFinales()[1][11], cuerpo.getCoordenadasFinales()[0][12], cuerpo.getCoordenadasFinales()[1][12], colorNuca, desfaseX, desfaseY);
        crearLinea(cuerpo.getCoordenadasFinales()[0][12], cuerpo.getCoordenadasFinales()[1][12], cuerpo.getCoordenadasFinales()[0][10], cuerpo.getCoordenadasFinales()[1][10], colorNuca, desfaseX, desfaseY);
        rellenar(cuerpo.getCoordenadasFinales()[0][13], cuerpo.getCoordenadasFinales()[1][13], colorNuca, desfaseX, desfaseY);
        
        // Cuello
        crearCirculo(cuerpo.getCoordenadasFinales()[0][9], cuerpo.getCoordenadasFinales()[1][9], 50, colorCuello, 400, 400);
        rellenar(cuerpo.getCoordenadasFinales()[0][9], cuerpo.getCoordenadasFinales()[1][9], colorCuello, desfaseX, desfaseY);
        
        // Pecho
        crearCirculo(cuerpo.getCoordenadasFinales()[0][0], cuerpo.getCoordenadasFinales()[1][0], 50, colorCuerpo, 400, 400);
        rellenar(cuerpo.getCoordenadasFinales()[0][6], cuerpo.getCoordenadasFinales()[1][6], colorCuerpo, desfaseX, desfaseY);
        
        // Pata
        crearLinea(cuerpo.getCoordenadasFinales()[0][19], cuerpo.getCoordenadasFinales()[1][19], cuerpo.getCoordenadasFinales()[0][20], cuerpo.getCoordenadasFinales()[1][20], colorPico, desfaseX, desfaseY);
        crearLinea(cuerpo.getCoordenadasFinales()[0][20], cuerpo.getCoordenadasFinales()[1][20], cuerpo.getCoordenadasFinales()[0][21], cuerpo.getCoordenadasFinales()[1][21], colorPico, desfaseX, desfaseY);
        crearLinea(cuerpo.getCoordenadasFinales()[0][21], cuerpo.getCoordenadasFinales()[1][21], cuerpo.getCoordenadasFinales()[0][22], cuerpo.getCoordenadasFinales()[1][22], colorPico, desfaseX, desfaseY);
        crearLinea(cuerpo.getCoordenadasFinales()[0][22], cuerpo.getCoordenadasFinales()[1][22], cuerpo.getCoordenadasFinales()[0][19], cuerpo.getCoordenadasFinales()[1][19], colorPico, desfaseX, desfaseY);
        rellenar(cuerpo.getCoordenadasFinales()[0][23], cuerpo.getCoordenadasFinales()[1][23], colorPico, desfaseX, desfaseY);
        
        // Espalda baja
        crearCirculo(cuerpo.getCoordenadasFinales()[0][1], cuerpo.getCoordenadasFinales()[1][1], 30, colorCuerpo, 400, 400);
        rellenar(cuerpo.getCoordenadasFinales()[0][7], cuerpo.getCoordenadasFinales()[1][7], colorCuerpo, desfaseX, desfaseY);
        
        // Torzo
        crearLinea(cuerpo.getCoordenadasFinales()[0][2], cuerpo.getCoordenadasFinales()[1][2], cuerpo.getCoordenadasFinales()[0][3], cuerpo.getCoordenadasFinales()[1][3], colorCuerpo, desfaseX, desfaseY);
        crearLinea(cuerpo.getCoordenadasFinales()[0][4], cuerpo.getCoordenadasFinales()[1][4], cuerpo.getCoordenadasFinales()[0][5], cuerpo.getCoordenadasFinales()[1][5], colorCuerpo, desfaseX, desfaseY);
        rellenar(cuerpo.getCoordenadasFinales()[0][8], cuerpo.getCoordenadasFinales()[1][8], colorCuerpo, desfaseX, desfaseY);
    }
    
    public void dibujarPico(int desfaseX, int desfaseY) {
        
        // Parte de arriba
        crearLinea(pico.getCoordenadasFinales()[0][0], pico.getCoordenadasFinales()[1][0] + cuerpo.getMov(), pico.getCoordenadasFinales()[0][1], pico.getCoordenadasFinales()[1][1] + cuerpo.getMov(), colorPico, desfaseX, desfaseY);
        crearLinea(pico.getCoordenadasFinales()[0][1], pico.getCoordenadasFinales()[1][1] + cuerpo.getMov(), pico.getCoordenadasFinales()[0][2], pico.getCoordenadasFinales()[1][2] + cuerpo.getMov(), colorPico, desfaseX, desfaseY);
        crearLinea(pico.getCoordenadasFinales()[0][2], pico.getCoordenadasFinales()[1][2] + cuerpo.getMov(), pico.getCoordenadasFinales()[0][0], pico.getCoordenadasFinales()[1][0] + cuerpo.getMov(), colorPico, desfaseX, desfaseY);
        rellenar(pico.getCoordenadasFinales()[0][3], pico.getCoordenadasFinales()[1][3] + cuerpo.getMov(), colorPico, desfaseX, desfaseY);
        
        //Parte de abajo
        crearLinea(pico.getCoordenadasFinales()[0][0], -pico.getCoordenadasFinales()[1][0] + cuerpo.getMov(), pico.getCoordenadasFinales()[0][1], -pico.getCoordenadasFinales()[1][1] + cuerpo.getMov(), colorPico, desfaseX, desfaseY);
        crearLinea(pico.getCoordenadasFinales()[0][1], -pico.getCoordenadasFinales()[1][1] + cuerpo.getMov(), pico.getCoordenadasFinales()[0][2], -pico.getCoordenadasFinales()[1][2] + cuerpo.getMov(), colorPico, desfaseX, desfaseY);
        crearLinea(pico.getCoordenadasFinales()[0][2], -pico.getCoordenadasFinales()[1][2] + cuerpo.getMov(), pico.getCoordenadasFinales()[0][0], -pico.getCoordenadasFinales()[1][0] + cuerpo.getMov(), colorPico, desfaseX, desfaseY);
        rellenar(pico.getCoordenadasFinales()[0][3], -pico.getCoordenadasFinales()[1][3] + (int) cuerpo.getMov(), colorPico, desfaseX, desfaseY);
    }
    
    public void dibujarDestellos(int desfaseX, int desfaseY) {
        crearLinea(destellos.getCoordenadasFinales()[0][0], destellos.getCoordenadasFinales()[1][0], destellos.getCoordenadasFinales()[0][1], destellos.getCoordenadasFinales()[1][1], colorDestellos, desfaseX, desfaseY);
        crearLinea(destellos.getCoordenadasFinales()[0][1], destellos.getCoordenadasFinales()[1][1], destellos.getCoordenadasFinales()[0][2], destellos.getCoordenadasFinales()[1][2], colorDestellos, desfaseX, desfaseY);
        crearLinea(destellos.getCoordenadasFinales()[0][2], destellos.getCoordenadasFinales()[1][2], destellos.getCoordenadasFinales()[0][3], destellos.getCoordenadasFinales()[1][3], colorDestellos, desfaseX, desfaseY);
        crearLinea(destellos.getCoordenadasFinales()[0][3], destellos.getCoordenadasFinales()[1][3], destellos.getCoordenadasFinales()[0][4], destellos.getCoordenadasFinales()[1][4], colorDestellos, desfaseX, desfaseY);
        crearLinea(destellos.getCoordenadasFinales()[0][4], destellos.getCoordenadasFinales()[1][4], destellos.getCoordenadasFinales()[0][5], destellos.getCoordenadasFinales()[1][5], colorDestellos, desfaseX, desfaseY);
        crearLinea(destellos.getCoordenadasFinales()[0][5], destellos.getCoordenadasFinales()[1][5], destellos.getCoordenadasFinales()[0][6], destellos.getCoordenadasFinales()[1][6], colorDestellos, desfaseX, desfaseY);
        crearLinea(destellos.getCoordenadasFinales()[0][6], destellos.getCoordenadasFinales()[1][6], destellos.getCoordenadasFinales()[0][7], destellos.getCoordenadasFinales()[1][7], colorDestellos, desfaseX, desfaseY);
        crearLinea(destellos.getCoordenadasFinales()[0][7], destellos.getCoordenadasFinales()[1][7], destellos.getCoordenadasFinales()[0][0], destellos.getCoordenadasFinales()[1][0], colorDestellos, desfaseX, desfaseY);
        rellenar(destellos.getCoordenadasFinales()[0][8], destellos.getCoordenadasFinales()[1][8], colorDestellos, desfaseX, desfaseY);
    }
    
    /**********************/
    /* Metodos del canvas */
    /**********************/
    
    @Override
    public void update(Graphics g) {
        lienzo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        //lienzo.getGraphics().drawImage(imagenDeFondo.getImage(), 0, 0, 1500, 927, this);
        
        dibujarCola(400, 400,-7);
        dibujarCola(400, 400, 7);
        dibujarPico(330, 270);
        dibujarCuerpo(400, 400);
        dibujarDestellos(420, 220);
        dibujarDestellos(320, 450);
        
        getGraphics().drawImage(lienzo, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                repaint();
                sleep(17);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dibujo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
