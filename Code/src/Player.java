
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

	public Player(int[] cardValues){
		for (int cardValue : cardValues){
			cardDeck.AddCard(new Card(cardValue));
		}
	}
}
