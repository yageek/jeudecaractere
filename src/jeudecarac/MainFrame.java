//Fenetre principale
package jeudecarac;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class MainFrame extends JFrame {

    EvtCarac evtCarac;
    


    /** constructeur */
    public MainFrame() {
        evtCarac = EvtCarac.getInstance();
        evtCarac.generateCarac();

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
        //evtCarac.generateCarac();
    }
}
