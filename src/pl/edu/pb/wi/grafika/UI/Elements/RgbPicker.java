package pl.edu.pb.wi.grafika.UI.Elements;

import javax.swing.*;
import java.awt.*;

public class RgbPicker extends JPanel {

    public RgbPicker() {
        super(new GridBagLayout());

        setBackground(Color.DARK_GRAY);

        JPanel picker = new JPanel();
        picker.setPreferredSize(new Dimension(200, 200));
        add(picker);

    }
}
