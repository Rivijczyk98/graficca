package pl.edu.pb.wi.grafika.models;

public class HsvColor {
    public float h, s, v;

    public HsvColor(float _h, float _s, float _v) {
        h = _h;
        s = _s;
        v = _v;
    }

    @Override
    public String toString() {
        return "h: " + h + ", s: " + s + ", v: " + v;
    }
}
