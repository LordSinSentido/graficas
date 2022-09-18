package ortogonal;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Proyeccion extends Canvas {
    BufferedImage buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    BufferedImage lienzo = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    
    private double[][] coordenadas3D;
    private boolean[][] matrizAdyacencia;
    private double[] vectorDireccion;
    private int factorEscala;
    private double[][] coordenadas2D;
    
    public Proyeccion(double[][] coordenadas3D, boolean[][] matrizAdyacencia, double[] vectorDireccion, int factorEscala) {
        this.coordenadas3D = coordenadas3D;
        this.matrizAdyacencia = matrizAdyacencia;
        this.vectorDireccion = vectorDireccion;
        this.factorEscala = factorEscala;
        
        setBounds(0, 0, 500, 500);
        setVisible(true);
    };
    
    public double[][] proyectarFigura() {
        double[][] coordenadasProyectadas = new double[coordenadas3D.length][2];
        
        for(int i = 0; i < coordenadas3D.length; i++) {
            for(int j = 0; j < coordenadas3D[0].length - 1; j++) {
                coordenadasProyectadas[i][j] = (coordenadas3D[i][j] - (vectorDireccion[j] / vectorDireccion[2]) * coordenadas3D[i][2]) * factorEscala;
            };
        };
        
        return coordenadasProyectadas;
    };
    
    public void crearLinea(double x1, double y1, double x2, double y2, Color color, int desfaseX, int desfaseY) {
        buffer.setRGB(0, 0, color.getRGB());
        double dx = x2 - x1;
        double dy = y2 - y1;
        double steps = 0;
        
        if(Math.abs(dx) >= Math.abs(dy)) {
            steps = Math.abs(dx);
        } else {
            steps = Math.abs(dy);
        };
        
        double xinc = dx / steps;
        double yinc = dy / steps;
        
        double x = x1;
        double y = y1;
        lienzo.getGraphics().drawImage(buffer, desfaseX + (int) Math.round(x), desfaseY - (int) Math.round(y), this);
        
        for(int i = 1; i <= steps; i++) {
            x = x + xinc;
            y = y + yinc;
            lienzo.getGraphics().drawImage(buffer, desfaseX + (int) Math.round(x), desfaseY - (int) Math.round(y), this);
        };
    };

    @Override
    public void update(Graphics g) {
        coordenadas2D = proyectarFigura();
        
        for(int i = 0; i < matrizAdyacencia.length; i++) {
            for(int j = 0; j < matrizAdyacencia.length; j++) {
                if(matrizAdyacencia[i][j] == true) {
                    crearLinea(coordenadas2D[i][0], coordenadas2D[i][1], coordenadas2D[j][0], coordenadas2D[j][1], Color.black, getWidth() / 2, getHeight() / 2);
                };
            };
        };
        getGraphics().drawImage(lienzo, 0, 0, this);
    };

    @Override
    public void paint(Graphics g) {
        update(g);
    };
};
