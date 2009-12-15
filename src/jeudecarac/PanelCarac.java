//panel qui s'occupe d'afficher les caractères.
package jeudecarac;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class PanelCarac extends JPanel implements Observer {

    private EvtCarac evtCarac;
    private int positionCaract;
    private int time;
    private TimerPerso tim;
    private MainFrame mainframe;

    public TimerPerso getTim() {
        return tim;
    }
    /*private int xRect;
    private int yRect;
    private int widthRect;
    private int heightRect;
    */


    /** constructeur */
    public PanelCarac(MainFrame mainframe) {
        this.mainframe=mainframe;
        evtCarac = EvtCarac.getInstance();
        evtCarac.addObserver(this); //on observe evtCarac.
        positionCaract = 0;
        time=(int)(Math.random()*30+10);
        /*ActionListener taskPerformer = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                positionCaract+=5;
                repaint();
            }
        };
        tim = new Timer(time, taskPerformer);
        tim.start();*/
        tim = new TimerPerso(time, true){
            @Override
            public void iteration() {
                positionCaract+=5;
                repaint();
            }
        };
        tim.start();
        //Position du rectangle
        /*this.xRect = 500;
        this.yRect = 45;
        this.widthRect = 100;
        this.heightRect = 60;*/
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    //repeindre le font (classe parent)
        //on va dessiner un rectangle
        //g.setColor(Color.red);
        //g.fillRect(this.xRect, this.yRect,this.widthRect, this.heightRect);
        g.setColor(Color.black);
        g.setFont(new Font("sansserif", Font.BOLD, 60));    //police
        if(positionCaract >= this.getWidth()){
            positionCaract=0;
            evtCarac.generateCarac(mainframe.getWidth());
            MainFrame.decScore();
        }
        g.drawString("" + evtCarac.getRandomCarac(), positionCaract, 100);     //tracer le caractère
    }

    /** Fonction appelée automatiquement lorsqu'un élément observé est modifié (ici evtCarac) */
    public void update(Observable o, Object arg) {
        tim.setDelay(evtCarac.getTime());
        repaint();
    }

    public void stopTimer(){
        tim.stopTimer();

    }

    public void startTimer(){
        tim.startTimer();
    }

    public void setPositionCaract(int positionCaract) {
        this.positionCaract = positionCaract;
    }

    /*public int getxRect() {
        return xRect;
    }

    public int getyRect() {
        return yRect;
    }

    public int getHeightRect() {
        return heightRect;
    }

    public int getWidthRect() {
        return widthRect;
    }


    public boolean TestInRect(){
    if(positionCaract > this.getxRect() && positionCaract < (this.getxRect() + this.getHeightRect())) return true;
    else return false;
        
    }*/

}
