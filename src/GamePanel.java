import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private int totalMines;
    private int gridRowSize;
    private int gridColumnSize;
    private boolean[][] mineLocationList;
    private GameSettings gameSettings;
    private DifficultySettings difficultySettings;

    public GamePanel(GameSettings gameSettings, DifficultySettings difficultySettings) {
        this.gameSettings = gameSettings;
        this.difficultySettings = difficultySettings;
        this.gridRowSize = this.difficultySettings.getGridRowSize();
        this.gridColumnSize = this.difficultySettings.getGridColumnSize();
        this.totalMines = this.difficultySettings.getTotalMines();
        
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel("Difficulty: " + this.difficultySettings.getDifficulty());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label, BorderLayout.NORTH);

        JPanel gridPanel = this.createGrid();
        this.add(gridPanel, BorderLayout.CENTER);
    }

    private JPanel createGrid() {
        JPanel gridPanel = new JPanel(new GridLayout(this.gridRowSize, this.gridColumnSize));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(5, 80, 40, 80));
        this.initializeMinesList();

        Cell[][] grid = new Cell[this.gridRowSize][this.gridColumnSize];
        for (int row = 0; row < this.gridRowSize; row++) {
            for (int col = 0; col < this.gridColumnSize; col++) {
                grid[row][col] = new Cell(row, col, this.mineLocationList, gameSettings);
                gridPanel.add(grid[row][col]);
            }
        }
        return gridPanel;
    }

    private void initializeMinesList() {
        this.mineLocationList = new boolean[this.gridRowSize][this.gridColumnSize];
        int minesPlaced = 0;
        while (minesPlaced < this.totalMines) {
            int row = this.randomize(this.gridRowSize);
            int col = this.randomize(this.gridColumnSize);

            if (!this.mineLocationList[row][col]) {
                this.mineLocationList[row][col] = true;
                minesPlaced++;
            }
        }
    }

    private int randomize(int max) {
        return (int) (Math.random() * max);
    }
}
