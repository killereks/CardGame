/**
 * 
 */
import java.util.ArrayList;

public class CardDeck {
	ArrayList<Card> cards = new ArrayList<Card>();

	/**
	 * Adds the given card into the card deck.
	 * @param card card to add
	 * @author 690034975
	 */
	public void AddCard(Card card) {
		cards.add(card);
	}

	/**
	 * Removes the given card from the card deck.
	 * @param card the card instance to remove.
	 * @author 690034975
	 */
	public void RemoveCard(Card card) {
		cards.remove(card);
	}

	/**
	 * Is this card deck a winning deck?
	 * @return true/false if this card deck is a winning deck.
	 * @author 690022392
	 */
	
	public boolean IsWinner(){
		for (Card c : cards) {
			if (c != cards.get(0))
				return false;
			}
		return true;
	}
}
