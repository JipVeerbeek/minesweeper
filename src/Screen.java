import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    // Create panels
    private GamePanel gamePanel = new GamePanel("Medium");
    private DifficultyPanel difficultyPanel = new DifficultyPanel();

    // Constructor
    public Screen() {
        // Setup the JFrame
        this.setTitle("MineSweeper");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(600, 400));
        this.setLayout(new BorderLayout());

        // Setup the difficulty panel
        this.setupDifficultyPanel();

        // Setup the game panel
        this.addGamePanel();

        // Set the difficulty change listener
        this.difficultyPanel.setDifficultyChangeListener(newDifficulty -> {
            this.rebuildGamePanel(newDifficulty);
        });

        // Make the JFrame visible
        this.setVisible(true);
        // Close the application when the JFrame is closed
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setupDifficultyPanel() {
        this.add(this.difficultyPanel, BorderLayout.NORTH);
    }

    private void addGamePanel() {
        this.add(this.gamePanel, BorderLayout.CENTER);
    }

    private void rebuildGamePanel(String newDifficulty) {
        this.remove(this.gamePanel);
        this.gamePanel = new GamePanel(newDifficulty);
        this.add(this.gamePanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}
