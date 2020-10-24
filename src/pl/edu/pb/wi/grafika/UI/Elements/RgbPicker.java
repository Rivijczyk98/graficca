package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.Handlers.ColorsOnMouseDragged;
import pl.edu.pb.wi.grafika.Handlers.RgbPaintOnMouseDragged;
import pl.edu.pb.wi.grafika.Handlers.colorHandlers.EColor;
import pl.edu.pb.wi.grafika.models.CmykColor;
import pl.edu.pb.wi.grafika.models.RgbColor;
import pl.edu.pb.wi.grafika.utils.ColorUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RgbPicker extends JPanel {

    private RgbPaintPanel picker = new RgbPaintPanel();
    private ColorPicker colors = new ColorPicker();
    private RgbChooser rgbChooser = new RgbChooser(this);
    private CmykChooser cmykChooser = new CmykChooser(this);

    private CmykColor cmykColor;
    private RgbColor rgbColor;

    public void cmykChanged(EColor color){


    }

    public void rgbChanged(EColor color){
        switch(color){
            case R:
                rgbColor.r = Integer.parseInt(rgbChooser.fields[0].getText());
                break;
            case G:
                rgbColor.g = Integer.parseInt(rgbChooser.fields[1].getText());
                break;
            case B:
                rgbColor.b = Integer.parseInt(rgbChooser.fields[2].getText());
                break;
        }
        ColorUtil.rgbToCmyk(rgbColor);
    }

    public void newColorSelected(){

    }

    public void setImage(BufferedImage _image) {
        picker.setImage(_image);
    }

    public void setColors(BufferedImage _image){
        colors.setImage(_image);
    }

    public RgbPicker() {
        super(new GridBagLayout());

        setBackground(Color.DARK_GRAY);

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;
        picker.setPreferredSize(new Dimension(200, 200));
        add(picker, gc);

        gc.gridy = 1;
        add(colors, gc);

        gc.gridy = 2;
        add(rgbChooser, gc);

        gc.gridy = 3;
        add(cmykChooser, gc);

        colors.addMouseMotionListener(new ColorsOnMouseDragged(colors, picker));
        picker.addMouseMotionListener(new RgbPaintOnMouseDragged(picker));
    }

}
