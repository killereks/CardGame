/**
 * The CardGame class runs the main functionality of the game
 * simulation. This class reads the cards in from the pack text
 * file, initialises the player hands and cards if the deck is valid,
 * and implements the rules of the simulation while checking each
 * player to find the winner.
 */
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class CardGame {

	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<CardDeck> decks = new ArrayList<CardDeck>();

	/**
	 * Create a new card game instance with given player count
	 * @param playerCount - amount of players in the game
	 * @author 690034975
	 */
	public CardGame(int playerCount){
		for (int i = 0; i < playerCount; i++){
			players.add(new Player(i));
			decks.add(new CardDeck());
		}
	}
	
	/**
	 * Initialises the players hands and the cards in each deck
	 * from the cards in the pack
	 *
	 * @param cards the list of cards
	 * @author 690022392
	 */	
	public void initGame(ArrayList<Integer> cards){
		/*
		
		int[] hand = {0,0,0,0};

		//assign cards to each player and deck
		// check determine winner

		System.out.println(cards);
		for (int i = 1; i < cards.size() + 1; i++) {
			hand[(i%4)] = cards.get(i-1);
			//System.out.println(Arrays.toString(hand));
			if ((i % 4 == 0)) {
				if (i <= (cards.size()/2)) {
					players.add(new Player(hand));
				} else {
					decks.add(new AddCards(hand));
				}
				System.out.println(Arrays.toString(hand));
			}
		}
		System.out.println(players);
		*/
	}

	/**
	 * Checks if there is a perfect amount of cards in the game
	 * @param cards - cards in the game
	 * @return true/false if there is enough cards for the game
	 * @author 690034975
	 */
	boolean EnoughCards(ArrayList<Integer> cards){
		return 8 * players.size() == cards.size();
	}

	/**
	 * Gives everyone 4 cards (players and decks)
	 * @param cards - list of cards to use
	 * @throws InvalidCardAmount - There aren't enough, or there are too many cards for the game.
	 * @author 690034975
	 */
	private void DealCards(ArrayList<Integer> cards) throws InvalidCardAmount {
		int index = 0;

		if (EnoughCards(cards)){
			for (int i = 0; i < 4; i++){
				for (Player player : players){
					player.AddCard(cards.get(index));
					index++;
				}
				for (CardDeck deck : decks){
					deck.AddCard(cards.get(index));
					index++;
				}
			}

		} else {
			throw new InvalidCardAmount("Not enough cards, or too many.");
		}
	}

	public void ReadCardsFromFile(String filename) throws FileNotFoundException, InvalidCardAmount {
		ArrayList<Integer> cards = CardsFromFile(filename);
		DealCards(cards);
	}
	
	Boolean take = true;
	Boolean discard = false;
	/**
	 * Run the main functionality of the game, implementing player
	 * moves each turn according to the rules. Threaded.
	 *
	 * @author 690022392
	 */
	public synchronized void GameStep(){
	    /*
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

		while (DetermineWinner() == null) {
			takeCard();
			discardCard();
			DetermineWinner();
		}
		System.out.println(DetermineWinner());
	}
		
	/**
	 * Make each player take a card from the previous
	 * deck. Threaded.
	 *
	 * @author 690022392
	 */	
	public synchronized void takeCard() {
		while (!take) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		for (int i = 0; i < players.size(); i++) {
			//for (int j; j < decks.size(); j++) {
			Card add = decks.get(i).topCard();
			decks.get(i).RemoveCard(add);
			players.get(i).AddCard(add.GetValue());			
			//}
		}
	}

	/**
	 * Make each player discard a card to the next
	 * deck. Threaded.
	 *
	 * @author 690022392
	 */	
	public synchronized void discardCard() {
		while (!discard) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		//for (int i; i < players.size(); i++) {
		//	cardDeck.RemoveCard(players.get(i).CardToDiscard());
		//for (Player player : players){
		for (int i = 0; i < players.size(); i++) {
			//for (int j; j < decks.size(); j++) {
			Card discard = players.get(i).CardToDiscard();
			decks.get((i+1) % players.size()).AddCard(discard);
			players.get(i).RemoveCard(discard);
			//}
		}
	}


	/**
	 * Notifies the main thread that it is time for players to
	 * take a card from the previous deck. Threaded.
	 *
	 * @author 690022392
	 */
	public synchronized void notifyTake() {
		take = true;
		notifyAll();
	}

	/**
	 * Notifies the main thread that it is time for players to
	 * discard a card to the next deck. Threaded.
	 *
	 * @author 690022392
	 */
	public synchronized void notifyDiscard() {
		discard = true;
		notifyAll();
	}


	/**
	 * Returns the player with winning deck of cards, or null if nobody wins.
	 *
	 * @return The player that has won the game.
	 * @author 690034975
	 */
	private Player DetermineWinner(){
		for (Player player : players){
			if (player.IsWinner()) return player;
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		/*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String name = reader.readLine();
		System.out.println("Hello! "+name);*/
		
		ArrayList<Integer> cards = CardsFromFile("src/four.txt");
		//System.out.println(cards);
		CardGame game = new CardGame(4);
		game.initGame(cards);
	}

	/**
	 * @param fileName name of the file to parse.
	 * @return File parsed into an array of integers which are separated by a new line '\n'.
	 * @throws FileNotFoundException When a file is not found, it throws this exception.
	 * @author 690034975 + 690022392
	 */
	private static ArrayList<Integer> CardsFromFile(String fileName) throws FileNotFoundException {
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

	/**
	 * Creates and writes content to a file
	 * @param fileName - name of the file
	 * @param contents - ArrayList of each line that should be inside the file
	 */
	public static void WriteToFile(String fileName, ArrayList<String> contents){
		try {
			File file = new File(fileName);
			if (file.createNewFile()){
				FileWriter writer = new FileWriter(fileName);
				for (String line : contents){
					writer.write(line+"\n");
				}
				writer.close();
			} else {
			System.out.println("WriteToFile: File already exists!");
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * @return stringified version of CardGame
	 * @author 690034975
	 */
	@Override
	public String toString(){
		String output = "players=(";
		for (Player player : players){
			output += player.toString();
		}
		output += ") decks=(";
		for (CardDeck deck : decks){
			output += deck.toString();
		}
		return output + ")";
	}

	public static String GetDate(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
}
