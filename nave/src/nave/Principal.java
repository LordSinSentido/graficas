
package nave;

import javax.swing.JFrame;

public class Principal extends JFrame {
    Dibujo dibujo = new Dibujo();
    Thread dibujar = new Thread(dibujo);
    
    public Principal() {
        setTitle("Proyecto");
        setLayout(null);
        
        dibujar.start();
        add(dibujo);
        dibujo.setVisible(true);
        
        setSize(1080, 720);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new Principal();
    }
}
