import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class CardGame {

	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<CardDeck> cards = new ArrayList<CardDeck>();
	
	/**
	 * Initialises the players hands and the cards in each deck
	 * from the cards in the pack
	 *
	 * @param The ArrayList of cards from the file
	 * @author 690022392
	 */	
	public void initGame(ArrayList<Integer> cards){

		ArrayList<Integer> hand = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> playerHands = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> cardDecks = new ArrayList<ArrayList<Integer>>();
		//int[] hand = new int();
		//ArrayList<int[]> playerHands = new ArrayList<int[]>();
		//ArrayList<int[]> cardDecks = new ArrayList<int[]>();
		/**
		 * assign cards to each player and deck
		 * check determine winner
		 * 
		 */
		System.out.println(cards);
		for (int i = 1; i < cards.size() + 1; i++) {		
			hand.add(cards.get(i-1));
			if ((i % 4 == 0)) {
				if (i <= (cards.size()/2)) {
					playerHands.add(new ArrayList<Integer>(hand));
					//Player player = new Player(new ArrayList<Integer>(hand))); DOESNT WORK
				} else {
					cardDecks.add(new ArrayList<Integer>(hand));
				}
				hand.clear();
			}
		}
		System.out.println(playerHands);
		System.out.println(cardDecks);
	}
	
	public void GameStep(){
	    /**
		 *	-order-
		 *	threaded - each player take the top card (first in
		 *	queue) from the deck to their left (p2 take from d2...)
		 *
		 *	move random card from each player 
		 *	(not same as player number) to the bottom (end of
		 *	queue) of the deck on the right (p2 -> d3...)
		 *
		 * check determine winner
		 * 
		 * repeat
		 *	
		 */
	}

	/**
	 * Returns the player with winning deck of cards, or null if nobody wins.
	 *
	 * @return The player that has won the game.
	 * @author 690034975
	 */
	public Player DetermineWinner(){
		for (Player player : players){
			if (player.IsWinner()) return player;
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		/*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String name = reader.readLine();
		System.out.println("Hello! "+name);*/
		
		ArrayList<Integer> cards = CardsFromFile("four.txt");
		//System.out.println(cards);
		CardGame game = new CardGame();
		game.initGame(cards);
	}

	/**
	 * @param fileName name of the file to parse.
	 * @return File parsed into an array of integers which are separated by a new line '\n'.
	 * @throws FileNotFoundException When a file is not found, it throws this exception.
	 * @author 690034975 + 690022392
	 */
	public static ArrayList<Integer> CardsFromFile(String fileName) throws FileNotFoundException {
		ArrayList<Integer> output = new ArrayList<Integer>();

		try {
			File file = new File(fileName);
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()){
				String data = reader.nextLine();
				int cardNo = Integer.parseInt(data);
				output.add(cardNo);
			}
			reader.close();
		} catch (FileNotFoundException e){
			System.out.println("File not found.");
			e.printStackTrace();
		}

		return output;
	}
}
