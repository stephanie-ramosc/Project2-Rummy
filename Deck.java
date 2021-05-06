/********************************************
* File: Deck.java                           *
* Project 2                                 *
* @author Stephanie M. Ramos Camacho        *
* Date: 5/16/2021                           *
* Class Name: CCOM 4029 Spr 2021            *
* E-mail: stephanie.ramoscamacho@upr.edu    *
********************************************/

//import java.util.*;

public class Deck implements DeckInterface {
  
  private MyStack deck;

 /**
   * Creates an empty deck of cards.
   */
   public Deck() 
   {
     deck = new MyStack(52);
   }
  /**
   *  returns next Card in a deck that is facing down without removing it
   */
   @Override
   public Card peek() 
   {
     return deck.peek();
   }
 /**
   * this method is used to add Cards to a Deck.  The Deck is completely empty when it is initialized.
   */
   @Override
   public void addCard(Card card) 
   {
     deck.push(card);
   }

 /**
   * returns number of cards on the deck
   * @return int
   */
   @Override
   public int getSizeOfDeck() 
   {
     return deck.getsize();
   }

   /**
   * removes first card on a deck so equivalent to flipping the card off of a deck that is faced down
   * @return <code>null</code> if there are no cards left on the Stack. Otherwise returns Card
   */
   
   @Override
   public Card dealCard()
   {
     return deck.pop();
   }
   
   

 /**
   * removes Card last card placed on a deck so equivalent to removing card from deck that is faced up
   * @return <code>null</code> if there are no cards left on the deck. Otherwise removes Card
   */
   
   @Override
   public Card removeCard()
   {
     return deck.pop();
   }
   
   

  /**
   * Shuffles the cards present in the deck.
   */
   
   @Override
   public void shuffle() 
   {
    deck.shuffleArray();
   }

  /**
   * Looks for an empty deck.
   * @return <code>true</code> if there are no cards left to be dealt from the deck.
   */
   
   @Override
   public boolean isEmpty() 
   {
  return deck.isEmpty();
   }

  /**
   * Restores the deck to being empty and ready to add Cards to
   */
   
   @Override
   public void restoreDeck() 
   {
    Card done = deck.pop();
    while(done != null) {done = deck.pop();}
   }


}