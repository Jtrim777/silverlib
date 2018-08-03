package silverlib.geo.three_dim;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Shape3D {
    protected ArrayList<Point3D> pts;
    private Point3D origin;
    private Color color;

    public Shape3D(Point3D center){
        pts = new ArrayList<>();
        pts.add(center);

        origin = center;

        color = Color.BLACK;
    }

    public Shape3D(List<Point3D> points){
        pts = (ArrayList<Point3D>) points;

        origin = pts.get(0);

        color = Color.BLACK;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Point3D> getPts() {
        return pts;
    }

    @Override
    public String toString() {
        return pts.toString();
    }
}
