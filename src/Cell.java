import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cell extends JPanel {
    private int row;
    private int col;

    private boolean isRevealed;
    private boolean isFlagged;
    private boolean isMine;

    private boolean[][] mineLocationList;

    private int neighbouringMines;

    private GameOver gameOver;

    public Cell(int row, int col, boolean[][] mineLocationList, GameOver gameOver) {
        this.gameOver = gameOver;

        this.row = row;
        this.col = col;
        this.isMine = mineLocationList[row][col];

        this.mineLocationList = mineLocationList;

        if (!this.isMine) this.neighbouringMines = this.getNeighbouringMinesCount();

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Cell.this.registerMouseClick(e);
            }
        });
    }

    private void registerMouseClick(MouseEvent e) {
        if (this.gameOver.isGameOver()) {
            return;
        }

        if (SwingUtilities.isRightMouseButton(e)) {
            if (this.isRevealed) {
                return;
            }
            this.isFlagged = !this.isFlagged;
            if (this.isFlagged) {
                this.setBackground(Color.ORANGE);
            } else {
                this.setBackground(null);
            }
        } else if (SwingUtilities.isLeftMouseButton(e)) {
            if (this.isRevealed || this.isFlagged) {
                return;
            }
            if (this.isMine) {
                this.gameOver.setGameOver(true);
                this.setBackground(Color.BLACK);
            } else {
                this.isRevealed = true;
                JLabel label = new JLabel(String.valueOf(this.neighbouringMines));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                this.add(label);
                this.revalidate();
                this.repaint();
                this.setBackground(Color.LIGHT_GRAY);
            }
        } else {
            System.out.println("What you doing?");
        }
    }

    private int getNeighbouringMinesCount() {
        int totalMines = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighbourRow = this.row + i;
                int neighbourCol = this.col + j;

                boolean isValidRow = neighbourRow >= 0 && neighbourRow < this.mineLocationList.length;
                boolean isValidCol = neighbourCol >= 0 && neighbourCol < this.mineLocationList[0].length;
                boolean isNotCurrentCell = i != 0 || j != 0;

                if (isValidRow && isValidCol && isNotCurrentCell) {
                    if (this.mineLocationList[neighbourRow][neighbourCol]) {
                        totalMines++;
                    }
                }
            }
        }
        return totalMines;
    }
}
