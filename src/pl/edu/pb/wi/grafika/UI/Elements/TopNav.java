package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.Handlers.*;
import pl.edu.pb.wi.grafika.UI.MainWindow;
import pl.edu.pb.wi.grafika.UI.prefabs.Button;

import javax.swing.*;
import java.awt.*;

public class TopNav extends JPanel {

    private JButton loadFile = new Button("Load");
    private JButton saveFileAsJPEG = new Button("Save JPEG");
    private JButton openCube = new Button("RGB Cube");
    private JButton resetImage = new Button("Reset Image");
    private JButton bezierButton = new Button("Bezier");

    public TopNav(PaintPanel _paintPanel, ToolsNav _toolsNav) {
        super(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 0.02f;

        loadFile.addActionListener(new LoadImageButtonHandler(_paintPanel));
        add(loadFile, gc);

        saveFileAsJPEG.setPreferredSize(new Dimension(150, 32));
        saveFileAsJPEG.addActionListener(
                new SaveJpegButtonHandler(_toolsNav, _paintPanel)
        );
        add(saveFileAsJPEG, gc);

        resetImage.setPreferredSize(new Dimension(150, 32));
        resetImage.addActionListener(
                new ResetImageHandler()
        );
        add(resetImage, gc);

        openCube.setPreferredSize(new Dimension(150, 32));
        openCube.addActionListener(new OpenRgbCubeHandler());
        add(openCube, gc);

        bezierButton.addActionListener(actionEvent -> {
            new BezierWindow();
        });
        add(bezierButton, gc);

        gc.weightx = 0.92;
        JPanel jp = new JPanel(new GridBagLayout());
        jp.setBackground(Color.DARK_GRAY);
        add(jp, gc);
    }
}
