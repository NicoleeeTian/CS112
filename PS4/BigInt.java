import java.util.*;
/* 
 * BigInt.java
 *
 * A class for objects that represent non-negative integers of 
 * up to 20 digits.
 */

public class BigInt  {
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int SIZE = 20;
    
    // the array of digits for this BigInt object
    private int[] digits;
    
    // the number of significant digits in this BigInt object
    private int numSigDigits;

    /*
     * Default, no-argument constructor -- creates a BigInt that 
     * represents the number 0.
     */
    public BigInt() {
        this.digits = new int[SIZE];
        this.numSigDigits = 1;  // 0 has one sig. digit--the rightmost 0!
    }
    /*
     * Non-Default,constructor -- creates a BigInt that 
     * represents a specific number from parameter.
     */
    public BigInt(int[] arr){
        if(arr == null|| arr.length>SIZE){
            throw new IllegalArgumentException();
        }else{
            int first = arr.length-1;
            this.digits = new int[SIZE];
            for(int i=0; i<arr.length;i++){
                if(arr[i]!=0){
                    first =i;
                    break;
                }
            }
            int j= first;
            while(j<arr.length){
                if (arr[j]<0 || arr[j]>9){
                    throw new IllegalArgumentException();
                }
                this.digits[SIZE-arr.length+j] = arr[j];
                j++;
            }
            this.numSigDigits = arr.length-first;
        }
    }

    // Task 2 
    public int getNumSigDigits(){
        return this.numSigDigits;
    }
    public String toString(){
        String x = "";
        for(int i=SIZE-this.numSigDigits;i<SIZE;i++){
            x+=this.digits[i];
        }
        return x;
    }

    //Task 3
    public BigInt(int n){
        if (n<0){
            throw new IllegalArgumentException();
        }else{
            this.digits = new int[SIZE];
            int last = n;
            int times=1;
            for(int i =SIZE-1;i>=0;i--){
                if (last>9){
                    int left =last%10;
                    this.digits[i]=left;
                    last/=10;
                    times++;
                }else{
                    this.digits[i]=last;
                    break;
                }
            }
            this.numSigDigits = times;
        }
    }


    public int compareTo(BigInt other){
        if (other== null){
            throw new IllegalArgumentException();
        }else{
            if (this.numSigDigits<other.numSigDigits){
                return -1;
            }else if(this.numSigDigits>other.numSigDigits){
                return 1;
            }else{
                for(int i=this.numSigDigits;i>0;i--){
                    if(this.digits[SIZE-i]>other.digits[SIZE-i]){
                        return 1;
                    }
                    if(this.digits[SIZE-i]<other.digits[SIZE-i]){
                        return -1;
                    }
                }
            }
        }
        return 0;
    }

    public BigInt add(BigInt other){
        BigInt sum = new BigInt();
        if (other == null){
            throw new IllegalArgumentException();
        }else if(this.numSigDigits==1&&this.digits[SIZE-1]==0 && other.numSigDigits==1&&other.digits[SIZE-1]==0){
            sum.digits[SIZE-1]=0;
            return sum;
        }else if(this.numSigDigits==1&&this.digits[SIZE-1]==0 && !(other.numSigDigits==1&&other.digits[SIZE-1]==0)){
            sum=other;
            return sum;
        }else if(!(this.numSigDigits==1&&this.digits[SIZE-1]==0 )&& (other.numSigDigits==1&&other.digits[SIZE-1]==0)){
            sum = this;
            return sum;
        }else{
            BigInt large=new BigInt();
            if(this.numSigDigits>=other.numSigDigits){
                large = this;
            }
            else{
                large = other;
            }
            sum.numSigDigits=0;
            int carry=0;
            for(int i=SIZE-1;i>=SIZE-large.numSigDigits-1;i--){
                if(sum.numSigDigits>SIZE||i<0){
                    throw new ArithmeticException();
                }else{
                    int digit = this.digits[i] + other.digits[i]; 
                    int last = (digit%10+ carry)%10;  
                    carry = (digit+carry)/10;
                    if(i==SIZE-large.numSigDigits-1 && last==0){
                        break;
                    }
                    sum.digits[i] = last;
                    sum.numSigDigits++;
                }
            }
        }
        return sum;
    }

    public BigInt mul(BigInt other){
        BigInt result = new BigInt();
        if (other == null){
            throw new IllegalArgumentException();
        }else{
            if (this.zeroCheck()||other.zeroCheck()){
                return new BigInt(0);
            }
            BigInt small = new BigInt();
            BigInt large = new BigInt();
            BigInt[] x= new BigInt[SIZE];
            if(this.numSigDigits<=other.numSigDigits){
                small=this;
                large=other;
            }else{
                small=other;
                large=this;
            }
            int carry =0;
            for(int i=SIZE-1;i>=SIZE-small.numSigDigits;i--){
                BigInt mul = new BigInt();
                mul.numSigDigits=0;
                for(int j=SIZE-1;j>=SIZE-large.numSigDigits*2;j--){
                    if(mul.numSigDigits>SIZE || (j<0 && carry!=0)){
                        throw new ArithmeticException();
                    }
                    if(j<0 && carry==0){
                        break;
                    }
                    int digit = small.digits[i]*large.digits[j];
                    int last = ((digit)%10+carry)%10;
                    carry = (digit+carry)/10;
                    if(carry==0 && last ==0){
                        break;
                    }
                    mul.digits[j]=last;
                    mul.numSigDigits++;
                }
                int zero = SIZE-1-i;
                int k=mul.numSigDigits;
                mul.numSigDigits+=zero;
                while(k>0){
                    mul.digits[SIZE-k-zero]=mul.digits[SIZE-k];
                    k--;
                }
                while(zero>0){
                    mul.digits[SIZE-zero]=0;
                    zero--;
                }
                x[i]=mul;
            }
            for(int i=x.length-1;i>=x.length-small.numSigDigits;i--){
                if(result.numSigDigits>SIZE){
                    throw new ArithmeticException();
                }
                result=result.add(x[i]);
            }
        }
        return result;
    }

    private boolean zeroCheck(){
        if (this.digits[SIZE-1]==0 && this.numSigDigits==1){
            return true;
        }
        return false;
    }

    public static void main(String [] args) {  
        BigInt test1 = new BigInt(2);
        BigInt test2 = new BigInt(5);
        BigInt cici = test1.mul(test2);
        System.out.println(cici);
        System.out.println();
        System.out.println("Unit tests for the BigInt class.");
        System.out.println();

        /* 
         * You should uncomment and run each test--one at a time--
         * after you build the corresponding methods of the class.
         */
        
        System.out.println("Test 1: result should be 7");
        int[] a1 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7 };
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getNumSigDigits());
        System.out.println();
        
        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();
        
        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();
        
        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 16: should throw an ArithmeticException");
        int[] a20 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };  // 20 nines!
        try {
            b1 = new BigInt(a20);
            System.out.println(b1.add(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
        

        System.out.println("Test 17: result should be 5670");
        b1 = new BigInt(135);
        b2 = new BigInt(42);
        BigInt product = b1.mul(b2);
        System.out.println(product);
        System.out.println();

        System.out.println("Test 18: result should be\n99999999999999999999");
        b1 = new BigInt(a20);   // 20 nines -- see above
        b2 = new BigInt(1);
        System.out.println(b1.mul(b2));
        System.out.println();

        System.out.println("Test 19: should throw an ArithmeticException");
        try {
            b1 = new BigInt(a20);
            b2 = new BigInt(2);
            System.out.println(b1.mul(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

    }
}
