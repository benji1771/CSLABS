

public class VersionString implements Comparable<VersionString>
{
    // Add data members as needed.
    int[] ver;
    // A varargs constructor. If you have not used a variable argument (varargs)
    // method before, it allows a method to accept any number of arguments. In
    // this case any number of int arguments. In this case, the arguments will
    // be given as an int array named values. The size of the array will be
    // controlled by the number of arguments. Thus
    //
    // VersionString s1 = VersionString(1);
    // VersionString s2 = VersionString(1, 2);
    // VersionString s3 = VersionString(1, 2, 3);
    // VersionString s4 = VersionString(1, 2, 3, 4);
    //
    // are all valid. In each case the arguments will be placed in the array values.
    // The rules for varargs are that there can be only one variable argument in the
    // method and it must be the last argument.
    //
    public static void main(String[] args){
        VersionString s1 = new VersionString(1);
        VersionString s2 = new VersionString(1, 2);
        VersionString s3 = new VersionString(1, 2, 3);
        VersionString s4 = new VersionString(1, 2, 3, 4);

        System.out.println(s2.compareTo(s1));
    }
    // Construct a VersionString from the given values. If no values are given or if
    // a negative value is given then an IllegalArgumentException will be thrown.
    public VersionString(int ... values)
    {
        if(values.length == 0) throw new IllegalArgumentException();
        for(int n: values)
            if(n < 0) throw new IllegalArgumentException();
        ver = values;
    }
    public int[] getVer(){
        return ver;
    }
    // Returns the VersionString as a dot separated string such as 1.1, 2.0.1, and
    // 3.2.3.1
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ver.length - 1; i++)
            sb.append(ver[i] + ".");
        sb.append(ver[ver.length - 1]);
        return sb.toString();
    }

    // VersionStrings are compared by the leading numbers first with ties broken
    // by later numbers and shorter strings are "less than" longer string with the
    // same prefix. Thus, 1.1 is less than both 1.1.0 and 1.1.1 but greater than
    // 1.0.13
    @Override
    public int compareTo(VersionString o)
    {
       int i = 0;
       int[] ot = o.getVer();
       while(i < ver.length && i < ot.length){
           if(ver[i] > ot[i]){
            return 1;
           }else if(ver[i] < ot[i]){
            return -1;
           }
           i++;
       }
       if(ver.length > ot.length) return 1;
       if(ver.length < ot.length) return -1;
       return 0;
    }

}