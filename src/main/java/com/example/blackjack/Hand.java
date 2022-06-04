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
        int aces = 0;
        for (Card currentCard : currentCards) {
        points += currentCard.getValue();
        if (currentCard.getRank() == 14) aces++;
        }
        while (aces != 0) {
            if (points + 10 <= 21) points += 10;
            aces--;
        }
        return points;
    }

    public ArrayList<Card> getCurrentCards() {
        return currentCards;
    }
}
