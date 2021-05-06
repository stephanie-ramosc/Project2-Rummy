/********************************************
* File: Table.java                          *
* Project 2                                 *
* @author Stephanie M. Ramos Camacho        *
* Date: 5/16/2021                           *
* Class Name: CCOM 4029 Spr 2021            *
* E-mail: stephanie.ramoscamacho@upr.edu    *
********************************************/


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
*	This GUI assumes that you are using a 52 card deck and that you have 13 sets in the deck.
*	The GUI is simulating a playing table
	@author Patti Ordonez
*/
public class Table extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5524732512208011162L;
	final static int numDealtCards = 9;
	JPanel player1;
	JPanel player2;
	JPanel deckPiles;
	JLabel deck;
	JLabel stack;
	@SuppressWarnings("rawtypes")
	JList p1HandPile;
	@SuppressWarnings("rawtypes")
	JList p2HandPile;
	Deck cardDeck;
	Deck stackDeck;

	SetPanel [] setPanels = new SetPanel[13];
	JLabel topOfStack;
	JLabel deckPile;
	JButton p1Stack;
	JButton p2Stack;

	JButton p1Deck;
	JButton p2Deck;

	JButton p1Lay;
	JButton p2Lay;

	JButton p1LayOnStack;
	JButton p2LayOnStack;

	Hand p1Hand;
	Hand p2Hand;
	
	PlayerWins winner;
	Turn p1turn, p2turn;
	RunsPanel [] runsPanels = new RunsPanel[13];

	private void deal(Card [] cards) {
		for(int i = 0; i < cards.length; i ++)
			cards[i] = (Card)cardDeck.dealCard();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Table() {
		super("The Card Game of the Century");

		setLayout(new BorderLayout());
		setSize(1200,700);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);


		cardDeck = new Deck();

		for(int i = 0; i < Card.suit.length; i++){
			for(int j = 0; j < Card.rank.length; j++){
				Card card = new Card(Card.suit[i],Card.rank[j]);
				cardDeck.addCard(card);
			}
		}
		cardDeck.shuffle();
		stackDeck = new Deck();

		JPanel top = new JPanel();

		for (int i = 0; i < Card.rank.length;i++) {
			setPanels[i] = new SetPanel(Card.getRankIndex(Card.rank[i]));
			runsPanels[i] = new RunsPanel(Card.getRankIndex(Card.rank[i]));
		}

		top.add(setPanels[0]);
		top.add(setPanels[1]);
		top.add(setPanels[2]);
		top.add(setPanels[3]);
		
		
		top.add(runsPanels[0]);
		top.add(runsPanels[1]);
		top.add(runsPanels[2]);
		top.add(runsPanels[3]);

		player1 = new JPanel();

		player1.add(top);

		add(player1, BorderLayout.NORTH);
		JPanel bottom = new JPanel();


		bottom.add(setPanels[4]);
		bottom.add(setPanels[5]);
		bottom.add(setPanels[6]);
		bottom.add(setPanels[7]);
		bottom.add(setPanels[8]);
		
		
		bottom.add(runsPanels[4]);
		bottom.add(runsPanels[5]);
		bottom.add(runsPanels[6]);
		bottom.add(runsPanels[7]);
		bottom.add(runsPanels[8]);

		player2 = new JPanel();

		player2.add(bottom);
		add(player2, BorderLayout.SOUTH);


		JPanel middle = new JPanel(new GridLayout(1,3));

		p1Stack = new JButton("Stack");
		p1Stack.addActionListener(this);
		p1Deck = new JButton("Deck ");
		p1Deck.addActionListener(this);
		p1Lay = new JButton("Lay  ");
		p1Lay.addActionListener(this);
		p1LayOnStack = new JButton("LayOnStack");
		p1LayOnStack.addActionListener(this);


		Card [] cardsPlayer1 = new Card[numDealtCards];
		deal(cardsPlayer1);
		p1Hand = new Hand();
		System.out.print("Initial Player 1: ");
		for(int i = 0; i < cardsPlayer1.length; i++)
		{
			p1Hand.addCard(cardsPlayer1[i]);
			System.out.print(cardsPlayer1[i].toString()+", ");
		}
		print("");
			
		p1HandPile = new JList(p1Hand.getArray());


		middle.add(new HandPanel("Player 1", p1HandPile, p1Stack, p1Deck, p1Lay, p1LayOnStack));

		deckPiles = new JPanel();
		deckPiles.setLayout(new BoxLayout(deckPiles, BoxLayout.Y_AXIS));
		deckPiles.add(Box.createGlue());
		JPanel left = new JPanel();
		left.setAlignmentY(Component.CENTER_ALIGNMENT);


		stack = new JLabel("Stack");
		stack.setAlignmentY(Component.CENTER_ALIGNMENT);

		left.add(stack);
		topOfStack = new JLabel();
		topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));
		topOfStack.setAlignmentY(Component.CENTER_ALIGNMENT);
		left.add(topOfStack);
		deckPiles.add(left);
		deckPiles.add(Box.createGlue());

		JPanel right = new JPanel();
		right.setAlignmentY(Component.CENTER_ALIGNMENT);

		deck = new JLabel("Deck");

		deck.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deck);
		deckPile = new JLabel();
		deckPile.setIcon(new ImageIcon(Card.directory + "b.gif"));
		deckPile.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deckPile);
		deckPiles.add(right);
		deckPiles.add(Box.createGlue());
		middle.add(deckPiles);


		p2Stack = new JButton("Stack");
		p2Stack.addActionListener(this);
		p2Deck = new JButton("Deck ");
		p2Deck.addActionListener(this);
		p2Lay = new JButton("Lay  ");
		p2Lay.addActionListener(this);
		p2LayOnStack = new JButton("LayOnStack");
		p2LayOnStack.addActionListener(this);
	
		Card [] cardsPlayer2 = new Card[numDealtCards];
		deal(cardsPlayer2);
		p2Hand = new Hand();
		System.out.print("Initial Player 2: ");
		for(int i = 0; i < cardsPlayer2.length; i++)
		{
			p2Hand.addCard(cardsPlayer2[i]);
			System.out.print(cardsPlayer2[i].toString()+", ");
		}
		print("");

		p2HandPile = new JList(p2Hand.getArray());

		middle.add(new HandPanel("Player 2", p2HandPile, p2Stack, p2Deck, p2Lay, p2LayOnStack));

		add(middle, BorderLayout.CENTER);

		JPanel leftBorder = new JPanel(new GridLayout(2,1));


		setPanels[9].setLayout(new BoxLayout(setPanels[9], BoxLayout.Y_AXIS));
		
		runsPanels[9].setLayout(new BoxLayout(runsPanels[9], BoxLayout.Y_AXIS));
		
		setPanels[10].setLayout(new BoxLayout(setPanels[10], BoxLayout.Y_AXIS));
		
		runsPanels[10].setLayout(new BoxLayout(runsPanels[10], BoxLayout.Y_AXIS));
		
		leftBorder.add(setPanels[9]);
		
		leftBorder.add(runsPanels[9]);
		
		leftBorder.add(setPanels[10]);
		
		leftBorder.add(runsPanels[10]);
		
		add(leftBorder, BorderLayout.WEST);

		JPanel rightBorder = new JPanel(new GridLayout(2,1));

		setPanels[11].setLayout(new BoxLayout(setPanels[11], BoxLayout.Y_AXIS));
		
		runsPanels[11].setLayout(new BoxLayout(runsPanels[11], BoxLayout.Y_AXIS));
		
		setPanels[12].setLayout(new BoxLayout(setPanels[12], BoxLayout.Y_AXIS));
		
		runsPanels[12].setLayout(new BoxLayout(runsPanels[12], BoxLayout.Y_AXIS));
		
		rightBorder.add(setPanels[11]);
		
		rightBorder.add(runsPanels[11]);
		
		rightBorder.add(setPanels[12]);
		
		rightBorder.add(runsPanels[12]);
		
		add(rightBorder, BorderLayout.EAST);
		
		p1turn = new Turn(true);
		p2turn = new Turn(false);
		
		winner = new PlayerWins();
		
		Card topCard = cardDeck.dealCard();
		if (topCard != null)
		{
			topOfStack.setIcon(topCard.getCardImage());
			stackDeck.addCard(topCard);
		}
		
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void actionPerformed(ActionEvent e)
	{
		Object src = e.getSource();	
		
		if((p1Deck == src && !p1turn.getDrawn() && p1turn.getPlayerTurn())|| (p2Deck == src && !p2turn.getDrawn() && p2turn.getPlayerTurn()))
		{

			Card card = cardDeck.dealCard();;

			if (card != null)
			{
				if(src == p1Deck)
				{
					p1Hand.addCard(card);
					p1HandPile.setListData(p1Hand.getArray());
					p1turn.setDrawn(true);
					print("Player 1");
					print("\t"+ " Added: " + card);
				}
				else if (p2Deck == src) 
				{
					p2Hand.addCard(card);
				    p2HandPile.setListData(p2Hand.getArray());
				    p2turn.setDrawn(true);
					print("Player 2");
					print("\t"+ " Added: " + card);
				}
			}
			if(cardDeck.getSizeOfDeck() == 0) 
			{
				deckPile.setIcon(new ImageIcon(Card.directory + "blank.gif"));
				System.out.println("Player 1 Score: " + p1Hand.evaluateHand());
				System.out.println("Player 2 Score: " + p2Hand.evaluateHand());
				if(p1Hand.compareTo(p2Hand) < 0) 
				{
					p1turn.setPlayerTurn(false);
					p2turn.setPlayerTurn(false);
					winner.infoBox("Player 1 Wins!;", "Winner");
				}
				if(p1Hand.compareTo(p2Hand) > 0)
				{
						p1turn.setPlayerTurn(false);
						p2turn.setPlayerTurn(false);
						winner.infoBox("Player 2 Wins!;", "Winner");
				}
				else
				{
					p1turn.setPlayerTurn(false);
					p2turn.setPlayerTurn(false);
					winner.infoBox("Its a tie!;", "Tie");
				}
			}

		}
		if((p1Stack == src && !p1turn.getDrawn() && p1turn.getPlayerTurn()) || (p2Stack == src && !p2turn.getDrawn() && p2turn.getPlayerTurn()))
		{
			Card card;
			card = stackDeck.removeCard();
			if(card != null)
			{
				Card topCard = stackDeck.peek();
				if (topCard != null)
				{
					topOfStack.setIcon(topCard.getCardImage());
				}
				else
				{
					topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));
				}
			}
				if(p1Stack == src && card != null){

					p1Hand.addCard(card);
					p1HandPile.setListData(p1Hand.getArray());
					p1turn.setDrawn(true);
					print("Player 1");
					print("\t"+ " Added: " + card);
				}
				else if(p2Stack == src && card != null) {
					p2Hand.addCard(card);
					p2HandPile.setListData(p2Hand.getArray());
					p2turn.setDrawn(true);
					print("Player 2");
					print("\t"+ " Added: " + card);
				}		
		}

		if(p1Lay == src && p1turn.getDrawn() && p1turn.getPlayerTurn()) {
			Object [] cards = p1HandPile.getSelectedValues();
			Card s_card;
			if (cards != null) {
				if(isAllSame(cards) && (cards.length == 3 || cards.length == 4)) {
					for(int i = 0; i < cards.length; i++) {
						Card card = (Card)cards[i];
						layCard(card, isAllSame(cards));
						p1Hand.removeCard(card);
						p1HandPile.setListData(p1Hand.getArray());
					}
				}
				else if (isRuns(cards) && (cards.length == 3 || cards.length == 4))  {
					for(int i = 0; i < cards.length; i++) {
						Card card = (Card)cards[i];
						layCard(card, isAllSame(cards));
						p1Hand.removeCard(card);
						p1HandPile.setListData(p1Hand.getArray());
					}
				}
				if (cards.length == 1 && setPanels[Card.getRankIndex(((Card) cards[0]).getRank())].getData().getNumberOfCards() == 3)  {
					s_card = (Card) cards[0];
					layCard(s_card, isAllSame(cards));
					p1Hand.removeCard(s_card);
					p1HandPile.setListData(p1Hand.getArray());
				}
			}
			if(p1Hand.isEmpty()) {
				p1turn.setPlayerTurn(false);
				p2turn.setPlayerTurn(false);
				winner.infoBox("Player 1 Wins!;", "Winner");
			}
		}

		if(p2Lay == src && p2turn.getDrawn() && p2turn.getPlayerTurn()) {
			Object [] cards = p2HandPile.getSelectedValues();
			Card s_card;
			if (cards != null) {
				if(isAllSame(cards) && (cards.length == 3 || cards.length == 4))  {
					for(int i = 0; i < cards.length; i++) {
						Card card = (Card)cards[i];
						layCard(card, isAllSame(cards));
						p2Hand.removeCard(card);
						p2HandPile.setListData(p2Hand.getArray());
					}
				}
				else if (isRuns(cards) && (cards.length == 3 || cards.length == 4))  {
					for(int i = 0; i < cards.length; i++) {
						Card card = (Card)cards[i];
						layCard(card, isAllSame(cards));
						p2Hand.removeCard(card);
						p2HandPile.setListData(p2Hand.getArray());
					}
				}
				
				if (cards.length == 1 && setPanels[Card.getRankIndex(((Card) cards[0]).getRank())].getData().getNumberOfCards() == 3)  {
					s_card = (Card) cards[0];
					layCard(s_card, isAllSame(cards));
					p2Hand.removeCard(s_card);
					p2HandPile.setListData(p2Hand.getArray());
				}
			}
			if(p2Hand.isEmpty()) {
				p1turn.setPlayerTurn(false);
				p2turn.setPlayerTurn(false);
				winner.infoBox("Player 2 Wins!;", "Winner");
			}
		}

		if(p1LayOnStack == src && p1turn.getDrawn() && p1turn.getPlayerTurn()) {
			int [] num  = p1HandPile.getSelectedIndices();
			if (num.length == 1) {
				Object obj = p1HandPile.getSelectedValue();
				if (obj != null) {
					p1Hand.removeCard(obj);
					p1HandPile.setListData(p1Hand.getArray());
					Card card = (Card)obj;
					stackDeck.addCard(card);
					topOfStack.setIcon(card.getCardImage());
					p1turn.setDrawn(false);
					p1turn.setPlayerTurn(false);
					p2turn.setPlayerTurn(true);
					print("\t" + " Discarded: " + card);
				}	
			}
			if(p1Hand.isEmpty()) {
				p1turn.setPlayerTurn(false);
				p2turn.setPlayerTurn(false);
				winner.infoBox("Player 1 Wins!;", "Winner");
			}
		}

		if(p2LayOnStack == src && p2turn.getDrawn() && p2turn.getPlayerTurn()) {
			int [] num  = p2HandPile.getSelectedIndices();
			if (num.length == 1) {
				Object obj = p2HandPile.getSelectedValue();
				if (obj != null) {

					p2Hand.removeCard(obj);
					p2HandPile.setListData(p2Hand.getArray());
					Card card = (Card)obj;
					stackDeck.addCard(card);
					topOfStack.setIcon(card.getCardImage());
					p2turn.setDrawn(false);
					p2turn.setPlayerTurn(false);
					p1turn.setPlayerTurn(true);
					print("\t" + " Discarded: " + card);
				}
			}
			if(p2Hand.isEmpty()) {
				p1turn.setPlayerTurn(false);
				p2turn.setPlayerTurn(false);
				winner.infoBox("Player 2 Wins!;", "Winner");
			}
		}
	}

	public static void main(String args[]) {
		Table t = new Table();
		t.setVisible(true);
		
	}
	void layCard(Card card, boolean sr) {
		char rank = card.getRank();
		char suit = card.getSuit();
		int suitIndex =  Card.getSuitIndex(suit);
		int rankIndex =  Card.getRankIndex(rank);
		if (sr)
			setPanels[rankIndex].addData(card);
		else
			runsPanels[rankIndex].addData(card);
		//setPanels[rankIndex].array[suitIndex].setText(card.toString());
		System.out.println("\t" + " laying: " + card);
		setPanels[rankIndex].array[suitIndex].setIcon(card.getCardImage());
		runsPanels[rankIndex].array[suitIndex].setIcon(card.getCardImage());
	}
	
	void print(String string) {
		System.out.println(string);
	}
	void print(boolean b) {
		System.out.println(b);
	}
	boolean isAllSame(Object[] obj) {
		boolean same = false;
		Card card = (Card) obj[0];
		Card card2;
		for (int i = 0; i < obj.length;i++) {
			card2 = (Card)obj[i];
			same = card.getRank()==card2.getRank();
			if(!same)
				return same;
		}
		return same;
	}
	
	boolean isRuns(Object[] obj) {
//		boolean runs= false;
		Card card;
		Card card2;
		for (int i = 0; i < obj.length-1; i++) {
			card = (Card) obj[i];
			card2 = (Card)obj[i+1];
			if(card2 != null) {
				if (!(Character.getNumericValue(card.getRank())+1 == Character.getNumericValue(card2.getRank())))
				{
					return false;
				}
			}
			else if(i+1 == obj.length+1) {
				break;
			}
		}
		return true;
	}

}

class HandPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1392557479833254221L;

	public HandPanel(String name,JList<Card> hand, JButton stack, JButton deck, JButton lay, JButton layOnStack) {
		//model = hand.createSelectionModel();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		add(Box.createGlue());
		JLabel label = new JLabel(name);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);
		stack.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(stack);
		deck.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(deck);
		lay.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lay);
		layOnStack.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(layOnStack);
		add(Box.createGlue());
		add(hand);
		add(Box.createGlue());
	}

}
class SetPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -328410276432938919L;
	private Set data;
	JButton [] array = new JButton[4];

	public SetPanel(int index) {
		super();
		setData(new Set(Card.rank[index]));

		for(int i = 0; i < array.length; i++) {
			array[i] = new JButton("   ");
			add(array[i]);
		}
	}

	public Set getData() {
		return data;
	}

	public void setData(Set data) {
		this.data = data;
	}
	
	public void addData(Card card) {
		this.data.addCard(card);
	}

}

class RunsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -328410276432938919L;
	private Runs data;
	JButton [] array = new JButton[4];

	public RunsPanel(int index) {
		super();
		runsData(new Runs(Card.rank[index]));

		for(int i = 0; i < array.length; i++) {
			array[i] = new JButton("   ");
			add(array[i]);
		}
	}

	public Runs getData() {
		return data;
	}

	public void runsData(Runs data) {
		this.data = data;
	}
	
	public void addData(Card card) {
		this.data.addCard(card);
	}

}

class Turn {
	private boolean CardDrawn,Playerturn;
	public Turn(boolean turn) {
		CardDrawn = false;
		Playerturn = turn;
	}
	public void setDrawn(boolean mode) {	
		CardDrawn = mode;
	}
	public void setPlayerTurn(boolean mode) {
		Playerturn = mode;
	}
	public boolean getDrawn() {
		return CardDrawn;
	}
	public boolean getPlayerTurn() {	
		return Playerturn;
	}
}