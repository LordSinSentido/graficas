
package nave;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Dibujo extends Canvas implements Runnable {
    BufferedImage buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    BufferedImage lienzo;
    
    ImageIcon imagenDeFondo = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg"));
    
    int XEstrallasMax = 740, XEstrellasMin = -740;
    int[] YEstrellas = new int[10];
    
    Estrella estrella1 = new Estrella((int) (Math.random()*(XEstrallasMax-XEstrellasMin)) + XEstrellasMin);
    Thread crearEstrella1 = new Thread(estrella1);
    
    Estrella estrella2 = new Estrella((int) (Math.random()*(XEstrallasMax-XEstrellasMin)) + XEstrellasMin);
    Thread crearEstrella2 = new Thread(estrella2);
    
    Estrella estrella3 = new Estrella((int) (Math.random()*(XEstrallasMax-XEstrellasMin)) + XEstrellasMin);
    Thread crearEstrella3 = new Thread(estrella3);
    
    Estrella estrella4 = new Estrella((int) (Math.random()*(XEstrallasMax-XEstrellasMin)) + XEstrellasMin);
    Thread crearEstrella4 = new Thread(estrella4);
    
    Estrella estrella5 = new Estrella((int) (Math.random()*(XEstrallasMax-XEstrellasMin)) + XEstrellasMin);
    Thread crearEstrella5 = new Thread(estrella5);
    
    Estrella estrella6 = new Estrella((int) (Math.random()*(XEstrallasMax-XEstrellasMin)) + XEstrellasMin);
    Thread crearEstrella6 = new Thread(estrella6);
    
    Estrella estrella7 = new Estrella((int) (Math.random()*(XEstrallasMax-XEstrellasMin)) + XEstrellasMin);
    Thread crearEstrella7 = new Thread(estrella7);
    
    Estrella estrella8 = new Estrella((int) (Math.random()*(XEstrallasMax-XEstrellasMin)) + XEstrellasMin);
    Thread crearEstrella8 = new Thread(estrella8);
    
    Estrella estrella9 = new Estrella((int) (Math.random()*(XEstrallasMax-XEstrellasMin)) + XEstrellasMin);
    Thread crearEstrella9 = new Thread(estrella9);
    
    Estrella estrella10 = new Estrella((int) (Math.random()*(XEstrallasMax-XEstrellasMin)) + XEstrellasMin);
    Thread crearEstrella10 = new Thread(estrella10);
    
    Cohete cohete = new Cohete();
    Thread crearCohete = new Thread(cohete);
    
    public Dibujo() {
        setBounds(-500, 0, 2080, 720);
        setVisible(true);
    }
    
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
    
    public void crearFigura(double[][] coordenadas, boolean[][] matrizAdyacencia, int desfaceEnX, int desfaceEnY, Color color) {
        for(int i = 0; i < matrizAdyacencia.length; i++) {
            for(int j = 0; j < matrizAdyacencia.length; j++) {
                if(matrizAdyacencia[i][j] == true) {
                    crearLinea(coordenadas[i][0], coordenadas[i][1], coordenadas[j][0], coordenadas[j][1], color, desfaceEnX, desfaceEnY);
                }
            }
        }
    }
    
    public void crearRelleno(double[][] coordenadasFigura, boolean[][] matrizAdyacencia, double[][] coordenadasRelleno, Color colorRelleno, Color colorAristas, int desfaceEnX, int desfaceEnY) {
        for(int i = 0; i < coordenadasRelleno.length; i++) {
            for(int j = 0; j < coordenadasRelleno[0].length; j++) {
                rellenar((int) coordenadasRelleno[i][0], (int) coordenadasRelleno[i][1], colorRelleno, desfaceEnX, desfaceEnY);
            }
        }
        
        crearFigura(coordenadasFigura, matrizAdyacencia, desfaceEnX, desfaceEnY, colorAristas);
    }
    
    public void rellenarEstrllas() {
        crearRelleno(estrella1.getCoordenadas2D(), estrella1.getMatrizAdyacencia(), estrella1.getCoordenadasDeRelleno2D(), Color.yellow, Color.magenta, this.getWidth()/ 2, YEstrellas[0]);
        crearRelleno(estrella2.getCoordenadas2D(), estrella2.getMatrizAdyacencia(), estrella2.getCoordenadasDeRelleno2D(), Color.yellow, Color.magenta, this.getWidth()/ 2, YEstrellas[1]);
        crearRelleno(estrella3.getCoordenadas2D(), estrella3.getMatrizAdyacencia(), estrella3.getCoordenadasDeRelleno2D(), Color.yellow, Color.magenta, this.getWidth()/ 2, YEstrellas[2]);
        crearRelleno(estrella4.getCoordenadas2D(), estrella4.getMatrizAdyacencia(), estrella4.getCoordenadasDeRelleno2D(), Color.yellow, Color.magenta, this.getWidth()/ 2, YEstrellas[3]);
        crearRelleno(estrella5.getCoordenadas2D(), estrella5.getMatrizAdyacencia(), estrella5.getCoordenadasDeRelleno2D(), Color.yellow, Color.magenta, this.getWidth()/ 2, YEstrellas[4]);
        crearRelleno(estrella6.getCoordenadas2D(), estrella6.getMatrizAdyacencia(), estrella6.getCoordenadasDeRelleno2D(), Color.yellow, Color.magenta, this.getWidth()/ 2, YEstrellas[5]);
        crearRelleno(estrella7.getCoordenadas2D(), estrella7.getMatrizAdyacencia(), estrella7.getCoordenadasDeRelleno2D(), Color.yellow, Color.magenta, this.getWidth()/ 2, YEstrellas[6]);
        crearRelleno(estrella8.getCoordenadas2D(), estrella8.getMatrizAdyacencia(), estrella8.getCoordenadasDeRelleno2D(), Color.yellow, Color.magenta, this.getWidth()/ 2, YEstrellas[7]);
        crearRelleno(estrella9.getCoordenadas2D(), estrella9.getMatrizAdyacencia(), estrella9.getCoordenadasDeRelleno2D(), Color.yellow, Color.magenta, this.getWidth()/ 2, YEstrellas[8]);
        crearRelleno(estrella10.getCoordenadas2D(), estrella10.getMatrizAdyacencia(), estrella10.getCoordenadasDeRelleno2D(), Color.yellow, Color.magenta, this.getWidth()/ 2, YEstrellas[9]);
    }
    
    @Override
    public void update(Graphics g) {
        lienzo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        lienzo.getGraphics().drawImage(imagenDeFondo.getImage(), 0, 0, 2080, 720, this);
        
        /* Estrellas */
        crearFigura(estrella1.getCoordenadas2D(), estrella1.getMatrizAdyacencia(),this.getWidth() / 2, YEstrellas[0], Color.yellow);
        crearFigura(estrella2.getCoordenadas2D(), estrella2.getMatrizAdyacencia(),this.getWidth() / 2, YEstrellas[1], Color.yellow);
        crearFigura(estrella3.getCoordenadas2D(), estrella3.getMatrizAdyacencia(),this.getWidth() / 2, YEstrellas[2], Color.yellow);
        crearFigura(estrella4.getCoordenadas2D(), estrella4.getMatrizAdyacencia(),this.getWidth() / 2, YEstrellas[3], Color.yellow);
        crearFigura(estrella5.getCoordenadas2D(), estrella5.getMatrizAdyacencia(),this.getWidth() / 2, YEstrellas[4], Color.yellow);
        crearFigura(estrella6.getCoordenadas2D(), estrella6.getMatrizAdyacencia(),this.getWidth() / 2, YEstrellas[5], Color.yellow);
        crearFigura(estrella7.getCoordenadas2D(), estrella7.getMatrizAdyacencia(),this.getWidth() / 2, YEstrellas[6], Color.yellow);
        crearFigura(estrella8.getCoordenadas2D(), estrella8.getMatrizAdyacencia(),this.getWidth() / 2, YEstrellas[7], Color.yellow);
        crearFigura(estrella9.getCoordenadas2D(), estrella9.getMatrizAdyacencia(),this.getWidth() / 2, YEstrellas[8], Color.yellow);
        crearFigura(estrella10.getCoordenadas2D(), estrella10.getMatrizAdyacencia(),this.getWidth() / 2, YEstrellas[9], Color.yellow);
        //rellenarEstrllas();
        
        
        /* Espiral */
        for(int i = 0; i < cohete.getCurva2DA1().length - 1; i++) {
            crearLinea(cohete.getCurva2DA1()[i][0], cohete.getCurva2DA1()[i][1], cohete.getCurva2DA1()[i + 1][0], cohete.getCurva2DA1()[i + 1][1], Color.red, this.getWidth()/ 2, this.getHeight() / 2);
            crearLinea(cohete.getCurva2DA2()[i][0], cohete.getCurva2DA2()[i][1], cohete.getCurva2DA2()[i + 1][0], cohete.getCurva2DA2()[i + 1][1], Color.red, this.getWidth()/ 2, this.getHeight() / 2);
            
            crearLinea(cohete.getCurva2DB1()[i][0], cohete.getCurva2DB1()[i][1], cohete.getCurva2DB1()[i + 1][0], cohete.getCurva2DB1()[i + 1][1], Color.red, this.getWidth()/ 2, this.getHeight() / 2);
            crearLinea(cohete.getCurva2DB2()[i][0], cohete.getCurva2DB2()[i][1], cohete.getCurva2DB2()[i + 1][0], cohete.getCurva2DB2()[i + 1][1], Color.red, this.getWidth()/ 2, this.getHeight() / 2);
        }
        
        /* Cohete */
        crearFigura(cohete.getCoordenadas2D(), cohete.getMatrizAdyacencia(),this.getWidth() / 2, this.getHeight() / 2, Color.lightGray);
        crearRelleno(cohete.getCoordenadas2D(), cohete.getMatrizAdyacenciaContorno(), cohete.getCoordenadasDeRelleno2D(), Color.lightGray, Color.white, this.getWidth() / 2, this.getHeight() / 2);

        crearFigura(cohete.getParabrisas2D(), cohete.getMatrizAdyacenciaParabrisas(),this.getWidth() / 2, this.getHeight() / 2, Color.cyan);
        crearRelleno(cohete.getParabrisas2D(), cohete.getMatrizAdyacenciaParabrisas(), cohete.getParabrisasRelleno2D(), Color.cyan, Color.cyan, this.getWidth() / 2, this.getHeight() / 2);

        getGraphics().drawImage(lienzo, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void run() {
        crearEstrella1.start();
        crearEstrella2.start();
        crearEstrella3.start();
        crearEstrella4.start();
        crearEstrella5.start();
        crearEstrella6.start();
        crearEstrella7.start();
        crearEstrella8.start();
        crearEstrella9.start();
        crearEstrella10.start();
        
        crearCohete.start();
        
        
        for(int i = 0; i < YEstrellas.length; i++) {
            YEstrellas[i] = (int) (Math.random()*(680-50)) + 50;
        }
        
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
