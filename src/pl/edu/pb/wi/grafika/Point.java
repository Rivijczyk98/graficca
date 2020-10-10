package pl.edu.pb.wi.grafika;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = -1;
        this.y = -1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isNull(){
        return x < 0 && y < 0;
    }

    public void setPoints(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean Equals (int x, int y) {
        return this.x == x && this.y == y;
    }

    public boolean Equals (Point s) {
        return this.x == s.getX() && this.y == s.getY();
    }
}
