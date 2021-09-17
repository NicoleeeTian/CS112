/*
 * First test client for the Card problem.
 * 
 * Do not use until after you have completed parts 1-5.
 */

public class Client1 {
    public static void processCard(Card c) {
        int rank = c.getRank();
        System.out.println("    getRank: " + rank);
        String rankName = c.getRankName();
        System.out.println("getRankName: " + rankName);
        int suitNum = c.getSuitNum();
        System.out.println(" getSuitNum: " + suitNum);
        String suitName = c.getSuitName();
        System.out.println("getSuitName: " + suitName);
        String name = c.getName();
        System.out.println("    getName: " + name);
        boolean ace = c.isAce();
        System.out.println("      isAce: " + ace);
        boolean faceCard = c.isFaceCard();
        System.out.println(" isFaceCard: " + faceCard);
        int value = c.getValue();
        System.out.println("   getValue: " + value);
        System.out.println();
    }
    
    public static void main(String[] args) {  
        System.out.println("card c1 (Jack of Spades):");
        Card c1 = new Card(11, 3);         // first constructor
        processCard(c1);
        
        System.out.println("card c2 (5 of Diamonds):");
        Card c2 = new Card(5, 1);          // first constructor
        processCard(c2);
        
        System.out.println("card c3 (Queen of Hearts):");
        Card c3 = new Card(12, "hearts");  // second constructor
        processCard(c3);
        
        System.out.println("card c4 (Ace of Clubs):");
        Card c4 = new Card(1, "CLUBS");    // second constructor
        processCard(c4);
        
        // Try to create invalid Cards. You may want to add additional tests
        // for invalid values.
        System.out.println("Trying to create a Card with a suit number of 5...");
        try {
            Card c5 = new Card(7, 5);
            System.out.println("failed to throw an IllegalArgument Exception...");           
        } catch(IllegalArgumentException e) {
            System.out.println("correctly threw an IllegalArgumentException...");
        }
        System.out.println();
        
        System.out.println("Trying to create a Card with a suit name of \"Foo\"...");
        try {
            Card c6 = new Card(7, "Foo");
            System.out.println("failed to throw an IllegalArgument Exception...");           
        } catch(IllegalArgumentException e) {
            System.out.println("correctly threw an IllegalArgumentException...");
        }
        System.out.println();
    }
}
