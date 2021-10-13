package CommonItems;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.InputStreamReader;

public class Main
{
    public static void printCommonItems(Reader in, PrintStream out)
    {
        // Add code here
        ArrayList<String> first, second;
        StringBuilder str = new StringBuilder();
        Scanner sc = new Scanner(in);
        first = new ArrayList<>(Arrays.asList(sc.nextLine().split(" ")));
        second = new ArrayList<>(Arrays.asList(sc.nextLine().split(" ")));
        int i = 0;
        int j = 0;
        int n1, n2;
        while(i < first.size() && j < second.size()){
            n1 = Integer.parseInt(first.get(i));
            n2 = Integer.parseInt(second.get(j));
            if(n1 == n2){
                str.append(n1 + " ");
                i++;
                j++;
            }else if(n1 > n2){
                j++;
            }else{
                i++;
            }
        }
        out.println(str.toString().trim());
    }

    // Add methods here if needed

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

        printCommonItems(in, System.out);
    }
}

