import java.util.Objects;

// Complete this Point3D class to help you solve the Lab. You can add any
// methods you wish to this class.
public class Point3D implements Comparable<Point3D>
{
    private final double x, y, z;

    public Point3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return Double.compare(point3D.x, x) == 0 &&
                Double.compare(point3D.y, y) == 0 &&
                Double.compare(point3D.z, z) == 0;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ", " + z + ')';
    }

    @Override
    public int compareTo(Point3D o)
    {
        Double d1 = Math.sqrt((x * x) + (y * y) + (z * z));
        Double d2 = Math.sqrt((o.x * o.x) + (o.y * o.y) + (o.z * o.z));
        if(d1 > d2) return 1;
        if(d1 < d2) return -1;
        else{
            if(x > o.x) return 1;
            if(x < o.x) return -1;
            if(y > o.y) return 1;
            if(y < o.y) return -1;
            if(z > o.z) return 1;
            if(z < o.z) return -1;
            return 0;
        }
    }
}
