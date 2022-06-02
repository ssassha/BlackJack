package com.example.blackjack;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
    private Label playerPoints;

    @FXML
    private Label dealerPoints;

    @FXML
    void hit() throws FileNotFoundException {
        if (isGameEnd) {
            current.setText("The game is over!");
        } else {
            String currentHit = game.hit();
            int k = game.getPlayerCardList().size();
            current.setText(currentHit);
            if (currentHit != "Hit or stand?") {
                isGameEnd = true;
                isGameOn = false;
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
    void stand() throws FileNotFoundException {
        if (isGameEnd) {
            current.setText("The game is over!");
        } else {
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
            isGameOn = true;
            isGameEnd = false;
            dealerCard3.setVisible(false);
            dealerCard4.setVisible(false);
            dealerCard5.setVisible(false);
            playerCard3.setVisible(false);
            playerCard4.setVisible(false);
            playerCard5.setVisible(false);
            current.setText("Hit or stand?");
            game.start();
            dealerPoints.setText(String.valueOf(game.getDealerPoints()));
            for (int i = 0; i < game.getDealerCardList().size(); i++) {
                rank = game.getDealerCardList().get(i).getRank();
                suit = game.getDealerCardList().get(i).getSuit().toString().toLowerCase();
                input = new FileInputStream("src\\main\\resources\\com\\example\\blackjack\\cards\\" + rank + "_of_" + suit + ".png");
                image = new Image(input);
                if (i == 0) dealerCard1.setImage(image);
                else dealerCard2.setImage(image);
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
        }
    }

    @FXML
    void quit() {
        System.exit(0);
    }
}

