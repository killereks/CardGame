import java.util.ArrayList;
/**
 * The CardDeck class is a placeholder for a deck of cards with methods
 * to operate on the cards.
 */
public class CardDeck {
	ArrayList<Card> cards = new ArrayList<Card>();

	public ArrayList<Card> getCards(){
		return cards;
	}

	/**
	 * CardDeck constructor, takes in any amount of card values
	 * @param args - list of card values
	 * @author 690034975
	 */
	public void CardDeck(int[] args){
		AddCards(args);
	}

	/**
	 * Adds the given card into the card deck.
	 * @param card card to add
	 * @author 690034975
	 */
	public void AddCard(Card card) {
		cards.add(card);
	}

	/**
	 * Adds a card with a given card value
	 * @param value - card value to add
	 * @author 690034975
	 */
	public void AddCard(int value){
		cards.add(new Card(value));
	}

	/**
	 * Constructor for the card deck
	 * @param cardValues - cards that should be given to the deck
	 * @author 690022392
	 */
	public void AddCards(int[] args) {
		for (int arg : args){
			AddCard(new Card(arg));
		}
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
	 * 
	 * @return true/false if this card deck is a winning deck.
	 * @author 690034975
	 */
	
	public boolean IsWinner(){
		if (cards.size() != 4) return false;

		for (int i = 0; i < cards.size()-1; i++){
			if (cards.get(i).GetValue() != cards.get(i+1).GetValue()){
				return false;
			}
		}

		return true;
	}

	/**
	 * @return next card to be drawn from this deck
	 * @author 690022392
	 */
	public Card topCard(){
		return cards.get(0);
	}

	/**
	 * @return number of cards in this deck
	 * @author 690022392
	 */
	public int cardCount(){
		return cards.size();
	}

	/**
	 * @return stringified version of CardDeck
	 * @author 690034975
	 */
	@Override
	public String toString(){
		String output = "{";
		for (int i = 0; i < cards.size(); i++){
			output += cards.get(i).toString();
			if (i < cards.size() - 1){
				output += ",";
			}
		}
		return output + "}";
	}

}