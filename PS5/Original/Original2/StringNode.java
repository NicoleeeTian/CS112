package Original.Original2;

public class StringNode {
    private char ch;
    private StringNode next;
    
    public StringNode(char c, StringNode n) {
        this.ch = c;
        this.next = n;
    }
    
    public static StringNode convert(String s) {
        if (s.length() == 0) {
            return null;
        }
        
        StringNode firstNode = new StringNode(s.charAt(0), null);
        StringNode prevNode = firstNode;
        StringNode nextNode;
        
        for (int i = 1; i < s.length(); i++) {
            nextNode = new StringNode(s.charAt(i), null);
            prevNode.next = nextNode;
            prevNode = nextNode;
        }
        
        return firstNode;
    }
    
    public static StringNode copy(StringNode str) {
        if (str == null) {
            return null;
        }

        StringNode copyRest = copy(str.next);
        return new StringNode(str.ch, copyRest);    
    }

    public static int numOccur(StringNode str, char ch) {
        if (str == null) {
            return 0;
        }

        int numInRest = numOccur(str.next, ch);
        if (str.ch == ch) {
            return 1 + numInRest;
        } else {
            return numInRest;
        }
    }
    
    public String toString() {
        String str = "";
        StringNode trav = this;   // start trav on the current node
        
        while (trav != null) {
            str = str + trav.ch;
            trav = trav.next;
        }
        
        return str;
    }
    
    public static void toUpperCase(StringNode str) {        
        StringNode trav = str; 
        while (trav != null) {
            trav.ch = Character.toUpperCase(trav.ch); 
            trav = trav.next;
        }
    } 

    public static void main(String[] args)  {
        // convert and toUpperCase
        StringNode s1 = StringNode.convert("hello");
        StringNode.toUpperCase(s1);
            
        // numOccur
        System.out.println(StringNode.numOccur(s1, 'L'));
   
        // copy the reference in s1 into s2
        StringNode s2 = s1;

        // make s3 refer to a "deep" copy of s1
        StringNode s3 = StringNode.copy(s1);
        
        // remove the second node in the list referred to by s1
        s1.next = s1.next.next;

        // which ones change? 
        // note: these print statements call the toString() method above
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}