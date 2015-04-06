package ant;

import java.awt.event.*;
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
    
    public AntGameFrameGUI()
    {
       createPanel();
       addPanel();
    }
    
    private void createPanel()
    {
        mMenu = new JPanel();
        duel = new JButton("Duel");
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
        quit.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        settings = new JButton("Settings");
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
        
        uploadWorldDuel = new JButton("Upload Ant World");
               
        randomWorldDuel = new JCheckBox("Random World");
        
        playDuel = new JButton("Play");
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
                
        uploadWorldTournament = new JButton("Upload Ant World");
        
        randomWorldTournament = new JCheckBox("Random World");
        
        playTournament = new JButton("Play");
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
