package pl.edu.pb.wi.grafika.UI;

import pl.edu.pb.wi.grafika.Handlers.MouseMovementStatistics;
import pl.edu.pb.wi.grafika.UI.Elements.*;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    TopNav topNav = new TopNav();
    ToolsNav tools = new ToolsNav();
    PaintPanel paintPanel = new PaintPanel();
    StatisticsNav statisticsNav = new StatisticsNav();
    EditorNav editorNav = new EditorNav();

    public MainWindow() throws HeadlessException {
        super();

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        setTitle("Graphicca");
        setSize(1280, 720);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1f;
        gc.weighty = 0.05f;
        gc.gridwidth = 2;
        add(topNav, gc);

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1f;
        gc.weighty = 0.05f;
        gc.gridy = 1;
        gc.gridwidth = 2;
        add(editorNav, gc);

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 0.1f;
        gc.weighty = 0.88f;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        add(tools, gc);

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 0.9f;
        gc.gridx = 1;
        add(paintPanel, gc);

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1f;
        gc.weighty = 0.02f;
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        add(statisticsNav, gc);

        paintPanel.addMouseMotionListener(new MouseMovementStatistics(statisticsNav));

        setVisible(true);
    }
}
