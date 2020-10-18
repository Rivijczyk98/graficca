package pl.edu.pb.wi.grafika.Handlers;

import pl.edu.pb.wi.grafika.UI.Elements.PaintPanel;
import pl.edu.pb.wi.grafika.UI.Elements.ToolsNav;
import pl.edu.pb.wi.grafika.utils.JpegCompression;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class SaveJpegButtonHandler implements ActionListener {

    private ToolsNav toolsNav;
    private PaintPanel panel;
    private BufferedImage image;

    public SaveJpegButtonHandler(ToolsNav _toolsNav, PaintPanel _panel) {
        super();

        panel = _panel;
        toolsNav = _toolsNav;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int compressionLevel = toolsNav.getJpegCompressionValue();
        image = panel.getImage();

        try {
            save(compressionLevel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(int compressionLevel) throws IOException {
        JFileChooser opener = new JFileChooser();
        opener.setDialogTitle("Specify save location");

        int userSelection = opener.showSaveDialog(new JFrame());

        if(userSelection != JFileChooser.APPROVE_OPTION) return;

        File file = opener.getSelectedFile();

        JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
        jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpegParams.setCompressionQuality(compressionLevel / 100f);

        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();

        writer.setOutput(new FileImageOutputStream(
                new File(file.getParent() + "/" + file.getName() + ".jpg")));

        writer.write(null, new IIOImage(image, null, null), jpegParams);

    }
}
