package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.UI.prefabs.Button;
import pl.edu.pb.wi.grafika.models.Method;
import pl.edu.pb.wi.grafika.utils.AnalysisUtils;
import pl.edu.pb.wi.grafika.utils.ColorUtil;

import javax.swing.*;
import java.awt.*;

public class AnalysisTools extends JPanel {

    RgbPicker rgbPicker = new RgbPicker();
    JPanel color1 = new JPanel();
    JPanel color2 = new JPanel();

    Color c1, c2, chosen;

    public AnalysisTools(AnalysisWindow analysisWindow) {
        super(new GridBagLayout());

        setBackground(Color.DARK_GRAY);

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.fill = 1;
        gc.weighty = .3f;

        add(rgbPicker, gc);

        gc.gridy = 1;
        gc.weighty = .1f;
        gc.weightx = .1f;
        add(color1, gc);
        gc.gridx = 1;
        gc.weighty = .2f;
        gc.weightx = .2f;
        JButton color1Button = new Button("Choose");
        color1Button.addActionListener(actionEvent -> {
            c1 = rgbPicker.getColor();
            color1.setBackground(c1);
        });
        add(color1Button, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.weighty = .1f;
        gc.weightx = .1f;
        add(color2, gc);
        gc.gridx = 1;
        gc.weighty = .2f;
        gc.weightx = .2f;
        JButton color2Button = new Button("Choose");
        color2Button.addActionListener(actionEvent -> {
            c2 = rgbPicker.getColor();
            color2.setBackground(c2);
        });
        add(color2Button, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        Button binarise = new Button("Binarise");
        add(binarise, gc);
        binarise.addActionListener(actionEvent -> {
            analysisWindow.panel.setImage(
                    AnalysisUtils.binarise(analysisWindow.panel.getImage(), c1, c2, rgbPicker.getColor())
            );
            chosen = rgbPicker.getColor();
        });

        gc.gridx = 1;
        Button reset = new Button("Reset");
        add(reset, gc);
        reset.addActionListener(actionEvent -> {
            analysisWindow.panel.setImage(Storage.getEditedImage());
        });

        gc.gridx = 0;
        gc.gridy = 4;
        Button dil = new Button("Dilatation");
        dil.addActionListener(actionEvent -> {
            analysisWindow.panel.setImage(
                    AnalysisUtils.morphology(
                            analysisWindow.panel.getImage(),
                            Method.DILATION,
                            chosen
                    )
            );

        });
        add(dil, gc);

        gc.gridx = 1;
        Button ero = new Button("Erosion");
        ero.addActionListener(actionEvent -> {
            analysisWindow.panel.setImage(
                    AnalysisUtils.morphology(
                            analysisWindow.panel.getImage(),
                            Method.EROSION,
                            chosen
                    )
            );
        });
        add(ero, gc);

        JLabel label = new JLabel("0.0%");

        gc.gridx = 0;
        gc.gridy = 5;
        Button result = new Button("Result");
        result.addActionListener(actionEvent -> {
            double res = AnalysisUtils.result(
                    analysisWindow.panel.getImage(),
                    chosen
            );
            label.setText(res + "%");
        });
        add(result, gc);

        gc.gridx = 1;
        label.setForeground(Color.WHITE);
        add(label, gc);
    }

    public void setC1(Color c){
        c1 = c;
        color1.setBackground(c1);
    }

    public void setC2(Color c){
        c2 = c;
        color2.setBackground(c2);
    }

    public RgbPicker getRgbPicker() {
        return rgbPicker;
    }
}
