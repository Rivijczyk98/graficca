package pl.edu.pb.wi.grafika.UI.Elements;

import javax.swing.*;
import java.awt.*;

public class HistogramsWindow extends JFrame {


    private HistogramView histogramView = new HistogramView();
    private HistogramTools histogramTools = new HistogramTools(histogramView);

    public HistogramsWindow() throws HeadlessException {
        super();

        setLayout(new GridBagLayout());
        setTitle("Histograms");
        setSize(1280, 720);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = 1;
        gc.weightx = 1f;
        gc.weighty = .05f;
        add(histogramTools, gc);

        gc.gridy = 1;
        gc.weighty = .95f;
        add(histogramView, gc);

        setVisible(true);
    }
}

