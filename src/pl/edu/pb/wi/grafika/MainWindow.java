package pl.edu.pb.wi.grafika;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

import static pl.edu.pb.wi.grafika.Shape.*;

public class MainWindow extends JFrame {
    public JPanel jPanel;
    private JPanel topNavbar;
    private JPanel leftNavbar;
    private JPanel Content;
    private JComboBox selectShape;
    private JButton newCanvasButton;
    private JButton saveImageButton;
    private JButton undoButton;
    private JButton redoButton;
    private JTextField rectangleX;
    private JTextField rectangleY;
    private JTextField rectangleWidth;
    private JTextField rectangleHeight;
    private JPanel rectangle;
    private JPanel circle;
    private JTextField circleX;
    private JTextField circleY;
    private JTextField circleRadius;
    private JPanel Line;
    private JButton generateRectangleButton;
    private JButton generateCircleButton;
    private JTextField LineX1;
    private JTextField LineY1;
    private JTextField LineX2;
    private JTextField LineY2;
    private JButton generateLine;
    private JLabel mouseX;
    private JLabel mouseY;
    private JTextField selectedx1;
    private JTextField selectedy1;
    private JTextField selectedx2;
    private JTextField selectedy2;
    private JButton changeButton;

    private Point[] points = new Point[2];

    private List<Drawable> graphics;
    private Drawable dragged = null;
    private Point resizeStart = new Point();
    private Drawable selected = null;

    private Memento memento;

    public MainWindow(){
        add(jPanel);

        Content.setLayout(new GridBagLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Main Window");
        setSize(1280, 720);

        setVisible(true);

        removePoints();

        graphics = new LinkedList<>();
        memento = new Memento(graphics);

        Content.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                switch (e.getButton()) {
                    case 1:
                        DrawShape(e);
                        break;
                    case 2:
                        SelectShape(e);
                        break;
                    case 3:
                        MoveShape(e);
                        break;
                }
            }
        });
        saveImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                BufferedImage bufferedImage = new BufferedImage(Content.getWidth(), Content.getHeight(), BufferedImage.TYPE_INT_RGB);
