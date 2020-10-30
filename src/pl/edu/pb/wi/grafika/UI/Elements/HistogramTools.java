package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.UI.prefabs.Button;
import pl.edu.pb.wi.grafika.utils.HistogramUtils;

import javax.swing.*;
import java.awt.*;

public class HistogramTools extends JPanel {
    public HistogramTools(HistogramView histogramView) {
        super(new GridBagLayout());

        setBackground(Color.DARK_GRAY);

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        JButton equalizeButton = new Button("Equalize");
        equalizeButton.setPreferredSize(new Dimension(150, 32));
        add(equalizeButton, gc);

        equalizeButton.addActionListener(actionEvent -> {
            Storage.setEditedImage(HistogramUtils.equalize(Storage.getEditedImage(), Storage.getHistograms()));
            histogramView.setHistograms();
        });

        JButton stretchButton = new Button("Stretch");
        stretchButton.setPreferredSize(new Dimension(150, 32));
        add(stretchButton, gc);

        stretchButton.addActionListener(actionEvent -> {
            Storage.setEditedImage(HistogramUtils.stretch(Storage.getEditedImage(), Storage.getHistograms()));
            histogramView.setHistograms();
        });
    }
}
