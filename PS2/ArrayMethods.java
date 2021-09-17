import java.util.*;
public class ArrayMethods {
    // Question 0
    public static final String[] COLLEGES = {
        "CAS", "CFA", "CGS", "COM", "ENG", "QST", 
        "SAR", "SED", "SHA", "OTHER"
      };

//Question 1
    public static int getCollegeNum(String coll){
        if (coll == null){
            return -1;
        }
        else{
            for(int i=0; i<COLLEGES.length;i++){
                if (coll.equals(COLLEGES[i])){
                    return i;
                }
            }
        }
        return -1;
    }

//Question 2
    public static void negateEvens(int[] arr){
        if (arr == null){
            throw new IllegalArgumentException();
        }else{
            for (int i=0;i<arr.length;i++){
                if (arr[i]%2==0){
                    arr[i]*=-1;
                }
            }
        }
    }

//Question 3
    public static int[] slice(int[] values, int start, int end){
        int[] x = new int[0];
        if (values == null){
            throw new IllegalArgumentException();
        }else{
            if (start<0||end<0||start>values.length||end>=values.length){
                throw new ArrayIndexOutOfBoundsException();
            }else if (end <= start){
                return x;
            }else{
                x = new int[end-start];
                for(int i=0;i<end-start;i++){
                    x[i] = values[i+start];
                }
            }
        }
        return x;
    }

//Question 4
    public static boolean isSorted(int[] arr){
        if (arr == null){
            throw new IllegalArgumentException();
        }else{
            if (arr.length<=1){
                return true;
            }else{
                for(int i=0;i<arr.length-1;i++){
                    if (arr[i]>arr[i+1]){
                        return false;
                    }
                }

            }
        }
        return true;
    }

//Question 5
    public static int maxSorted(int[] values){
        int result = 1;
        if (values == null){
            throw new IllegalArgumentException();
        }else if(values.length <=1){
            return values.length;
        }else{
            for(int i=0;i<values.length-1;i++){
                int x =0;
                for (int j=i;j<values.length-1;j++){
                    if (values[j]<=values[j+1]){
                        x++;
                    }
                    else{
                        x++;
                        if (x>=result){
                            result = x;
                        }
                        break;
                    }
                }
            }
        }
        return result;
    }


    public static void main(String[] args){
        int[] a2 = {2, 5, 6, 3, 7, 4, 1};
        int[] a3 = ArrayMethods.slice(a2, 2, 5);
        System.out.println(Arrays.toString(a3));
        int[] a4 = ArrayMethods.slice(a2, 5, 2);
        System.out.println(Arrays.toString(a4));
        int[] a5 = {2, 5, 6, 6, 9, 9, 9, 10};
        int[] a6 = {2, 5, 6, 4, 9, 9, 7, 10};
        System.out.println(ArrayMethods.isSorted(a5));
        System.out.println(ArrayMethods.isSorted(a6));
        int[] a7 = {3, 8, 6, 14, -3, 0, 14, 207, 98, 12};
        System.out.println(ArrayMethods.maxSorted(a7));
        int[] a8 = {17, 42, 3, 5, 5, 5, 8, 4, 6, 1, 19};
        System.out.println(ArrayMethods.maxSorted(a8));
        int[] a1 = {1, 2, 4, 5, -8, -10, -11};
        negateEvens(a1);
        System.out.println(Arrays.toString(a1));
    }
    
}
