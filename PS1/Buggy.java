import java.util.*;

public class Buggy{
    /*
     * This static method should take an integer x and return:
     *    x + 1 if x is even
     *    the unchanged value of x when x is odd
     */
    public static int makeOdd(int x){
        if (x % 2 == 0){
            x++;
        }
        return x;
    }
    public static void main(String args[]) {
       Scanner console = new Scanner(System.in);
        System.out.print("Enter an integer x:");
        int x = console.nextInt();
        System.out.println("makeOdd(x) = " + makeOdd(x));

        int a = 10;
        int b = 4;
        double c = 4;
        double d = 4.0;
        System.out.println(a + d);
        System.out.println( a + "d");
        System.out.println(b/a);
        System.out.println(c/a);
        System.out.println(d/a);
        System.out.println(a / b * d);
        System.out.println((double)(a / b));
        System.out.println((double)a / b);
        System.out.println(1 + 2 + "3");
        System.out.println(1 + "2" + 3);
    }
}
