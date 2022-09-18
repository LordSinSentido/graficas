
package ortogonal;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Principal extends JFrame {
    
    public Principal() {
        setTitle("Proyección ortogonal");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
        double[][] coordenadasFigura = new double[][] {
            {0,0,0}, {2,0,0}, {2,8,0}, {0,8,0}, {0,5,0}, {-2,5,0}, {-2,3,0}, {0,3,0},
            {0,0,2}, {2,0,2}, {2,8,2}, {0,8,2}, {0,5,2}, {-2,5,2}, {-2,3,2}, {0,3,2}
        };
        double[] vectorDireccion = new double[] {1,0,-1};
        boolean[][] matrizAdyacencia = {
            {true, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false},
            {true, true, true, false, false, false, false, false, false, true, false, false, false, false, false, false},
            {false, true, true, true, false, false, false, false, false, false, true, false, false, false, false, false},
            {false, false, true, true, true, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, true, true, true, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, true, true, true, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, true, true, true, false, false, false, false, false, false, false, false},
            {true, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false},
            {false, false, true, false, false, false, false, false, false, false, true, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
        };
        
        Proyeccion proyeccionOrtogonal = new Proyeccion(coordenadasFigura, matrizAdyacencia, vectorDireccion, 20);
        add(proyeccionOrtogonal);
        proyeccionOrtogonal.setVisible(true);
        
        pack();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    };
    
    public static void main(String[] args) {
        new Principal();
    };
};