package ant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        tourn = new JButton("Tournament");
        quit = new JButton("Quit");
        settings = new JButton("Settings");
        
        duelMode = new JPanel();
        uploadBrainDuel = new JButton("Upload Ant Brain");
        uploadWorldDuel = new JButton("Upload Ant World");
        randomWorldDuel = new JCheckBox("Random World");
        playDuel = new JButton("Play");
        backDuel = new JButton("Back");
        
        tournamentMode = new JPanel();
        uploadBrainTournament = new JButton("Upload Ant Brain");
        uploadWorldTournament = new JButton("Upload Ant World");
        randomWorldTournament = new JCheckBox("Random World");
        playTournament = new JButton("Play");
        backTournament = new JButton("Back");
        
        settingsDisplay = new JPanel();
        backSettings = new JButton("Back");
        
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
    
}
