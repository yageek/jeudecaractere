package jeudecarac;

/**
 * Classe principale
 * @author Roussel - Heinrich
 */
public class Main {

    public static void main(String[] args) {

        //Lance la fenetre dans un nouveau thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });


    }
}
