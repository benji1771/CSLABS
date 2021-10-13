package PointsOnLine;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.InputStreamReader;

public class Main
{
    public static int numberOfPointTriplesOnALine(Reader in)
    {
        Scanner sc = new Scanner(in);
        ArrayList<String> inputs = new ArrayList<>();
        String temp;
        while(sc.hasNextLine()){
            temp = sc.nextLine();
            if(!inputs.contains(temp)) inputs.add(temp);
        }
        int result = 0;
        for(int i = 0; i < inputs.size() - 2; i++){
            double[] first = Arrays.asList(inputs.get(i).split(" ")).stream().mapToDouble(Double::parseDouble).toArray();
            for(int k = i + 1; k < inputs.size() - 1; k++){
                double[] second = Arrays.asList(inputs.get(k).split(" ")).stream().mapToDouble(Double::parseDouble).toArray();
                for(int j = k + 1; j < inputs.size(); j++){
                    double[] third = Arrays.asList(inputs.get(j).split(" ")).stream().mapToDouble(Double::parseDouble).toArray();
                    double check = first[0] * (second[1] - third[1]) + second[0] * (third[1] - first[1]) + third[0] * (first[1] - second[1]);
                    if(check == 0.0){
                        result++;
                    }
                }
            }
        }
        return result;
    }

    // Do not edit main
    public static void main(String[] args)
    {
        Reader in;
        if(args.length > 0)
        {
            try
            {
                in = new FileReader(args[0]);
            }
            catch (IOException ex)
            {
                System.err.println("Unable to open " + args[0]);
                System.exit(1);
                return;
            }
        }
        else
        {
            in = new InputStreamReader(System.in);
        }

        System.out.println(numberOfPointTriplesOnALine(in));
    }
}
