package pl.edu.pb.wi.grafika.utils;

import java.awt.*;

public class ColorUtil {

    public static int convert(int value, int maxValue) {
        return (value * 255) / maxValue;
    }

    public static Color convertColor(int r, int g, int b, int maxValue) {
        return new Color(
                convert(r,maxValue),
                convert(g,maxValue),
                convert(b,maxValue)
        );
    }

}
