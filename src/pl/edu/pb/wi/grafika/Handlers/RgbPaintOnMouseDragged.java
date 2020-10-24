package pl.edu.pb.wi.grafika.Handlers;

import pl.edu.pb.wi.grafika.UI.Elements.RgbPaintPanel;
import pl.edu.pb.wi.grafika.UI.Elements.RgbPicker;

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

    }
}
