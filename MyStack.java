/********************************************
* File: MyStack.java                        *
* Project 2                                 *
* @author Stephanie M. Ramos Camacho        *
* Date: 5/16/2021                           *
* Class Name: CCOM 4029 Spr 2021            *
* E-mail: stephanie.ramoscamacho@upr.edu    *
********************************************/


import java.util.Random;

/*
 * MyStack creates objects that contains an Card array that will 
 */
public class MyStack
{

  private Card[] card_stack;

  private int top;

  private int size;
  
  
  public MyStack(int s)
  {
	  this.card_stack = new Card[s];
	  this.size = s;
	  this.top = -1;
  }


  public Boolean isFull()
  {

    return (top == (size - 1));

  }

  public Boolean isEmpty()
  {

    return (top == -1);

  }

  public void push(Card card)
  {
    if(!isFull())
    {
    	top ++;
    	card_stack[top] = card;

    }
    
  }

  public Card pop()
  {
	  int curr_top = top;
	  if(!isEmpty())
	  {
    	top--;
    	return card_stack[curr_top];
	  }
	  return null;
  }
  
  public void shuffleArray() 
  {
		Random rand = new Random();
		
		for (int i = 0; i < card_stack.length; i++) {
			int randomIndex = rand.nextInt(card_stack.length);
			Card temp = card_stack[randomIndex];
			card_stack[randomIndex] = card_stack[i];
			card_stack[i] = temp;
		}
  }
  
  public Card peek()
  {
	if (!isEmpty()) 
	{
		return card_stack[top];
	}
	return null;
  }

  public int getsize()
  {

    return top;

  }
}
