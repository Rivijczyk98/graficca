package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.Handlers.colorHandlers.EColor;
import pl.edu.pb.wi.grafika.models.CmykColor;
import pl.edu.pb.wi.grafika.models.RgbColor;
import pl.edu.pb.wi.grafika.utils.ColorUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ColorPicker extends JPanel {

    private BufferedImage image;
    private int position = 0;

    public void setImage(BufferedImage _image){
        image = _image;
        revalidate();
        paintComponent(getGraphics());
    }

    public BufferedImage getImage() {return image;}

    public void setPosition(int x){
        position = x;
        revalidate();
        paintComponent(getGraphics());
    }

    public ColorPicker() {
        super();

        setPreferredSize(new Dimension(200, 30));
//        setBorder(BorderFactory.createMatteBorder(5,0,5,0, Color.DARK_GRAY));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(image == null) return;

        Graphics2D graphics2D = (Graphics2D) g;
        g.clearRect(0, 0, getWidth(), getHeight());

        int width = (this.getWidth() - image.getWidth(null)) / 2;
        int height = (this.getHeight() - image.getHeight(null)) / 2;

        graphics2D.drawImage(image, width, height, null);

        setForeground(Color.WHITE);
        graphics2D.drawRect(position - 2, 0, 4, 29);

//        setBorder(BorderFactory.createMatteBorder(5,0,5,0, Color.DARK_GRAY));
    }
}
