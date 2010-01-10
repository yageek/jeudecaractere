//permet de créer un timer. Utilisé pour déplacer le caractère à interval de temps régulier.
package jeudecarac;

public abstract class TimerPerso extends Thread {

    private int time;           //temps entre deux appels de la fonction.
    private boolean running;
    private boolean pause;

    public int getDelay() {
        return time;
    }

    public void setDelay(int time) {
        this.time = time;
    }

    //constructeur du TimerPerso
    public TimerPerso(int time, boolean pause) {
        this.time = time;
        this.pause=pause;
        running=true;
    }

    //démarrer le timer
    public void startTimer() {
        pause = true;
        Thread.currentThread().interrupt();
    }

    //arreter le timer.
    public void stopTimer() {
        pause = false;
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {
        while (running) {
            if(pause) iteration();
            try {
                Thread.sleep(time); //on s'arrète pendant le temps time avant d'exécuter la prochaine iteration()
            } catch (InterruptedException ex) {
            }
        }
    }

    public boolean isRunning(){
        return pause;
    }

    public void quit() {
        running=false;
        Thread.currentThread().interrupt();
    }

    public abstract void iteration(); 
}
