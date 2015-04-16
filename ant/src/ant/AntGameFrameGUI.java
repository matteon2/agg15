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
    private JButton duel, tournament, quit, uploadBrainOneDuel, uploadBrainTwoDuel,
            uploadWorldDuel, playDuel, backDuel, uploadBrainTournament,
            uploadWorldTournament, playTournament, backTournament, backFromDisplay;
    private JCheckBox randomWorldDuel, randomWorldTournament;
    private JLabel antImg, duelModeText, tournamentModeText, playerOne, playerTwo,
            noOfPlayers;
    private JTextField pathName, playerName;

    private int count = 0;
    private boolean teamOne = false;
    private boolean teamTwo = false;
    private boolean world = false;

    private WorldParser wp;
    private RandomWorld rw;
    private AntBrainParser abp;

    private JPanel topPaneNames;
    private JPanel topPaneButton;
    private JPanel middlePaneWorld;
    private JPanel bottomPane;

    private JLabel cross1, cross2, cross3;
    private JLabel tick1, tick2, tick3;
    private JPanel flowL;
    private JPanel flowLa;

    /**
     * This calls the createPanel and addPanel method to add all components to the JFrame
     */
    public AntGameFrameGUI() {

        createPanel();
        addPanel();
    }

    /**
     * Creates all components for the GUI and adds functionality to them when required
     */
    private void createPanel() {

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

        Font font = new Font("Comic Sans MS", 0, 11);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);

        //Creates basic layout panels
        uploadBrainOneDuel = new JButton("Upload Red Ant Brain");
        uploadBrainOneDuel.setFont(new Font("Comic Sans MS", 0, 11));
        uploadBrainOneDuel.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Make player name local?
                String Player_Name = JOptionPane.showInputDialog("Player Name", "Enter Your Player Name");
                if ((Player_Name != null) && (Player_Name.length() > 0)) {
                    abp = new AntBrainParser();

//                    JFileChooser chooser = new JFileChooser();
//                    chooser.showOpenDialog(null);
//                    File f = chooser.getSelectedFile();
                    //                String filename = f.getAbsolutePath();
                    //                pathName.setText(filename);
                    //--------------------------------
                    JFileChooser chooser = new JFileChooser();
                    //chooser.showOpenDialog(null);
                    //File f = chooser.getSelectedFile(); //-------------------------------------->

                    int returnVal = chooser.showOpenDialog(null);
                    //Is a file selected?
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        //Check extension is .world
                        File f = chooser.getSelectedFile();
                        String extension = "";

                        //Do the rest (copy from below)
                        String path = f.getAbsolutePath(); //path to file
                        //System.out.println(path);

                        int i = path.lastIndexOf('.');
                        if (i > 0) {
                            extension = path.substring(i + 1);
                        }

                        //file extension test
                        //System.out.println(extension);
                        //Is the file extension correct?
                        if (extension.equals("ant")) {
                        //System.out.println("RIGHT FILE TYPE");

                            //do correct stuff here
                            abp.createBrain(path);

                            if (abp.isParsed()) {
                                //The file is correctly validated
                                //System.out.println("Correctly parsed");
                                tick1.setVisible(true);
                                cross1.setVisible(false);
                            //do this

                                //Change button to true (tick)
                                teamOne = true;
                                if (world && teamOne && teamTwo) {
                                    playDuel.setEnabled(true);
                                }

                            } else {
                                //If the file failed parsing, the play button is disabled
                                //System.out.println("Not parsed");
                                //String message = wp.getMessage();
                                teamOne = false;
                                playDuel.setEnabled(false);
                                tick1.setVisible(false);
                                cross1.setVisible(true);
                                JOptionPane.showMessageDialog(null, "Incorrect Brain", "File Not Parsed", JOptionPane.WARNING_MESSAGE);

                            }

                        } else {
                            //If the wrong file is chosen, disable the play button
                            teamOne = false;
                            playDuel.setEnabled(false);
                            tick1.setVisible(false);
                            cross1.setVisible(true);
                            JOptionPane.showMessageDialog(null, "Please choose a .ant file", "Incorrect File Type", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        //System.out.println("No file chosen or error");
                        JOptionPane.showMessageDialog(null, "No File Chosen", "Please Choose a File", JOptionPane.WARNING_MESSAGE);
                    }

                    //--------------------------------
                }
            }
        });

        uploadBrainTwoDuel = new JButton("Upload Black Ant Brain");
        uploadBrainTwoDuel.setFont(new Font("Comic Sans MS", 0, 11));
        uploadBrainTwoDuel.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Make player name local?
                String Player_Name = JOptionPane.showInputDialog("Player Name", "Enter Your Player Name");
                if ((Player_Name != null) && (Player_Name.length() > 0)) {
                    abp = new AntBrainParser();

//                    JFileChooser chooser = new JFileChooser();
//                    chooser.showOpenDialog(null);
//                    File f = chooser.getSelectedFile();
                    //                String filename = f.getAbsolutePath();
                    //                pathName.setText(filename);
                    //--------------------------------
                    JFileChooser chooser = new JFileChooser();
                    //chooser.showOpenDialog(null);
                    //File f = chooser.getSelectedFile(); //-------------------------------------->

                    int returnVal = chooser.showOpenDialog(null);
                    //Is a file selected?
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        //Check extension is .world
                        File f = chooser.getSelectedFile();
                        String extension = "";

                        //Do the rest (copy from below)
                        String path = f.getAbsolutePath(); //path to file
                        //System.out.println(path);

                        int i = path.lastIndexOf('.');
                        if (i > 0) {
                            extension = path.substring(i + 1);
                        }

                        //file extension test
                        //System.out.println(extension);
                        //Is the file extension correct?
                        if (extension.equals("ant")) {
                        //System.out.println("RIGHT FILE TYPE");

                            //do correct stuff here
                            abp.createBrain(path);

                            if (abp.isParsed()) {
                                //The file is correctly validated
                                //System.out.println("Correctly parsed");
                                tick2.setVisible(true);
                                cross2.setVisible(false);
                            //do this

                                //Change button to true (tick)
                                teamTwo = true;
                                if (world && teamOne && teamTwo) {
                                    playDuel.setEnabled(true);
                                } else {
                                    playDuel.setEnabled(false);
                                }

                            } else {
                                //If the file failed parsing, the play button is disabled
                                //System.out.println("Not parsed");
                                //String message = wp.getMessage();
                                teamTwo = false;
                                playDuel.setEnabled(false);
                                tick2.setVisible(false);
                                cross2.setVisible(true);
                                JOptionPane.showMessageDialog(null, "Incorrect Brain", "File Not Parsed", JOptionPane.WARNING_MESSAGE);

                            }

                        } else {
                            //If the wrong file is chosen, disable the play button
                            teamTwo = false;
                            playDuel.setEnabled(false);
                            tick2.setVisible(false);
                            cross2.setVisible(true);
                            JOptionPane.showMessageDialog(null, "Please choose a .ant file", "Incorrect File Type", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        //System.out.println("No file chosen or error");
                        JOptionPane.showMessageDialog(null, "No File Chosen", "Please Choose a File", JOptionPane.WARNING_MESSAGE);
                    }

                    //--------------------------------
                }
            }
        });

        uploadWorldDuel = new JButton("Upload Ant World");
        uploadWorldDuel.setFont(new Font("Comic Sans MS", 0, 11));
        uploadWorldDuel.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                wp = new WorldParser();

                JFileChooser chooser = new JFileChooser();
                //chooser.showOpenDialog(null);
                //File f = chooser.getSelectedFile(); //-------------------------------------->

                int returnVal = chooser.showOpenDialog(null);
                //Is a file selected?
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    //Check extension is .world
                    File f = chooser.getSelectedFile();
                    String extension = "";

                    //Do the rest (copy from below)
                    String path = f.getAbsolutePath(); //path to file
                    //System.out.println(path);

                    int i = path.lastIndexOf('.');
                    if (i > 0) {
                        extension = path.substring(i + 1);
                    }

                    //file extension test
                    //System.out.println(extension);
                    //Is the file extension correct?
                    if (extension.equals("world")) {
                        //System.out.println("RIGHT FILE TYPE");

                        //do correct stuff here
                        wp.openFile(path);
                        wp.readFile();
                        wp.toArray();

                        if (wp.checkX() && wp.checkY() && wp.checkCharacter() && wp.checkPerimeter()
                                && wp.checkEmptyPerimeter() && wp.checkRocks() && wp.checkFoodBlob()
                                && wp.checkRedAnthill() && wp.checkBlackAnthill()) {
                            //The file is correctly validated
                            //System.out.println("Correctly parsed");
                            tick3.setVisible(true);
                            cross3.setVisible(false);
                            //do this

                            //Change button to true (tick)
                            world = true;
                            if (world && teamOne && teamTwo) {
                                playDuel.setEnabled(true);
                            } else {
                                playDuel.setEnabled(false);
                            }

                        } else {
                            //If the file failed parsing, the play button is disabled
                            //System.out.println("Not parsed");
                            String message = wp.getMessage();
                            world = false;
                            playDuel.setEnabled(false);
                            tick3.setVisible(false);
                            cross3.setVisible(true);
                            JOptionPane.showMessageDialog(null, message, "File Not Parsed", JOptionPane.WARNING_MESSAGE);

                        }

                    } else {
                        //If the wrong file is chosen, disable the play button
                        world = false;
                        playDuel.setEnabled(false);
                        tick3.setVisible(false);
                        cross3.setVisible(true);
                        JOptionPane.showMessageDialog(null, "Please choose a .world file", "Incorrect File Type", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    //System.out.println("No file chosen or error");
                    JOptionPane.showMessageDialog(null, "No File Chosen", "Please Choose a File", JOptionPane.WARNING_MESSAGE);
                }
                //String path = f.getAbsolutePath(); //path to file
                //pathName.setText(path);

            }
        });

        randomWorldDuel = new JCheckBox("Random World");
        randomWorldDuel.setFont(new Font("Comic Sans MS", 0, 11));
        randomWorldDuel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //when button is checked
                if (e.getID() == ActionEvent.ACTION_PERFORMED) {
                    //System.out.println("HI");
                    //rw = new RandomWorld(); -> UNCOMMENT LATER

                    //Every other click either enable or disable options
                    if (count % 2 == 0) {
                        world = true;
                        uploadWorldDuel.setEnabled(false);//disable the upload world button
                        if (!tick3.isEnabled()) {
                            tick3.setVisible(false);
                            cross3.setVisible(false);
                        } else {
                            tick3.setVisible(true);
                            cross3.setVisible(false);
                        }

                        if (teamOne && teamTwo && world) {
                            playDuel.setEnabled(true);
                        } else {
                            playDuel.setEnabled(false);
                        }

                    } else {
                        if (!tick3.isEnabled()) {
                            tick3.setVisible(true);
                            cross3.setVisible(false);
                        } else {
                            tick3.setVisible(false);
                            cross3.setVisible(false);
                        }

                        world = false;
                        uploadWorldDuel.setEnabled(true);

                        if (teamOne && teamTwo && world) {
                            playDuel.setEnabled(true);
                        } else {
                            playDuel.setEnabled(false);
                        }
                    }
                    count++;
                }
            }
        });

        playDuel = new JButton("PLAY");
        playDuel.setEnabled(false);
        playDuel.setFont(new Font("Comic Sans MS", 0, 11));
        playDuel.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                getContentPane().removeAll();
                getContentPane().add(gameDisplay); //Adding to content pane, not to AntGameFrameGUI
                repaint();

                //rw.getRandomWorld(); -> UNCOMMENT LATER
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

        //Adds image to file
        cross1 = new JLabel();
        cross1.setIcon(new ImageIcon(getClass().getResource("/ant/cross.png")));
        cross2 = new JLabel();
        cross2.setIcon(new ImageIcon(getClass().getResource("/ant/cross.png")));
        cross3 = new JLabel();
        cross3.setIcon(new ImageIcon(getClass().getResource("/ant/cross.png")));

        tick1 = new JLabel();
        tick1.setIcon(new ImageIcon(getClass().getResource("/ant/tick.png")));
        tick2 = new JLabel();
        tick2.setIcon(new ImageIcon(getClass().getResource("/ant/tick.png")));
        tick3 = new JLabel();
        tick3.setIcon(new ImageIcon(getClass().getResource("/ant/tick.png")));

        topPaneNames = new JPanel(new GridLayout(0, 4));
        //topPaneButton = new JPanel();
        //middlePaneWorld = new JPanel();
        

        playerOne = new JLabel("Red Team");

        playerTwo = new JLabel("Black Team");

        //Adds first tick
        topPaneNames.add(playerOne);
        topPaneNames.add(uploadBrainOneDuel);
        topPaneNames.add(tick1);
        topPaneNames.add(cross1);
        tick1.setVisible(false);
        cross1.setVisible(false);

        topPaneNames.add(playerTwo);
        topPaneNames.add(uploadBrainTwoDuel);
        topPaneNames.add(tick2);
        topPaneNames.add(cross2);
        tick2.setVisible(false);
        cross2.setVisible(false);

        topPaneNames.add(randomWorldDuel);
        topPaneNames.add(uploadWorldDuel);
        topPaneNames.add(tick3);
        topPaneNames.add(cross3);
        tick3.setVisible(false);
        cross3.setVisible(false);

        // Creates features for Tournament Mode JPanel
        tournamentModeText = new JLabel("Tournament Mode");
        tournamentModeText.setFont(new Font("Comic Sans MS", 0, 36));

        uploadBrainTournament = new JButton("Upload Ant Brain");
        uploadBrainTournament.setFont(new Font("Comic Sans MS", 0, 11));
        uploadBrainTournament.addActionListener(new addButtonListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String Player_Name = JOptionPane.showInputDialog("Player Name", "Enter Your Player Name");
                if ((Player_Name != null) && (Player_Name.length() > 0)) {
                    JFileChooser chooser = new JFileChooser();
                    chooser.showOpenDialog(null);
                    File f = chooser.getSelectedFile();
                    //                String filename = f.getAbsolutePath();
                    //                pathName.setText(filename);    
                }
            }
        });

        randomWorldTournament = new JCheckBox("Random World");
        randomWorldTournament.setFont(new Font("Comic Sans MS", 0, 11));
        randomWorldTournament.setSelected(true);
        randomWorldTournament.setEnabled(false);
