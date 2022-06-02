import com.example.blackjack.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BlackjackTest {

    @Test
    public void cardTest() {
        Card card = new Card(2, Suit.CLUBS);
        assertEquals(2, card.getRank());
        assertEquals(Suit.CLUBS, card.getSuit());
    }

    @Test
    public void deckTest() {
        Deck deck = new Deck();
        Card card = deck.drawCard();
        while (card.getRank() != 3) card = deck.drawCard();
        assertEquals(3, card.getRank());
    }

    @Test
    public void handTest() {
        Hand hand = new Hand();
        Deck deck = new Deck();
        hand.hit(deck);
        assertTrue(hand.scoring() >= 2);
    }

    @Test
    public void gameTest() {
        Game game = new Game();
        game.start();
        assertTrue(game.getDealerPoints() >= 4);
    }
}

