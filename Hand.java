/********************************************
* File: Hand.java                           *
* Project 2                                 *
* @author Stephanie M. Ramos Camacho        *
* Date: 5/16/2021                           *
* Class Name: CCOM 4029 Spr 2021            *
* E-mail: stephanie.ramoscamacho@upr.edu    *
********************************************/


// Hand.java - John K. Estell - 8 May 2003
// last modified: 23 Febraury 2004
// Implementation of a abstract hand of playing cards.
// Uses the Card class.  Requires subclass for specifying
// the specifics of what constitutes the evaluation of a hand
// for the game being implemented.

import java.util.ArrayList;


/**
 * Represents the basic functionality of a hand of cards.
 * Extensions of this class will provide the
 * definition of what constitutes a hand for that game and how hands are compared
 * to one another by overriding the <code>compareTo</code> method.
 * @author John K. Estell
 * @version 1.0
 */
public class Hand implements HandInterface {
  
  private ArrayList<Card> hand;
  public Hand() 
  {
    hand = new ArrayList<Card>();
  }

  /**
   * Adds a card to this hand.
   * @param card card to be added to the current hand.
   */
   public void addCard( Card card ) 
   {
     if(!isFull()) 
     {
       hand.add(card);
       sort();
     }
          
   }


  /**
   * Obtains the card stored at the specified location in the hand.  Does not
   * remove the card from the hand.
   * @param index position of card to be accessed.
   * @return the card of interest, or the null reference if the index is out of
   * bounds.
   */
   public Card getCard( int index ) 
   {
     return hand.get(index);
   }


  /**
   * Removes the specified card from the current hand.
   * @param card the card to be removed.
   * @return the card removed from the hand, or null if the card
   * was not present in the hand.
   */
   public Card removeCard( Card card ) 
   {
    if(!isEmpty()) 
    {
      if (hand.indexOf(card) != -1) 
      {
        hand.remove(card);
        sort();
        return card;
      }
    }
    sort();
    return null;
   }

  /**
   * Removes the card at the specified index from the hand.
   * @param index poisition of the card to be removed.
   * @return the card removed from the hand, or the null reference if
   * the index is out of bounds.
   */
   public Card removeCard( int index ) 
   {
     sort();
     return hand.remove(index);
   }
   
  /**
   * Removes the specified card from the current hand.
   * @param object the card to be removed.
   * @return the card removed from the hand, or null if the card.
   * was not present in the hand.
   */
   public Card removeCard( Object o_card ) 
   {
    Card card = (Card) o_card;
    if (hand.indexOf(card) != -1) 
    {
      hand.remove(card);
      return card;
    }
    return null;
   }
 
  /**
   * The number of cards held in the hand.
   * @return number of cards currently held in the hand.
   */
   public int getNumberOfCards() 
   {
     return hand.size();
   }


  /**
   * Sorts the card in the hand.
   * Sort is performed according to the order specified in the {@link Card} class.
   */
   public void sort() {
        ArrayList<Card> n_hand = new ArrayList<Card>();
        while (hand.size() > 0) 
        {
           int MinIndex = 0; 
           Card card = hand.get(0); 
           for (int i = 1; i < hand.size(); i++) 
           {
              Card n_card = hand.get(i);
              if ( n_card.getRank() < card.getRank() || (n_card.getRank() == card.getRank() && n_card.getSuit() < card.getSuit()) ) 
              {
                  MinIndex = i;
                  card= n_card;
              }
           }
           hand.remove(MinIndex);
           n_hand.add(card);
        }
        hand = n_hand;
     }


  /**
   * Checks to see if the hand is empty.
   * @return <code>true</code> is the hand is empty.
   */
   public boolean isEmpty() 
   {
     return hand.isEmpty();
   }


  /**
   * Determines whether or not the hand contains the specified card.
   * @param card the card being searched for in the hand.
   * @return <code>true</code> if the card is present in the hand.
   */
   public boolean containsCard( Card card )
   {
     if(hand.indexOf(card) != -1) 
     {
       return true;
     }
     return false;
   }

  /**
   * Searches for the first instance of the specified card in the hand.
   * @param card card being searched for.
   * @return position index of card if found, or <code>-1</code> if not found.
   */
   public int findCard( Card card ) 
   {
     return hand.indexOf(card);
   }

  /**
   *  Compares two hands.
   *  @param otherHandObject the hand being compared.
   *  @return < 0 if this hand is less than the other hand, 0 if the two hands are
   *  the same, or > 0 if this hand is greater then the other hand.
   */
   public int compareTo( Object otherHandObject ) 
   {
     Hand otherCard = (Hand) otherHandObject;
     int rankDiff = evaluateHand() - otherCard.evaluateHand();
     return rankDiff;
   }


  /**
   *  Evaluates the hand.  Must be defined in the subclass that implements the hand
   *  for the game being written by the client programmer.
   *  @return an integer corresponding to the rating of the hand.
   */
   public int evaluateHand() 
   {
     int score = 0;
     int ctr = getNumberOfCards()-1;
     char rank;
     while(ctr!=0) 
     {
       rank = hand.get(ctr).getRank();
       if(rank == 'a') score += 1;
       else if(rank == 't'|| rank == 'j' || rank == 'q' || rank == 'k') score += 10;
       else score += Character.getNumericValue(rank);
       ctr--;
     }
     return score;
     
   }
   
   /** 
    * Gets the contents of the Arraylist, converts it into an array.
    * @return the array.
  **/
   public Card[] getArray() 
   {
     int hand_size = getNumberOfCards();
     Card[] hand_arr = new Card[hand_size];
     for (int i = 0; i<hand_size;i++) 
     {
       hand_arr[i] = hand.get(i);
     }
     return hand_arr;
   }
   
   /**
    * Returns if the Arraylist is full.  
    * @return true or false.
    **/
   public boolean isFull() 
   {
     return getNumberOfCards() == 10;
   }


  /**
    * Returns a description of the hand.
    * @return a list of cards held in the hand.
    */
    public String toString() 
    {return hand.toString();}

}