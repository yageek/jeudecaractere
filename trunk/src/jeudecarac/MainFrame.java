//Fenetre principale
package jeudecarac;

import java.awt.Dimension;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

    EvtCarac evtCarac;

    /** constructeur */
    public MainFrame() {
        evtCarac = EvtCarac.getInstance();

        //Ajoute un écouteur pour les touches appuyées
        addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new PanelCarac()); //ajoute le Panel à la fenetre
        setSize(new Dimension(300, 400));
    }

    /** Fction appelée quand une touche est préssée */
    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        evtCarac.setCarac(evt.getKeyChar());
    }
}
