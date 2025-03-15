import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    public Screen() {
        this.setTitle("MineSweeper");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(600, 400));
        this.setLayout(new BorderLayout()); // Use BorderLayout for separation

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
