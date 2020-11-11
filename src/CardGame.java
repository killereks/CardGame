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

public class CardGame{

	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<CardDeck> decks = new ArrayList<CardDeck>();

	/**
	 * Create a new card game instance with given player count
	 * @param playerCount - amount of players in the game
	 * @author 690034975
	 */
	public CardGame(int playerCount){
		for (int i = 0; i < playerCount; i++){
			//players.add(new Player(i+1));
			decks.add(new CardDeck());
		}
		for (int i = 0; i < playerCount; i++){
			players.add(new Player(decks, i+1));
			//decks.add(new CardDeck());
		}
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
	
	/**
	 * Run the main functionality of the game, implementing player
	 * moves each turn according to the rules. Threaded.
	 *
	 * @author 690022392
	 */
	public void GameStep(){
		for (int i = 0; i < players.size(); i++) {
			players.get(i).start();
		}
	}

	
	public static void main(String[] args) throws IOException, InvalidCardAmount {
		
		ArrayList<Integer> cards = CardsFromFile("four2.txt");
		CardGame game = new CardGame(4);
		game.DealCards(cards);
		game.GameStep();
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
