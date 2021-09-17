public class Test {
    public static void main(String[] args){
        String s1 = "Don't string";
        String s2 = "me along!"; 
        System.out.println("a "+ s1.substring(6) + " " + s2.substring(0, 2));
        System.out.println("b "+ s1.charAt(6) + s1.substring(9)+ " " + s2.substring(3,8));
        System.out.println("c "+ s1.charAt(0) +s1.substring(9).toUpperCase() + s2.substring(s2.length()-1));
        System.out.println("d "+ s1.toLowerCase().charAt(0) +"" + s1.charAt(9) + s2.substring(0,2));
        System.out.println("e "+ s1.charAt(8));
        System.out.println("f "+s1.indexOf('i'));
        System.out.print("g "+ s1.replace('t','u'));


    }     
}
