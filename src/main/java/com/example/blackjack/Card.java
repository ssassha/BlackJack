package com.example.blackjack;

public class Card {
    private final int rank;
    private final Suit suit;
    private final int value;

    public Card(int rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        if (rank == 14) value = 1;
        else value = Math.min(rank, 10);
    }

    public int getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
}
