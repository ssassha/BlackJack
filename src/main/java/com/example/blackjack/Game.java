package com.example.blackjack;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game {
    Deck deck = new Deck();
    Player player = new Player();
    Dealer dealer = new Dealer();

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
        if (player.getPoints() == 21) {

            return blackjack();
        }
        if (player.getPoints() > 21) return lose();
        return "Hit or stand?";
    }

    public String stand() {
        if (dealer.getPoints() == 21) return lose();
        if (dealer.getPoints() > 21) return win();
        if (dealer.getPoints() > player.getPoints()) return lose();
        if (dealer.getPoints() < player.getPoints()) return win();
        return "Stay!";
    }

    public String blackjack() {
        return "Blackjack!";
    }

    public String win() {
        return "You won!";
    }

    public String lose() {
        return "You lost!";
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

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Card> getPlayerCardList() {
        return player.getHand().getCurrentCards();
    }

    public ArrayList<Card> getDealerCardList() {
        return dealer.getHand().getCurrentCards();
    }
}
