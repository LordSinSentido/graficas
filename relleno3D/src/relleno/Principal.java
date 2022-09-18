
package relleno;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Principal extends JFrame {
    Dibujo dibujo = new Dibujo();
    
    public Principal() {
        setTitle("Relleno");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
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
