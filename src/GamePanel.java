import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel(String difficulty) {
        JLabel label = new JLabel("Difficulty: " + difficulty);
        this.add(label);
        this.setBackground(Color.ORANGE);
    }
}
