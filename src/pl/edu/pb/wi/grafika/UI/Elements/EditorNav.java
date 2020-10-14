package pl.edu.pb.wi.grafika.UI.Elements;

import javax.swing.*;
import java.awt.*;

public class EditorNav extends JPanel {

    private JLabel errorLog = new JLabel("");

    public EditorNav() {
        super(new GridBagLayout());

        setBackground(Color.DARK_GRAY);

        setBorder(BorderFactory.createMatteBorder(1,0,2,0, Color.WHITE));

        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.weightx = 0.03;
        gc.ipadx = 20;
        JLabel error = new JLabel("Errors: ");
        error.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        error.setForeground(Color.WHITE);

        Font f = error.getFont();
        error.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        add(error, gc);

        gc.gridx = 1;
        gc.weightx = 0.97;
        errorLog.setForeground(Color.WHITE);
        add(errorLog, gc);
    }

    public void changeErrorText(String text){
        errorLog.setText(text);
    }
}
