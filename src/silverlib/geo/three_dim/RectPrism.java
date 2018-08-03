package silverlib.geo.three_dim;

public class RectPrism extends Shape3D {
    private Point3D[] vertices;
    private double width;
    private double length;
    private double height;
    private double volume;
    private double surfaceArea;

    public RectPrism(Point3D p1, Point3D p2){
        super(p1);

        Point3D v1 = p1;
        Point3D v8 = p2;

        if (p2.x < p1.x || p2.y < p1.y || p2.z < p1.z){
            v8 = p1;
            v1 = p2;
        }

        width = v8.x - v1.x;
        length = v8.z - v1.z;
        height = v8.y - v1.y;

        volume = width * length * height;
        surfaceArea = (2*width*height) + (2*length*height) + (2*width*length);

        Point3D v2 = new Point3D(v1.x+width, v1.y, v1.z);
        Point3D v3 = new Point3D(v1.x, v1.y, v1.z+length);
        Point3D v4 = new Point3D(v1.x, v1.y+height, v1.z);
        Point3D v5 = new Point3D(v1.x+width, v1.y+height, v1.z);
        Point3D v6 = new Point3D(v1.x, v1.y+height, v1.z+length);
        Point3D v7 = new Point3D(v1.x+width, v1.y, v1.z+length);

        vertices = new Point3D[]{v1, v2, v3, v4, v5, v6, v7, v8};

        Point3D[][] faceSourcePoints = new Point3D[][]{{v1,v6},{v1,v5},{v1,v7},{v4,v8},{v2,v8},{v3,v8}};

        this.pts.clear();

        for (Point3D[] faceSrc : faceSourcePoints){
            double deltaX = faceSrc[1].x - faceSrc[0].x;
            double deltaY = faceSrc[1].y - faceSrc[0].y;
            double deltaZ = faceSrc[1].z - faceSrc[0].z;

            double xPts = deltaX > 15 ? (double)(int)deltaX : 15;
            double yPts = deltaY > 15 ? (double)(int)deltaY : 15;
            double zPts = deltaZ > 15 ? (double)(int)deltaZ : 15;

            double xInc = deltaX/xPts;
            double yInc = deltaY/yPts;
            double zInc = deltaZ/zPts;

            xInc = deltaX == 0 ? 0 : xInc;
            yInc = deltaY == 0 ? 0 : yInc;
            zInc = deltaZ == 0 ? 0 : zInc;

            Point3D start = faceSrc[0];

            for(int x=0; x<xPts; x++){
                for(int y=0; y<yPts; y++){
                    for(int z=0; z<zPts; z++){
                        double nx = start.x + (xInc*x);
                        double ny = start.y + (yInc*y);
                        double nz = start.z + (zInc*z);

                        pts.add(new Point3D(nx, ny, nz));
                    }
                }
            }
        }
    }

    public Point3D[] getVertices() {
        return vertices;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getHeight() {
        return height;
    }

    public double getVolume() {
        return volume;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
