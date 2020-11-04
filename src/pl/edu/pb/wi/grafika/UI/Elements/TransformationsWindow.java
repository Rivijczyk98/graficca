package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.UI.prefabs.Button;
import pl.edu.pb.wi.grafika.models.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.CropImageFilter;
import java.util.LinkedList;

public class TransformationsWindow extends JFrame {

    public TransformationsPanel panel = new TransformationsPanel(this);
    public TransformationsTools tools = new TransformationsTools(this);

    public TransformationsWindow() throws HeadlessException {
        super();

        setLayout(new GridBagLayout());
        setTitle("Transformations 2D");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(1280, 720));
        setResizable(true);

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = 1;
        gc.weightx = .2f;
        gc.weighty = 1;

        add(tools, gc);

        gc.gridx = 1;
        gc.weightx = .8f;
        add(panel, gc);

        setVisible(true);
    }
}

class TransformationsTools extends JPanel {
    Button create = new Button("Create");
    Button move = new Button("Move");
    Button rotate = new Button("Rotate");
    Button scale = new Button("Scale");

    public JTextArea positions = new JTextArea("");

    Button active = create;
    public TransformationsTools(TransformationsWindow transformationsWindow) {
        super(new GridBagLayout());

        setBackground(Color.DARK_GRAY);

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = 1;

        add(create, gc);
        create.addActionListener(actionEvent -> {
            active.setEnabled(true);
            create.setEnabled(false);
            active = create;
            transformationsWindow.panel.clear();
        });
        create.setEnabled(false);

        gc.gridy = 1;
        add(move, gc);
        move.addActionListener(actionEvent -> {
            active.setEnabled(true);
            move.setEnabled(false);
            active = move;
            transformationsWindow.panel.clear();
        });

        gc.gridy = 2;
        add(rotate, gc);
        rotate.addActionListener(actionEvent -> {
            active.setEnabled(true);
            rotate.setEnabled(false);
            active = rotate;
            transformationsWindow.panel.clear();
        });

        gc.gridy = 3;
        add(scale, gc);
        scale.addActionListener(actionEvent -> {
            active.setEnabled(true);
            scale.setEnabled(false);
            active = scale;
            transformationsWindow.panel.clear();
        });

        gc.gridy = 4;
        positions.setPreferredSize(new Dimension(300,300));
        add(positions, gc);

        gc.gridy = 5;
        Button applyNewPoints = new Button("Apply");
        applyNewPoints.addActionListener(actionEvent -> {
            if(!positions.getText().equals(""))
                transformationsWindow.panel.changePoints(positions.getText());
        });
        add(applyNewPoints, gc);

        gc.gridy = 6;
        JTextField jtx = new JTextField();
        add(jtx, gc);

        gc.gridy = 7;
        JTextField jty = new JTextField();
        add(jty, gc);

        gc.gridy = 8;
        JTextField jtx2 = new JTextField();
        add(jtx2, gc);

        gc.gridy = 9;
        JTextField jty2 = new JTextField();
        add(jty2, gc);

        gc.gridy = 10;
        Button apply = new Button("Apply");
        apply.addActionListener(actionEvent -> {
            switch(getActive()){
                case "Move":
                    transformationsWindow.panel.moveSelected(jtx.getText(), jty.getText(), jtx2.getText(), jtx2.getText());
                    break;
                case "Rotate":
                    transformationsWindow.panel.rotateSelected(jtx.getText(), jty.getText(), jtx2.getText());
                    break;
                case "Scale":
                    transformationsWindow.panel.scaleSelected(jtx.getText(), jty.getText(), jtx2.getText(), jtx2.getText());
                    break;
                case "Create":
                    transformationsWindow.panel.createSelected(jtx.getText(), jty.getText());
                    break;
            }
        });
        add(apply, gc);

        gc.gridy = 11;
        Button saveCreate = new Button("Save");
        saveCreate.addActionListener(actionEvent -> {
            transformationsWindow.panel.saveCreated();
        });
        add(saveCreate, gc);
    }

    public String getActive() {
        return active.getText();
    }
}

class TransformationsPanel extends JPanel {

    LinkedList<Shape> shapes = new LinkedList<>();
    Shape creation = null;
    Shape selected = null;
    Point startPoint = null;

    public void clear(){
        creation = null;
        startPoint = null;
        revalidate();
        paintComponent(getGraphics());
    }

