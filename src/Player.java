/**
 * The Player class is used to construct players as well as
 * implement the actions a player will make each turn of the game.
 */
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Random;

public class Player extends Thread{
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

	public void RemoveCard(Card card){
		cardDeck.RemoveCard(card);
	}

	public void TakeTurn(CardDeck discardDeck, CardDeck pickupDeck){

	}

	Boolean take = true;
	//Boolean discard = false;

	/**
	 * Make each player take a card from the previous deck. Threaded.
	 *
	 * @author 690022392
	 */
	public synchronized void takeCard(Player player, CardDeck deck) {
	//public synchronized void takeCard(CardDeck deck) {
		while (!take) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		//new Player((player.cardDeck)).start();
		//for (int i = 0; i < players.size(); i++) {
			//for (int j; j < decks.size(); j++) {
			
		Card add = deck.topCard();
		deck.RemoveCard(add);
		player.AddCard(add.GetValue());
		//AddCard(add.GetValue());
		notifyDiscard();		
			//}
	}

	/**
	 * Make each player discard a card to the next
	 * deck. Threaded.
	 *
	 * @author 690022392
	 */	
	public synchronized void discardCard(Player player, CardDeck deck) {
	//public synchronized void discardCard(CardDeck deck) {	
		while (take) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		//for (int i; i < players.size(); i++) {
		//	cardDeck.RemoveCard(players.get(i).CardToDiscard());
		//for (Player player : players){
		//for (int i = 0; i < players.size(); i++) {
			//for (int j; j < decks.size(); j++) {
		Card discard = CardToDiscard();
		deck.AddCard(discard);
		player.RemoveCard(discard);
		//RemoveCard(discard);
		notifyTake();
			//}
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
		take = false;
		notifyAll();
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
