package pl.edu.pb.wi.grafika.Handlers;

import org.w3c.dom.css.RGBColor;
import pl.edu.pb.wi.grafika.UI.Elements.ColorPicker;
import pl.edu.pb.wi.grafika.UI.Elements.PaintPanel;
import pl.edu.pb.wi.grafika.UI.Elements.RgbPaintPanel;
import pl.edu.pb.wi.grafika.utils.ColorUtil;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ColorsOnMouseDragged extends MouseMotionAdapter {

    ColorPicker picker;
    RgbPaintPanel rgbPicker;

    public ColorsOnMouseDragged(ColorPicker _picker, RgbPaintPanel _rgb) {
        super();

        rgbPicker = _rgb;
        picker = _picker;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        picker.setPosition(Math.max(0, Math.min(199, e.getX())));

        rgbPicker.setImage(
                ColorUtil.generateGradient(
                        200,
                        Color.BLACK,
                        Color.WHITE,
                        Color.BLACK,
                        new Color(picker.getImage().getRGB(Math.max(0, Math.min(199, e.getX())), 0)
                        )
                )
        );
    }
}
