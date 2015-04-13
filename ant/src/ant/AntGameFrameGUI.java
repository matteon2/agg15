package ant;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author joycecorsel
 */

public class AntGameFrameGUI extends JFrame {
    private JPanel mMenu, tournamentMode, duelMode, gameDisplay;
    private JButton duel, tournament, quit, uploadBrainDuel, uploadWorldDuel, 
            playDuel, backDuel, uploadBrainTournament, uploadWorldTournament, 
            playTournament, backTournament, backFromDisplay;
    private JCheckBox randomWorldDuel, randomWorldTournament;
    private JLabel antImg, duelModeText, tournamentModeText;
    private JTextField pathName, playerName;
    
    public AntGameFrameGUI()
    {
       createPanel();
       addPanel();
       // formatComponents();       // THIS DOES NOT WORK YET
    }
    
    private void createPanel()
    {
        
        // Creates JPanels for different 'windows'
        mMenu = new JPanel();
        duelMode = new JPanel();
        tournamentMode = new JPanel();        
        
        // Creates features for Main Menu JPanel
        
        // Loads image from within package     
        antImg = new JLabel();
        antImg.setIcon(new ImageIcon(getClass().getResource("/ant/intro.jpg")));
        
        
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
        
        // Creates features for Duel Mode JPanel
        duelModeText = new JLabel("Duel Mode");
        duelModeText.setFont(new Font("Comic Sans MS", 0, 36));
        
        playerName = new JTextField("Enter Player Name");
        
        uploadBrainDuel = new JButton("Upload Ant Brain");
        uploadBrainDuel.setFont(new Font("Comic Sans MS", 0, 11));
        uploadBrainDuel.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String Player_Name = JOptionPane.showInputDialog("Player Name", "Enter Your Player Name");
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
        
        playDuel = new JButton("PLAY");
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
        
        playTournament = new JButton("PLAY");
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
        mMenu.add(quit);
        // Adding main menu to JFrame - only main menu added so first thing shown
        add(mMenu);
        
        duelMode.add(duelModeText);
        duelMode.add(playerName);
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
                
        gameDisplay.add(backFromDisplay);
        
    }
    
    private void formatComponents() {           // DOES NOT WORK YET
        // Formatting of mMenu
        
        javax.swing.GroupLayout mMenuLayout = new javax.swing.GroupLayout(mMenu);
        mMenu.setLayout(mMenuLayout);
        mMenuLayout.setHorizontalGroup(
            mMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mMenuLayout.createSequentialGroup()
                .addGroup(mMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mMenuLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(duel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(tournament)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(quit))
                    .addGroup(mMenuLayout.createSequentialGroup()
                        .addContainerGap(19, Short.MAX_VALUE)
                        .addComponent(antImg)))
                .addGap(18, 18, 18))
        );
        mMenuLayout.setVerticalGroup(
            mMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(antImg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(duel)
                    .addComponent(tournament)
                    .addComponent(quit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        
        // Formatting of duelMode
        
        javax.swing.GroupLayout duelModeLayout = new javax.swing.GroupLayout(duelMode);
        duelMode.setLayout(duelModeLayout);
        duelModeLayout.setHorizontalGroup(
            duelModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(duelModeLayout.createSequentialGroup()
                .addGroup(duelModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(duelModeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backDuel))
                    .addGroup(duelModeLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addGroup(duelModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(duelModeText)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, duelModeLayout.createSequentialGroup()
                                .addComponent(playDuel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7))))
                    .addGroup(duelModeLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(duelModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(uploadWorldDuel)
                            .addComponent(uploadBrainDuel))
                        .addGap(18, 18, 18)
                        .addComponent(randomWorldDuel)))
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, duelModeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(playerName, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );
        duelModeLayout.setVerticalGroup(
            duelModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(duelModeLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(duelModeText)
                .addGap(80, 80, 80)
                .addComponent(playerName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(uploadBrainDuel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(duelModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uploadWorldDuel)
                    .addComponent(randomWorldDuel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(playDuel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(backDuel)
                .addContainerGap())
        );

        //javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(duelMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(duelMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        
        // Formatting of tournamentMode

        pack();
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
        //frame.pack();
        frame.setLocationRelativeTo(null);  // to show at center of screen
        frame.setVisible(true);
    }

    // Class added to enable Button Clicking
    // To be left empty
    private static abstract class addButtonListener implements ActionListener {
        public addButtonListener() {
        }
    }
    
}
