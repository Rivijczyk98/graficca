package pl.edu.pb.wi.grafika.utils;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import pl.edu.pb.wi.grafika.models.CmykColor;
import pl.edu.pb.wi.grafika.models.HsvColor;
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
        float _r = r / 255f;
        float _g = g / 255f;
        float _b = b / 255f;

        float k = 1 - Math.max(_r, Math.max(_g, _b));
        float c = (1 - _r - k) / (1 - k);
        float m = (1 - _g - k) / (1 - k);
        float y = (1 - _b - k) / (1 - k);
        return new CmykColor((int)(c * 255),(int)(m * 255),(int)(y * 255),(int)(k * 255));
    }

    public static RgbColor cmykToRgb(CmykColor cmyk){
        return cmykToRgb(cmyk.c, cmyk.m, cmyk.y, cmyk.k);
    }

    public static RgbColor cmykToRgb(int c, int m, int y, int k){
        float _c = c / 255f;
        float _m = m / 255f;
        float _y = y / 255f;
        float _k = k / 255f;

        float r = 255f * (1 - _c) * (1 - _k);
        float g = 255f * (1 - _m) * (1 - _k);
        float b = 255f * (1 - _y) * (1 - _k);

        return new RgbColor((int)r,(int)g,(int)b);
    }

    public static HsvColor rgbToHsv(RgbColor rgb){
        float _r = rgb.r / 255f;
        float _g = rgb.g / 255f;
        float _b = rgb.b / 255f;

        float cMin = Math.min(_r, Math.min(_g, _b));
        float cMax = Math.max(_r, Math.max(_g, _b));
        float dt = cMax - cMin;

        float h =
                dt == 0 ? 0 :
                        cMax == _r ? 60 * (((_g - _b) / dt) % 6) :
                        cMax == _g ? 60 * ((_b - _r) / dt + 2) :
                        60 * ((_r - _g) / dt + 4);

        float s = cMax == 0 ? 0 : dt/cMax;

        return new HsvColor(h < 0 ? (360 + h) : h, s, cMax);
    }

}
