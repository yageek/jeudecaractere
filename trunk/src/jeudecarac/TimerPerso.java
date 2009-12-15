/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudecarac;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 4heinyan
 */
public abstract class TimerPerso extends Thread {

    private int time;
    private boolean running;
    private boolean pause;

    public void setDelay(int time) {
        this.time = time;
    }

    public TimerPerso(int time, boolean pause) {
        this.time = time;
        this.pause=pause;
        running=true;
    }

    public void startTimer() {
        pause = true;
        Thread.currentThread().interrupt();
    }

    public void stopTimer() {
        pause = false;
        
    }

    @Override
    public void run() {
        while (running) {
            if(pause) iteration();
            /*while(!pause){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                }
            }*/
            try {
                Thread.sleep(time);
            } catch (InterruptedException ex) {
                
            }
        }
    }

    public void quit() {
        running=false;
        Thread.currentThread().interrupt();
    }

    public abstract void iteration();
}
