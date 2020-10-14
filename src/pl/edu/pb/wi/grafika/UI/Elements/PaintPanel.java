package pl.edu.pb.wi.grafika.UI.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PaintPanel extends JPanel {

    private BufferedImage originalImage;

    public void setImage(BufferedImage _image) {
        originalImage = _image;
        paintComponent(getGraphics());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(originalImage == null) return;

        Graphics2D graphics2D = (Graphics2D) g;
        g.clearRect(0, 0, getWidth(), getHeight());

        int width = (this.getWidth() - originalImage.getWidth(null)) / 2;
        int height = (this.getHeight() - originalImage.getHeight(null)) / 2;

        graphics2D.drawImage(originalImage, width, height, null);

    }
}
