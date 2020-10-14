package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.Handlers.LoadImageButtonHandler;
import pl.edu.pb.wi.grafika.Handlers.SaveJpegButtonHandler;
import pl.edu.pb.wi.grafika.Handlers.SaveP3ButtonHandler;
import pl.edu.pb.wi.grafika.Handlers.SaveP6ButtonHandler;
import pl.edu.pb.wi.grafika.UI.MainWindow;
import pl.edu.pb.wi.grafika.UI.prefabs.Button;

import javax.swing.*;
import java.awt.*;

public class TopNav extends JPanel {

    private JButton loadFile = new Button("Load");
    private JButton saveFileAsP3 = new Button("Save P3");
    private JButton saveFileAsP6 = new Button("Save P6");
    private JButton saveFileAsJPEG = new Button("Save JPEG");

    public TopNav(PaintPanel _paintPanel) {
        super(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 0.02f;

        loadFile.addActionListener(new LoadImageButtonHandler(_paintPanel));
        add(loadFile, gc);

        saveFileAsP3.addActionListener(new SaveP3ButtonHandler());
        add(saveFileAsP3, gc);

        saveFileAsP6.addActionListener(new SaveP6ButtonHandler());
        add(saveFileAsP6, gc);

        saveFileAsJPEG.setPreferredSize(new Dimension(150, 32));
        saveFileAsJPEG.addActionListener(new SaveJpegButtonHandler());
        add(saveFileAsJPEG, gc);

        gc.weightx = 0.92;
        JPanel jp = new JPanel(new GridBagLayout());
        jp.setBackground(Color.DARK_GRAY);
        add(jp, gc);
    }
}
