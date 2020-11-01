package pl.edu.pb.wi.grafika.UI.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;

public class BezierPanel extends JPanel {

    public LinkedList<Point> pointList = new LinkedList<>();
    public Point dragged = null;

    public BezierWindow bezierWindow;

    public BezierPanel(BezierWindow _bezierWindow) {
        super(new GridBagLayout());

        bezierWindow = _bezierWindow;

        setBackground(Color.WHITE);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                bezierWindow.setPosition(e.getPoint());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if(dragged != null){
                    dragged.setLocation(e.getX(), e.getY());
                    paintComponent(getGraphics());
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                if(dragged != null) return;

                Point c = getPoint(e.getPoint());

                if(e.getButton() == 2){
                    bezierWindow.bezierTools.setSelectedPoint(c);
                    return;
                }

                if( c != null ){
                    dragged = c;
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(getPoint(e.getPoint()) == null && dragged == null) {
                    addPoint(e.getPoint());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if(dragged != null){
                    dragged = null;
                }
            }
        });
    }

    public void addPoint(Point p){
        pointList.add(p);
        revalidate();
        paintComponent(getGraphics());
    }

    public Point getPoint(Point e){
        for(Point p : pointList){
            if(p.distance(e) < 7){
                return p;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Point p : pointList){
            g.setColor(Color.RED);
            g.fillOval((int)(p.getX() - 3), (int)(p.getY() - 3), 7, 7);
        }

//        if(dragged != null) pointList.add(dragged);

        Point start = null;
        if(pointList.size() > 1){
            LinkedList<Point> points = new LinkedList<>();
            double precision = 1f / Float.parseFloat(bezierWindow.bezierTools.degree.getText());
            int size = pointList.size();

            for(float c = 0; c <= 1f; c += precision){

                double sumX = 0;
                double sumY = 0;

                for(int i = 0; i < size; i++){
                    double w = binomialCoefficient(size - 1, i) * Math.pow(1 - c, size - 1 - i) * Math.pow(c, i);
                    sumX += pointList.get(i).getX() * w;
                    sumY += pointList.get(i).getY() * w;
                }

//                for (int i = 0; i < size; i++){
//                    sumX += bPoly[i] * pointList.get(i).getX();
//                    sumY += bPoly[i] * pointList.get(i).getY();
//                }

                if(c == 0){
                    start = new Point((int)sumX, (int)sumY);
                }

                int x = (int)Math.round(sumX);
                int y = (int)Math.round(sumY);

                points.add(new Point(x, y));
            }

            for(Point p : points){
                g.setColor(Color.BLACK);
                g.fillOval((int)(p.getX() - 1), (int)(p.getY() - 1), 3, 3 );
            }
        }

//        if(dragged != null){
////            pointList.remove(dragged);
//            g.setColor(Color.RED);
//            g.fillOval((int)(dragged.getX() - 3), (int)(dragged.getY() - 3), 7, 7);
//        }
    }

    private double binomialCoefficient(int n, int k){
        return (factorial(n) / (factorial(k) * factorial(n - k)));
    }

    private float factorial(float x){
        if(x <= 1f)
            return 1f;
        else
            return factorial(x - 1f ) * x;
    }
}
