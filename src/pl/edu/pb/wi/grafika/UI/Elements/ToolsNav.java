package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.Handlers.NumericInputHandler;
import pl.edu.pb.wi.grafika.UI.Elements.Morphology.Window;
import pl.edu.pb.wi.grafika.UI.prefabs.Button;
import pl.edu.pb.wi.grafika.utils.ColorUtil;

import javax.swing.*;
import java.awt.*;

public class ToolsNav extends JPanel {

    private JTextField jpegCompressionPicker = new JTextField("70");
    private RgbPicker rgbPicker = new RgbPicker();

    private BasicImageModifications modifications = new BasicImageModifications();

    public ToolsNav() {
        super(new GridBagLayout());

        setBackground(Color.DARK_GRAY);

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;
        gc.ipadx = 2;
        gc.ipady = 10;

        gc.weighty = 0.02f;

        JLabel jpegHeader = new JLabel("Jpeg Compression Level");
        jpegHeader.setForeground(Color.WHITE);
        add(jpegHeader, gc);

        gc.gridy = 1;
        gc.weighty = 0.03f;
        add(jpegCompressionPicker, gc);

        gc.gridy = 2;
        gc.weighty = 0.15f;
        add(rgbPicker, gc);

        gc.gridy = 3;
        gc.weighty = 0.6f;
        add(modifications, gc);

        gc.gridy = 4;
        gc.weighty = 0.2f;
        add(new HistogramsBinarization(), gc);

        gc.gridy = 5;
        gc.weighty = .2f;
        JButton morphologyButton = new Button("Morphology");
        morphologyButton.setPreferredSize(new Dimension(150, 32));
        add(morphologyButton, gc);
        morphologyButton.addActionListener(actionEvent -> {
            if(Storage.isOriginalImagePresent()){
                new Window();
            } else {
                Storage.setErrorMessage("No image found!");
            }
        });


        gc.gridy = 6;
        gc.weighty = .2f;
        JButton lastButton = new Button("Analysis");
        lastButton.setPreferredSize(new Dimension(150, 32));
        add(lastButton, gc);
        lastButton.addActionListener(actionEvent -> {
            if(Storage.isOriginalImagePresent()){
                new AnalysisWindow();
            } else {
                Storage.setErrorMessage("No image found");
            }
        });

        jpegCompressionPicker.addKeyListener(new NumericInputHandler(jpegCompressionPicker, 0, 100));

    }

    public RgbPicker getRgbPicker(){
        return rgbPicker;
    }

    public int getJpegCompressionValue() {
        String s = jpegCompressionPicker.getText();
        return Integer.parseInt(s);
    }

}
