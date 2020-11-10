/**
 * The Player class is used to construct players as well as
 * implement the actions a player will make each turn of the game.
 */
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Random;

public class Player {
	CardDeck cardDeck = new CardDeck();
	Integer number;

	/**
	 * Is this card deck a winning deck?
	 * @return true/false if this card deck is a winning deck.
	 * @author 690034975
	 */
	public boolean IsWinner(){
		return cardDeck.IsWinner();
	}

	/**
	 * Constructor for the player
	 * @param cardValues - cards that should be given to the player
	 * @author 690034975
	 */
	public Player(Integer playerNumber, int[] cardValues){
		for (int cardValue : cardValues){
			cardDeck.AddCard(new Card(cardValue));
		}
		number = playerNumber;
	}

	public Player(Integer playerNumber){
		number = playerNumber;
	}

	public void AddCard(int value){
		cardDeck.AddCard(value);
	}

	public void TakeTurn(CardDeck discardDeck, CardDeck pickupDeck){

	}

	/**
	 * @return stringified version of Player
	 * @author 690034975
	 */
	@Override
	public String toString(){
		return "player"+number+"="+ cardDeck.toString();
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
}
