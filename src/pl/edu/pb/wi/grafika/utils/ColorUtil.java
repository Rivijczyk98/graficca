package pl.edu.pb.wi.grafika.utils;

import pl.edu.pb.wi.grafika.models.CmykColor;
import pl.edu.pb.wi.grafika.models.RgbColor;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class ColorUtil {

    public static int convert(int value, int maxValue) {
        return ((value * 255) / maxValue);
    }

    public static Color convertColor(int r, int g, int b, int maxValue) throws Exception {

        if(r > maxValue || g > maxValue || b > maxValue) {
            throw new Exception("Color value outside of maxValue");
        }

        return new Color(
                convert(r,maxValue),
                convert(g,maxValue),
                convert(b,maxValue)
        );
    }

    public static BufferedImage generateGradient (
            int size,
            Color leftDown,
            Color leftUp,
            Color rightDown,
            Color rightUp
    ) {
        BufferedImage picker = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = picker.createGraphics();
        GradientPaint gradientUp = new GradientPaint(0f, 0f, leftUp, size, 0f, rightUp);
        GradientPaint gradientDown = new GradientPaint(0f, 0f, new Color(leftDown.getRed(), leftDown.getGreen(), leftDown.getBlue(), 0), 0f, size, rightDown);

        g.setPaint(gradientUp);
        g.fillRect(0,0, size, size);
        g.setPaint(gradientDown);
        g.fillRect(0,0, size, size);

        g.dispose();
        return picker;
    }

    public static BufferedImage getMultiColor(int height, int width){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = img.createGraphics();

        Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA, Color.RED};
        float[] points = {0.0f, 0.166f, 0.332f, 0.498f, 0.664f, 0.830f, 1f};
        Point2D start = new Point2D.Float(0f, 0f);
        Point2D end = new Point2D.Float(width, height);
        LinearGradientPaint p = new LinearGradientPaint(start, end, points, colors);

        g.setPaint(p);
        g.fillRect(0,0, width,height);

        g.dispose();
        return img;
    }

    public static CmykColor rgbToCmyk(RgbColor rgb){
        return rgbToCmyk(rgb.r, rgb.g, rgb.b);
    }

    public static CmykColor rgbToCmyk(int r, int g, int b){
        r /= 255;
        g /= 255;
        b /= 255;

        int k = 1 - Math.max(r, Math.max(g, b));
        int c = (1 - r - k) / (1 - k);
        int m = (1 - g - k) / (1 - k);
        int y = (1 - b - k) / (1 - k);
        return new CmykColor(c,m,y,k);
    }

    public static RgbColor cmykToRgb(CmykColor cmyk){
        return cmykToRgb(cmyk.c, cmyk.m, cmyk.y, cmyk.k);
    }

    public static RgbColor cmykToRgb(int c, int m, int y, int k){
        int r = 255 * (1 - c) * (1 - k);
        int g = 255 * (1 - m) * (1 - k);
        int b = 255 * (1 - y) * (1 - k);

        return new RgbColor(r,g,b);
    }

}
