//panel qui s'occupe d'afficher les caractères.
package jeudecarac;

import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class PanelCarac extends JPanel implements Observer {

    EvtCarac evtCarac;

    /** constructeur */
    public PanelCarac() {
        evtCarac = EvtCarac.getInstance();
        evtCarac.addObserver(this); //on observe evtCarac.
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    //repeindre le font (classe parent)
        g.setFont(new Font("sansserif", Font.BOLD, 60));    //police
        g.drawString("" + evtCarac.getCarac(), 100, 100);     //tracer le caractère
    }

    /** Fonction appelée automatiquement lorsqu'un élément observé est modifié (ici evtCarac) */
    public void update(Observable o, Object arg) {
        repaint();
    }
}
