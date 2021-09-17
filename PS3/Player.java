import java.util.*;
public class Player {
    private String name;
    private Card[] hand;
    private int numCards;

    public Player(String name){
        this.name = name;
        this.hand = new Card[Blackjack.MAX_CARDS_PER_PLAYER];
        this.numCards = 0;
    }
    
    public String getName(){
        return this.name;
    }

    public int getNumCards(){
        return this.numCards;
    }

    public void addCard(Card c){
        this.numCards++;
        int count = 0;
        if (c == null){
            throw new IllegalArgumentException();
        }else{
            for(int i=0;i<this.hand.length;i++){
                if (this.hand[i]== null){
                    this.hand[i] = c;
                    break;
                }else{
                    count++;
                }
                if (count == this.hand.length){
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    public Card getCard(int index){
        if (index<0 || index>11){
            throw new IllegalArgumentException();
        }else{
            return this.hand[index];
        }  
    }

    public int getHandValue(){
        int sum = 0;
        int count = 0;
        for(int i=0;i<numCards;i++){
            int value = hand[i].getValue();
            if (value != 1){
                sum += value;
            }else{
                count++;
            }
        }
        if (sum+11<=21 && count>0){
            sum+=11;
            count--;
        }
        sum += count;
        return sum;
    }

    public void printHand(){
        String result ="";
        for (int i=0;i<numCards;i++){
            result += hand[i];
            result += "  ";
        }
        result += "(value = " + getHandValue() +")";
        System.out.println(result);
    }

    public boolean hasBlackjack(){
        return (getHandValue()==21 && this.numCards==2);
    }

    public boolean wantsHit(Scanner s, Player p){
        System.out.println("Want another hit, " + this.name+ " (y/n)?");
        String answer = s.nextLine();
        return answer.toUpperCase().equals("Y");
    }

    public void discardCards(){
        for ( int i =0; i<numCards;i++){
            this.hand[i] =null;
        }
        this.numCards = 0;
    }
}
