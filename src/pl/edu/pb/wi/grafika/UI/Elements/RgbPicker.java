package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.Handlers.ColorsOnMouseDragged;
import pl.edu.pb.wi.grafika.Handlers.RgbPaintOnMouseDragged;
import pl.edu.pb.wi.grafika.Handlers.colorHandlers.EColor;
import pl.edu.pb.wi.grafika.models.CmykColor;
import pl.edu.pb.wi.grafika.models.HsvColor;
import pl.edu.pb.wi.grafika.models.RgbColor;
import pl.edu.pb.wi.grafika.utils.ColorUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RgbPicker extends JPanel {

    private RgbPaintPanel picker = new RgbPaintPanel();
    private ColorPicker colors = new ColorPicker();
    private RgbChooser rgbChooser = new RgbChooser(this);
    private CmykChooser cmykChooser = new CmykChooser(this);

    private CmykColor cmykColor = new CmykColor(0,0,0,0);
    private RgbColor rgbColor = new RgbColor(255,255,255);

    public void cmykChanged(EColor color){
        switch(color){
            case C:
                cmykColor.c =
                        cmykChooser.fields[0].getText().equals("")
                                ? 0
                                : Integer.parseInt(cmykChooser.fields[0].getText());
                break;
            case M:
                cmykColor.m =
                        cmykChooser.fields[1].getText().equals("")
                                ? 0
                                : Integer.parseInt(cmykChooser.fields[1].getText());
                break;
            case Y:
                cmykColor.y =
                        cmykChooser.fields[2].getText().equals("")
                                ? 0
                                : Integer.parseInt(cmykChooser.fields[2].getText());
                break;
            case K:
                cmykColor.k =
                        cmykChooser.fields[3].getText().equals("")
                                ? 0
                                : Integer.parseInt(cmykChooser.fields[3].getText());
                break;
        }
        rgbColor = ColorUtil.cmykToRgb(cmykColor);
        setRgbValues();

        recalculatePositions();
    }

    public void rgbChanged(EColor color){
        switch(color){
            case R:
                rgbColor.r =
                        rgbChooser.fields[0].getText().equals("")
                                ? 0
                                : Integer.parseInt(rgbChooser.fields[0].getText());
                break;
            case G:
                rgbColor.g =
                        rgbChooser.fields[1].getText().equals("")
                                ? 0
                                : Integer.parseInt(rgbChooser.fields[1].getText());
                break;
            case B:
                rgbColor.b =
                        rgbChooser.fields[2].getText().equals("")
                                ? 0
                                : Integer.parseInt(rgbChooser.fields[2].getText());
                break;
        }
        cmykColor = ColorUtil.rgbToCmyk(rgbColor);
        setCmykValues();

        recalculatePositions();
    }

    public void newColorSelected(int c){
        Color color = new Color(c);

        rgbColor = new RgbColor(color.getRed(), color.getGreen(), color.getBlue());
        cmykColor = ColorUtil.rgbToCmyk(rgbColor);

        setRgbValues();
        setCmykValues();
    }

    private void recalculatePositions(){
        HsvColor hsv = ColorUtil.rgbToHsv(rgbColor);

        System.out.println(hsv.toString());
        System.out.println(((hsv.h / 360f) * 200));

        picker.setImage(
                ColorUtil.generateGradient(
                        200,
                        Color.BLACK,
                        Color.WHITE,
                        Color.BLACK,
                        new Color(
                                colors.getImage().getRGB((int)((hsv.h / 360f) * 200), 15)
                        )
                )
        );
        picker.setPosition(new Point((int)(hsv.s * 200), 200 - (int)(hsv.v * 200)));
        colors.setPosition((int)((hsv.h / 360f) * 200));
    }

    private void setRgbValues(){
        rgbChooser.fields[0].setText(String.valueOf(rgbColor.r));
        rgbChooser.fields[1].setText(String.valueOf(rgbColor.g));
        rgbChooser.fields[2].setText(String.valueOf(rgbColor.b));
    }

    private void setCmykValues(){
        cmykChooser.fields[0].setText(String.valueOf(cmykColor.c));
        cmykChooser.fields[1].setText(String.valueOf(cmykColor.m));
        cmykChooser.fields[2].setText(String.valueOf(cmykColor.y));
        cmykChooser.fields[3].setText(String.valueOf(cmykColor.k));
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

        colors.addMouseMotionListener(new ColorsOnMouseDragged(colors, picker, this));
        picker.addMouseMotionListener(new RgbPaintOnMouseDragged(picker, this));
    }

}
