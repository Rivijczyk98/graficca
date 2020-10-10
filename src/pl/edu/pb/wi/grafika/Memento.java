package pl.edu.pb.wi.grafika;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Memento {

    private LinkedList<List<Drawable>> _history = new LinkedList<>();
    private int space = 20;
    private int currentPosition = 0;

    public Memento(List<Drawable> drawableList) {
        super();

        _history.add(new LinkedList<>(drawableList));
    }

    public List<Drawable> Undo() {
        return
                currentPosition == 0
                        ? null
                        : _history.get(--currentPosition);
    }

    public List<Drawable> Redo() {
        return
                currentPosition + 1 >= space || currentPosition + 1 >= _history.size()
                        ? _history.get(currentPosition)
                        : _history.get(++currentPosition);
    }

    public void Add(List<Drawable> _graphics ) {
        if(currentPosition + 1 < space) {
            _history.add(new LinkedList<>(_graphics));
            currentPosition += 1;
        } else {
            _history.removeFirst();
            _history.add(new LinkedList<>(_graphics));
        }
    }

    public void DrawCurrent(Component component) {
        component.repaint();

        if(_history.get(currentPosition) != null)
            for (Drawable d: _history.get(currentPosition)) {
                d.Draw(component.getGraphics());
            }
    }
}
