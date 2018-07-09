package silverlib.geo;

import java.util.ArrayList;

public class ArbitraryShape extends Shape {
    private int maxX;
    private int minX;
    private int maxY;
    private int minY;

    private int width;
    private int height;

    public ArbitraryShape(){
        super(new Point(0,0));

        this.pts = new ArrayList<>();
    }

    public int maxX() {
        return maxX;
    }

    public int minX() {
        return minX;
    }

    public int maxY() {
        return maxY;
    }

    public int minY() {
        return minY;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public ArbitraryShape(ArrayList<Point> pts){
        super(pts.get(0));
        this.pts = pts;

        minX = pts.get(0).x();
        maxX = pts.get(0).x();
        minY = pts.get(0).y();
        maxY = pts.get(0).y();

        for (Point p : pts){
            if (p.x() < minX){
                minX = p.x();
            }
            if (p.x() > maxX){
                maxX = p.x();
            }
            if (p.y() < minY){
                minY = p.y();
            }
            if (p.y() > maxY){
                maxY = p.y();
            }
        }

        width = maxX - minX;
        height = maxY - minY;

    }

    public boolean borderContains(Point op){
        for (Point p : pts){
            if (p.x() == op.x() && p.y() == op.y()){
                return true;
            }
        }

        return false;
    }

    public boolean surrounds(Point op){
        if (borderContains(op)){
            return true;
        }

        int intersectCount = 0;
        boolean didJustIntersect = false;

        for (int x = minX - 5; x < op.x(); x++) {
            boolean onLine = borderContains(new Point(x,op.y()));

            if (onLine && !didJustIntersect){
                intersectCount += 1;
                didJustIntersect = true;
            } else if (!onLine && didJustIntersect){
                didJustIntersect = false;
            }
        }

        return intersectCount > 0 && intersectCount % 2 > 0;
    }

    @Override
    public String toString() {
        return null;
    }
}

