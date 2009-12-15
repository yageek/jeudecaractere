//utilise les pattern Observer et Singleton.
package jeudecarac;

import java.util.Observable;

public class EvtCarac extends Observable {   //classe observable

    private static EvtCarac evtCarac;
    private char typedCarac;                 //le caractère à affiche
    private char randomCarac;
    private int time;

    public int getTime() {
        return time;
    }

    public void generateCarac(int width){
        randomCarac=(char)(Math.random()*(122-97)+97);
        velocity(width);
        this.setChanged();      //notre état a changé
        this.notifyObservers(); //on prévient tout ceux qui observent.
    }

    public void velocity(int width){
        time = (int)((Math.random()*5+10)*300/width);
        this.setChanged();      //notre état a changé
        this.notifyObservers(); //on prévient tout ceux qui observent.
    }

    private EvtCarac() {
        typedCarac = ' ';
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
            this.typedCarac = carac;
            
            this.setChanged();      //notre état a changé
            this.notifyObservers(); //on prévient tout ceux qui observent.
        }
    }

    public char getRandomCarac() {
        return randomCarac;
    }

    public char getTypedCarac(){
        return typedCarac;
    }
}
