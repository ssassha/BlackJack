package com.example.blackjack;

import java.util.ArrayList;

public class Hand {
    private final ArrayList<Card> currentCards = new ArrayList<>();

    public Hand() {}

    public void hit(Deck currentDeck) {
        currentCards.add(currentDeck.drawCard());
    }

    public int scoring() {
        int points = 0;
        for (Card currentCard : currentCards) {
            points += currentCard.getValue();
        }
        return points;
    }

    public ArrayList<Card> getCurrentCards() {
        return currentCards;
    }
}
