import java.util.*;
public class Problem2 {
   public static void main(String arga[]) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter three numbers: ");
    int a = scan.nextInt();
    int b = scan.nextInt();
    int c = scan.nextInt();
    
    if (a < b) {
        if (b > c || c < 4) {
            System.out.println("Celtics");
        } else {
            System.out.println("Bruins");
        }
        System.out.println("Patriots");
    } else if (b >= c) {
        if (!(a >= b)) {
            System.out.println("Red Sox");
        } else if (b == c && b < 5) {
            System.out.println("Renegades");
        }
        System.out.println("Terriers");
        if (a < c) {
            System.out.println("Crimson");
        }
    } else {
        System.out.println("Huskies");
        if (a == b) {
            System.out.println("Eagles");
        } else {
            System.out.println("Revolution");
        }
    }
    System.out.println("Go BU!");
   }
}
