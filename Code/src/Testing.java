import java.util.ArrayList;

public class Testing extends CardGame {

    public static void main(String[] args){
        Player player = new Player(new int[]{4,4,4,4});

        assert(!player.IsWinner());

        Print("All tests have passed!");
    }

    public static void Print(String value){
        System.out.println(value);
    }
    public static void Print(boolean value){
        System.out.println(value);
    }
}
