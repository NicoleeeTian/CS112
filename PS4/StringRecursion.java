public class StringRecursion{

    public static void printLetters(String str){
        if(str == null|| str.length()==0){
            System.out.println("");
        }else if(str.length()==1){
            System.out.println(str);
        }
        else{
            if(str.substring(0,1).equals(" ")){
                System.out.print(" , , ");
            }else{
                System.out.print(str.substring(0,1)+", ");
            }
            printLetters(str.substring(1));
        }
    }

    public static String replace(String str, char oldChar, char newChar){
        if(str==null){
            return null;
        }else if(str.length()==0){
            return "";
        }else{
            if (str.charAt(0)==oldChar){
                return newChar+""+ replace(str.substring(1),oldChar, newChar);
            }else{
                return str.substring(0,1)+ replace(str.substring(1),oldChar, newChar);
            }
        }
    }

    public static int findLast(char ch, String str){
        if(str==null||str.length()==0){
            return -1;
        }else{
            if(str.charAt(str.length()-1)==ch){
                return str.length()-1;
            }else{
                return findLast(ch, str.substring(0,str.length()-1));
            }
        }
    }

    public static void main(String[] args){
        printLetters("Rabbit");
        printLetters("I like to recurse!");

        System.out.println(replace("base case", 'e', 'y'));
        System.out.println(replace("base case", 'r', 'y'));

        System.out.println(findLast('r', "recurse"));
        System.out.println(findLast('p', "recurse"));
        System.out.println(findLast('a', "aram"));
        System.out.println(findLast('s', "happus"));
    }
}