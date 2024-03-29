//Fenetre principale
package jeudecarac;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
    private static JLabel scoreLabel;
    private JLabel typedLetter;
    private static int score;

    public static int getScore() {
        return score;
    }

    /** constructeur */
    public MainFrame() {
        super();
        evtCarac = EvtCarac.getInstance();
        evtCarac.generateCarac(this.getWidth(), 0);

        //Init panel
        panelCarac = new PanelCarac(this);
        //init Button
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        quitButton = new JButton("Quit");
        score=0;
        scoreLabel = new JLabel("Score = "+score);
        
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

        //si la fenetre est redimensionnée, on adapte la vitesse de défilement du caractère, pour que ce ne soit pas plus facile. Plus la largeur est grande, plus le caractère va vite.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                changeScore();
            }
        });
        
        //Ajoute un écouteur pour les touches appuyées
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        //écouteur pour les appuis sur les boutons.
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

        setSize(new Dimension(500, 400));

        this.setTitle("MANU & YAGEEK");
    }

    public void changeScore(){
        evtCarac.velocity(this.getWidth(), score);
    }

    //incrémenter le score.
    public static void incScore(){
        score++;
        scoreLabel.setText("Score = "+score);
    }

    //diminuer de 1 le score.
    public static void decScore(){
        score--;
        if(score<=0){
            scoreLabel.setText("Score = "+score+" => haha !");  //moquerie gratuite
        }else{
            scoreLabel.setText("Score = "+score);
        }
    }

    /** Fction appelée quand une touche est préssée */
    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        evtCarac.setCarac(evt.getKeyChar());
        evtCarac.randomHeight(this.getHeight());

        //On teste si c'est correct
        if(evtCarac.getTypedCarac() == evtCarac.getRandomCarac()    &&  panelCarac.getTim().isRunning()){//* & panelCarac.TestInRect()*/)   //si le timer est en train de tourner => jeu en cours.
            panelCarac.setPositionCaract(0);
            evtCarac.randomHeight(this.getHeight());
            evtCarac.generateCarac(this.getWidth(), score);
            typedLetter.setForeground(new Color(21,112,00));
            incScore();
        }else{
            typedLetter.setForeground(new Color(255,0,00));
            decScore();
        }
        typedLetter.setText("You typed: " + evtCarac.getTypedCarac());     //affichage du caractère tapé
    }

    //quitter l'application.
    public void quit() {
        this.dispose();
        System.exit(0);
    }
}
