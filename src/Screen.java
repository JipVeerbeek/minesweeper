import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    private GameSettings gameSettings = new GameSettings();
    private DifficultySettings difficultySettings = new DifficultySettings();
    // Create panels
    private DifficultyPanel difficultyPanel = new DifficultyPanel();
    private GamePanel gamePanel = new GamePanel(this.gameSettings, this.difficultySettings);

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
        this.difficultyPanel.setDifficultyChangeListener(difficulty -> {
            this.rebuildGamePanel(difficulty);
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

    private void rebuildGamePanel(String difficulty) {
        this.gameSettings.setGameOver(false);
        this.difficultySettings.setDifficulty(difficulty);

        this.remove(this.gamePanel);
        this.gamePanel = new GamePanel(this.gameSettings, this.difficultySettings);
        this.add(this.gamePanel, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }
}
