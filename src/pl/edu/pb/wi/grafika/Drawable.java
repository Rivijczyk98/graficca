package pl.edu.pb.wi.grafika;

public abstract class Drawable implements IDrawable {

    protected Point[] points;
    protected final float error = 3f;
    protected Shape shape;

    public Drawable(Point[] points) {
        this.points = new Point[2];

//        if(
//                Math.sqrt(Math.pow(points[0].getX(), 2) + Math.pow(points[0].getY(),2))
//                < Math.sqrt(Math.pow(points[1].getX(), 2) + Math.pow(points[1].getY(),2))
//        ) {
            this.points[0] = new Point(points[0].getX(), points[0].getY());
            this.points[1] = new Point(points[1].getX(), points[1].getY());
//        } else {
//            this.points[1] = new Point(points[0].getX(), points[0].getY());
//            this.points[0] = new Point(points[1].getX(), points[1].getY());
//        }

    }

    public void setPoints(int x1, int y1, int x2, int y2) {
        points = new Point[]{
                new Point(x1, y1),
                new Point(x2, y2)
        };
    }

    @Override
    public boolean isVerticle(Point src, Point point1, Point point2) {
        return  (
                        Math.abs(src.getY() - point1.getY()) < error &&
                                Math.abs(src.getX() - point1.getX()) < error
                ) || (
                        Math.abs(src.getY() - point2.getY()) < error &&
                                Math.abs(src.getX() - point1.getX()) < error
                ) || (
                        Math.abs(src.getY() - point2.getY()) < error &&
                                Math.abs(src.getX() - point2.getX()) < error
                ) || (
                        Math.abs(src.getY() - point1.getY()) < error &&
                                Math.abs(src.getX() - point2.getX()) < error
                );
    }

    @Override
    public Point getClosest(Point src) {

        double[] distances = new double[4];

        distances[0] =
                Math.sqrt(
                        Math.pow(src.getX() - points[0].getX(), 2)
                        + Math.pow(src.getY() - points[0].getY(), 2)
                );

        distances[1] =
                Math.sqrt(
                        Math.pow(src.getX() - points[0].getX(), 2)
                                + Math.pow(src.getY() - points[1].getY(), 2)
                );

        distances[2] =
                Math.sqrt(
                        Math.pow(src.getX() - points[1].getX(), 2)
                                + Math.pow(src.getY() - points[1].getY(), 2)
                );

        distances[3] =
                Math.sqrt(
                        Math.pow(src.getX() - points[1].getX(), 2)
                                + Math.pow(src.getY() - points[0].getY(), 2)
                );

        double min =
                Math.min(
                        Math.min(distances[0], distances[1]),
                        Math.min(distances[2], distances[3])
                );

        if(min == distances[0]){
            return new Point(points[0].getX(), points[0].getY());
        } else if( min == distances[1]) {
            return new Point(points[0].getX(), points[1].getY());
        } else if( min == distances[2]) {
            return new Point(points[1].getX(), points[1].getY());
        } else {
            return new Point(points[1].getX(), points[0].getY());
        }
    }

    @Override
    public Point[] RecalculatePoints(Point startPoint, Point finalPoint) {

        Point[] tempPoints = new Point[]{
                new Point(points[0].getX(), points[0].getY()),
                new Point(points[1].getX(), points[1].getY())
        };

        tempPoints[0].setX(tempPoints[0].getX() == startPoint.getX() ? finalPoint.getX() : tempPoints[0].getX());
        tempPoints[0].setY(tempPoints[0].getY() == startPoint.getY() ? finalPoint.getY() : tempPoints[0].getY());

        tempPoints[1].setX(tempPoints[1].getX() == startPoint.getX() ? finalPoint.getX() : tempPoints[1].getX());
        tempPoints[1].setY(tempPoints[1].getY() == startPoint.getY() ? finalPoint.getY() : tempPoints[1].getY());

        return tempPoints;
    }
}
