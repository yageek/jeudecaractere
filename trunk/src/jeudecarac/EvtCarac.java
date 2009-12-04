//utilise les pattern Observer et Singleton.
package jeudecarac;

import java.util.Observable;

public class EvtCarac extends Observable {   //classe observable

    private static EvtCarac evtCarac;
    private char carac;                 //le caractère à affiche
    private char randomCarac;
    private int time;

    public int getTime() {
        return time;
    }

    public void generateCarac(){
        randomCarac=(char)(Math.random()*(122-97)+97);
        time = (int)(Math.random()*30+10);
        this.setChanged();      //notre état a changé
        this.notifyObservers(); //on prévient tout ceux qui observent.
    }

    private EvtCarac() {
        carac = ' ';
       randomCarac = ' ';
    }

    /** pour etre sur de n'avoir qu'une seule instance */
    public static EvtCarac getInstance() {
        if (evtCarac == null) {
            evtCarac = new EvtCarac();
        }
        return evtCarac;
    }

    /** Change la caractère à afficher */
    public void setCarac(char carac) {
        //on vérifie que c'est une lettre. Sinon on ne prévient personne.
        if (carac >= 97 && carac <= 122) {
            this.carac = carac;
            
            this.setChanged();      //notre état a changé
            this.notifyObservers(); //on prévient tout ceux qui observent.
        }
    }

    public char getCarac() {
        return randomCarac;
    }
}
