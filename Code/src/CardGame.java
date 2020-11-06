import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class CardGame {
	
	public void initGame(){

	}
	  
	public void GameStep(){
	    
	}
	  
	public void DetermineWinner(ArrayList<Card> hand){
        for (Card c : hand) {
            if (!c.equals(hand.get(0)))
                return false;
        }
        return true;
	}
	
	public static void main(String[] args) throws IOException {
		/*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String name = reader.readLine();
		System.out.println("Hello! "+name);*/
		
		CardsFromFile("four.txt");
	}
	
	public static void CardsFromFile(String fileName) throws FileNotFoundException {
		try {
			File file = new File(fileName);
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				System.out.println(data);
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
	}

}
