
package pdf;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

class Pixel
{
    private final BufferedImage bufferedImage;
    public static Pixel instance;
    public static int width, height;

    public Pixel(BufferedImage buffer)
    {
        bufferedImage = buffer;
        width = this.bufferedImage.getWidth();
        height = this.bufferedImage.getHeight();
        instance = this;
    }

    public void dibujaPixel(int x, int y, Color c)
    {
        bufferedImage.setRGB(x, y, c.getRGB());
    }

    public static void paintBackground(Color color)
    {
        for (int i = 0; i < instance.bufferedImage.getWidth(); i++)
        {
            for (int j = 0; j < instance.bufferedImage.getHeight(); j++)
            {
                instance.dibujaPixel(i, j, color);
            }
        }
    }
}

class Proyeccion
{
    public Proyeccion()
    {

    }

    public static void dibujaLinea(int x0, int y0, int x1, int y1, Color col)
    {
        Color c = col;
        float adyacente = (float) Float.max(x0, x1) - Float.min(x0, x1);
        float opuesto = (float) Float.max(y0, y1) - Float.min(y0, y1);
        float pendiente = (float) opuesto / adyacente;

        int sigX = 0;
        int sigY = 0;
        pendiente = Math.abs(pendiente);

        if (x0 < x1)
        {
            sigX = 1;
        }
        else
        {
            sigX = -1;
        }

        if (y0 < y1)
        {
            sigY = 1;
        }
        else
        {
            sigY = -1;
        }

        if (Math.toDegrees(Math.atan(pendiente)) > 45)
        {
            pendiente = (float) Math.abs(adyacente / opuesto);
            for (int i = 0; i <= Math.abs(opuesto); i++)
            {
                Pixel.instance.dibujaPixel(x0 + (int) (i * pendiente * sigX), y0 + (i * sigY), c);
            }
        }
        else
        {
            for (int h = 0; h <= Math.abs(adyacente); h++)
            {
                Pixel.instance.dibujaPixel(x0 + h * sigX, y0 + (int) (h * pendiente * sigY), c);
            }
        }
    }

    public static void dibujarUnPuntoDeFuga(int x1, int y1, int z1, int large, Color c)
    {
        ArrayList<Integer> valuesX1 = new ArrayList<>();
        ArrayList<Integer> valuesY1 = new ArrayList<>();
        ArrayList<Integer> valuesX2 = new ArrayList<>();
        ArrayList<Integer> valuesY2 = new ArrayList<>();
        ArrayList<Integer> puntox = new ArrayList<>();
        ArrayList<Integer> puntoy = new ArrayList<>();
        int[] arrX = {0, 1, 1, 0, 0};
        int[] arrY = {0, 0, 1, 1, 0};
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
            double tempZ = z1 + large;
            tempX = x1 + (arrX[cont] * large);
            tempY = y1 + (arrY[cont] * large);

            x = xc + (tempX - xc + 70) * ((z - zc) / (tempZ - zc));
            y = yc + (tempY - yc + 25) * ((z - zc) / (tempZ - zc));

            valuesX2.add((int) x);
            valuesY2.add((int) y);
        }

        puntox.add(470);
        puntoy.add(150);

