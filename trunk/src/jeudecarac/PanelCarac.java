//panel qui s'occupe d'afficher les caractères.
package jeudecarac;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelCarac extends JPanel implements Observer {

    private EvtCarac evtCarac;
    private int positionCaract;
    private int time;
    private Timer tim;


    /** constructeur */
    public PanelCarac() {
        evtCarac = EvtCarac.getInstance();
        evtCarac.addObserver(this); //on observe evtCarac.
        positionCaract = 0;
        time=(int)(Math.random()*30+10);
        ActionListener taskPerformer = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                positionCaract+=5;
                repaint();
            }
        };
        tim = new Timer(time, taskPerformer);
        tim.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    //repeindre le font (classe parent)
        g.setFont(new Font("sansserif", Font.BOLD, 60));    //police
        if(positionCaract >= this.getWidth()){
            positionCaract=0;
            evtCarac.generateCarac();
        }
        g.drawString("" + evtCarac.getRandomCarac(), positionCaract, 100);     //tracer le caractère
    }

    /** Fonction appelée automatiquement lorsqu'un élément observé est modifié (ici evtCarac) */
    public void update(Observable o, Object arg) {
        tim.setDelay(evtCarac.getTime());
        repaint();
    }

    public void stopTimer(){
        tim.stop();

    }

    public void startTimer(){
        tim.start();
    }
}
