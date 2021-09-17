public class Methods8 {
    public static void printDiag(String s) {
        for(int i=0;i<s.length();i++){
            for(int j=0;j<i+1;j++){
                if(i==j){
                    System.out.print(s.substring(i,i+1));
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    public static String lastN(String s, int n) {
        if (s.length()<n){
            return s;
        }else{
            String rest = s.substring(s.length()-n);
            return rest;
        }
    }
    public static String remSub(String s1, String s2){
        int index = s1.indexOf(s2);
        String s3;
        if (index==-1){
            s3 = s1;
            return s1;
        }else{
            s3 = s1.substring(0,index) + s1.substring(index+s2.length());
            return s3;
        }
    }
    public static String interleave(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        int minl;
        String result = "";
        if (l1<=l2){
            minl=l1;
        }else{
            minl=l2;
        }
        for(int i=0;i<minl;i++){
            result += s1.substring(i,i+1) +s2.substring(i,i+1);
        }
        result += s1.substring(minl) + s2.substring(minl);
        return result;
    }

    public static void main(String[] args){
        printDiag("method");
        String test1=lastN("programming", 5);
        System.out.println("test1"+test1);
        String test2=lastN("programming", 11);
        System.out.println("test2"+test2);
        String test3 = remSub("ding-a-ling", "ing");
        System.out.println("test3"+test3);
        String test4 = remSub("Boston", "ten");
        System.out.println("test4" + test4);
        System.out.println(interleave("aaaa", "bbbb"));

    }
}
