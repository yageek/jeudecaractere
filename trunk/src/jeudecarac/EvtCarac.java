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

    public void randomHeight(int height){       //on génère une hauteur aléatoire pour le caractère. dépend du paramètre height : hauteur de la fenetre
        hauteurCaract = (int)(Math.random()*(height - 200)+50);
    }

    public int getHauteurCaract() {
        return hauteurCaract;
    }

    //génération d'un nouveau caractère aléatoire, à une vitesse aléatoire fonction du score, et à une hauteur aléatoire.
    public void generateCarac(int width, int score){
        randomCarac=(char)(Math.random()*(122-97)+97);
        velocity(width, score);     //on change la vitesse selon le score
        this.setChanged();          //notre état a changé
        this.notifyObservers();     //on prévient tous ceux qui observent.
    }

    public void velocity(int width, int score){     //on change la vitesse selon le score.
        if(score>15){
            time = (int)((Math.random()*5+15)*555/width/(0.1*score));       //la vitesse de défilement dépend de la largeur de la fenetre.
        }else{
            time = (int)((Math.random()*5+15)*555/width);       //indépendant du score si score <= 15
        }
        this.setChanged();      //notre état a changé
        this.notifyObservers(); //on prévient tout ceux qui observent.
    }

    private EvtCarac() {
        typedCarac = ' ';
        randomCarac = ' ';
        hauteurCaract = 100;
    }

    /* pour etre sur de n'avoir qu'une seule instance (pattern Singleton */
    public static EvtCarac getInstance() {
        if (evtCarac == null) {
            evtCarac = new EvtCarac();
        }
        return evtCarac;
    }

    /* Change le caractère à afficher */
    public void setCarac(char carac) {
        //on vérifie que c'est une lettre. Sinon on ne prévient personne.
        if (carac >= 97 && carac <= 122) {
            this.typedCarac = carac;
            
            this.setChanged();      //notre état a changé
            this.notifyObservers(); //on prévient tout ceux qui observent.
        }
    }

    //retourne le caractère tiré aléatoirement
    public char getRandomCarac() {
        return randomCarac;
    }

    //retourne le caractère qui a été tapé par le joueur.
    public char getTypedCarac(){
        return typedCarac;
    }
}
