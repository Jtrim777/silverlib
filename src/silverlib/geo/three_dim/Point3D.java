package silverlib.geo.three_dim;

import silverlib.math.Num;

public class Point3D {
    protected double x;
    protected double y;
    protected double z;

    public Point3D(double xi, double yi, double zi){
        x = xi;
        y = yi;
        z = zi;
    }

    public Point3D(String s){
        String s2 = s.replaceAll("[()]", "");

        String[] pts = s2.split(",");

        x = Double.parseDouble(pts[0]);
        y = Double.parseDouble(pts[1]);
        z = Double.parseDouble(pts[2]);
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    public boolean equals(Point3D other){
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }

    public Point3D add(Point3D rhs){
        double nx = this.x + rhs.x;
        double ny = this.y + rhs.y;
        double nz = this.z + rhs.z;

        return new Point3D(nx, ny, nz);
    }

    public double distanceTo(Point3D end){
        return Math.sqrt(Num.square(end.x-this.x) + Num.square(end.y-this.y) + Num.square(end.z-this.z));
    }

    @Override
    public String toString() {
        return "("+ x + ", " + y + ", " + z + ")";
    }
}
