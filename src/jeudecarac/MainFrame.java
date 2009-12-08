//Fenetre principale
package jeudecarac;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    EvtCarac evtCarac;
    private PanelCarac panelCarac;
    private JButton startButton;
    private JButton stopButton;
    private JButton quitButton;
    private JLabel scoreLabel;
    private JLabel typedLetter;

    /** constructeur */
    public MainFrame() {
        super();
        evtCarac = EvtCarac.getInstance();
        evtCarac.generateCarac();

        //Init panel
        panelCarac = new PanelCarac();
        //init Button
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        quitButton = new JButton("Quit");
        scoreLabel = new JLabel("Score = 0");
        typedLetter = new JLabel(" ");
        typedLetter.setHorizontalAlignment(JLabel.CENTER);
        typedLetter.setFont(new Font("sansserif", Font.BOLD,30 ));

        JPanel pan = new JPanel();
        pan.setLayout(new FlowLayout());
        pan.add(startButton);
        pan.add(stopButton);
        pan.add(quitButton);
        pan.add(scoreLabel);


        startButton.setEnabled(false);
        startButton.setFocusable(false);    //pour que la fenetre attrape l'évenement "touche appuyée"
        quitButton.setFocusable(false);
        stopButton.setFocusable(false);

        //Ajoute un écouteur pour les touches appuyées
        addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        quitButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });

        stopButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                panelCarac.stopTimer();
                startButton.setEnabled(true);
                stopButton.setEnabled(false);

            }
        });

        startButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                panelCarac.startTimer();
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //on ajoute les éléments graphiques
        this.setLayout(new BorderLayout());
        getContentPane().add(typedLetter, BorderLayout.NORTH);
        getContentPane().add(panelCarac, BorderLayout.CENTER); //ajoute le Panel à la fenetre
        getContentPane().add(pan, BorderLayout.SOUTH);

        //Ajout dasn kle flux

        setSize(new Dimension(300, 400));




    }

    /** Fction appelée quand une touche est préssée */
    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        evtCarac.setCarac(evt.getKeyChar());
        typedLetter.setText("You typed: " + evtCarac.getTypedCarac());     //affichage du caractère tapé
    }

    public void quit() {

        this.dispose();
        System.exit(0);
    }
}
