import java.util.*;
public class Problem5{
    public static int[] intersect(int[] arr1, int[] arr2){
        int s;
        if(arr1.length<arr2.length){
            s =arr1.length;
        }else{
            s=arr2.length;
        }
        int[] intersect = new int[s];
        Sort.quickSort(arr1);
        Sort.quickSort(arr2);
        int index = 0;
        int x1 = 0;
        int x2 = 0;
        while(index<s&& x1<arr1.length && x2<arr2.length){
            if (arr1[x1] == arr2[x2]){
                if(index==0||(index-1>=0 && intersect[index-1]!=arr1[x1])){
                    intersect[index]=arr1[x1];
                    index++;
                }
                x1++;
                x2++;
            }else if(arr1[x1]<arr2[x2]){
                x1++;
            }else{
                x2++;
            }
        }
        while(index<s){
            intersect[index]=0;
            index++;
        }
        return intersect;
    }

    public static void main(String[] args){
        int[] a1 = {10, 5, 7, 5, 9, 4};
        int[] a2 = {7, 5, 15, 7, 7, 9, 10};
        int[] result1 = intersect(a1, a2);
        System.out.println("result1: " + Arrays.toString(result1));

        int[] a3 = {0, 2, -4, 6, 10, 8};
        int[] a4 = {12, 0, -4, 8};
        int[] result2 = intersect(a3, a4);
        System.out.println("result2: " + Arrays.toString(result2));

        int[] a5 = {7,7,7};
        int[] a6 = {7,7,7,7};
        int[] result3 = intersect(a5, a6);
        System.out.println("result2: " + Arrays.toString(result3));
    }
}