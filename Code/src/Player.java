
/**
 * The Player class is used to construct players as well as
 * implement the actions a player will make each turn of the game.
 */
import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;

public class Player extends Thread {
	CardDeck cardDeck = new CardDeck();
	Integer number;

	/**
	 * Is this card deck a winning deck?
	 * 
	 * @return true/false if this card deck is a winning deck.
	 * @author 690034975
	 */
	public boolean IsWinner() {
		return cardDeck.IsWinner();
	}

	/**
	 * Constructor for the player
	 * 
	 * @param cardValues - cards that should be given to the player
	 * @author 690034975
	 */
	public Player(Integer playerNumber, int[] cardValues) {
		for (int cardValue : cardValues) {
			cardDeck.AddCard(new Card(cardValue));
		}
		number = playerNumber;
	}

	ArrayList<CardDeck> decks;

	public Player(ArrayList<CardDeck> decks, Integer playerNumber) {
		number = playerNumber;
		this.decks = decks;
	}

	public void AddCard(int value) {
		cardDeck.AddCard(value);
	}

	public void RemoveCard(Card card) {
		cardDeck.RemoveCard(card);
	}

	Boolean take = true;
	Boolean ended = false;

	private static volatile boolean finished = false;
	private static volatile String winner = null;

	ArrayList<String> output = new ArrayList<String>();


	/**
	 * Run the thread-safe player according to the game rules
	 * 
	 * @author 690022392
	 */
	public synchronized void run() {

		String fileName = "Player " + Integer.toString(number);
		output.add(fileName);
		output.add("Initial hand - " + toString() + "\n");

		System.out.println("Hello");
		while (!IsWinner()) {
			if (finished) {
				break;
			}
			System.out.println("");
			if (decks.get(number - 1).cardCount() >= 2) {
				takeCard(decks.get(number - 1));
				discardCard(decks.get((number) % decks.size()));
				System.out.println(toString());
				output.add(toString() + "\n");
				if (IsWinner()) {
					finished = true;
					winner = "Player " + Integer.toString(number);
					System.out.println(winner + "/////");
					break;
				}
			}
		}

		if (!IsWinner()) {
			if (winner == null) {
				Thread.yield();
			}
		}
		System.out.println(winner);
		if (IsWinner()) {
			System.out.println("The winner is " + toString());
			output.add(winner + " wins");
			notifyAll();
		} else {
			output.add(winner+" has informed "+"Player "+Integer.toString(number)+" that they have won");
			output.add("Player "+Integer.toString(number)+" exits");
			output.add(toString());
		}
		
		WriteToFile(fileName, output);

		
	}

	/**
	 * Make each player take a card from the previous deck. Threaded.
	 *
	 * @author 690022392
	 */
	public synchronized void takeCard(CardDeck deck) {
		while (!take) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}

		Card add = deck.topCard();
		String play = "Player " + Integer.toString(number);
		output.add(play + " draws a " + Integer.toString(add.GetValue()) + " from deck " + Integer.toString(number));
		deck.RemoveCard(add);
		AddCard(add.GetValue());
		notifyDiscard();		
	}

	/**
	 * Make each player discard a card to the next
	 * deck. Threaded.
	 *
	 * @author 690022392
	 */	
	public synchronized void discardCard(CardDeck deck) {	
		while (take) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}

		Card discard = CardToDiscard();
		String play = "Player " + Integer.toString(number);
		output.add(play + " discards a " + Integer.toString(discard.GetValue()) + " to deck " + Integer.toString((number+1 % decks.size())));
		deck.AddCard(discard);
		RemoveCard(discard);
		notifyTake();
	}


	/**
	 * Notifies the main thread that it is time for players to
	 * take a card from the previous deck. Threaded.
	 *
	 * @author 690022392
	 */
	public synchronized void notifyTake() {
		take = true;
		notify();
	}

	/**
	 * Notifies the main thread that it is time for players to
	 * discard a card to the next deck. Threaded.
	 *
	 * @author 690022392
	 */
	public synchronized void notifyDiscard() {
		take = false;
		notify();
	}

	/**
	 * @return stringified version of Player
	 * @author 690022392
	 */
	@Override
	public String toString(){
		return "Player "+number+" current hand is "+ cardDeck.toString();
	}

	/**
	 * Picks a random card such that the card isn't this player's number.
	 *
	 * For example if player number is 3 and his cards are 3,2,1,4 it will return either 2, 1 or 4
	 *
	 * @return Card card that should be discarded.
	 * @author 690034975
	 */
	Card CardToDiscard(){
		ArrayList<Card> potentialMoves =  new ArrayList<Card>();

		potentialMoves.addAll(cardDeck.getCards());
		potentialMoves.removeIf(s -> s.GetValue() == number);

		Random rand = new Random();
		return potentialMoves.get(rand.nextInt(potentialMoves.size()));
	}

	/**
	 * Creates and writes content to a file
	 * @param fileName - name of the file
	 * @param contents - ArrayList of each line that should be inside the file
	 * @author 690022392 + 690034975
	 */
	public static void WriteToFile(String fileName, ArrayList<String> contents){

		try {
			FileWriter writer = new FileWriter(fileName);
			for (String line : contents){
				writer.write(line+"\n");
			}
			writer.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
