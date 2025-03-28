import javax.swing.*;
import java.awt.*;

public class GameOverScreen extends JFrame {
    private GameSettings gameSettings;

    public GameOverScreen(GameSettings gameSettings, String message) {
        this.gameSettings = gameSettings;

        this.setTitle("Game Over");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label, BorderLayout.CENTER);

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> {
            if (e.getActionCommand().equals("Restart")) {
                this.gameSettings.setGameOver(false);
                this.dispose();
            }
        });
        this.add(restartButton, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}
