package com.example.blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private final ArrayList<Card> cardList;

    public Deck() {
        this.cardList = new ArrayList<>();
        createDeck();
    }

    private void createDeck() {
        for (int i = 2; i < 15; i++) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(i, suit);
                cardList.add(card);
            }
        }
    }

    public Card drawCard() {
        if (cardList.size() == 0) {
            Deck deck = new Deck();
            deck.drawCard();
        }
        Random random = new Random();
        int n = random.nextInt(cardList.size());
        return cardList.remove(n);
    }

    public int getCardListSize() {
        return cardList.size();
    }
}
