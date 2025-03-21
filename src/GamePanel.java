import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private int totalMines;
    private int gridRowSize;
    private int gridColumnSize;
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
        boolean[][] mineLocationList = this.initializeMinesList();

        Cell[][] grid = new Cell[this.gridRowSize][this.gridColumnSize];
        for (int row = 0; row < this.gridRowSize; row++) {
            for (int col = 0; col < this.gridColumnSize; col++) {
                int neighbouringMines = 0;
                boolean isMine = false;
                if (mineLocationList[row][col]) {
                    isMine = true;
                } else {
                    neighbouringMines = this.getNeighbouringMinesCount(mineLocationList, row, col);
                }
                grid[row][col] = new Cell(gameSettings, isMine, neighbouringMines);
                gridPanel.add(grid[row][col]);
            }
        }
        return gridPanel;
    }

    private boolean[][] initializeMinesList() {
        boolean[][] mineLocationList = new boolean[this.gridRowSize][this.gridColumnSize];
        int minesPlaced = 0;
        while (minesPlaced < this.totalMines) {
            int row = this.randomize(this.gridRowSize);
            int col = this.randomize(this.gridColumnSize);

            if (!mineLocationList[row][col]) {
                mineLocationList[row][col] = true;
                minesPlaced++;
            }
        }

        return mineLocationList;
    }

    private int getNeighbouringMinesCount(boolean[][] mineLocationList, int row, int col) {
        int totalMines = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighbourRow = row + i;
                int neighbourCol = col + j;

                boolean isValidRow = neighbourRow >= 0 && neighbourRow < mineLocationList.length;
                boolean isValidCol = neighbourCol >= 0 && neighbourCol < mineLocationList[0].length;
                boolean isNotCurrentCell = i != 0 || j != 0;

                if (isValidRow && isValidCol && isNotCurrentCell) {
                    if (mineLocationList[neighbourRow][neighbourCol]) {
                        totalMines++;
                    }
                }
            }
        }
        return totalMines;
    } 

    private int randomize(int max) {
        return (int) (Math.random() * max);
    }
}
