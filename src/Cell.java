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

    private Game game;

    public Cell(int row, int col, boolean[][] mineLocationList, Game game) {
        this.game = game;

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
        if (this.game.isGameOver() || this.isRevealed) {
            return;
        }

        if (SwingUtilities.isRightMouseButton(e)) {
            this.toggleFlag();
        } else if (SwingUtilities.isLeftMouseButton(e)) {
            this.revealCell();
        }
    }

    private void toggleFlag() {
        if (this.isRevealed) {
            return;
        }
        this.isFlagged = !this.isFlagged;
        this.setBackground(this.isFlagged ? Color.ORANGE : null);
    }

    private void revealCell() {
        if (this.isFlagged) {
            return;
        }

        this.isRevealed = true;
        if (this.isMine) {
            this.game.setGameOver(true);
            this.setBackground(Color.BLACK);
        } else {
            this.displayNeighbouringMines();
        }
    }

    private void displayNeighbouringMines() {
        JLabel label = new JLabel(String.valueOf(this.neighbouringMines));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label);
        this.setBackground(Color.LIGHT_GRAY);
        this.revalidate();
        this.repaint();
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
