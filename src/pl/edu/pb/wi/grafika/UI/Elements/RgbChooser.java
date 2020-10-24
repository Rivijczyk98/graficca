package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.Handlers.NumericInputHandler;
import pl.edu.pb.wi.grafika.Handlers.colorHandlers.EColor;
import pl.edu.pb.wi.grafika.Handlers.colorHandlers.rInputHandler;

import javax.swing.*;
import java.awt.*;

public class RgbChooser extends JPanel {

    JLabel[] labels = new JLabel[] {
            new JLabel("R"), new JLabel("G"), new JLabel("B")
    };
    JTextField[] fields = new JTextField[]{
            new JTextField("255"), new JTextField("255"), new JTextField("255")
    };

    public RgbChooser(RgbPicker picker) {
        super(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        setBackground(Color.DARK_GRAY);

        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0.3;

        add(labels[0], gc);
        gc.gridy = 1;
        add(fields[0], gc);

        gc.gridy = 0;
        gc.gridx = 1;
        add(labels[1], gc);
        gc.gridy = 1;
        add(fields[1], gc);

        gc.gridy = 0;
        gc.gridx = 2;
        add(labels[2], gc);
        gc.gridy = 1;
        add(fields[2], gc);

        for(JLabel l: labels) {
            l.setForeground(Color.WHITE);
        }

        fields[0].addKeyListener(new rInputHandler(fields[0], 0, 255, picker, EColor.R));
        fields[1].addKeyListener(new rInputHandler(fields[1], 0, 255, picker, EColor.G));
        fields[2].addKeyListener(new rInputHandler(fields[2], 0, 255, picker, EColor.B));
    }
}
