import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private int totalMines;
    private int gridSize;
    private boolean[][] mineLocationList;

    public GamePanel(String difficulty) {
        this.gridSize = this.determineDifficulty(difficulty);
        
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel("Difficulty: " + difficulty);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label, BorderLayout.NORTH);

        JPanel gridPanel = this.createGrid();
        this.add(gridPanel, BorderLayout.CENTER);
    }

    private int determineDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy":
                this.totalMines = 15;
                return 10;
            case "Medium":
                this.totalMines = 30;
                return 15;
            case "Hard":
                this.totalMines = 50;
                return 20;
            default:
                this.totalMines = 30;
                return 15;
        }
    }

    private JPanel createGrid() {
        JPanel gridPanel = new JPanel(new GridLayout(this.gridSize, this.gridSize));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(5, 80, 40, 80));

        this.initializeMinesList();

        Cell[][] grid = new Cell[this.gridSize][this.gridSize];
        for (int row = 0; row < this.gridSize; row++) {
            for (int col = 0; col < this.gridSize; col++) {
                grid[row][col] = new Cell(this.mineLocationList[row][col]);
                gridPanel.add(grid[row][col]);
            }
        }
        return gridPanel;
    }

    private void initializeMinesList() {
        this.mineLocationList = new boolean[this.gridSize][this.gridSize];
        int minesPlaced = 0;
        while (minesPlaced < this.totalMines) {
            int row = this.randomize(this.gridSize);
            int col = this.randomize(this.gridSize);

            if (!this.mineLocationList[row][col]) {
                this.mineLocationList[row][col] = true;
                minesPlaced++;
            }
        }
    }

    // private int neighbouringMines()

    private int randomize(int max) {
        return (int) (Math.random() * max);
    }
}
