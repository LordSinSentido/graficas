
package rectangular;

import javax.swing.JFrame;

public class Principal extends JFrame {
    Rectangular malla = new Rectangular(20);
    public Principal() {
        malla.setVisible(true);
        add(malla);
        
        setTitle("Mallado rectangular");
        pack();
        setSize(500, 500);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new Principal();
    }
}