        for (int cont = 1; cont < valuesX1.size(); cont++)
        {
            int contador = 0;
            c = Color.white;
            Proyeccion.dibujaLinea(valuesX1.get(cont - 1), valuesY1.get(cont - 1), valuesX1.get(cont), valuesY1.get(cont), c);
            Proyeccion.dibujaLinea(valuesX2.get(cont - 1), valuesY2.get(cont - 1), valuesX2.get(cont), valuesY2.get(cont), c);
            Proyeccion.dibujaLinea(valuesX1.get(cont - 1), valuesY1.get(cont - 1), valuesX2.get(cont - 1), valuesY2.get(cont - 1), c);
            c = Color.magenta;
            Proyeccion.dibujaLinea(valuesX2.get(cont - 1), valuesY2.get(cont - 1), puntox.get(contador), puntoy.get(contador), c);
        }
    }

    public static void dibujarDosPuntoDeFuga(int x1, int y1, int z1, int large, Color c)
    {
        ArrayList<Integer> valuesX1 = new ArrayList<>();
        ArrayList<Integer> valuesY1 = new ArrayList<>();
        ArrayList<Integer> valuesX2 = new ArrayList<>();
        ArrayList<Integer> valuesY2 = new ArrayList<>();
        ArrayList<Integer> puntox = new ArrayList<>();
        ArrayList<Integer> puntoy = new ArrayList<>();
        ArrayList<Integer> puntox1 = new ArrayList<>();
        ArrayList<Integer> puntoy1 = new ArrayList<>();
        int[] arrX = {0, 1, 1, 0, 0};
        int[] arrY = {0, 0, 1, 1, 0};
        double x, y, z = 200.0; //Plano de Proyeccion
        double xc = -50.0;
        double yc = -40.0;
        double zc = -80.0;

        for (int cont = 0; cont < arrX.length; cont++)
        {
            double tempX, tempY;
            tempX = x1 + (arrX[cont] * 35);
            tempY = y1 + (arrY[cont] * 35);

            x = xc + (tempX - xc) * ((z - zc) / (z1 - zc));
            y = yc + (tempY - yc - 30) * ((z - zc) / (z1 - zc));

            valuesX1.add((int) x);
            valuesY1.add((int) y);
        }

        for (int cont = 0; cont < arrX.length; cont++)
        {
            double tempX, tempY;
            double tempZ = z1 + large;
            tempX = x1 + (arrX[cont] * large);
            tempY = y1 + (arrY[cont] * 75);

            x = xc + (tempX - xc + 90) * ((z - zc) / (tempZ - zc));
            y = yc + (tempY - yc + 30) * ((z - zc) / (tempZ - zc));

            valuesX2.add((int) x);
            valuesY2.add((int) y);
        }

        puntox.add(380);
        puntoy.add(270);

        puntox1.add(100);
        puntoy1.add(270);

        for (int cont = 1; cont < valuesX1.size(); cont++)
        {
            c = Color.white;
            Proyeccion.dibujaLinea(valuesX1.get(cont - 1), valuesY1.get(cont - 1), valuesX1.get(cont), valuesY1.get(cont), c);
            Proyeccion.dibujaLinea(valuesX2.get(cont - 1), valuesY2.get(cont - 1), valuesX2.get(cont), valuesY2.get(cont), c);
            Proyeccion.dibujaLinea(valuesX1.get(cont - 1), valuesY1.get(cont - 1), valuesX2.get(cont - 1), valuesY2.get(cont - 1), c);
            c = Color.magenta;
           Proyeccion.dibujaLinea(valuesX1.get(2), valuesY1.get(2), puntox.get(0), puntoy.get(0), c);

            if (cont - 1 == 3)
            {

            }
            else
            {
               Proyeccion.dibujaLinea(valuesX2.get(cont - 1), valuesY2.get(cont - 1), puntox.get(0), puntoy.get(0), c);
            }

           Proyeccion.dibujaLinea(valuesX1.get(cont - 1), valuesY1.get(cont - 1), puntox1.get(0), puntoy1.get(0), c);

        }
    }

    public static void dibujarTresPuntoDeFuga(int x1, int y1, int z1, int large, Color c)
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
            c = Color.magenta;
            Proyeccion.dibujaLinea(valuesX4.get(1), valuesY4.get(1), puntox2.get(0), puntoy2.get(0), c);

            c = Color.white;
            Proyeccion.dibujaLinea(valuesX1.get(cont - 1), valuesY1.get(cont - 1), valuesX1.get(cont), valuesY1.get(cont), c);
            Proyeccion.dibujaLinea(valuesX2.get(cont - 1), valuesY2.get(cont - 1), valuesX2.get(cont), valuesY2.get(cont), c);
            Proyeccion.dibujaLinea(valuesX3.get(cont - 1), valuesY3.get(cont - 1), valuesX3.get(cont), valuesY3.get(cont), c);
            Proyeccion.dibujaLinea(valuesX4.get(cont - 1), valuesY4.get(cont - 1), valuesX4.get(cont), valuesY4.get(cont), c);

            Proyeccion.dibujaLinea(valuesX1.get(cont - 1), valuesY1.get(cont - 1), valuesX2.get(cont - 1), valuesY2.get(cont - 1), c);
            Proyeccion.dibujaLinea(valuesX2.get(cont - 1), valuesY2.get(cont - 1), valuesX4.get(cont - 1), valuesY4.get(cont - 1), c);
            Proyeccion.dibujaLinea(valuesX4.get(cont - 1), valuesY4.get(cont - 1), valuesX3.get(cont - 1), valuesY3.get(cont - 1), c);
            Proyeccion.dibujaLinea(valuesX3.get(cont - 1), valuesY3.get(cont - 1), valuesX1.get(cont - 1), valuesY1.get(cont - 1), c);

            c = Color.magenta;
            Proyeccion.dibujaLinea(valuesX2.get(cont - 1), valuesY2.get(cont - 1), puntox.get(0), puntoy.get(0), c);
            Proyeccion.dibujaLinea(valuesX4.get(cont - 1), valuesY4.get(cont - 1), puntox.get(0), puntoy.get(0), c);

            Proyeccion.dibujaLinea(valuesX3.get(cont - 1), valuesY3.get(cont - 1), puntox1.get(0), puntoy1.get(0), c);
            Proyeccion.dibujaLinea(valuesX4.get(cont - 1), valuesY4.get(cont - 1), puntox1.get(0), puntoy1.get(0), c);

            Proyeccion.dibujaLinea(valuesX3.get(1), valuesY3.get(1), puntox2.get(0), puntoy2.get(0), c);
            Proyeccion.dibujaLinea(valuesX2.get(1), valuesY2.get(1), puntox2.get(0), puntoy2.get(0), c);
        }
    }
}

