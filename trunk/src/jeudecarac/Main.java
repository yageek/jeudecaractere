package jeudecarac;

/**
 * Classe principale
 * @author Roussel - Heinrich
 */
public class Main {

    public static void main(String[] args) {
        //Mets la fenetre dans la file du dispatch thread. La méthode 'run' va etre appelée dans le dispatch thread quand ce sera son tour.
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });


    }
}
