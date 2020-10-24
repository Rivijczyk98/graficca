package pl.edu.pb.wi.grafika.Handlers.colorHandlers;

import pl.edu.pb.wi.grafika.Handlers.NumericInputHandler;
import pl.edu.pb.wi.grafika.UI.Elements.ColorPicker;
import pl.edu.pb.wi.grafika.UI.Elements.RgbPicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class rInputHandler extends NumericInputHandler {

    private RgbPicker colorPicker;
    private EColor color;

    public rInputHandler(JTextField _text, int _min, int _max, RgbPicker _picker, EColor _color) {
        super(_text, _min, _max);
        colorPicker = _picker;
        color = _color;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        if(color == EColor.R
                || color == EColor.G
                || color == EColor.B
        ) {
            colorPicker.rgbChanged(color);
        } else {
            colorPicker.cmykChanged(color);
        }

    }
}

