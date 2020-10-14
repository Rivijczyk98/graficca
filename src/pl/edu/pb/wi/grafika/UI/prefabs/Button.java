package pl.edu.pb.wi.grafika.UI.prefabs;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    public Button(Icon icon) {
        super(icon);
        init();
    }

    public Button(String text) {
        super(text);
        init();
    }

    public void init() {
        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(2,4,2, 4),
                        BorderFactory.createLineBorder(Color.ORANGE, 3)
                ),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        setPreferredSize(new Dimension(100, 32));
    }


}
