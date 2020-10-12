package pl.edu.pb.wi.grafika.UI.Elements;

import javax.swing.*;
import java.awt.*;

public class Tool extends JButton {

    public Tool(String text) {
        super(text);

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        setPreferredSize(new Dimension(30,30));
    }

    public Tool(Icon icon){
        super(icon);

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        setPreferredSize(new Dimension(30,30));
    }
}
