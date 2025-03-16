import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel(String difficulty) {
        int gridSize = this.determineDifficulty(difficulty);
        
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel("Difficulty: " + difficulty);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label, BorderLayout.NORTH);

        JPanel gridPanel = this.createGrid(gridSize);
        this.add(gridPanel, BorderLayout.CENTER);
    }

    private int determineDifficulty(String difficulty) {
        return switch (difficulty) {
            case "Easy" -> 10;
            case "Medium" -> 15;
            case "Hard" -> 20;
            default -> 15;
        };
    }

    private JPanel createGrid(int gridSize) {
        JPanel gridPanel = new JPanel(new GridLayout(gridSize, gridSize));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(5, 80, 40, 80));
        
        Cell[][] grid = new Cell[gridSize][gridSize]; // Declare the grid array
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = new Cell();
                gridPanel.add(grid[i][j]);
            }
        }
        return gridPanel;
    }
}
