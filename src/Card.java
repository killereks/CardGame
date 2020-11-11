/**
 * The Card class uses a getter and setter to convert a card value
 * to an instance of a card with said value.
 */
public class Card {
	private int value;

	public Card(int value) {
		this.value = value;
	}
	
	public int GetValue(){ 
		return this.value; 
	}

	/**
	 * @return stringified version of card.
	 * @author 690034975
	 */
	@Override
	public String toString(){
		return Integer.toString(value);
	}
}
