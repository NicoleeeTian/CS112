/**
 * Deck.java
 *
 * Computer Science 112, Boston University
 * 
 * A blueprint class for objects that represent a deck of cards.
 *
 * YOU SHOULD NOT MODIFY THIS FILE.
 */

import java.util.*;

public class Deck {
    /* fields for the deck */
    private Card[] cards;
    private int cardsLeft;  // number of cards not yet dealt
    private Random rand;    // random-number generator used when shuffling     
    
    /*
     * Deck constructor - takes a numeric seed for the random-number
     * generator. If the seed is -1, it is not used, which means
     * you will get a different ordering each time the program is run.
     */
    public Deck(int seed) {
        this.cards = new Card[52];
        int count = 0;
        
        for (int suit = Card.FIRST_SUIT; suit <= Card.LAST_SUIT; suit++) {
            for (int type = Card.FIRST_RANK; type <= Card.LAST_RANK; type++) {
                this.cards[count] = new Card(type, suit);
                count++;
            }
        }

        this.cardsLeft = 52;
  
        if (seed == -1) {
            this.rand = new Random();
        } else {
            this.rand = new Random(seed);
        }
    }

    /*
     * Deck constructor that takes no parameters. This is useful when
     * you don't want to specify a seed for the random-number generator.
     * It calls the other constructor, passing it the special seed of -1.
     */
    public Deck() {
        this(-1);
    }
    
    /*
     * reset - restores the deck to a full set of cards by resetting
     * the number of cards left to 52. This method should typically
     * be followed by a call to shuffle(), or else the cards will
     * be re-dealt in the same order as they were the first time
     * this deck was used.
     */
    public void reset() {
        this.cardsLeft = 52;
    }

    /*
     * shuffle - rearranges the cards in the deck
     */
    public void shuffle() {
        for (int i = 0; i < 52; i++) {
            int swapWith = Math.abs(this.rand.nextInt()) % 52;
            Card temp = this.cards[i];
            this.cards[i] = this.cards[swapWith];
            this.cards[swapWith] = temp;
        }
    }

    /*
     * dealCard - returns a single card from the deck, or null
     * if there are no cards left. Note that the cards aren't
     * actually removed from the cards array. Rather, we use
     * the cardsLeft field to determine which element of the
     * array should be returned.
     */
    public Card dealCard() {
        if (this.cardsLeft > 0) {
            return this.cards[52 - (this.cardsLeft--)];
        } else {
            return null;
        }
    }

    /*
     * isEmpty - returns true if the deck is empty (i.e., we have 
     * already dealt all of the cards in the cards array) and false otherwise.
     */
    public boolean isEmpty() {
        return (this.cardsLeft == 0);
    }

    /*
     * getCardsLeft - returns the number of cards that have not
     * yet been dealt
     */
    public int getCardsLeft() {
        return this.cardsLeft;
    }
}
