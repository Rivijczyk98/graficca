package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.DataStorage.Storage;

import javax.swing.*;
import java.awt.*;

public class StatisticsNav extends JPanel {

    private JLabel mousePosition;

    public StatisticsNav() {
        super(new GridBagLayout());

        setBackground(Color.DARK_GRAY);

        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1;
        mousePosition = new JLabel("X: 0, Y: 0");

        mousePosition.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        mousePosition.setForeground(Color.WHITE);

        add(mousePosition, gc);

        setBorder(BorderFactory.createMatteBorder(2,0,0,0, Color.WHITE));
    }

    public void updateMousePosition(){
        mousePosition.setText("X: " + Storage.mousePosition.x + ", Y: " + Storage.mousePosition.y);
    }
}
