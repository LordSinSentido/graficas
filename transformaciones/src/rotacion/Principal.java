
package rotacion;

import javax.swing.JFrame;

public class Principal extends JFrame {
    Dibujo dibujo = new Dibujo();
    Thread dibujar = new Thread(dibujo);
    
    public Principal() {
        dibujo.setVisible(true);
        add(dibujo);
        dibujar.start();
        
        setTitle("Rotación");
        setResizable(false);
        pack();
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new Principal();
    }
}
