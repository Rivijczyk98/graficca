package pl.edu.pb.wi.grafika.UI.Elements;

import javax.swing.*;
import java.awt.*;

public class BezierWindow extends JFrame {

    public BezierTools bezierTools = new BezierTools(this);
    public BezierPanel bezierPanel = new BezierPanel(this);

    public BezierWindow() throws HeadlessException {
        super();

        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);
        setSize(1280, 720);

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = 1;
        gc.weightx = .2f;
        gc.weighty = 1f;

        add(bezierTools, gc);

        gc.weightx = .9f;
        gc.gridx = 1;
        add(bezierPanel, gc);

        setVisible(true);
    }

    public void setPosition(Point p){
        bezierTools.setPosition(p.getX(), p.getY());
    }

}
