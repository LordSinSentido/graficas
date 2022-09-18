
package transformaciones;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Principal extends JFrame {
    Dibujo dibujo = new Dibujo();
    Thread dibujar = new Thread(dibujo);
    
    public Principal() {
        setTitle("Transformaciones");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
        dibujar.start();
        add(dibujo);
        dibujo.setVisible(true);
        
        pack();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new Principal();
    }
}
