package ant;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;



/**
 *
 * @author joycecorsel
 */

public class AntGameFrameGUI extends JFrame {
    private JPanel mMenu, tournamentMode, duelMode, settingsDisplay, gameDisplay;
    private JButton duel, tourn, quit, settings, 
            uploadBrainDuel, uploadWorldDuel,playDuel, backDuel, 
            uploadBrainTournament, uploadWorldTournament, playTournament, backTournament, backSettings;
    private JCheckBox randomWorldDuel, randomWorldTournament;
    private JLabel antImg;
    
    public AntGameFrameGUI()
    {
       createPanel();
       addPanel();
    }
    
    private void createPanel()
    {
        BufferedImage wPic = null;
        try {
            wPic = ImageIO.read(this.getClass().getResource("intro.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(AntGameFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        antImg = new JLabel(new ImageIcon(wPic));
        
        mMenu = new JPanel();
        duel = new JButton("Duel");
        duel.setFont(new Font("Comic Sans MS", 0, 11));
        duel.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                getContentPane().removeAll();
                getContentPane().add(duelMode); //Adding to content pane, not to AntGameFrameGUI
                repaint();
                printAll(getGraphics());
            }
        });
        
        tourn = new JButton("Tournament");
        tourn.setFont(new Font("Comic Sans MS", 0, 11));
        tourn.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                getContentPane().removeAll();
                getContentPane().add(tournamentMode); //Adding to content pane, not to AntGameFrameGUI
                repaint();
                printAll(getGraphics());
            }
        });
        
        quit = new JButton("Quit");
        quit.setFont(new Font("Comic Sans MS", 0, 11));
        quit.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        
        settings = new JButton("Settings");
        settings.setFont(new Font("Comic Sans MS", 0, 11));
        settings.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                getContentPane().removeAll();
                getContentPane().add(settingsDisplay); //Adding to content pane, not to AntGameFrameGUI
                repaint();
                printAll(getGraphics());
            }
        });
        
        duelMode = new JPanel();
        
        uploadBrainDuel = new JButton("Upload Ant Brain");
        uploadBrainDuel.setFont(new Font("Comic Sans MS", 0, 11));
        uploadWorldDuel = new JButton("Upload Ant World");
        uploadWorldDuel.setFont(new Font("Comic Sans MS", 0, 11));
        
        randomWorldDuel = new JCheckBox("Random World");
        randomWorldDuel.setFont(new Font("Comic Sans MS", 0, 11));
        
        playDuel = new JButton("Play");
        playDuel.setFont(new Font("Comic Sans MS", 0, 11));
        playDuel.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                getContentPane().removeAll();
                getContentPane().add(gameDisplay); //Adding to content pane, not to AntGameFrameGUI
                repaint();
                printAll(getGraphics());
            }
        });
        
        backDuel = new JButton("Back");
        backDuel.setFont(new Font("Comic Sans MS", 0, 11));
        backDuel.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                getContentPane().removeAll();
                getContentPane().add(mMenu); //Adding to content pane, not to AntGameFrameGUI
                repaint();
                printAll(getGraphics());
            }
        });
        
        tournamentMode = new JPanel();
        
        uploadBrainTournament = new JButton("Upload Ant Brain");
        uploadBrainTournament.setFont(new Font("Comic Sans MS", 0, 11));
        
        uploadWorldTournament = new JButton("Upload Ant World");
        uploadWorldTournament.setFont(new Font("Comic Sans MS", 0, 11));
        
        randomWorldTournament = new JCheckBox("Random World");
        randomWorldTournament.setFont(new Font("Comic Sans MS", 0, 11));
        
        playTournament = new JButton("Play");
        playTournament.setFont(new Font("Comic Sans MS", 0, 11));
        playTournament.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                getContentPane().removeAll();
                getContentPane().add(gameDisplay); //Adding to content pane, not to AntGameFrameGUI
                repaint();
                printAll(getGraphics());
            }
        });
        
        backTournament = new JButton("Back");
        backTournament.setFont(new Font("Comic Sans MS", 0, 11));
        backTournament.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                getContentPane().removeAll();
                getContentPane().add(mMenu); //Adding to content pane, not to AntGameFrameGUI
                repaint();
                printAll(getGraphics());
            }
        });
        
        settingsDisplay = new JPanel();
        
        backSettings = new JButton("Back");
        backSettings.setFont(new Font("Comic Sans MS", 0, 11));
        backSettings.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                getContentPane().removeAll();
                getContentPane().add(mMenu); //Adding to content pane, not to AntGameFrameGUI
                repaint();
                printAll(getGraphics());
            }
        });
        
        gameDisplay = new JPanel();
    }
    
    private void addPanel()
    {
        mMenu.add(antImg);
        mMenu.add(duel);
        mMenu.add(tourn);
        mMenu.add(quit);
        mMenu.add(settings);
        add(mMenu);
        
        duelMode.add(uploadBrainDuel);
        duelMode.add(uploadWorldDuel);
        duelMode.add(randomWorldDuel);
        duelMode.add(playDuel);
        duelMode.add(backDuel);
        
        tournamentMode.add(uploadBrainTournament);
        tournamentMode.add(uploadWorldTournament);
        tournamentMode.add(randomWorldTournament);
        tournamentMode.add(playTournament);
        tournamentMode.add(backTournament);
        
        settingsDisplay.add(backSettings);
        
    }
    
    public static void main(String args[])
    {
        AntGameFrameGUI frame = new AntGameFrameGUI();
        frame.setTitle("Ant Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static abstract class addButtonListener implements ActionListener {

        public addButtonListener() {
        }
    }
    
}
