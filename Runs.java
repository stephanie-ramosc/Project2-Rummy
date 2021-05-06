/********************************************
* File: Runs.java                           *
* Project 2                                 *
* @author Stephanie M. Ramos Camacho        *
* Date: 5/16/2021                           *
* Class Name: CCOM 4029 Spr 2021            *
* E-mail: stephanie.ramoscamacho@upr.edu    *
********************************************/

import java.util.ArrayList;

public class Runs implements RunsInterface
{
	private ArrayList<Card> runs;
	private char suit;
	private int top;
	public Runs(char suit) 
	{	
		this.runs = new ArrayList<Card>(4);
		this.suit = suit;
		top = -1;
	}
	@Override
	public Card getCard(int index) 
	{
		return runs.get(index);
	}
	@Override
	public Card removeCard(Card card) 
	{	
		top--;
		runs.remove(card);
		return card;
	}
	@Override
	public Card removeCard(int index) 
	{
		top--;
		return runs.remove(index);
	}
	@Override
	public int getNumberOfCards() {
		return runs.size();
	}
	@Override
	public void sort() 
	{
	      ArrayList<Card> n_runs = new ArrayList<Card>();
	      while (runs.size() > 0) 
	      {
	         int MinIndex = 0; 
	         Card card = runs.get(0); 
	         for (int i = 1; i < runs.size(); i++) 
	         {
	            Card n_card = runs.get(i);
	            if ( n_card.getRank() < card.getRank() || (n_card.getRank() == card.getRank() && n_card.getSuit() < card.getSuit()) ) 
	            {
	                MinIndex = i;
	                card= n_card;
	            }
	         }
	         runs.remove(MinIndex);
	         n_runs.add(card);
	      }
	      runs = n_runs;
	   }
	@Override
	public boolean isEmpty() 
	{
		return top == -1;
	}
	@Override
	public boolean containsCard(Card card) 
	{
		return runs.contains(card);
	}
	@Override
	public int findCard(Card card) 
	{
		return runs.indexOf(card);
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
			rank = runs.get(ctr).getRank();
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
		if(!containsCard(card) && card.getSuit() == getSuit()) 
		{
			runs.add(card);
		}
		
	}
	@Override
	public int getSuitIndex() 
	{
		return Card.getRankIndex(getSuit());
	}
	@Override
	public char getSuit() 
	{
		return suit;
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
		if (runs.indexOf(card) != -1) 
		{
			runs.remove(card);
			return card;
		}
		return null;
	}
	
	/*
	*    Must override the behavior of the HandInterface so that
	*   a runs only accepts a card if it is of the same rank.
	*/
}
	