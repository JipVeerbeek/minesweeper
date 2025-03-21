import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cell extends JPanel {
    private boolean isRevealed;
    private boolean isFlagged;
    private boolean isMine;

    private int neighbouringMines;

    private GameSettings gameSettings;

    public Cell(GameSettings gameSettings, boolean isMine, int neighbouringMines) {
        this.gameSettings = gameSettings;

        this.isMine = isMine;
        this.neighbouringMines = neighbouringMines;

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Cell.this.registerMouseClick(e);
            }
        });
    }

    private void registerMouseClick(MouseEvent e) {
        if (this.gameSettings.isGameOver() || this.isRevealed) {
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
            this.gameSettings.setGameOver(true);
            new GameOverScreen(this.gameSettings, "Game Over! You hit a mine!");
            this.setBackground(Color.BLACK);
        } else {
            this.displayNeighbouringMinesCountLabel();
            this.gameSettings.decrementCellsToReveal();
            int cellsToReveal = this.gameSettings.getCellsToReveal();

            if (cellsToReveal == 0) {
                this.gameSettings.setGameOver(true);
                new GameOverScreen(this.gameSettings, "Congratulations! You won!");
            }
        }
    }

    private void displayNeighbouringMinesCountLabel() {
        JLabel label = new JLabel(String.valueOf(this.neighbouringMines));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label);
        this.setBackground(Color.LIGHT_GRAY);
        this.revalidate();
        this.repaint();
    }
}
