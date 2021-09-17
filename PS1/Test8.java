/*
 * Test program for PS 1, problem 8.
 *
 * Put this program in the same folder as your Methods8.java.
 *
 * If it doesn't compile, that means that one or more of your methods
 * does not have the correct header -- i.e., either the return type
 * or the parameters are incorrect.
 *
 * The correct results to these method calls are given in the assignment.
 */

public class Test8 {
    public static void main(String[] args) {
        System.out.println("** part 1 **");
        Methods8.printDiag("method");
        System.out.println();
        
        System.out.println("** part 2, example 1 **");
        String last = Methods8.lastN("programming", 5);
        System.out.println(last);
 
        System.out.println("** part 2, example 2 **");
        last = Methods8.lastN("programming", 1);
        System.out.println(last);
        
        System.out.println("** part 2, example 3 **");
        last = Methods8.lastN("programming", 15);
        System.out.println(last);
        System.out.println();
        
        System.out.println("** part 3, example 1 **");
        String s = Methods8.remSub("ding-a-ling", "ing");
        System.out.println(s);
        
        System.out.println("** part 3, example 2 **");
        s = Methods8.remSub("variable", "var");
        System.out.println(s);
        
        System.out.println("** part 3, example 3 **");
        s = Methods8.remSub("Boston", "ten");
        System.out.println(s);
        System.out.println();
        
        System.out.println("** part 4, example 1 **");
        s = Methods8.interleave("aaaa", "bbbb");
        System.out.println(s);
        
        System.out.println("** part 4, example 2 **");
        s = Methods8.interleave("hello", "world");
        System.out.println(s);
        
        System.out.println("** part 4, example 3 **");
        s = Methods8.interleave("leaving", "NOW");
        System.out.println(s);
        
        System.out.println("** part 4, example 4 **");
        s = Methods8.interleave("", "hello");
        System.out.println(s);
    }
}