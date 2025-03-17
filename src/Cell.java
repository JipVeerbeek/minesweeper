import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cell extends JPanel {
    public boolean isMine;
    public boolean isFlagged;
    public boolean isRevealed;
    public int neighbouringMines;

    public Cell() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Cell.this.registerMouseClick(e);
            }
        });
    }

    private void registerMouseClick(MouseEvent e) {
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
                System.out.println("Game Over");
                this.setBackground(Color.BLACK);
            } else {
                this.isRevealed = true;
                this.setBackground(Color.LIGHT_GRAY);
            }
        } else {
            System.out.println("What you doing?");
        }
    }
}
