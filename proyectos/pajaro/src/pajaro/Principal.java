
package pajaro;

import javax.swing.JFrame;

public class Principal extends JFrame {
    Dibujo dibujo = new Dibujo();
    Thread dibujar = new Thread(dibujo);
    
    public Principal() {
        dibujo.setVisible(true);
        add(dibujo);
        dibujar.start();
        
        setTitle("Proyecto del segundo parcial");
        setSize(800, 800);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new Principal();
    }
}
    