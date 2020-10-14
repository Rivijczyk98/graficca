package pl.edu.pb.wi.grafika.Handlers;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.UI.Elements.PaintPanel;
import pl.edu.pb.wi.grafika.utils.P3;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadImageButtonHandler implements ActionListener {

    private PaintPanel paintPanel;

    public LoadImageButtonHandler(PaintPanel _paintPanel) {
        super();

        paintPanel = _paintPanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser opener = new JFileChooser();
        opener.setFileFilter(new FileNameExtensionFilter("*.ppm", "ppm"));

        int returnValue = opener.showDialog(null, "Select image");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage image;
                switch(new BufferedReader(new FileReader(opener.getSelectedFile())).readLine())
                {
                    case "P3":
                        image = P3.loadRecursive(opener.getSelectedFile());
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + new BufferedReader(new FileReader(opener.getSelectedFile())).readLine());
                }
                paintPanel.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            loadImage(imageOpener.getSelectedFile());
//            activateInputField();
        }
    }

    private void loadImage(File file) {

    }
}
