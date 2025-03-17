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
        
        Cell[][] grid = new Cell[gridSize][gridSize];
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                grid[row][col] = new Cell();
                gridPanel.add(grid[row][col]);
            }
        }
        return gridPanel;
    }
}
