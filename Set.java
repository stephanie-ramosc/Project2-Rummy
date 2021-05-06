/********************************************
* File: Set.java                           *
* Project 2                                 *
* @author Stephanie M. Ramos Camacho        *
* Date: 5/16/2021                           *
* Class Name: CCOM 4029 Spr 2021            *
* E-mail: stephanie.ramoscamacho@upr.edu    *
********************************************/


import java.util.ArrayList;

public class Set implements SetInterface
{
  private ArrayList<Card> set;
  private char rank;
  private int top;
  public Set(char rank) 
  { 
    this.set = new ArrayList<Card>(4);
    this.rank = rank;
    top = -1;
  }
  @Override
  public Card getCard(int index) 
  {
    return set.get(index);
  }
  @Override
  public Card removeCard(Card card) 
  { 
    top--;
    set.remove(card);
    return card;
  }
  @Override
  public Card removeCard(int index) 
  {
    top--;
    return set.remove(index);
  }
  @Override
  public int getNumberOfCards() {
    return set.size();
  }
  @Override
  public void sort() 
  {
        ArrayList<Card> n_set = new ArrayList<Card>();
        while (set.size() > 0) 
        {
           int MinIndex = 0; 
           Card card = set.get(0); 
           for (int i = 1; i < set.size(); i++) 
           {
              Card n_card = set.get(i);
              if ( n_card.getRank() < card.getRank() || (n_card.getRank() == card.getRank() && n_card.getSuit() < card.getSuit()) ) 
              {
                  MinIndex = i;
                  card= n_card;
              }
           }
           set.remove(MinIndex);
           n_set.add(card);
        }
        set = n_set;
     }
  @Override
  public boolean isEmpty() 
  {
    return top == -1;
  }
  @Override
  public boolean containsCard(Card card) 
  {
    return set.contains(card);
  }
  @Override
  public int findCard(Card card) 
  {
    return set.indexOf(card);
  }
  @Override
  public int compareTo(Object otherHandObject) 
  {
    Hand otherCard = (Hand) otherHandObject;
    int rankDiff = evaluateHand() - otherCard.evaluateHand();
    return rankDiff;
  }
  @Override
  public int evaluateHand() 
  {
    int score = 0;
    int ctr = getNumberOfCards()-1;
    char rank;
    while(ctr!=0) 
    {
      rank = set.get(ctr).getRank();
      if(rank == 'a') score += 1;
      else if(rank == 't'|| rank == 'j' || rank == 'q' || rank == 'k') score += 10;
      else score += Character.getNumericValue(rank);
      ctr--;
    }
    return score;
  }
  @Override
  public void addCard(Card card) 
  {
    if(!containsCard(card) && card.getRank() == getRank()) 
    {
      set.add(card);
    }
    
  }
  @Override
  public int getRankIndex() 
  {
    return Card.getRankIndex(getRank());
  }
  @Override
  public char getRank() 
  {
    return rank;
  }
  @Override
  public boolean isFull() 
  {
    return top == 4;
  }
  @Override
  public Card removeCard(Object o_card)
  {
    Card card = (Card) o_card;
    if (set.indexOf(card) != -1) 
    {
      set.remove(card);
      return card;
    }
    return null;
  }
  
  /*
  *    Must override the behavior of the HandInterface so that
  *   a Set only accepts a card if it is of the same rank.
  */
}