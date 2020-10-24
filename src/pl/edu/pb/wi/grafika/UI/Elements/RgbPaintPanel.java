package pl.edu.pb.wi.grafika.UI.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RgbPaintPanel extends JPanel {

    private BufferedImage originalImage;
    private Point position = new Point(-1, -1);

    public void setImage(BufferedImage _image) {
        originalImage = _image;
        revalidate();
        paintComponent(getGraphics());
    }

    public void setPosition(Point point) {
        point.x = Math.max(-1, Math.min(200, point.x));
        point.y = Math.max(-1, Math.min(199, point.y));
        position = point;
        revalidate();
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

        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.drawOval(position.x - 1, position.y - 1, 7, 7 );
    }

    public BufferedImage getImage(){
        return originalImage;
    }

}
