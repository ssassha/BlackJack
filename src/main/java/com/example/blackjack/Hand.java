package com.example.blackjack;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> currentCards = new ArrayList<>();
    private int points;

    public Hand() {}

    public void hit(Deck currentDeck) {
        currentCards.add(currentDeck.drawCard());
    }

    public int scoring() {
        points = 0;
        for (int i = 0; i < currentCards.size(); i++) {
            points += currentCards.get(i).getValue();
        }
        return points;
    }

    public ArrayList<Card> getCurrentCards() {
        return currentCards;
    }
}
