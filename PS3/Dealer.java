import java.util.*;
public class Dealer extends Player{
    private boolean checkFirstCard;

    public Dealer(){
        super("dealer");
        checkFirstCard = false;
    }

    public void revealFirstCard(){
        checkFirstCard = true;
    }

    public void printHand(){
        String result ="";
        if (checkFirstCard == false){
            result += "XX  ";
        }else{
            result += getCard(0)+"  ";
        }
        for (int i=1;i<getNumCards();i++){
            result += getCard(i)+"  ";
        }
        if (checkFirstCard){
            result += "(value = " + getHandValue() +")";
        }
        System.out.println(result);
    }

    public boolean wantsHit(Scanner s, Player p){
        if(p.getHandValue()<17 && getHandValue() <=p.getHandValue() ){
                return true;
        }else{
            if (p.getHandValue()==21){
                return false;
            }else{
                if (getHandValue()<p.getHandValue()){
                    return true;
                }
            }
        }
        return false;
    }
    public void discardCards(){
        super.discardCards();
        checkFirstCard = false;
    }
    public static void main(String[] args){
        Player dealer = new Dealer();
        dealer.printHand();
    }
}
