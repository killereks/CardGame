import java.util.ArrayList;

public class Testing extends CardGame {

    public static void main(String[] args){


        ArrayList<Card> deck1 = new ArrayList<Card>();
        for (int i = 0; i < 4; i++) deck1.add(new Card(4));

    }

    public static void Print(String value){
        System.out.println(value);
    }
    public static void Print(boolean value){
        System.out.println(value);
    }
}
