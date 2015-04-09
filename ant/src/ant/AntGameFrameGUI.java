package ant;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author joycecorsel
 */

public class AntGameFrameGUI extends JFrame {
    private JPanel mMenu, tournamentMode, duelMode, settingsDisplay, gameDisplay;
    private JButton duel, tournament, quit, settings, 
            uploadBrainDuel, uploadWorldDuel,playDuel, backDuel, 
            uploadBrainTournament, uploadWorldTournament, playTournament, backTournament, backSettings, backFromDisplay;
    private JCheckBox randomWorldDuel, randomWorldTournament;
    private JLabel antImg, duelModeText, tournamentModeText, settingsText;
//    private JTextField pathName;
    
    public AntGameFrameGUI()
    {
       createPanel();
       addPanel();
    }
    
    private void createPanel()
    {
        // Loads image from within package
//        BufferedImage wPic = null;
//        try {
//            wPic = ImageIO.read(this.getClass().getResource("intro.jpg"));
//        } catch (IOException ex) {
//            Logger.getLogger(AntGameFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        antImg = new JLabel(new ImageIcon(wPic));
        
        antImg = new JLabel();
        antImg.setIcon(new ImageIcon(getClass().getResource("/ant/intro.jpg")));
        
        // Creates JPanels for different 'windows'
        mMenu = new JPanel();
        duelMode = new JPanel();
        tournamentMode = new JPanel();
//        settingsDisplay = new JPanel();
        
        // Creates features for Main Menu JPanel
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
        
        tournament = new JButton("Tournament");
        tournament.setFont(new Font("Comic Sans MS", 0, 11));
        tournament.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                getContentPane().removeAll();
                getContentPane().add(tournamentMode); //Adding to content pane, not to AntGameFrameGUI
                repaint();
                printAll(getGraphics());
            }
        });
        
        quit = new JButton("Exit");
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
        
        
        // Creates features for Duel Mode JPanel
        duelModeText = new JLabel("Multiplayer Mode");
        duelModeText.setFont(new Font("Comic Sans MS", 0, 36));
        
        uploadBrainDuel = new JButton("Upload Ant Brain");
        uploadBrainDuel.setFont(new Font("Comic Sans MS", 0, 11));
        uploadBrainDuel.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
//                String filename = f.getAbsolutePath();
//                pathName.setText(filename);
            }
        });
        
//        pathName = new JTextField();
//        pathName.setEditable(False);
        
        uploadWorldDuel = new JButton("Upload Ant World");
        uploadWorldDuel.setFont(new Font("Comic Sans MS", 0, 11));
        uploadWorldDuel.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
//                String filename = f.getAbsolutePath();
//                pathName.setText(filename);
            }
        });
        
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
        
        
        // Creates features for Tournament Mode JPanel
        tournamentModeText = new JLabel("Tournament Mode");
        tournamentModeText.setFont(new Font("Comic Sans MS", 0, 36));
        
        uploadBrainTournament = new JButton("Upload Ant Brain");
        uploadBrainTournament.setFont(new Font("Comic Sans MS", 0, 11));
        uploadBrainTournament.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
//                String filename = f.getAbsolutePath();
//                pathName.setText(filename);
            }
        });
        
        uploadWorldTournament = new JButton("Upload Ant World");
        uploadWorldTournament.setFont(new Font("Comic Sans MS", 0, 11));
        uploadWorldTournament.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
//                String filename = f.getAbsolutePath();
//                pathName.setText(filename);
            }
        });
        
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
        
        
        // Creates features for Settings JPanel
        settingsText = new JLabel("Settings");
        settingsText.setFont(new Font("Comic Sans MS", 0, 36));
        
//        backSettings = new JButton("Back");
//        backSettings.setFont(new Font("Comic Sans MS", 0, 11));
//        backSettings.addActionListener(new addButtonListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                getContentPane().removeAll();
//                getContentPane().add(mMenu); //Adding to content pane, not to AntGameFrameGUI
//                repaint();
//                printAll(getGraphics());
//            }
//        });
        
        gameDisplay = new JPanel();
        backFromDisplay = new JButton("Exit to Menu");
        backFromDisplay.setFont(new Font("Comic Sans MS", 0, 11));
        backFromDisplay.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                getContentPane().removeAll();
                getContentPane().add(mMenu); //Adding to content pane, not to AntGameFrameGUI
                repaint();
                printAll(getGraphics());
            }
        });
    }
    
    private void addPanel()
    {
        // Adding features to JPanels
        mMenu.add(antImg);
        mMenu.add(duel);
        mMenu.add(tournament);
//        mMenu.add(settings);
        mMenu.add(quit);
        // Adding main menu to JFrame - only main menu added so first thing shown
        add(mMenu);
        
        duelMode.add(duelModeText);
        duelMode.add(uploadBrainDuel);
//        duelMode.add(pathName);
        duelMode.add(uploadWorldDuel);
        duelMode.add(randomWorldDuel);
        duelMode.add(playDuel);
        duelMode.add(backDuel);
        
        tournamentMode.add(tournamentModeText);
        tournamentMode.add(uploadBrainTournament);
        tournamentMode.add(uploadWorldTournament);
        tournamentMode.add(randomWorldTournament);
        tournamentMode.add(playTournament);
        tournamentMode.add(backTournament);
        
//        settingsDisplay.add(settingsText);
//        settingsDisplay.add(backSettings);
        
        gameDisplay.add(backFromDisplay);
        
    }
    
    public static void main(String args[])
    {
        int WINDOW_WIDTH = 500;
        int WINDOW_HEIGHT = 500;
        AntGameFrameGUI frame = new AntGameFrameGUI();
        // JFrame properties
        frame.setTitle("Ant Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
//        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Class added to enable Button Clicking
    // To be left empty
    private static abstract class addButtonListener implements ActionListener {
        public addButtonListener() {
        }
    }
    
}
