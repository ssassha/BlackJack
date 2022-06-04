package com.example.blackjack;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Controller {

    int rank;
    String suit;
    FileInputStream input;
    Image image;
    boolean isGameOn = false;
    boolean isGameEnd = false;

    Game game = new Game();

    @FXML
    private Label current;

    @FXML
    private ImageView dealerCard1;

    @FXML
    private ImageView dealerCard2;

    @FXML
    private ImageView dealerCard3;

    @FXML
    private ImageView dealerCard4;

    @FXML
    private ImageView dealerCard5;

    @FXML
    private ImageView dealerCard6;

    @FXML
    private ImageView playerCard1;

    @FXML
    private ImageView playerCard2;

    @FXML
    private ImageView playerCard3;

    @FXML
    private ImageView playerCard4;

    @FXML
    private ImageView playerCard5;

    @FXML
    private ImageView playerCard6;

    @FXML
    private ImageView back;

    @FXML
    private Label playerPoints;

    @FXML
    private Label dealerPoints;

    @FXML
    private Label balance;

    @FXML
    private Label currentBet;

    @FXML
    private TextField txtField;

    public Controller() {
    }

    @FXML
    void hit() throws FileNotFoundException {
        if (isGameEnd) {
            current.setText("The game is over!");
        } else {
            String currentHit = game.hit();
            int k = game.getPlayerCardList().size();
            current.setText(currentHit);
            if (!Objects.equals(currentHit, "Hit or stand?")) {
                back.setVisible(false);
                dealerCard2.setVisible(true);
                dealerPoints.setText(String.valueOf(game.getDealerPoints()));
                balance.setText(String.valueOf(game.getPlayerBalance()));
                isGameEnd = true;
                isGameOn = false;
                currentBet.setText("0");
                if (game.getPlayerBalance() == 0) current.setText("Game over! Press quit");
            }
            playerPoints.setText(String.valueOf(game.getPlayerPoints()));
            rank = game.getPlayerCardList().get(k - 1).getRank();
            suit = game.getPlayerCardList().get(k - 1).getSuit().toString().toLowerCase();
            input = new FileInputStream("src\\main\\resources\\com\\example\\blackjack\\cards\\" + rank + "_of_" + suit + ".png");
            image = new Image(input);
            switch (k) {
                case (3):
                    playerCard3.setVisible(true);
                    playerCard3.setImage(image);
                    break;
                case (4):
                    playerCard4.setVisible(true);
                    playerCard4.setImage(image);
                    break;
                case (5):
                    playerCard5.setVisible(true);
                    playerCard5.setImage(image);
                    break;
                case (6):
                    playerCard6.setVisible(true);
                    playerCard6.setImage(image);
                    break;
            }
        }
    }

    @FXML
    void doubleBet() throws FileNotFoundException {
        if (game.getPlayerBalance() >= game.currentBet * 2) {
            game.setCurrentBet(game.currentBet * 2);
            currentBet.setText(String.valueOf(game.currentBet));
            hit();
            if (isGameOn) stand();
        }
        else current.setText("You don't have enough money!");
    }

    @FXML
    void stand() throws FileNotFoundException {
        if (isGameEnd) {
            current.setText("The game is over!");
        } else {
            back.setVisible(false);
            dealerCard2.setVisible(true);
            dealerPoints.setText(String.valueOf(game.getDealerPoints()));
            int k = 3;
            while (game.getDealerPoints() < 17) {
                game.getDealer().draw(game.getDeck());
                dealerPoints.setText(String.valueOf(game.getDealerPoints()));
                rank = game.getDealerCardList().get(k - 1).getRank();
                suit = game.getDealerCardList().get(k - 1).getSuit().toString().toLowerCase();
                input = new FileInputStream("src\\main\\resources\\com\\example\\blackjack\\cards\\" + rank + "_of_" + suit + ".png");
                image = new Image(input);
                switch (k) {
                    case (3):
                        dealerCard3.setVisible(true);
                        dealerCard3.setImage(image);
                        break;
                    case (4):
                        dealerCard4.setVisible(true);
                        dealerCard4.setImage(image);
                        break;
                    case (5):
                        dealerCard5.setVisible(true);
                        dealerCard5.setImage(image);
                        break;
                    case (6):
                        dealerCard6.setVisible(true);
                        dealerCard6.setImage(image);
                        break;
                }
                k++;
            }
            current.setText(game.stand());
            balance.setText(String.valueOf(game.getPlayerBalance()));
            if (game.getPlayerBalance() == 0) current.setText("Game over! Press quit");
            currentBet.setText("0");
            isGameEnd = true;
            isGameOn = false;
        }
    }

    @FXML
    void startGame() throws FileNotFoundException {
        if (isGameOn) {
            current.setText("The game has already started!");
        }
        else {
            int bet = Integer.parseInt(txtField.getText());
            if (bet <= 0) {
                current.setText("Minimal bet is 1!");
            }
            else if (bet > game.getPlayerBalance()) {
                current.setText("You don't have enough money!");
            }
            else {
                currentBet.setText(txtField.getText());
                balance.setText(String.valueOf(game.getPlayerBalance() - bet));
                isGameOn = true;
                isGameEnd = false;
                dealerCard3.setVisible(false);
                dealerCard4.setVisible(false);
                dealerCard5.setVisible(false);
                dealerCard6.setVisible(false);
                playerCard3.setVisible(false);
                playerCard4.setVisible(false);
                playerCard5.setVisible(false);
                playerCard6.setVisible(false);
                current.setText("Hit or stand?");
                game.start();
                game.setCurrentBet(Integer.parseInt(txtField.getText()));
                dealerPoints.setText(String.valueOf(game.getDealerPoints() -
                        game.getDealer().getHand().getCurrentCards().get(1).getValue()));
                for (int i = 0; i < game.getDealerCardList().size(); i++) {
                    rank = game.getDealerCardList().get(i).getRank();
                    suit = game.getDealerCardList().get(i).getSuit().toString().toLowerCase();
                    input = new FileInputStream("src\\main\\resources\\com\\example\\blackjack\\cards\\" + rank + "_of_" + suit + ".png");
                    image = new Image(input);
                    if (i == 0) {
                        dealerCard1.setImage(image);
                    } else {
                        dealerCard2.setImage(image);
                        dealerCard2.setVisible(false);
                        back.setVisible(true);
                    }
                }
                playerPoints.setText(String.valueOf(game.getPlayerPoints()));
                for (int i = 0; i < game.getPlayerCardList().size(); i++) {
                    rank = game.getPlayerCardList().get(i).getRank();
                    suit = game.getPlayerCardList().get(i).getSuit().toString().toLowerCase();
                    input = new FileInputStream("src\\main\\resources\\com\\example\\blackjack\\cards\\" + rank + "_of_" + suit + ".png");
                    image = new Image(input);
                    if (i == 0) playerCard1.setImage(image);
                    else playerCard2.setImage(image);
                }
                if (game.getPlayerPoints() == 21) {
                    current.setText(game.stand());
                    balance.setText(String.valueOf(game.getPlayerBalance()));
                    if (game.getPlayerBalance() == 0) current.setText("Game over! Press quit");
                    currentBet.setText("0");
                    isGameEnd = true;
                    isGameOn = false;
                }
            }
        }
    }

    @FXML
    void quit() {
        System.exit(0);
    }
}

