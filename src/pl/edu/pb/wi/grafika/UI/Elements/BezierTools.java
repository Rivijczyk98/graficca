package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.UI.prefabs.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BezierTools extends JPanel {

    JLabel position = new JLabel("X: 0, Y: 0");

    public JTextField degree = new JTextField("1000");
    public JTextField selectedXText, selectedYText;
    public BezierWindow bezierWindow;

    Point selected;

    public BezierTools(BezierWindow _bezierWindow) {
        super(new GridBagLayout());

        bezierWindow = _bezierWindow;

        setBackground(Color.DARK_GRAY);

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = 1;
        gc.weightx = 1;
        position.setForeground(Color.WHITE);
        add(position,gc);

        gc.gridy = 1;
        JLabel title = new JLabel("New point");
        title.setForeground(Color.WHITE);

        gc.gridy = 2;
//        gc.weightx = .4f;
        JLabel xL = new JLabel("X: ");
        xL.setForeground(Color.WHITE);
        add(xL, gc);

        gc.gridx = 1;
        JTextField xT = new JTextField("0");
        add(xT, gc);

        gc.gridy = 3;
        JTextField yT = new JTextField("0");
        add(yT, gc);

        gc.gridx = 0;
        JLabel yL = new JLabel("Y: ");
        yL.setForeground(Color.WHITE);
        add(yL, gc);

        gc.gridy = 4;
        JButton button = new Button("Add point");
        add(button, gc);
        button.addActionListener(actionEvent -> {
            bezierWindow.bezierPanel.addPoint(
                    new Point(
                            Integer.parseInt(xT.getText()),
                            Integer.parseInt(yT.getText())
                    )
            );
        });

        gc.gridy = 5;
        JLabel st = new JLabel("Number of points: ");
        st.setForeground(Color.WHITE);
        add(st, gc);

        gc.gridx = 1;
        add(degree, gc);

        degree.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try{
                    if(!degree.getText().equals(""))
                        refresh();
                } catch (Error ignore){

                }

            }
        });

        gc.gridx = 0;
        gc.gridy = 6;
        JLabel selectedLabel = new JLabel("Selected point");
        selectedLabel.setForeground(Color.WHITE);
        add(selectedLabel, gc);

        gc.gridy = 7;
        JLabel selectedXLabel = new JLabel("X: ");
        selectedXLabel.setForeground(Color.WHITE);
        add(selectedXLabel, gc);

        gc.gridx = 1;
        selectedXText = new JTextField("0");
        add(selectedXText, gc);

        gc.gridy = 8;
        selectedYText = new JTextField("0");
        add(selectedYText, gc);

        gc.gridx = 0;
        JLabel selectedYLabel = new JLabel("Y: ");
        selectedYLabel.setForeground(Color.WHITE);
        add(selectedYLabel, gc);

        gc.gridy = 9;
        Button b = new Button("Change");
        add(b, gc);
        b.addActionListener(actionEvent -> {
            selected.setLocation(Double.parseDouble(selectedXText.getText()), Double.parseDouble(selectedYText.getText()));
            refresh();
        });

        gc.gridx = 1;
        Button del = new Button("Delete");
        add(del, gc);
        del.addActionListener(actionEvent -> {
            if(selected != null){
                bezierWindow.bezierPanel.pointList.remove(selected);
                selected = null;
                refresh();
            }
        });

    }

    public void refresh(){
        bezierWindow.bezierPanel.paintComponent(bezierWindow.bezierPanel.getGraphics());
    }

    public void setSelectedPoint(Point p){
        selected = p;
        selectedXText.setText(p.getX() + "");
        selectedYText.setText(p.getY() + "");
    }

    public void setPosition(double x, double y) {
        position.setText("X: " + x + ", Y: " + y);
    }
}
