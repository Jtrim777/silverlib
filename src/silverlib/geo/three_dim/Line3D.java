package silverlib.geo.three_dim;

public class Line3D extends Shape3D{
    private double length;
    private double slopeX;
    private double slopeZ;

    public Line3D(Point3D start, Point3D end){
        super(start);

        double deltaX = end.x - start.x;
        double deltaY = end.y - start.y;
        double deltaZ = end.z - start.z;

        slopeX = deltaX/deltaY;
        slopeZ = deltaZ/deltaY;

        length = start.distanceTo(end);

        double pointsInLine = deltaY > 15 ? (double)(int)deltaY : 15;
        double yChange = deltaY > 15 ? deltaY/pointsInLine : deltaY/15;

        Point3D pt = start;

        for (double di = 0; di < pointsInLine; di++){
            double nx = pt.x + yChange * slopeX;
            double ny = pt.y + yChange;
            double nz = pt.z + yChange * slopeZ;

            pt = new Point3D(nx, ny, nz);

            this.pts.add(pt);
        }

        this.pts.add(end);
    }
}
