package com.example.blackjack;

public class Player {
    private Hand hand = new Hand();
    private int points;

    public Player() {
    }

    public void startGame(Deck deck) {
        hand = new Hand();
        hand.hit(deck);
        points = hand.scoring();
        hand.hit(deck);
        points = hand.scoring();
    }

    public void hit(Deck deck) {
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
