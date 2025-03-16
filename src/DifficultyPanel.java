import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class DifficultyPanel extends JPanel implements ActionListener {
    /*
    * Consumer is een functionele interface die geen waarde teruggeeft (void),
    * maar wel een parameter accepteert. In dit geval accepteert de Consumer
    * een String. Dit is een handige manier om een methode te kunnen aanroepen
    * zonder dat je de methode expliciet hoeft te definiëren.
    */
    private Consumer<String> listener; 

    private String difficulty;

    /*
    * Deze methode accepteert een Consumer<String> als parameter. Dit betekent
    * dat je een methode kunt doorgeven die een String accepteert. Deze methode
    * wordt aangeroepen wanneer de difficulty wordt aangepast.
    */
    public void setDifficultyChangeListener(Consumer<String> listener) {
        this.listener = listener;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        this.add(button);
        return button;
    }

    // Constructor
    public DifficultyPanel() {
        createButton("Easy");
        createButton("Medium");
        createButton("Hard");
    }

    public void actionPerformed(ActionEvent e) {
        this.difficulty = e.getActionCommand();
        if (this.listener != null) {
            /*
            * Wanneer de difficulty wordt aangepast, wordt de listener aangeroepen
            * met de nieuwe difficulty als parameter.
            */
            this.listener.accept(this.difficulty);
        }
    }
}
