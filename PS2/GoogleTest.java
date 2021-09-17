import java.util.*;
public class GoogleTest{
    public static void mystery(int[] values) {
        int val = values[0];
    
        for (int i = 0; i < values.length - 1; i++) {
            values[i] = values[i + 1];
            values[i]++;
            // 1-1: What does the array look like here,
            // for each value of the loop variable `i`?
        }
    
        values[values.length - 1] = val + 1;
    
        // 1-2: What does the array look like here?
    }
    public static void main(String[] args){
        int[] arr = {1, 3, 5, 7, 9, 11, 13};
        mystery(arr);
        System.out.println(Arrays.toString(arr));
        int[][] twoD = { {1, 2, 3, 4},
                 {5, 6, 7, 8},
                 {9, 10, 11, 12},
                 {13, 14, 15, 16} };
        for (int i=0;i<twoD.length;i++){
            System.out.println(twoD[i][0]);
        }
        for (int i=twoD.length-1;i>=0;i--){     
            System.out.println(twoD[i][twoD[0].length-i-1]);
        }
        int[] x = new int[3];
        System.out.println(Arrays.toString(x));
    }

}