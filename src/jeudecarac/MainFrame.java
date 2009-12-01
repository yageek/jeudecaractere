//Fenetre principale

package jeudecarac;

import java.awt.Dimension;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
    PanelCarac p;
    
    public MainFrame() {
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new PanelCarac();
        getContentPane().add(p);
        setSize(new Dimension(300, 400));
    }

    /** Une touche est préssée */
    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        p.drawLetter(evt.getKeyChar());
    }
}
