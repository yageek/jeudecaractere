//panel qui s'occupe d'afficher les caractères.

package jeudecarac;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelCarac extends JPanel{
    private char plop;

    /** constructeur */
    public PanelCarac() {
        plop=0;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);    //repeindre le font (classe parent)
        if(plop!=0){
            g.setFont(new Font("sansserif", Font.BOLD, 60));
            g.drawString(""+plop, 100, 100);
        }
    }

    /** Affiche la lettre en paramètre */
    public void drawLetter(char plop){
        this.plop=plop;
        update();
    }

    /** mise à jour du Panel */
    public void update(){
        repaint();
    }
}
