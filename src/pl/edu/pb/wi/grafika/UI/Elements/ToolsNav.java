package pl.edu.pb.wi.grafika.UI.Elements;

import javax.swing.*;
import java.awt.*;

public class ToolsNav extends JPanel {

    private Tool lineButton;
    private Tool rectangleButton;
    private Tool circleButton;

    public ToolsNav() {
        super(new GridBagLayout());

        setBackground(Color.DARK_GRAY);

        GridBagConstraints gc = new GridBagConstraints();

        lineButton = new Tool("/");
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.weightx = 0.5f;
        add(lineButton, gc);

        ImageIcon icon = new ImageIcon("icons/rectangle.svg", "rectangle icon");
        rectangleButton = new Tool(icon);
        gc.gridy = 1;
        add(rectangleButton, gc);

        circleButton = new Tool("o");
        gc.gridx = 1;
        gc.gridy = 0;
        add(circleButton, gc);

    }

}
