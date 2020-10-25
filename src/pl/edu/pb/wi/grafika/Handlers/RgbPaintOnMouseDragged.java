package pl.edu.pb.wi.grafika.Handlers;

import pl.edu.pb.wi.grafika.UI.Elements.RgbPaintPanel;
import pl.edu.pb.wi.grafika.UI.Elements.RgbPicker;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class RgbPaintOnMouseDragged extends MouseMotionAdapter {

    private RgbPaintPanel panel;
    private RgbPicker rgbPicker;

    public RgbPaintOnMouseDragged(RgbPaintPanel _panel, RgbPicker picker) {
        super();

        rgbPicker = picker;
        panel = _panel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        panel.setPosition(e.getPoint());
        Point point = new Point();
        point.x = Math.max(0, Math.min(199, e.getX()));
        point.y = Math.max(0, Math.min(199, e.getY()));
        rgbPicker.newColorSelected(panel.getImage().getRGB((int)point.getX(), (int)point.getY()));
    }
}
