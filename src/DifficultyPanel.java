import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DifficultyPanel implements ActionListener {
    public DifficultyPanel() {
        JPanel difficultyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton easy = new JButton("Easy");
        JButton medium = new JButton("Medium");
        JButton hard = new JButton("Hard");
        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);
        difficultyPanel.add(easy);
        difficultyPanel.add(medium);
        difficultyPanel.add(hard);
    }

    // private void initializeDifficulties() {
        
    // }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Easy":
                // this.initializeButtons(15, 15);
                break;
            case "Medium":
                // this.initializeButtons(25, 25);
                break;
            case "Hard":
                // this.initializeButtons(50, 50);
                break;
            default:
                break;
        }
    }

    // public void initializeButtons(int x, int y) {
    //     JPanel gamePanel = new JPanel(new GridLayout(x, y)); // Create a new panel for the game grid
    //     Dimension screenSize = this.getContentPane().getSize();
    //     int panelWidth = (int) (screenSize.width * 0.8);
    //     int panelHeight = (int) (screenSize.height * 0.75);
    //     gamePanel.setPreferredSize(new Dimension(panelWidth, panelHeight)); // Set panel size to 80% width and 60% height

    //     for (int i = 0; i < x; i++) {
    //         for (int j = 0; j < y; j++) {
    //             JButton button = new JButton();
    //             button.setPreferredSize(new Dimension(50, 50)); // Optional: Set button size
    //             gamePanel.add(button);
    //             // button.setText(i + "" +j);
    //         }
    //     }

    //     JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)); // Wrapper to center the game panel
    //     wrapperPanel.add(gamePanel);

    //     this.getContentPane().removeAll(); // Clear the frame
    //     this.add(wrapperPanel, BorderLayout.CENTER); // Add the wrapper panel to the center
    //     this.initializeDifficulties(); // Re-add the difficulty buttons
    //     this.revalidate(); // Refresh the frame
    //     this.repaint();
    // }

    
}