//        randomWorldTournament.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //when button is checked
//                //do nothing
//            }
//        });
             
        //Adds the no of players label
        noOfPlayers = new JLabel("No of Players: 0");
        noOfPlayers.setFont(new Font("Comic Sans MS", 0, 14));

        playTournament = new JButton("PLAY");
        playTournament.setEnabled(false);
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
        
        bottomPane = new JPanel(new GridLayout(0,2));
        bottomPane.add(uploadBrainTournament);
        bottomPane.add(randomWorldTournament);
        bottomPane.add(noOfPlayers);
        
        flowLa = new JPanel();
        flowLa.add(bottomPane);

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
        
        flowL = new JPanel();
        flowL.add(playTournament);
        flowL.add(backTournament);
    }

    /**
     * Adds all GUI components to the correct JPanels
     */
    private void addPanel() {
        // Adding features to JPanels
        mMenu.add(antImg);
        mMenu.add(duel);
        mMenu.add(tournament);
        mMenu.add(quit);
        // Adding main menu to JFrame - only main menu added so first thing shown
        add(mMenu);

        duelMode.add(duelModeText);
        duelMode.add(topPaneNames);
//        duelMode.add(uploadBrainOneDuel);
//        duelMode.add(uploadBrainTwoDuel);
        //duelMode.add(topPaneButton);
//        duelMode.add(pathName);
//        duelMode.add(uploadWorldDuel);
//        duelMode.add(randomWorldDuel);
        //duelMode.add(middlePaneWorld);
        duelMode.add(playDuel);
        duelMode.add(backDuel);

        tournamentMode.add(tournamentModeText);
//        tournamentMode.add(uploadBrainTournament);
//        tournamentMode.add(randomWorldTournament);
        tournamentMode.add(flowLa);
        tournamentMode.add(flowL);
//        tournamentMode.add(backTournament);
//        tournamentMode.add(noOfPlayers);

        gameDisplay.add(backFromDisplay);

    }

    /**
     * Main method - This runs the Ant Game
     * The JFrame is created and additional formatting features are added
     * @param args 
     */
    public static void main(String args[]) {
        int WINDOW_WIDTH = 650;
        int WINDOW_HEIGHT = 650;
        AntGameFrameGUI frame = new AntGameFrameGUI();
        // JFrame properties
        frame.setTitle("Ant Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        //frame.pack();
        frame.setResizable(false);
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
