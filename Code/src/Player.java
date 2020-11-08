
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
}
