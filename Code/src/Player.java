import java.util.Dictionary;

public class Player {
	CardDeck cardDeck = new CardDeck();

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
	public Player(int[] cardValues){
		for (int cardValue : cardValues){
			cardDeck.AddCard(new Card(cardValue));
		}
	}

	public void TakeTurn(CardDeck discardDeck, CardDeck pickupDeck){

	}

	/*Card CardToDiscard(){
		Map<Card, Integer> cardFrequency = new HashMap<Card, Integer>();

		for (Card card : CardDeck){

		}
	}
	*/
}
