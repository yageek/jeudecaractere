//utilise les pattern Observer et Singleton.
package jeudecarac;

import java.util.Observable;

public class EvtCarac extends Observable {   //classe observable

    private static EvtCarac evtCarac;
    private char typedCarac;                 //le caractère à affiche
    private char randomCarac;
    private int time;
    private int hauteurCaract;

    public int getTime() {
        return time;
    }

    public void randomHeight(int height){
        hauteurCaract = (int)(Math.random()*(height - 200)+100);
    }

    public int getHauteurCaract() {
        return hauteurCaract;
    }



    public void generateCarac(int width, int score){
        randomCarac=(char)(Math.random()*(122-97)+97);
        velocity(width, score);
        this.setChanged();      //notre état a changé
        this.notifyObservers(); //on prévient tout ceux qui observent.
    }

    public void velocity(int width, int score){
        if(score>15){
            time = (int)((Math.random()*5+15)*555/width/(0.1*score));
        }else{
            time = (int)((Math.random()*5+15)*555/width);
        }
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
