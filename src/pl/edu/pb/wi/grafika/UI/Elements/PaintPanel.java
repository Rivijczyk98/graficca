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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(editedImage == null) return;

        Graphics2D graphics2D = (Graphics2D) g;
        g.clearRect(0, 0, getWidth(), getHeight());

        int imgWidth = editedImage.getWidth(null);
        int imgHeight = editedImage.getHeight(null);

        double imgAspect = (double) imgHeight / imgWidth;

        int canvasWidth = getWidth();
        int canvasHeight = getHeight();

        double canvasAspect = (double) canvasHeight / canvasWidth;

        int x1 = 0; // top left X position
        int y1 = 0; // top left Y position
        int x2 = canvasWidth - 1; // bottom right X position
        int y2 = canvasHeight - 1; // bottom right Y position

        if (imgWidth < canvasWidth && imgHeight < canvasHeight) {
            if (canvasWidth - imgWidth < canvasHeight - imgHeight) {
                int newImageHeight = (int) (canvasWidth * imgAspect);
                y1 = (canvasHeight - newImageHeight) / 2;
                y2 = newImageHeight + y1;
            } else {
                int newImageWidth = (int) (canvasHeight / imgAspect);
                x1 = (canvasWidth - newImageWidth) / 2;
                x2 = newImageWidth + x1;
            }

        } else {
            if (canvasAspect > imgAspect) {
                y1 = canvasHeight;
                // keep image aspect ratio
                canvasHeight = (int) (canvasWidth * imgAspect);
                y1 = (y1 - canvasHeight) / 2;
            } else {
                x1 = canvasWidth;
                // keep image aspect ratio
                canvasWidth = (int) (canvasHeight / imgAspect);
                x1 = (x1 - canvasWidth) / 2;
            }
            x2 = canvasWidth + x1;
            y2 = canvasHeight + y1;
        }

        graphics2D.drawImage(editedImage, x1, y1, x2, y2,0,0, imgWidth, imgHeight, null);

    }

    public BufferedImage getImage(){
        return editedImage;
    }
}
