import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static final int EXIT_FAILURE = 1;

    // Reads all of the points given on System.in (a point consists of three
    // space separated doubles) and prints the top n points that where closest
    // to the origin on System.out.
    public static void top(int n)
    {
        MinPQ<Point3D> points = new MinPQ<>();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String[] pt = sc.nextLine().split(" ");
            Double[] dt = Arrays.stream(pt).map(Double::valueOf).toArray(Double[]::new);
            if(pt.length == 3)
                points.insert(new Point3D(dt[0], dt[1], dt[2]));
        }
        int i = 0;
        while(!points.isEmpty() && i < n){
            System.out.println(points.delMin().toString());
            i++;
        }
    }

    private static void printUsage()
    {
        System.out.println("Given an integer n from the command line and an input stream where\n" +
                "each line contains three doubles, x, y, and z this program will print the top\n" +
                "n points (x, y, z) that are closest to the origin. Ties are broken by taking\n" +
                "points with the smallest x, y, and z values in that order.");
    }

    public static void main(String[] args)
    {
        if(args.length != 1)
        {
            System.err.println(args.length + " arguments given, 1 argument expected!");
            printUsage();
            System.exit(EXIT_FAILURE);
        }

        int n;
        try
        {
            n = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex)
        {
            System.err.println(args[0] + " is not a valid number!");
            printUsage();
            System.exit(EXIT_FAILURE);
            return;
        }

        if(n < 1)
        {
            System.err.println("n must be > 0!");
            printUsage();
            System.exit(EXIT_FAILURE);
        }

        top(n);
    }
}
