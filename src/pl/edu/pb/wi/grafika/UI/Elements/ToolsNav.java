package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.Handlers.NumericInputHandler;

import javax.swing.*;
import java.awt.*;

public class ToolsNav extends JPanel {

    private JTextField jpegCompressionPicker = new JTextField("70");

    public ToolsNav() {
        super(new GridBagLayout());

        setBackground(Color.DARK_GRAY);

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;
        gc.ipadx = 2;
        gc.ipady = 10;

        JLabel jpegHeader = new JLabel("Jpeg Compression Level");
        jpegHeader.setForeground(Color.WHITE);
        add(jpegHeader, gc);

        gc.gridy = 1;
        add(jpegCompressionPicker, gc);

        jpegCompressionPicker.addKeyListener(new NumericInputHandler(jpegCompressionPicker, 0, 100));

    }

    public int getJpegCompressionValue() {
        String s = jpegCompressionPicker.getText();
        return Integer.parseInt(s);
    }

}
