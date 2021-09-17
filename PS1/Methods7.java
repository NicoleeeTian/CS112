/*
 * Methods7.java
 * 
 * Code added by: name and email
 *
 * Practice with static methods, part I
 */

public class Methods7 {
    /*
     * 0) printVertical - takes a string s and prints the characters of 
     *    the string vertically -- with one character per line.
     */
    public static void printVertical(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println(c);
        }
    }
    public static void printEveryOther(String s) {
        for (int i=0; i<s.length();i+=2){
            System.out.print(s.substring(i,i+1));
        }
    }
    public static int longerLen(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1>=len2){
            return len1;
        }else{
            return len2;
        }
    }
    public static int secondIndex(String s, char c) {
        int x =0;
        for(int i=0;i<s.length();i++){
            if (s.charAt(i)==c){
                x++;
            }
            if (x==2){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        /* Sample test call */
        printVertical("method");
        printEveryOther("method"); 
        int len = longerLen("bye", "hello");
        System.out.println("the longer length is: " + len);
        int x= secondIndex("banana",'x');
        System.out.print("sceondIndex:"+x);


    }
}
