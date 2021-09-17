/*
 * Palindrome.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name:
 *     username:
 */
   
public class Palindrome {
    // Add your definition of isPal here.
    /*
    isPal() method tests whether the input String str is a Palindrome or not
    */
    public static boolean isPal(String str){
        if (str== null){
            throw new NullPointerException();
        }else if(str.length()<2){
            return true;
        }else{
            String s=str.toLowerCase();
            int len = str.length();
            LLStack<Character> first= new LLStack<Character>();
            LLStack<Character> second= new LLStack<Character>();
            for(int i=0;i<len;i++){
                if(s.charAt(i)>=97 &&s.charAt(i)<=122){
                    first.push(s.charAt(i));
                }
                if(s.charAt(len-1-i)>=97 &&s.charAt(len-1-i)<=122){
                    second.push(s.charAt(len-1-i));
                }
            }
            while(first.isEmpty()==false){
                if((first.pop()==second.pop())==false){
                    return false;
                }
            }
            return true;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        /*
         * Add five more unit tests that test a variety of different
         * cases. Follow the same format that we have used above.
         */
        System.out.println("(1) Testing on \"R,,,adar\"");
        try {
            boolean results = isPal("R,,,adar");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests

        System.out.println("(2) Testing on \"x\"");
        try {
            boolean results = isPal("x");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests

        System.out.println("(3) Testing on \"abcabc\"");
        try {
            boolean results = isPal("abcabc");
            boolean expected = false;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests

        System.out.println("(4) Testing on \"_CE_EC_\"");
        try {
            boolean results = isPal("_CE_EC_");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests

        System.out.println("(5) Testing on \"xxxxx\"");
        try {
            boolean results = isPal("xxxxx");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
    }
}