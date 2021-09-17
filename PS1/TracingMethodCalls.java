public class TracingMethodCalls {
    public static int compute(int x, int y) {
        y += x;
        x = 10 - y;
        System.out.println(x + " " + y);
        return x;
    }
    public static double triArea( int b, int h) {
        double area = b*h/2.0;
        return area;
    }
    public static void main(String[] args) {
        int x = 6;
        int y = 3;
        System.out.println(x + " " + y);
        y = compute(x, y);
        System.out.println(x + " " + y);
        x = compute(y, x) + 1;
        System.out.println(x + " " + y);
        compute(x, x);
        System.out.println(x + " " + y);
        System.out.println(triArea(6,3));
        System.out.println(triArea(7,5));
        int m = 20;   // do not change this line
        while (m >= 0) {
            System.out.println(m + " ");
            m -= 2;
        }

        for (int i = 4; i > 0; i--) {
            for (int j = 1; j <= i+1; j++) {
                System.out.println(i + " " + j);
            }
            System.out.println("--");
        }
        String name = "cece tian";
        System.out.print(name.charAt(0)+""+name.charAt(6));
    }
}
