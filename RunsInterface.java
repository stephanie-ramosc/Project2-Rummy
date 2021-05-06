/********************************************
* File: RunsInterface.java                  *
* Project 2                                 *
* @author Stephanie M. Ramos Camacho        *
* Date: 5/16/2021                           *
* Class Name: CCOM 4029 Spr 2021            *
* E-mail: stephanie.ramoscamacho@upr.edu    *
********************************************/


public interface RunsInterface extends HandInterface
{
	/*
	*    Must override the behavior of the HandInterface so that
	*   a runs only accepts a card if it is of the same rank.
	*/
    public void addCard( Card card ) ;
	/**
     *   returns the SuitIndex of the runs
	 @return int returns int corresponding to rank as defined in CardInterface
     */
	public int getSuitIndex();
	
   /**
     *   returns the Suit of the runs
    *  @return char returns char of rank as defined in CardInterface
     */
	public char getSuit();
  /**
     *  Determines whether runs is contains all four cards.
	 @return if true then no more Card may be added to the runs
     */
	public boolean isFull();
	/**
	* Ranks the cards in a runs according to their suit
	*/
}