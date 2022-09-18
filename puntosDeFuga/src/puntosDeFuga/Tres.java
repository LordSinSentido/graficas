
package puntosDeFuga;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tres extends Canvas {
    
    BufferedImage buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    BufferedImage lienzo;

    public Tres() {
        setBounds(0, 0, 500, 500);
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
    
     public void dibujarTresPuntoDeFuga(int x1, int y1, int z1, int large, Color c)
    {
        ArrayList<Integer> valuesX1 = new ArrayList<>();
        ArrayList<Integer> valuesY1 = new ArrayList<>();
        ArrayList<Integer> valuesX2 = new ArrayList<>();
        ArrayList<Integer> valuesY2 = new ArrayList<>();
        ArrayList<Integer> valuesX3 = new ArrayList<>();
        ArrayList<Integer> valuesY3 = new ArrayList<>();
        ArrayList<Integer> valuesX4 = new ArrayList<>();
        ArrayList<Integer> valuesY4 = new ArrayList<>();
        ArrayList<Integer> puntox = new ArrayList<>();
        ArrayList<Integer> puntoy = new ArrayList<>();
        ArrayList<Integer> puntox1 = new ArrayList<>();
        ArrayList<Integer> puntoy1 = new ArrayList<>();
        ArrayList<Integer> puntox2 = new ArrayList<>();
        ArrayList<Integer> puntoy2 = new ArrayList<>();
        int[] arrX = {0, 0, 0};
        int[] arrY = {0, 1, 0};

        double x, y, z = 200.0;
        double xc = -50.0;
        double yc = -40.0;
        double zc = -80.0;

        for (int cont = 0; cont < arrX.length; cont++)
        {
            double tempX, tempY;
            tempX = x1 + (arrX[cont] * large);
            tempY = y1 + (arrY[cont] * large);

            x = xc + (tempX - xc) * ((z - zc) / (z1 - zc));
            y = yc + (tempY - yc) * ((z - zc) / (z1 - zc));

            valuesX1.add((int) x);
            valuesY1.add((int) y);
        }

        for (int cont = 0; cont < arrX.length; cont++)
        {
            double tempX, tempY;
            double tempZ = z1;
            tempX = x1 + (arrX[cont] * large);
            tempY = y1 + (arrY[cont] * large - 5);

            x = xc + (tempX - xc + 30) * ((z - zc) / (tempZ - zc));
            y = yc + (tempY - yc - 5) * ((z - zc) / (tempZ - zc));

            valuesX2.add((int) x);
            valuesY2.add((int) y);
        }

        for (int cont = 0; cont < arrX.length; cont++)
        {
            double tempX, tempY;
            double tempZ = z1;
            tempX = x1 + (arrX[cont] * large);
            tempY = y1 + (arrY[cont] * large - 5);

            x = xc + (tempX - xc - 30) * ((z - zc) / (tempZ - zc));
            y = yc + (tempY - yc - 5) * ((z - zc) / (tempZ - zc));

            valuesX3.add((int) x);
            valuesY3.add((int) y);
        }

        for (int cont = 0; cont < arrX.length; cont++)
        {
            double tempX, tempY;
            double tempZ = z1;
            tempX = x1 + (arrX[cont] * large);
            tempY = y1 + (arrY[cont] * large - 5);

            x = xc + (tempX - xc) * ((z - zc) / (tempZ - zc));
            y = yc + (tempY - yc - 20) * ((z - zc) / (tempZ - zc));

            valuesX4.add((int) x);
            valuesY4.add((int) y);
        }

        puntox.add(550);
        puntoy.add(400);

        puntox1.add(300);
        puntoy1.add(400);

        puntox2.add(420);
        puntoy2.add(580);

        for (int cont = 1; cont < valuesX1.size(); cont++)
        {
            c = Color.CYAN;
            crearLinea(valuesX4.get(1), valuesY4.get(1), puntox2.get(0), puntoy2.get(0), c, 250, 250);

            c = Color.red;
            crearLinea(valuesX1.get(cont - 1), valuesY1.get(cont - 1), valuesX1.get(cont), valuesY1.get(cont), c, 250, 250);
            crearLinea(valuesX2.get(cont - 1), valuesY2.get(cont - 1), valuesX2.get(cont), valuesY2.get(cont), c, 250, 250);
            crearLinea(valuesX3.get(cont - 1), valuesY3.get(cont - 1), valuesX3.get(cont), valuesY3.get(cont), c, 250, 250);
            crearLinea(valuesX4.get(cont - 1), valuesY4.get(cont - 1), valuesX4.get(cont), valuesY4.get(cont), c, 250, 250);

            crearLinea(valuesX1.get(cont - 1), valuesY1.get(cont - 1), valuesX2.get(cont - 1), valuesY2.get(cont - 1), c, 250, 250);
            crearLinea(valuesX2.get(cont - 1), valuesY2.get(cont - 1), valuesX4.get(cont - 1), valuesY4.get(cont - 1), c, 250, 250);
            crearLinea(valuesX4.get(cont - 1), valuesY4.get(cont - 1), valuesX3.get(cont - 1), valuesY3.get(cont - 1), c, 250, 250);
            crearLinea(valuesX3.get(cont - 1), valuesY3.get(cont - 1), valuesX1.get(cont - 1), valuesY1.get(cont - 1), c, 250, 250);

            c = Color.CYAN;
            crearLinea(valuesX2.get(cont - 1), valuesY2.get(cont - 1), puntox.get(0), puntoy.get(0), c, 250, 250);
            crearLinea(valuesX4.get(cont - 1), valuesY4.get(cont - 1), puntox.get(0), puntoy.get(0), c, 250, 250);

            crearLinea(valuesX3.get(cont - 1), valuesY3.get(cont - 1), puntox1.get(0), puntoy1.get(0), c, 250, 250);
            crearLinea(valuesX4.get(cont - 1), valuesY4.get(cont - 1), puntox1.get(0), puntoy1.get(0), c, 250, 250);

            crearLinea(valuesX3.get(1), valuesY3.get(1), puntox2.get(0), puntoy2.get(0), c, 250, 250);
            crearLinea(valuesX2.get(1), valuesY2.get(1), puntox2.get(0), puntoy2.get(0), c, 250, 250);
        }
    }

    @Override
    public void update(Graphics g) {
        lienzo = new BufferedImage(1500, 1500, BufferedImage.TYPE_INT_RGB);
        dibujarTresPuntoDeFuga(-30, -50, 1, 50, Color.BLACK);
        getGraphics().drawImage(lienzo, -240, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }
}
