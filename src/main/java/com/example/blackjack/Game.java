package com.example.blackjack;

import java.util.ArrayList;

public class Game {
    Deck deck = new Deck();
    Player player = new Player();
    Dealer dealer = new Dealer();
    int currentBet;

    public Game() {
    }

    public void start() {
        if (deck.getCardListSize() < 4) {
            deck = new Deck();
            start();
        }
        else {
            player.startGame(deck);
            dealer.startGame(deck);
        }
    }

    public String hit() {
        if (deck.getCardListSize() == 0) {
            deck = new Deck();
        }
        player.hit(deck);
        if (player.getPoints() == 21) return blackjack();
        if (player.getPoints() > 21) return lose();
        return "Hit or stand?";
    }

    public String stand() {
        if (player.getPoints() == 21) return blackjack();
        if (dealer.getPoints() == 21) return lose();
        if (dealer.getPoints() > 21) return win();
        if (dealer.getPoints() > player.getPoints()) return lose();
        if (dealer.getPoints() < player.getPoints()) return win();
        return stay();
    }

    public String blackjack() {
        player.won(currentBet);
        return "Blackjack!";
    }

    public String win() {
        player.won(currentBet);
        return "You won!";
    }

    public String lose() {
        player.lost(currentBet);
        return "You lost!";
    }

    public String stay() {
        return "Stay";
    }

    public int getPlayerPoints() {
        return player.getPoints();
    }

    public int getDealerPoints() {
        return dealer.getPoints();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public int getPlayerBalance() {
        return player.getBalance();
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Card> getPlayerCardList() {
        return player.getHand().getCurrentCards();
    }

    public ArrayList<Card> getDealerCardList() {
        return dealer.getHand().getCurrentCards();
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }
}
