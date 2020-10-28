package pl.edu.pb.wi.grafika.UI.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PaintPanel extends JPanel {

    private BufferedImage editedImage;

    public void setImage(BufferedImage _image) {
        editedImage = _image;
        revalidate();
        paintComponent(getGraphics());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(editedImage == null) return;

        Graphics2D graphics2D = (Graphics2D) g;
        g.clearRect(0, 0, getWidth(), getHeight());

        int width = (this.getWidth() - editedImage.getWidth(null)) / 2;
        int height = (this.getHeight() - editedImage.getHeight(null)) / 2;

        graphics2D.drawImage(editedImage, width, height, null);

    }

    public BufferedImage getImage(){
        return editedImage;
    }
}
