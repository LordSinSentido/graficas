
package paralela;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Principal extends JFrame {
    
    public Principal() {
        setTitle("Proyección paralela y oblicua");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
        double[][] coordenadasFigura = new double[][] {{0,0,0}, {0,0,2}, {0,2,0}, {0,2,2}, {2,0,0}, {2,0,2}, {2,2,0}, {2,2,2}};
        double[] vectorDireccionParalela = new double[] {0,0,1};
        double[] vectorDireccionOblicua = new double[] {2,1,2};
        boolean[][] matrizAdyacencia = 
        {
            {true, true, true, false, true, false, false, false},
            {true, true, false, true, false, true, false, false},
            {true, false, true, true, false, false, true, false},
            {false, true, true, true, false, false, false, true},
            {true, false, false, false, true, true, true, false},
            {false, true, false, false, true, true, false, true},
            {false, false, true, false, true, false, true, true},
            {false, false, false, true, false, true, true, true}
        };
        
        Proyeccion proyeccionParalela = new Proyeccion(coordenadasFigura, matrizAdyacencia, vectorDireccionParalela, 100);
        add(proyeccionParalela);
        proyeccionParalela.setVisible(true);
        
        Proyeccion proyeccionOblicua = new Proyeccion(coordenadasFigura, matrizAdyacencia, vectorDireccionOblicua, 100);
        add(proyeccionOblicua);
        proyeccionOblicua.setVisible(true);
        
        pack();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    };
    
    public static void main(String[] args) {
        new Principal();
    };
};