package pl.edu.pb.wi.grafika.Handlers;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.UI.Elements.PaintPanel;
import pl.edu.pb.wi.grafika.utils.P3;
import pl.edu.pb.wi.grafika.utils.P6;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class LoadImageButtonHandler implements ActionListener {

    private PaintPanel paintPanel;

    public LoadImageButtonHandler(PaintPanel _paintPanel) {
        super();

        paintPanel = _paintPanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser opener = new JFileChooser();
        opener.setFileFilter(new FileNameExtensionFilter("*.ppm *.jpeg *.jpg", "ppm", "jpeg", "jpg"));

        int returnValue = opener.showDialog(null, "Select image");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage image = null;
                String filename = opener.getSelectedFile().toString();
                if (filename.substring(filename.lastIndexOf(".")).equals(".jpeg")
                        || filename.substring(filename.lastIndexOf(".")).equals(".jpg")) {
                    image = ImageIO.read(opener.getSelectedFile());
                } else {
                    byte[] b = new byte[2];
                    new FileInputStream(opener.getSelectedFile()).read(b);

                    switch (new String(b, StandardCharsets.UTF_8)) {
                        case "P3":
                            image = P3.load(opener.getSelectedFile());
                            break;
                        case "P6":
                            image = P6.load(opener.getSelectedFile());
                            break;

                        default:
                            Storage.setErrorMessage("Invalid ppm type (" + new String(b, StandardCharsets.UTF_8) + ")");
                    }
                }
                paintPanel.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
