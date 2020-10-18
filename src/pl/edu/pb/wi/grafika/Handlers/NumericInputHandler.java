package pl.edu.pb.wi.grafika.Handlers;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumericInputHandler extends KeyAdapter {

    private JTextField text;
    private int min;
    private int max;

    public NumericInputHandler(JTextField _text, int _min, int _max){
        text = _text;
        min = _min;
        max = _max;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
            if(isInRange(text.getText() + e.getKeyChar())) {
                text.setEditable(true);
            } else {
                text.setEditable(false);
            }
        } else if (e.getKeyCode() == 8){
            text.setEditable(true);
            if(text.getText().length() == 1){
                text.setText("0");
            }
        } else {
            text.setEditable(false);
        }
    }

    private boolean isInRange(String value){
        return Integer.parseInt(value) >= min && Integer.parseInt(value) <= max;
    }
}
