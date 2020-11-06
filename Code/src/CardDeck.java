import java.util.ArrayList;

public class CardDeck {
	ArrayList<Card> cards = new ArrayList<Card>();
	
	public void AddCard(Card card) {
		cards.add(card);
	}
	public void RemoveCard(Card card) {
		cards.remove(card);
	}
}