//                Content.paint(bufferedImage.getGraphics());
//
//                try {
//                    ImageIO.write(bufferedImage, "jpg", new File("newImage.jpg"));
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
            }
        });
        generateRectangleButton.addActionListener(new ActionListener() {
            /**
             * @param actionEvent for now only clicked
             *
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int x = Integer.parseInt(rectangleX.getText());
                    int y = Integer.parseInt(rectangleY.getText());
                    int height = Integer.parseInt(rectangleHeight.getText());
                    int width = Integer.parseInt(rectangleWidth.getText());

                    Rectangle r = new Rectangle(
                            new Point[]{
                                    new Point(x,y),
                                    new Point(x + height, y + width)
                            });
                    graphics.add(r);
                    memento.Add(graphics);
                    r.Draw(Content.getGraphics());

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        generateCircleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int x = Integer.parseInt(circleX.getText());
                    int y = Integer.parseInt(circleY.getText());
                    int radius = Integer.parseInt(circleRadius.getText());

                    Circle c = new Circle(
                            new Point[]{
                                    new Point(x - radius, y - radius),
                                    new Point(x + radius, y + radius)
                            });

                    graphics.add(c);
                    memento.Add(graphics);
                    c.Draw(Content.getGraphics());

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        generateLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Line l = new Line(
                            new Point[] {
                                    new Point(Integer.parseInt(LineX1.getText()), Integer.parseInt(LineY1.getText())),
                                    new Point(Integer.parseInt(LineX2.getText()), Integer.parseInt(LineY2.getText()))
                            }
                    );
                    graphics.add(l);
                    memento.Add(graphics);
                    l.Draw(Content.getGraphics());

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        newCanvasButton.addActionListener(new ActionListener() {
            /**
             * Clears canvas on button click.
             * @param actionEvent onMouseClicked
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graphics.clear();
                memento.Add(graphics);
                PaintGraphics();
            }
        });
        Content.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                mouseX.setText("X: " + e.getX());
                mouseY.setText("Y: " + e.getY());

                if(dragged != null && resizeStart.isNull()) {
                    DrawDragged(e);
                } else if( dragged != null && !resizeStart.isNull()) {
                    Resize(e);
                }

                if(graphics != null)
                    for (Drawable d : graphics) {
                        d.Draw(Content.getGraphics());
                    }
            }
        });
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graphics = memento.Undo();
                memento.DrawCurrent(Content);
            }
        });
        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graphics = memento.Redo();
                memento.DrawCurrent(Content);
            }
        });
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graphics.remove(selected);

                selected.setPoints(
                        Integer.parseInt(selectedx1.getText()),
                        Integer.parseInt(selectedy1.getText()),
                        Integer.parseInt(selectedx2.getText()),
                        Integer.parseInt(selectedy2.getText())
                );

                graphics.add(selected);

                memento.Add(graphics);
                memento.DrawCurrent(Content);
            }
        });
    }

    /**
     * Draws live copy of edited shape.
     * @param e - mouse event to get current mouse location.
     */
    private void Resize(MouseEvent e) {
        Point[] newPoints = dragged.RecalculatePoints(resizeStart, new Point(e.getX(), e.getY()));

        switch(dragged.shape){
            case RECTANGLE:
                new Rectangle(newPoints).Draw(Content.getGraphics());
                break;
            case CIRCLE:
                new Circle(newPoints).Draw(Content.getGraphics());
                break;
            case LINE:
                new Line(newPoints).Draw(Content.getGraphics());
                break;
        }

        PaintGraphics();
    }

    /**
     * Draws current location of moved object.
     * @param e - mouse event to get current mouse location
     */
    private void DrawDragged(MouseEvent e) {
        Drawable drawable = null;

        jPanel.repaint();
        Content.repaint();

        switch (dragged.shape) {
            case RECTANGLE:
                int width = dragged.points[0].getX() - dragged.points[1].getX();
                int height = dragged.points[0].getY() - dragged.points[1].getY();

                drawable = new Rectangle(
                        new Point[] {
                                new Point(e.getX(), e.getY()),
                                new Point(e.getX() + width, e.getY() + height)
                        }
                );
                break;
            case CIRCLE:
                drawable = new Circle(
                        new Point[]{
                                new Point(e.getX(), e.getY()),
                                new Point(
                                        e.getX() + (Math.abs(dragged.points[0].getX() - dragged.points[1].getX())),
                                        e.getY() + (Math.abs(dragged.points[0].getY() - dragged.points[1].getY())))
                        }
                );
                break;
            case LINE:
                drawable = new Line(
                        new Point[]{
                                new Point(e.getX(), e.getY()),
                                new Point(
                                        e.getX() + (dragged.points[0].getX() - dragged.points[1].getX()),
                                        e.getY() + (dragged.points[0].getY() - dragged.points[1].getY()))
                        }
                );
                break;
        }

        PaintGraphics();

        drawable.Draw(Content.getGraphics());
    }

    private void MoveShape(MouseEvent e) {
        if(dragged != null && !resizeStart.isNull()) {

            dragged.points = dragged.RecalculatePoints(resizeStart, new Point(e.getX(), e.getY()));

            resizeStart = new Point();

        } else if(dragged != null) {

            dragged.setPoints(
                    e.getX(),
                    e.getY(),
                    e.getX() + (dragged.points[0].getX() - dragged.points[1].getX()),
                    e.getY() + (dragged.points[0].getY() - dragged.points[1].getY())
            );

        }

        if( dragged != null ){

            graphics.add(dragged);
            memento.Add(graphics);

            dragged = null;

            memento.DrawCurrent(Content);

            return;
        }

        for (Drawable d: graphics) {
            if(d.isVerticle(new Point(e.getX(), e.getY()), d.points[0], d.points[1]))
            {
                dragged = d;
                graphics.remove(d);

                resizeStart = d.getClosest( new Point(e.getX(), e.getY()) );
                return;
            }
            if(d.Contains(new Point(e.getX(), e.getY()))) {

                dragged = d;
                graphics.remove(d);

                return;
            }
        }
    }

    private void SelectShape(MouseEvent e){
        for (Drawable d: graphics) {
            if(d.Contains(new Point(e.getX(), e.getY()))) {
                selected = d;
                selectedx1.setText(String.valueOf(selected.points[0].getX()));
                selectedy1.setText(String.valueOf(selected.points[0].getY()));
                selectedx2.setText(String.valueOf(selected.points[1].getX()));
                selectedy2.setText(String.valueOf(selected.points[1].getY()));
                return;
            }
        }
    }

    /**
     * Method with two states used to draw shape selected in ComboBox.
     * @param e - mouse clicked location.
     */
    private void DrawShape(MouseEvent e) {
        if(dragged != null) return;

        if(points[0].isNull()) {
            points[0].setX(e.getX());
            points[0].setY(e.getY());
        } else {
            points[1].setX(e.getX());
            points[1].setY(e.getY());

            Graphics g = Content.getGraphics();

            switch (selectShape.getItemAt(selectShape.getSelectedIndex()).toString()){
                case "Line":
                    Line l = new Line(points);
                    graphics.add(l);
                    l.Draw(g);
                    break;
                case "Rectangle":
                    Rectangle r = new Rectangle(points);
                    graphics.add(r);
                    r.Draw(g);
                    break;
                case "Circle":
                    Circle c = new Circle(points);
                    graphics.add(c);
                    c.Draw(g);
                    break;
            }
            memento.Add(graphics);
            removePoints();
        }
    }

    /**
     * Set Points to fulfill isNull requirement.
     */
    private void removePoints() {
        points[0] = new Point();
        points[1] = new Point();
    }

    /**
     * Short often used method to refresh Content JPanel and draw actual state of graphics.
     */
    private void PaintGraphics() {
        jPanel.repaint();
        Content.repaint();
        memento.DrawCurrent(Content);
    }

}