    public TransformationsPanel(TransformationsWindow transformationsWindow) {
        super();

        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (e.getButton() == 2 ){
                    selected = tryToSelect(e.getPoint());
                    if(selected != null){
                        StringBuilder s = new StringBuilder();
                        for(int i = 0; i < selected.verticles.size(); i++){
                            Point v = selected.verticles.get(i);
                            s.append(i).append(". x: ").append(v.getX()).append(" y: ").append(v.getY()).append("\n");
                        }
                        transformationsWindow.tools.positions.setText(s.toString());
                    }
                    return;
                }

                switch(transformationsWindow.tools.getActive()){
                    case "Create":
                        if(e.getButton() == 3 && creation != null){
                            shapes.add(creation);
                            creation = null;
                        } else if(e.getButton() == 1 && creation == null){
                            creation = new Shape(e.getPoint());
                        } else if(e.getButton() == 1) {
                            creation.verticles.add(e.getPoint());
                        }
                        break;
                    case "Move":

                        break;
                    case "Scale":

                        break;
                    case "Rotate":

                        break;
                }

                revalidate();
                paintComponent(getGraphics());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                switch(transformationsWindow.tools.getActive()){
                    case "Create":

                        break;
                    case "Move":
                    case "Scale":
                        if(e.getButton() == 1) {
                            Shape s = tryToSelect(e.getPoint());
                            if(s != null){
                                selected = s;
                                startPoint = e.getPoint();
                            }
                        }
                        break;
                    case "Rotate":
                        if(e.getButton() == 1){
                            startPoint = e.getPoint();
                        }
                        break;
                }


            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                switch(transformationsWindow.tools.getActive()){
                    case "Create":

                        break;
                    case "Move":
                        if(e.getButton() == 1 && selected != null && startPoint != null){
                            double vectorX = e.getX() - startPoint.getX();
                            double vectorY = e.getY() - startPoint.getY();
                            for(Point p : selected.verticles){
                                p.setLocation(p.getX() + vectorX, p.getY() + vectorY);
                            }
                            revalidate();
                            paintComponent(getGraphics());
                            startPoint = null;
                        }
                        break;
                    case "Scale":
                        if(e.getButton() == 1 && selected != null && startPoint != null){
                            double vectorX = (e.getX() - startPoint.getX()) * .01f + 1;
                            double vectorY = (e.getY() - startPoint.getY()) * .01f + 1;
                            for(Point p : selected.verticles){
                                p.setLocation(p.getX() * vectorX, p.getY() * vectorY);
                            }
                            revalidate();
                            paintComponent(getGraphics());
                            startPoint = null;
                        }
                        break;
                    case "Rotate":
                        int sumX = 0;
                        int sumY = 0;
                        for(Point p : selected.verticles){
                            sumX += p.getX();
                            sumY += p.getY();
                        }
                        int size = selected.verticles.size();
                        Point center = new Point(sumX / size, sumY / size);

                        double angle = Math.toDegrees(
                                Math.acos(
                                        (Math.pow(center.distance(startPoint), 2) + Math.pow(center.distance(e.getPoint()), 2) - Math.pow(startPoint.distance(e.getPoint()), 2)) /
                                                (2 * center.distance(startPoint) * center.distance(e.getPoint()))
                                )
                        );

                        angle = angle / 180f * 2 * Math.PI;

                        Shape shape = null;
                        for(Point p : selected.verticles){
                            double x = startPoint.getX() + (p.getX() - startPoint.getX()) * Math.cos(angle) - (p.getY() - startPoint.getY()) * Math.sin(angle);
                            double y = startPoint.getY() + (p.getX() - startPoint.getX()) * Math.sin(angle) + (p.getY() - startPoint.getY()) * Math.cos(angle);
                            if(shape == null){
                                shape = new Shape(new Point((int)x,(int)y));
                            } else {
                                shape.verticles.add(new Point((int)x,(int)y));
                            }
                        }

                        assert shape != null;
                        selected.verticles = shape.verticles;

                        revalidate();
                        paintComponent(getGraphics());

                        break;
                }


            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                switch(transformationsWindow.tools.getActive()){
                    case "Create":

                        break;
                    case "Move":
                        if(startPoint != null){
                            revalidate();
                            paintComponent(getGraphics());
                            Graphics g = getGraphics();
                            g.setColor(Color.BLUE);
                            g.drawLine((int)startPoint.getX(), (int)startPoint.getY(), e.getX(), e.getY());

                            g.setColor(Color.LIGHT_GRAY);
                            double vectorX = e.getX() - startPoint.getX();
                            double vectorY = e.getY() - startPoint.getY();
                            Shape shape = new Shape(selected.verticles);
                            for(int i = 1; i < shape.verticles.size(); i++){
                                g.drawLine(
                                        (int)(shape.verticles.get(i).getX() + vectorX),
                                        (int)(shape.verticles.get(i).getY() + vectorY),
                                        (int)(shape.verticles.get(i - 1).getX() + vectorX),
                                        (int)(shape.verticles.get(i - 1).getY() + vectorY)
                                );
                            }
                            g.drawLine(
                                    (int)(shape.verticles.get(shape.verticles.size() - 1).getX() + vectorX),
                                    (int)(shape.verticles.get(shape.verticles.size() - 1).getY() + vectorY),
                                    (int)(shape.verticles.get(0).getX() + vectorX),
                                    (int)(shape.verticles.get(0).getY() + vectorY)
                            );
                        }
                        break;
                    case "Scale":
                        if(startPoint != null){
                            revalidate();
                            paintComponent(getGraphics());

                            Graphics g = getGraphics();
                            g.setColor(Color.BLUE);
                            g.drawLine((int)startPoint.getX(), (int)startPoint.getY(), e.getX(), e.getY());

                            g.setColor(Color.LIGHT_GRAY);
                            double vectorX = (e.getX() - startPoint.getX()) * .01f + 1f;
                            double vectorY = (e.getY() - startPoint.getY()) * .01f + 1f;

                            Shape shape = new Shape(selected.verticles);

                            for(int i = 1; i < shape.verticles.size(); i++){
                                g.drawLine(
                                        (int)(shape.verticles.get(i).getX() * vectorX),
                                        (int)(shape.verticles.get(i).getY() * vectorY),
                                        (int)(shape.verticles.get(i - 1).getX() * vectorX),
                                        (int)(shape.verticles.get(i - 1).getY() * vectorY)
                                );
                            }
                            g.drawLine(
                                    (int)(shape.verticles.get(shape.verticles.size() - 1).getX() * vectorX),
                                    (int)(shape.verticles.get(shape.verticles.size() - 1).getY() * vectorY),
                                    (int)(shape.verticles.get(0).getX() * vectorX),
                                    (int)(shape.verticles.get(0).getY() * vectorY)
                            );
                        }
                        break;
                    case "Rotate":
                        if(startPoint != null){
                            revalidate();
                            paintComponent(getGraphics());

                            Graphics g = getGraphics();
                            g.setColor(Color.BLUE);
                            g.drawLine((int)startPoint.getX(), (int)startPoint.getY(), e.getX(), e.getY());

                            int sumX = 0;
                            int sumY = 0;
                            for(Point p : selected.verticles){
                                sumX += p.getX();
                                sumY += p.getY();
                            }
                            int size = selected.verticles.size();
                            Point center = new Point(sumX / size, sumY / size);

                            g.setColor(Color.LIGHT_GRAY);
                            double angle = Math.toDegrees(
                                    Math.acos(
                                            (Math.pow(center.distance(startPoint), 2) + Math.pow(center.distance(e.getPoint()), 2) - Math.pow(startPoint.distance(e.getPoint()), 2)) /
                                                    (2 * center.distance(startPoint) * center.distance(e.getPoint()))
                                    )
                            );

                            angle = angle / 180f * 2 * Math.PI;

                            Shape shape = null;
                            for(Point p : selected.verticles){
                                double x = startPoint.getX() + (p.getX() - startPoint.getX()) * Math.cos(angle) - (p.getY() - startPoint.getY()) * Math.sin(angle);
                                double y = startPoint.getY() + (p.getX() - startPoint.getX()) * Math.sin(angle) + (p.getY() - startPoint.getY()) * Math.cos(angle);
                                if(shape == null){
                                    shape = new Shape(new Point((int)x,(int)y));
                                } else {
                                    shape.verticles.add(new Point((int)x,(int)y));
                                }
                            }

                            assert shape != null;
                            for(int i = 1; i < shape.verticles.size(); i++){
                                g.drawLine((int)shape.verticles.get(i).getX(), (int)shape.verticles.get(i).getY(), (int)shape.verticles.get(i - 1).getX(), (int)shape.verticles.get(i - 1).getY());
                            }
                            g.drawLine((int)shape.verticles.get(shape.verticles.size() - 1).getX(), (int)shape.verticles.get(shape.verticles.size() - 1).getY(),
                                    (int)shape.verticles.get(0).getX(), (int)shape.verticles.get(0).getY());

                        }
                        break;
                }


            }
        });

    }

    public void changePoints(String s){
        String[] lines = s.split("\n");
        LinkedList<Point> points = new LinkedList<>();
        for (String l : lines){
            String[] temp = l.split(" ");
            points.add(new Point((int)Float.parseFloat(temp[2]), (int)Float.parseFloat(temp[4])));
        }
        selected.verticles = points;
        revalidate();
        paintComponent(getGraphics());
    }

    public void scaleSelected(String x1, String y1, String x2, String y2){
        if(selected == null) return;

        Point p1 = new Point(Integer.parseInt(x1), Integer.parseInt(y1));
        Point p2 = new Point(Integer.parseInt(x2), Integer.parseInt(y2));

        double vectorX = (p2.getX() - p1.getX()) * .01f + 1;
        double vectorY = (p2.getY() - p1.getY()) * .01f + 1;
        for(Point p : selected.verticles){
            p.setLocation(p.getX() * vectorX, p.getY() * vectorY);
        }
        revalidate();
        paintComponent(getGraphics());
    }

    public void rotateSelected(String x1, String y1, String angleString){
        if(selected == null) return;

        double angle = Double.parseDouble(angleString);

        Point p1 = new Point(Integer.parseInt(x1), Integer.parseInt(y1));

        angle = angle / 180f * 2 * Math.PI;

        Shape shape = null;
        for(Point p : selected.verticles){
            double x = p1.getX() + (p.getX() - p1.getX()) * Math.cos(angle) - (p.getY() - p1.getY()) * Math.sin(angle);
            double y = p1.getY() + (p.getX() - p1.getX()) * Math.sin(angle) + (p.getY() - p1.getY()) * Math.cos(angle);
            if(shape == null){
                shape = new Shape(new Point((int)x,(int)y));
            } else {
                shape.verticles.add(new Point((int)x,(int)y));
            }
        }

        assert shape != null;
        selected.verticles = shape.verticles;

        revalidate();
        paintComponent(getGraphics());

    }

    public void createSelected(String x1, String y1){
        if(creation != null){
            creation.verticles.add(new Point(Integer.parseInt(x1), Integer.parseInt(y1)));
        } else {
            creation = new Shape(new Point(Integer.parseInt(x1), Integer.parseInt(y1)));
        }
        revalidate();
        paintComponent(getGraphics());
    }

    public void saveCreated(){
        if(creation != null){
            shapes.add(creation);
        }
        revalidate();
        paintComponent(getGraphics());

        creation = null;
    }

    public void moveSelected(String x1, String y1, String x2, String y2){
        Point startPoint = new Point(Integer.parseInt(x1), Integer.parseInt(y1));
        Point endPoint = new Point(Integer.parseInt(x2), Integer.parseInt(y2));

        if(selected == null) return;

        double vectorX = endPoint.getX() - startPoint.getX();
        double vectorY = endPoint.getY() - startPoint.getY();
        for(Point p : selected.verticles){
            p.setLocation(p.getX() + vectorX, p.getY() + vectorY);
        }
        revalidate();
        paintComponent(getGraphics());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Shape s : shapes){
            for(int i = 1; i < s.verticles.size(); i++){
                g.drawLine((int)s.verticles.get(i).getX(), (int)s.verticles.get(i).getY(), (int)s.verticles.get(i - 1).getX(), (int)s.verticles.get(i - 1).getY());
                g.fillOval((int)s.verticles.get(i).getX() - 1, (int)s.verticles.get(i).getY() - 1, 3,3);
            }
            g.drawLine((int)s.verticles.get(s.verticles.size() - 1).getX(), (int)s.verticles.get(s.verticles.size() - 1).getY(),
                    (int)s.verticles.get(0).getX(), (int)s.verticles.get(0).getY());
            g.fillOval((int)s.verticles.get(0).getX() - 1, (int)s.verticles.get(0).getY() - 1, 3,3);
        }

        if(creation != null){
            for(Point p : creation.verticles){
                g.fillOval((int) p.getX() - 1, (int)p.getY() - 1, 3, 3);
            }
        }
    }

    private Shape tryToSelect(Point p){
        for(Shape s : shapes){
            for(int i = 1; i <= s.verticles.size(); i++){
                Point v1 = s.verticles.get((i - 1) % s.verticles.size());
                Point v2 = s.verticles.get(i % s.verticles.size());
                if(p.distance(v1) + p.distance(v2) - v1.distance(v2) <= 1f){
                    return s;
                }
            }
        }
        return null;
    }
}
