public class part1{
    public static boolean search(int item, int[] arr, int start) {
        // Always check for null references first!
        if (arr == null) {
            throw new IllegalArgumentException();
        }
        if (start == arr.length-1){
            return false;
        }else{
            if (arr[start] == item){
                return true;
            }
            else{
                return search(item, arr, start+1);
            }
        }
    }

    public static int foo(int x, int y) {
        if (y == 0) {
            return x;
        } else if (x <= y) {
            return y;
        } else {
            int result1 = foo(x - 1, y - 1);
            int result2 = foo(x - 1, y + 1);
            return result1 + result2;
        }
    }

    public static void generateSums(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
                sum = sum + i;
                System.out.println(sum);
            } 
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5};
        if (search(4,arr,0) == true){
            System.out.println("right");
        }else{
            System.out.println("wrong");
        }
        System.out.println(foo(5,3));
        generateSums(6);
    }


}