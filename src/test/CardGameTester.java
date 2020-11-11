import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;

public class CardGameTester {
    @Test
    void PlayerWinnerTest(){
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
    @Test
    void EnoughCardsTest(){
        CardGame cg = new CardGame(1);
        ArrayList<Integer> cards = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8));
        ArrayList<Integer> cards2 = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7));

        Assertions.assertEquals(cg.EnoughCards(cards), true);
        Assertions.assertEquals(cg.EnoughCards(cards2), false);
    }

    @Test
    void CardDeckTest(){
        CardDeck deck = new CardDeck();

        Assertions.assertEquals(deck.getCards().size(), 0);

        CardDeck deck2 = new CardDeck();
        deck2.AddCard(5);

        Assertions.assertEquals(deck2.getCards().size(), 1);

        deck2.AddCard(new Card(3));
        Assertions.assertEquals(deck2.getCards().size(), 2);

        CardDeck deck3 = new CardDeck();
        deck3.AddCards(new int[]{2,2,2,2});

        CardDeck deck4 = new CardDeck();
        deck4.AddCards(new int[]{2,2,2});

        CardDeck deck5 = new CardDeck();
        deck5.AddCards(new int[]{2,2,2,1});

        Assertions.assertEquals(deck3.IsWinner(), true);
        Assertions.assertEquals(deck4.IsWinner(), false);
        Assertions.assertEquals(deck5.IsWinner(), false);
    }

    @Test
    void PlayerTests(){
        Player player = new Player(0);

        Assertions.assertEquals(player.cardDeck.getCards().size(), 0);

        player.AddCard(5);

        Assertions.assertEquals(player.cardDeck.getCards().size(), 1);
    }
}
