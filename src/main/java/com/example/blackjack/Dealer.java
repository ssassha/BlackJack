package com.example.blackjack;

import java.util.concurrent.TimeUnit;

public class Dealer {
    private Hand hand = new Hand();
    private int points;

    public Dealer() {

    }

    public void startGame(Deck deck) {
        hand = new Hand();
        hand.hit(deck);
        points = hand.scoring();
        hand.hit(deck);
        points = hand.scoring();
    }

    public void draw(Deck deck) {
        hand.hit(deck);
        points = hand.scoring();
    }

    public int getPoints() {
        return points;
    }

    public Hand getHand() {
        return hand;
    }
}
