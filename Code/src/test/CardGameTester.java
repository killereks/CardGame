import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardGameTester {
    @Test
    void SimpleTest(){
        Player player = new Player(0, new int[]{4,4,4,4});
        Player player2 = new Player(1, new int[]{4,4,4,1});
        Player player3 = new Player(2, new int[]{4,4,4});
        Player player4 = new Player(3, new int[]{3,2,2,2});
        Player player5 = new Player(4, new int[]{5,4,4,4});

        Assertions.assertEquals(player.IsWinner(), true);
        Assertions.assertEquals(player2.IsWinner(), false);
        Assertions.assertEquals(player3.IsWinner(), false);
        Assertions.assertEquals(player4.CardToDiscard().GetValue(), 2);
        Assertions.assertEquals(player5.CardToDiscard().GetValue(), 5);
    }
}
