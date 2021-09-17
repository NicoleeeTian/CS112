/*
 * Card.java
 * 
 * A blueprint class to represent an individual playing card.
 * 
 * CS 112, Boston University
 * 
 * completed by: <your name>, <your email>
 */

public class Card {
    // constants for the ranks of non-numeric cards
    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    
    // other constants for the ranks
    public static final int FIRST_RANK = 1;
    public static final int LAST_RANK = 13;
    
    // Arrays of strings for the rank names and abbreviations.
    // The name of the rank r is given by RANK_NAMES[r].
    // The abbreviation of the rank r is given by RANK_ABBREVS[r].
    private static final String[] RANK_NAMES = {
      null, "Ace", "2", "3", "4", "5", "6", 
      "7", "8", "9", "10", "Jack", "Queen", "King"
    };
    private static final String[] RANK_ABBREVS = {
      null, "A", "2", "3", "4", "5", "6",
      "7", "8", "9", "10", "J", "Q", "K"
    };
    
    // constants for the suits
    public static final int FIRST_SUIT = 0;
    public static final int LAST_SUIT = 3;
    public static final int CLUBS = 0;
    public static final int DIAMONDS = 1;
    public static final int HEARTS = 2;
    public static final int SPADES = 3;
    
    // Arrays of strings for the suit names and abbreviations.
    // The name of the suit s is given by SUIT_NAMES[s].
    // The abbreviation of the suit s is given by SUIT_ABBREVS[s].
    private static final String[] SUIT_NAMES = {
      "Clubs", "Diamonds", "Hearts", "Spades"
    };
    private static final String[] SUIT_ABBREVS = {
      "C", "D", "H", "S"
    };
    
    //define fields
    private int rank;
    private int suitNum;

    /***** part 2: getSuitNum *****/
    private static int getSuitNum(String suit) {
        // The return statement below is included so the starter code 
        // will compile.
        // Replace it with your implementation of the method.
        for(int i=0;i<SUIT_NAMES.length;i++){
          if (SUIT_NAMES[i].equalsIgnoreCase(suit)){
            return i;
          }
        }
        return -1;
    }

    /***** Implement parts 3-7 below. *****/
    //Constructor
    public Card(int rank, int suitNum){
      if (rank<1 || rank>13){
        throw new IllegalArgumentException();
      }
      if (suitNum<0||suitNum>3){
        throw new IllegalArgumentException();
      }
      this.rank = rank;
      this.suitNum = suitNum;
    }

    public Card(int rank, String suit){
      if (rank<1 || rank>13){
        throw new IllegalArgumentException();
      }
      if (getSuitNum(suit)<0||getSuitNum(suit)>3){
        throw new IllegalArgumentException();
      }
      this.rank = rank;
      this.suitNum = getSuitNum(suit);
    }

    // returns the integr representing the Card object's rank.
    public int getRank(){
      int r = this.rank;
      return r;
    }

    // returns a String representstion of the Card object's rank
    public String getRankName(){
      if (this.getRank()!=0){
        String s = RANK_NAMES[this.getRank()];
        return s;
      }
      else{
        return null;
      }
    }
    // returns the the Card object's suit number
    public int getSuitNum(){
      int n = this.suitNum;
      return n;
    }

    // returns a String representation of the Card object's suit
    public String getSuitName(){
      String s = SUIT_NAMES[getSuitNum()];
      return s;
    }

    // returns a String representing the full name of the Card
    public String getName(){
      String name = "";
      name = this.getRankName() + " of " + this.getSuitName();
      return name;
    }

    // test if is Ace
    public boolean isAce(){
      if (this.getRankName().equals("Ace")){
        return true;
      }else{
        return false;
      }
    }

    public boolean isFaceCard(){
      if (this.getRankName().equals("Jack")||this.getRankName().equals("Queen")||this.getRankName().equals("King")){
        return true;
      }else{
        return false;
      }
    }

    public int getValue(){
      if (this.isFaceCard()){
        return 10;
      }else{
        return this.rank;
      }
    }
    
    public String toString(){
      String result = "";
      if(this.getRankName()!=null){
        for(int i=1;i<RANK_NAMES.length;i++){
          if (RANK_NAMES[i].equals(this.getRankName())){
            result+= RANK_ABBREVS[i];
            break;
          }
        }
        for(int i=0;i<SUIT_NAMES.length;i++){
          if (SUIT_NAMES[i].equals(this.getSuitName())){
            result+= SUIT_ABBREVS[i];
            break;
          }
        }
      } 
      return result;     
    }
    public boolean sameSuitAs(Card other){
      if (other != null){
        if (this.getSuitName().equals(other.getSuitName())){
          return true;
        }else{
          return false;
        }
      }
      return false;
    }

    public boolean equals(Card other){
      if (other != null){
        if (this.getRankName()!=null&& other.getRankName()!=null){
          if (this.getRankName().equals(other.getRankName())&&this.sameSuitAs(other)){
            return true;
          }
        }
      }
      return false;
    }
  
    public static void main(String[] args){
      System.out.println(getSuitNum("Hearts"));
      System.out.println(getSuitNum("Spades"));
      System.out.println(getSuitNum("foo"));
      Card c1 = new Card(1, 2);         // Ace of Hearts
      Card c2 = new Card(5, 2);         // 5 of Diamonds
      Card c3 = new Card(12, "hearts");
      System.out.println(c1.getName());
      System.out.println(c2.getName());
      System.out.println(c3);
      System.out.println(c2.sameSuitAs(null));
    }
}
