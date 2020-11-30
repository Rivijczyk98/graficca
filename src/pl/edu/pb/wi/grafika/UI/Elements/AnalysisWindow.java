package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.utils.ColorUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class AnalysisWindow extends JFrame {

    AnalysisTools tools = new AnalysisTools(this);
    PaintPanel panel = new PaintPanel();

    public AnalysisWindow() throws HeadlessException {
        super();

        setTitle("Analysis");
        setResizable(true);
        setLayout(new GridBagLayout());
        setSize(1280, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBackground(Color.DARK_GRAY);

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = 1;
        gc.weightx = .2f;
        gc.weighty = 1f;

        add(tools, gc);

        gc.weightx = .9f;
        gc.gridx = 1;
        add(panel, gc);

        setVisible(true);

        tools.getRgbPicker()
                .setImage(ColorUtil.generateGradient(200, Color.BLACK, Color.WHITE, Color.BLACK, Color.RED));

        tools.getRgbPicker()
                .setColors(ColorUtil.getMultiColor(30, 200));

        panel.setImage(Storage.getEditedImage());

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseDragged(e);

                if(e.getButton() == 2){
                    BufferedImage b =
                            new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
                    panel.paint(b.getGraphics());

                    tools.setC1(new Color(b.getRGB(e.getX(), e.getY())));

                } else if (e.getButton() == 3) {
                    BufferedImage b =
                            new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
                    panel.paint(b.getGraphics());

                    tools.setC2(new Color(b.getRGB(e.getX(), e.getY())));

                }
            }
        });
    }

}
