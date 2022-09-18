
package puntosDeFuga;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Principal extends JFrame {
    public Principal() {
        setTitle("Puntos de fuga");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
        Uno uno = new Uno();
        add(uno);
        uno.setVisible(true);
        
        Dos dos = new Dos();
        add(dos);
        dos.setVisible(true);
        
        Tres tres = new Tres();
        add(tres);
        tres.setVisible(true);
        
        pack();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    };
    
    public static void main(String[] args) {
        new Principal();
    };
}
